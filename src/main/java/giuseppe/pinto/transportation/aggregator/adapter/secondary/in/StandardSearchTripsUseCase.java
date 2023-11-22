package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SolutionsAdapter;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDto;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import giuseppe.pinto.transportation.aggregator.port.in.TripsRepository;
import reactor.core.publisher.Flux;

public class StandardSearchTripsUseCase implements SearchTripsUseCase {

    private final RequestAdapter requestAdapter;
    private final SolutionsAdapter solutionsAdapter;
    private final TripsRepository tripsRepository;

    public StandardSearchTripsUseCase(TripsRepository tripsRepository,
                                      SolutionsAdapter solutionsAdapter,
                                      RequestAdapter requestAdapter) {
        this.requestAdapter = requestAdapter;
        this.solutionsAdapter = solutionsAdapter;
        this.tripsRepository = tripsRepository;
    }

    @Override
    public Flux<Solutions> searchOn(SearchRequestDto searchRequestDTO) {
        return solutionsAdapter.from(
                tripsRepository.getDriverOutcomeFrom(
                        requestAdapter.from(searchRequestDTO)));
    }




}
