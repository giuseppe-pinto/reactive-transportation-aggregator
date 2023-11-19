package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.RequestAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.TripDTOAdapter;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.RealDriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.domain.exception.DriverNotFullyReactiveUnsupportedOperationException;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDTO;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.TripDTO;
import reactor.core.publisher.Flux;

import java.util.List;

public class StandardSearchTripsUseCase implements SearchTripsUseCase {

    private final RequestAdapter requestAdapter = new RequestAdapter();
    private final TripDTOAdapter tripDTOAdapter = new TripDTOAdapter();
    private final StandardTripsRepository tripsRepository = new StandardTripsRepository(new RealDriverConfigurationRepository());


    @Override
    public Flux<List<TripDTO>> searchOn(SearchRequestDTO searchRequestDTO) {
        return tripDTOAdapter.from(
                tripsRepository.getListOfTrip(
                        requestAdapter.from(searchRequestDTO)));
    }

    @Override
    public Flux<TripDTO> searchOnNewVersion(SearchRequestDTO searchRequestDTO) {
        throw new DriverNotFullyReactiveUnsupportedOperationException();
    }


}
