package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class Solutions {

    private List<String> identifiers = new ArrayList<>();
    public Solutions(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    public Solutions() {
    }
}
