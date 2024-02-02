package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDto;
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
    private static final Solutions SOLUTIONS_FROM_BLUE_DRIVER = new Solutions(List.of(
            "MXP|NAP|2023-11-12:10-00|2023-11-13:10-00|GIUSEPPE_AIRLINE|1000|10.00|EUR|BLUE"));
    private static final Solutions SOLUTIONS_FROM_RED_DRIVER = new Solutions(List.of(
            "MXP|NAP|2023-11-12:10-00|2023-11-13:10-00|PAOLO_AIRLINE|2000|60.00|EUR|RED",
            "MXP|NAP|2023-11-12:08-00|2023-11-13:08-00|MARIO_AIRLINE|2050|5.00|EUR|RED"));
    private static final Solutions SOLUTIONS_FROM_GREEN_DRIVER = new Solutions(List.of(
            "MXP|NAP|2023-11-12:16-00|2023-11-13:16-00|FRANCO_AIRLINE|3000|35.00|EUR|GREEN"));

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void resultsFromGetMethod() {

        Flux<Solutions> responseBody = webTestClient.get().uri("/aggregator/search")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Solutions.class)
                .getResponseBody();


        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(SOLUTIONS_FROM_BLUE_DRIVER)
                .expectNext(SOLUTIONS_FROM_RED_DRIVER)
                .expectNext(SOLUTIONS_FROM_GREEN_DRIVER)
                .verifyComplete();

    }

    @Test
    void resultFromPostMethod() {

        SearchRequestDto searchRequestDto = new SearchRequestDto(
                "MXP", "NAP", "2023-10-12", "2023-10-13");


        Flux<Solutions> responseBody = webTestClient
                .post()
                .uri("/aggregator/search")
                .bodyValue(searchRequestDto)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Solutions.class)
                .getResponseBody();


        StepVerifier.create(responseBody)
                .expectSubscription()
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, SOLUTIONS_FROM_BLUE_DRIVER))
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, SOLUTIONS_FROM_RED_DRIVER))
                .consumeNextWith(solutions -> assertionAndLogOn(solutions, SOLUTIONS_FROM_GREEN_DRIVER))
                .verifyComplete();

    }

    private static void assertionAndLogOn(Solutions solutions, Solutions solutionsFromBlueDriver) {
        assertThat(solutions).isEqualTo(solutionsFromBlueDriver);
        log.info(solutions::toString);
    }
}