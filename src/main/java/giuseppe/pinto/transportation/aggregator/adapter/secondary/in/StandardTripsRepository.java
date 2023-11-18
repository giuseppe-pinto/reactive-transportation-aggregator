package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.FirstNonReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.SecondNonReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.ThirdNonReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.TripsRepository;
import giuseppe.pinto.transportation.aggregator.port.out.NonReactiveDriverRepository;
import reactor.core.publisher.Flux;
import giuseppe.pinto.transportation.aggregator.domain.Trip;

import java.util.List;
import java.util.stream.Collectors;

public class StandardTripsRepository implements TripsRepository {

    @Override
    public Flux<Trip> getAll(SearchRequest searchRequest) {

        List<NonReactiveDriverRepository> drivers = assignDrivers();

        /*return Flux.merge(
                drivers
                        .stream()
                        .map(driver -> driver.performRequest(searchRequest))
                        .collect(Collectors.toList())
        );*/

        return null;

    }

    private List<NonReactiveDriverRepository> assignDrivers() {
        return List.of(
                new FirstNonReactiveDriverRepository(),
                new SecondNonReactiveDriverRepository(),
                new ThirdNonReactiveDriverRepository());
    }


}
