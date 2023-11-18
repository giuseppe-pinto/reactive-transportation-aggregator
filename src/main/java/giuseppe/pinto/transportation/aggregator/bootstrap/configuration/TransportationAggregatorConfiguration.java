package giuseppe.pinto.transportation.aggregator.bootstrap.configuration;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller.TransportationSearchController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TransportationSearchController.class})
public class TransportationAggregatorConfiguration {
}
