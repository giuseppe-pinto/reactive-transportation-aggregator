package giuseppe.pinto.transportation.aggregator.bootstrap;

import giuseppe.pinto.transportation.aggregator.bootstrap.configuration.TransportationAggregatorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class TransportationAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportationAggregatorApplication.class, args);
    }

}
