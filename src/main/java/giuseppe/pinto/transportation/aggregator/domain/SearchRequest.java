package giuseppe.pinto.transportation.aggregator.domain;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class SearchRequest {
    private final String departure;
    private final String arrival;
    private final LocalDate departureDate;
    private final LocalDate returnDate;
}
