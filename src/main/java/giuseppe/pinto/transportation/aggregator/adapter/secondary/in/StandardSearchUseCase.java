package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SolutionsAdapter;
import giuseppe.pinto.transportation.aggregator.port.in.SearchUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.OneWaySearchRequestDto;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import giuseppe.pinto.transportation.aggregator.port.in.DriverOutcomeService;
import reactor.core.publisher.Flux;

public class StandardSearchUseCase implements SearchUseCase {

    private final RequestAdapter requestAdapter;
    private final SolutionsAdapter solutionsAdapter;
    private final DriverOutcomeService driverOutcomeService;

    public StandardSearchUseCase(DriverOutcomeService driverOutcomeService,
                                 SolutionsAdapter solutionsAdapter,
                                 RequestAdapter requestAdapter) {
        this.requestAdapter = requestAdapter;
        this.solutionsAdapter = solutionsAdapter;
        this.driverOutcomeService = driverOutcomeService;
    }

    @Override
    public Flux<Solutions> searchOn(OneWaySearchRequestDto oneWaySearchRequestDTO) {
        return solutionsAdapter.from(
                driverOutcomeService.from(
                        requestAdapter.from(oneWaySearchRequestDTO)));
    }


}
