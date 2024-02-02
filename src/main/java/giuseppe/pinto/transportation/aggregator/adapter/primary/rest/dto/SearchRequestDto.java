package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

public record SearchRequestDto(
        String departure,
        String arrival,
        String departureDate,
        String returnDate
) {}
