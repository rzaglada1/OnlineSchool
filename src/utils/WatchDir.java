package utils;

import server_client.StorageBlackList;
import utils.log.Log;
import utils.log.LogProperty;
import utils.log.LogToFile;
import java.io.IOException;
import java.nio.file.*;

public class WatchDir implements Runnable {
    Path pathDir;
    String stringName;
    private boolean isActive = true;

    void disable() {
        isActive = false;
    }

    public WatchDir(String stringPath, String stringName) {
        pathDir = Paths.get(stringPath);
        this.stringName = stringName;
    }

    private void toDo() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        WatchKey watchKey = pathDir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        while (isActive) {
            Thread.sleep(1);
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                Path fileName = (Path) event.context();

                if (fileName.endsWith(stringName) && stringName.equals(LogToFile.STR_NAME_SERVICE)) {
                    setFromFile(LogProperty.LOG_LEVEL);
                }

                if (fileName.endsWith(stringName) && stringName.equals(StorageBlackList.STR_NAME_BLACK_LIST)) {
                    StorageBlackList.getInstance()
                            .loadBlackListFile(Path.of(StorageBlackList.STR_DIR_BLACK_LIST
                                    , StorageBlackList.STR_NAME_BLACK_LIST));

                }

            }
            if (!watchKey.reset()) {
                disable();
            }
        }
    }


    private void setFromFile(LogProperty logProperty) {
        Log.setLogLevel(LogToFile.getInstance().loadFromServiceFile(logProperty));
    }


    @Override
    public void run() {
        try {
            toDo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
