package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solutions {

    private List<String> identifiers = new ArrayList<>();
    public Solutions(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    public Solutions() {
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    @Override
    public String toString() {
        return "Solutions{" +
                "identifiers=" + identifiers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solutions solutions = (Solutions) o;
        return Objects.equals(identifiers, solutions.identifiers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiers);
    }
}
