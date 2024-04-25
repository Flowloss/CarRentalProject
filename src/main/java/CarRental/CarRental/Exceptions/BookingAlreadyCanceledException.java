package CarRental.CarRental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingAlreadyCanceledException extends RuntimeException {
    private String name;
    private String field;
    private Object value;

    public BookingAlreadyCanceledException(String name, String field, Object value) {
        super(String.format("Booking with %s:'%s' is already canceled", field, value));
        this.name = name;
        this.field = field;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}

