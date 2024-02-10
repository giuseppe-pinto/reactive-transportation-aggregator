package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import reactor.core.publisher.Flux;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class SolutionsAdapter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-ss");


    public Flux<Solutions> from(Flux<SupplierOutcome> driverOutcomes){

        return driverOutcomes
                .map(driverOutcome -> new Solutions(driverOutcome.trips().stream().map(trip -> String.join( "|",
                                trip.departure(),
                                trip.arrival(),
                                trip.departureDate().format(formatter),
                                trip.arrivalDate().format(formatter),
                                trip.carrier(),
                                trip.carrierNumber(),
                                trip.price().toString(),
                                trip.currency().getCurrencyCode(),
                                trip.supplier().name())).collect(Collectors.toList())));

    }

}
