package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import reactor.core.publisher.Flux;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class SolutionsAdapter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-ss");


    public Flux<Solutions> from(Flux<DriverOutcome> driverOutcomes){

        return driverOutcomes
                .map(driverOutcome -> new Solutions(driverOutcome.getTrips().stream().map(trip -> String.join( "|",
                                trip.getDeparture(),
                                trip.getArrival(),
                                trip.getDepartureDate().format(formatter),
                                trip.getArrivalDate().format(formatter),
                                trip.getCarrier(),
                                trip.getCarrierNumber(),
                                trip.getPrice().toString(),
                                trip.getCurrency().getCurrencyCode(),
                                trip.getDriver().name())).collect(Collectors.toList())));

    }

}
