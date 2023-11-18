package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@Builder
@ToString
public class TripDTO {

    private final String identifier;

}
