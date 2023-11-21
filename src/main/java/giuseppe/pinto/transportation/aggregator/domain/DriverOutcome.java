package giuseppe.pinto.transportation.aggregator.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class DriverOutcome {

    private final List<Trip> trips;

}
