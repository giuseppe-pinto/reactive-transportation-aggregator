package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

public record OneWaySearchRequestDto(
        String departure,
        String arrival,
        String departureDate
) {}
