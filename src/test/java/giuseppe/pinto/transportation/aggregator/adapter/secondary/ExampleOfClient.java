package giuseppe.pinto.transportation.aggregator.adapter.secondary;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class ExampleOfClient {


    public static void main(String[] args) {


        WebClient webClient = WebClient
                .create("http://127.0.0.1:8080");

        System.out.println("Ciao");

        webClient.post()
                .uri("/aggregator/search")
                .bodyValue(SearchRequestDTO.builder()
                        .departure("MIL")
                        .arrival("NAP")
                        .departureDate("2024-01-04")
                        .returnDate("2024-01-10")
                        .build())
                .exchangeToFlux(data -> {
                    System.out.println(data.statusCode());
                    System.out.println(data.toString());
                    return Flux.just(data);
                })
                .subscribe();

        //System.out.println(block1);


        List block2 = webClient.post()
                .uri("/aggregator/search")
                .bodyValue(SearchRequestDTO.builder()
                        .departure("MIL")
                        .arrival("NAP")
                        .departureDate("2024-01-04")
                        .returnDate("2024-01-10")
                        .build())
                .retrieve()
                .bodyToMono(List.class)
                .block();

        System.out.println(block2);


    }


}
