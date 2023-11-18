package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import reactor.core.publisher.Flux;
import giuseppe.pinto.transportation.aggregator.domain.Trip;

import java.time.format.DateTimeFormatter;

public class TripDTOAdapter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

    public Flux<TripDTO> from(Flux<Trip> trips){

        return trips.map(trip ->
                TripDTO.builder()
                        .identifier(String.join( "|",
                                trip.getDeparture(),
                                trip.getArrival(),
                                trip.getDepartureDate().format(formatter),
                                trip.getArrivalDate().format(formatter),
                                trip.getAirline(),
                                trip.getFlightNumber(),
                                trip.getPrice().toString(),
                                trip.getCurrency().getCurrencyCode(),
                                trip.getDriver().name()))
                        .build());

    }

}
