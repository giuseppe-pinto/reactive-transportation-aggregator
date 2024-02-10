package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.blocking.BlueBlockingSupplierRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.blocking.GreenBlockingSupplierRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.blocking.RedBlockingSupplierRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.reactive.BlueReactiveSupplierRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.reactive.GreenReactiveSupplierRepository;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.SuppliersConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.supplier.SupplierRepository;
import reactor.core.publisher.Flux;

public class SimpleSuppliersConfigurationRepository implements SuppliersConfigurationRepository {

    private final boolean useReactive;

    public SimpleSuppliersConfigurationRepository(boolean useReactive) {
        this.useReactive = useReactive;
    }

    @Override
    public Flux<SupplierRepository> getSuppliersFor(OneWaySearchRequest oneWaySearchRequest) {
        if(useReactive){
            return Flux.just(new GreenReactiveSupplierRepository(),
                    new BlueReactiveSupplierRepository());
        }
        return Flux.just(new BlueBlockingSupplierRepository(),
                new RedBlockingSupplierRepository(),
                new GreenBlockingSupplierRepository());
    }
}
