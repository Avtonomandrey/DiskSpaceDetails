package ru.eleron;

import java.io.IOException;
import java.util.List;

/**
 * Created by Avtonomov on 03.08.2016.
 */
public interface FreeSpaceManagerIF {

    void start();

    void stop();

    void compareWithFreeSpaceValue(long freeSpaceValue);

    List getFileStoresDetails() throws IOException;



}
