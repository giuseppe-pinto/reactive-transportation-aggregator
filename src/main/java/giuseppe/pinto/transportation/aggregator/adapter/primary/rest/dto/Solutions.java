package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Solutions {
    private final List<String> identifiers;
}
