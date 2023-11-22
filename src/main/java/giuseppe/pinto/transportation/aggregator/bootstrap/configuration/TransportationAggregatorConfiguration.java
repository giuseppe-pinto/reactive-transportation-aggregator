package giuseppe.pinto.transportation.aggregator.bootstrap.configuration;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller.TransportationAggregatorController;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SolutionsAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardSearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardTripsRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.SimpleDriversConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.port.in.TripsRepository;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TransportationAggregatorController.class})
public class TransportationAggregatorConfiguration {

    @Bean
    public DriverConfigurationRepository driverConfigurationRepository(){
        return new SimpleDriversConfigurationRepository();
    }

    @Bean
    public TripsRepository tripsRepository(DriverConfigurationRepository driverConfigurationRepository){
        return new StandardTripsRepository(driverConfigurationRepository);
    }

    @Bean
    public SearchTripsUseCase searchTripsUseCase(TripsRepository tripsRepository){
       return new StandardSearchTripsUseCase(tripsRepository, new SolutionsAdapter(), new RequestAdapter());
    }

}
