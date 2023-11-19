package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.TripsRepository;
import giuseppe.pinto.transportation.aggregator.port.out.MultiTripReactiveDriverRepository;
import reactor.core.publisher.Flux;
import giuseppe.pinto.transportation.aggregator.domain.Trip;

import java.util.List;

public class StandardTripsRepository implements TripsRepository {

    private final DriverConfigurationRepository driverConfigurationRepository;

    public StandardTripsRepository(DriverConfigurationRepository driverConfigurationRepository) {
        this.driverConfigurationRepository = driverConfigurationRepository;
    }

    @Override
    public Flux<Trip> getAll(SearchRequest searchRequest) {


        List<MultiTripReactiveDriverRepository> drivers = driverConfigurationRepository.getDriversFor(searchRequest);

        /*return Flux.merge(
                drivers
                        .stream()
                        .map(driver -> driver.performRequest(searchRequest))
                        .collect(Collectors.toList())
        );*/

        return null;

    }


}
