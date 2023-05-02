package com.online_school.utils;

import com.online_school.Main;
import com.online_school.server_client.StorageBlackList;
import com.online_school.utils.log.Log;
import com.online_school.utils.log.LogProperty;
import com.online_school.utils.log.LogToFile;

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

                if (fileName.endsWith(stringName) && stringName.equals(Main.STR_NAME_SERVICE)) {
                    setFromFile(LogProperty.LOG_LEVEL);
                }

                if (fileName.endsWith(stringName) && stringName.equals(Main.STR_NAME_BLACK_LIST)) {
                    StorageBlackList.getInstance()
                            .loadBlackListFile(Path.of(Main.STR_PATH_DIRECTORY
                                    , Main.STR_NAME_BLACK_LIST));

                }

            }
            if (!watchKey.reset()) {
                disable();
            }
        }
    }


    private void setFromFile(LogProperty logProperty) {
        Path pathServiceFile = Path.of(Main.STR_PATH_DIRECTORY, Main.STR_NAME_SERVICE);
        Log.setLogLevel(LogToFile.getInstance().loadFromServiceFile(logProperty, pathServiceFile));
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
