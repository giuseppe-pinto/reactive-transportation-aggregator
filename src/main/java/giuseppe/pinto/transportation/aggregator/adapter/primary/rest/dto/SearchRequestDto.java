package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class SearchRequestDto {
    private final String departure;
    private final String arrival;
    private final String departureDate;
    private final String returnDate;
}
