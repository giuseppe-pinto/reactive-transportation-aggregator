package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import giuseppe.pinto.transportation.aggregator.bootstrap.configuration.TransportationAggregatorConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ContextConfiguration(classes = TransportationAggregatorConfiguration.class)
class TransportationAggregatorIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(TransportationAggregatorIntegrationTest.class);

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void resultsFromGetMethod() {

        Flux<Solutions> responseBody = webTestClient.get().uri("/aggregator/search?departure=MXP&arrival=NAP&departureDate=2024-11-12")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Solutions.class)
                .getResponseBody();


        StepVerifier.create(responseBody)
                .expectSubscription()
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, new Solutions(List.of(
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|GREEN_FIRST_AIRLINE|1000|100.00|EUR|GREEN"))))
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, new Solutions(List.of(
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|BLUE_FIRST_AIRLINE|1000|35.00|EUR|BLUE",
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|BLUE_FIRST_AIRLINE|1001|35.00|EUR|BLUE"))))
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, new Solutions(List.of(
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|GREEN_SECOND_AIRLINE|2000|135.00|EUR|GREEN"))))
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, new Solutions(List.of(
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|BLUE_SECOND_AIRLINE|2000|35.00|EUR|BLUE"))))
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, new Solutions(List.of(
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|GREEN_THIRD_AIRLINE|3000|325.00|EUR|GREEN"))))
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, new Solutions(List.of(
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|BLUE_THIRD_AIRLINE|3001|35.00|EUR|BLUE",
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|BLUE_THIRD_AIRLINE|3002|35.00|EUR|BLUE",
                        "MXP|NAP|2024-11-12:16-00|2024-11-12:19-00|BLUE_THIRD_AIRLINE|3003|35.00|EUR|BLUE"))))
                .verifyComplete();

    }

    private static void assertionAndLogOn(Solutions actualSolutions, Solutions expectedSolutions) {
        assertThat(actualSolutions).isEqualTo(expectedSolutions);
        log.info(actualSolutions::toString);
    }
}