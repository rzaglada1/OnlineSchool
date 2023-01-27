package utils;

import utils.log.Log;
import utils.log.LogProperty;
import utils.log.LogToFile;

import java.nio.file.*;

public class WatchDir implements Runnable {
    Path pathDir;
    private boolean isActive = true;

    void disable() {
        isActive = false;
    }

    public WatchDir(String stringPath) {
        pathDir = Paths.get(stringPath);
    }

    private void toDo() throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        WatchKey watchKey = pathDir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        while (isActive) {
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                Path fileName = (Path) event.context();

                if (fileName.endsWith(LogToFile.STR_NAME_SERVICE)) {
                    setFromFile(LogProperty.LOG_LEVEL);
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
