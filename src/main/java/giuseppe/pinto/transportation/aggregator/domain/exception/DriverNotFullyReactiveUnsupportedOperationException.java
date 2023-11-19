package giuseppe.pinto.transportation.aggregator.domain.exception;

public class DriverNotFullyReactiveUnsupportedOperationException extends UnsupportedOperationException {
    public DriverNotFullyReactiveUnsupportedOperationException() {
        super("Method not allowed for the moment. The single driver are not fully reactive");
    }
}
