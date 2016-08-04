package ru.eleron;

/**
 * @author Andrey Avtonomov
 */
public interface FreeSpaceEventListenerIF {

    void notifyThatAvailableSpaceIsLess(FreeSpaceEvent freeSpaceEvent);

}
