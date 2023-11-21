package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import giuseppe.pinto.transportation.aggregator.bootstrap.configuration.TransportationAggregatorConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ContextConfiguration(classes = TransportationAggregatorConfiguration.class)
class TransportationAggregatorIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void happyPath() {

/*
        webTestClient.get().uri("/aggregator/search")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Solutions.class)
                .consumeWith(System.out::println);*/


        Flux<Solutions> responseBody = webTestClient.get().uri("/aggregator/search")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Solutions.class)
                .getResponseBody();


        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(
                        new Solutions(List.of("MIL|NAP|2023-Nov-12|2023-Nov-13|GIUSEPPE_AIRLINE|1000|10.00|EUR|BLUE")))
                //.consumeNextWith(System.out::println)
                .expectNext(
                        new Solutions(List.of("MIL|NAP|2023-Nov-12|2023-Nov-13|PAOLO_AIRLINE|2000|60.00|EUR|RED",
                                "MIL|NAP|2023-Nov-12|2023-Nov-13|MARIO_AIRLINE|2050|5.00|EUR|RED")))
                //.consumeNextWith(System.out::println)
                .expectNext(
                        new Solutions(List.of("MIL|NAP|2023-Nov-12|2023-Nov-13|FRANCO_AIRLINE|3000|35.00|EUR|GREEN"))
                )
                //.consumeNextWith(System.out::println)
                .verifyComplete();

    }



}