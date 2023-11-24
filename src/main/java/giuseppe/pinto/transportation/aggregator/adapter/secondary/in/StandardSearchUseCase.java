package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SolutionsAdapter;
import giuseppe.pinto.transportation.aggregator.port.in.SearchUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDto;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import giuseppe.pinto.transportation.aggregator.port.in.DriverOutcomeRepository;
import reactor.core.publisher.Flux;

public class StandardSearchUseCase implements SearchUseCase {

    private final RequestAdapter requestAdapter;
    private final SolutionsAdapter solutionsAdapter;
    private final DriverOutcomeRepository driverOutcomeRepository;

    public StandardSearchUseCase(DriverOutcomeRepository driverOutcomeRepository,
                                 SolutionsAdapter solutionsAdapter,
                                 RequestAdapter requestAdapter) {
        this.requestAdapter = requestAdapter;
        this.solutionsAdapter = solutionsAdapter;
        this.driverOutcomeRepository = driverOutcomeRepository;
    }

    @Override
    public Flux<Solutions> searchOn(SearchRequestDto searchRequestDTO) {
        return solutionsAdapter.from(
                driverOutcomeRepository.from(
                        requestAdapter.from(searchRequestDTO)));
    }


}
