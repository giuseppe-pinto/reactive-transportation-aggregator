package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import reactor.core.publisher.Flux;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class SolutionsAdapter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");


    public Flux<Solutions> from(Flux<DriverOutcome> trips){

        return trips
                .map(driverOutcome -> Solutions.builder()
                        .identifiers(driverOutcome.getTrips().stream().map(trip -> String.join( "|",
                                trip.getDeparture(),
                                trip.getArrival(),
                                trip.getDepartureDate().format(formatter),
                                trip.getArrivalDate().format(formatter),
                                trip.getCarrier(),
                                trip.getCarrierNumber(),
                                trip.getPrice().toString(),
                                trip.getCurrency().getCurrencyCode(),
                                trip.getDriver().name())).collect(Collectors.toList()))
                        .build());

    }

}
