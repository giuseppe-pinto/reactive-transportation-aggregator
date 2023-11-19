package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import reactor.core.publisher.Flux;
import giuseppe.pinto.transportation.aggregator.domain.Trip;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TripDTOAdapter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");


    public Flux<List<TripDTO>> from(Flux<List<Trip>> trips){

        return trips
                .map(listOfTrips -> listOfTrips.stream().map(trip ->  TripDTO.builder()
                        .identifier(String.join( "|",
                                trip.getDeparture(),
                                trip.getArrival(),
                                trip.getDepartureDate().format(formatter),
                                trip.getArrivalDate().format(formatter),
                                trip.getCarrier(),
                                trip.getCarrierNumber(),
                                trip.getPrice().toString(),
                                trip.getCurrency().getCurrencyCode(),
                                trip.getDriver().name()))
                        .build()).collect(Collectors.toList()));


    }

}
