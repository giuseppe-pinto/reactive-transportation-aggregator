package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.FirstDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.SecondDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.ThirdDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.TripsRepository;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import reactor.core.publisher.Flux;
import giuseppe.pinto.transportation.aggregator.domain.Trip;

import java.util.List;
import java.util.stream.Collectors;

public class StandardTripsRepository implements TripsRepository {

    @Override
    public Flux<Trip> getAll(SearchRequest searchRequest) {

        List<DriverRepository> drivers = assignDrivers();

        return Flux.merge(
                drivers
                        .stream()
                        .map(driver -> driver.performRequest(searchRequest))
                        .collect(Collectors.toList())
        );

    }

    private List<DriverRepository> assignDrivers() {
        return List.of(
                new FirstDriverRepository(),
                new SecondDriverRepository(),
                new ThirdDriverRepository());
    }


}
