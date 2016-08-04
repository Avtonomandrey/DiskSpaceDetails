package ru.eleron;

public class FileStoreDetails {

    private final String fileStoreName;
    private final long availableSpace;
    private final long usedSpace;
    private boolean danger;

    FileStoreDetails(String name, long availableSpace, long usedSpace, boolean danger) {

        this.fileStoreName = name;
        this.availableSpace = availableSpace;
        this.usedSpace = usedSpace;
        this.danger = danger;

    }

    public String getFileStoreName() {
        return fileStoreName;
    }

    public long getAvailableSpace() {
        return availableSpace;
    }

    public long getUsedSpace() {
        return usedSpace;
    }

    public boolean getIsDanger() {
        return danger;
    }




    @Override
    public String toString() {
        return "в разделе " + fileStoreName + " свободного места: " + availableSpace
                + " байт, использованного места: " + usedSpace + " байт";
    }
}
