package ru.eleron;

import java.util.EventObject;

/**
 * @author Andrey Avtonomov
 */
public class FreeSpaceEvent extends EventObject {

    private String message;

    public FreeSpaceEvent(Object source, String message) {

        super(source);
        this.message = message;
    }

    public FreeSpaceEvent(String message) {

        this(null, message);
    }

    public String getMessage() {
        return message;
    }

}
