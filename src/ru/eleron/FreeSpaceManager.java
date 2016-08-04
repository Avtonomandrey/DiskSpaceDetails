package ru.eleron;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Andrey Avtonomov
 */
public class FreeSpaceManager {

    private final long freeSpace;
    private final FreeSpaceEventListenerIF freeSpaceListener;
    private ScheduledExecutorService scheduledExecutor;

    public FreeSpaceManager(long freeSpaceValue, FreeSpaceEventListenerIF listener) {

        freeSpace = freeSpaceValue;
        freeSpaceListener = listener;
        start();
    }

    private void start() {

        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {

                    compareWithFreeSpaceValue(freeSpace);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 20000, TimeUnit.MILLISECONDS);

    }

    public void stop() {

        if (!scheduledExecutor.isTerminated()) {
            scheduledExecutor.shutdownNow();
        }

    }

    // метод сравнения доступного места на разделе диска с требуемым
    private void compareWithFreeSpaceValue(long freeSpaceValue) throws IOException {

        for (FileStore store : FileSystems.getDefault().getFileStores()) {
            long availableSpace = store.getUsableSpace();
            if (availableSpace < freeSpaceValue) {
                FreeSpaceEvent fsv = new FreeSpaceEvent(store, "Не хватает места в разделе " + store);
                freeSpaceListener.notifyThatAvailableSpaceIsLess(fsv);
            }


        }
    }

    public List getFileStoresDetails() throws IOException {

        List<FileStoreDetails> fileStoresArrayList = new ArrayList<>();
        for (FileStore store : FileSystems.getDefault().getFileStores()) {
            long availableSpace = store.getUsableSpace();
            long usedSpace = store.getTotalSpace() - store.getUnallocatedSpace();
            if (availableSpace < freeSpace) {
                fileStoresArrayList.add(new FileStoreDetails(store.toString(), availableSpace, usedSpace, true));
            } else {
                fileStoresArrayList.add(new FileStoreDetails(store.toString(), availableSpace, usedSpace, false));
            }


        }
        return fileStoresArrayList;
    }
}
