package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SolutionsAdapter;
import giuseppe.pinto.transportation.aggregator.port.in.SearchUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.OneWaySearchRequestDto;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import giuseppe.pinto.transportation.aggregator.port.in.SuppliersOutcomeService;
import reactor.core.publisher.Flux;

public class StandardSearchUseCase implements SearchUseCase {

    private final RequestAdapter requestAdapter;
    private final SolutionsAdapter solutionsAdapter;
    private final SuppliersOutcomeService suppliersOutcomeService;

    public StandardSearchUseCase(SuppliersOutcomeService suppliersOutcomeService,
                                 SolutionsAdapter solutionsAdapter,
                                 RequestAdapter requestAdapter) {
        this.requestAdapter = requestAdapter;
        this.solutionsAdapter = solutionsAdapter;
        this.suppliersOutcomeService = suppliersOutcomeService;
    }

    @Override
    public Flux<Solutions> searchOn(OneWaySearchRequestDto oneWaySearchRequestDTO) {
        return solutionsAdapter.from(
                suppliersOutcomeService.from(
                        requestAdapter.from(oneWaySearchRequestDTO)));
    }


}
