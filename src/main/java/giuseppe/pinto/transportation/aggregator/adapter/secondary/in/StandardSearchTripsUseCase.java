package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SolutionsAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.RealDriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDto;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import reactor.core.publisher.Flux;

public class StandardSearchTripsUseCase implements SearchTripsUseCase {

    private final RequestAdapter requestAdapter = new RequestAdapter();
    private final SolutionsAdapter solutionsAdapter = new SolutionsAdapter();
    private final StandardTripsRepository tripsRepository = new StandardTripsRepository(new RealDriverConfigurationRepository());

    @Override
    public Flux<Solutions> searchOn(SearchRequestDto searchRequestDTO) {
        return solutionsAdapter.from(
                tripsRepository.getDriverOutcomeFrom(
                        requestAdapter.from(searchRequestDTO)));
    }




}
