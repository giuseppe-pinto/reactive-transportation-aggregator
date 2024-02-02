package giuseppe.pinto.transportation.aggregator.bootstrap.configuration;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller.TransportationAggregatorController;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SolutionsAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardSearchUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardDriverOutcomeRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.mono.SimpleDriversConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.in.SearchUseCase;
import giuseppe.pinto.transportation.aggregator.port.in.DriverOutcomeRepository;
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
    public DriverOutcomeRepository tripsRepository(DriverConfigurationRepository driverConfigurationRepository){
        return new StandardDriverOutcomeRepository(driverConfigurationRepository);
    }

    @Bean
    public SearchUseCase searchTripsUseCase(DriverOutcomeRepository driverOutcomeRepository){
       return new StandardSearchUseCase(driverOutcomeRepository, new SolutionsAdapter(), new RequestAdapter());
    }

}
