package com.online_school.server_client;

import com.online_school.Main;
import com.online_school.utils.MenuUtils;
import com.online_school.utils.RegexUtil;
import com.online_school.utils.log.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StorageBlackList {
    private static StorageBlackList instance;
    private List<String> blackList;

    String nameLog = "Log OnlineSchool";

    private Path pathBlackListFile;


    private RegexUtil regexUtil;

    public void setRegexUtil(RegexUtil regexUtil) {
        this.regexUtil = regexUtil;
    }

    private StorageBlackList() {
        blackList = new ArrayList<>();
        pathBlackListFile = Path.of(Main.STR_PATH_DIRECTORY, Main.STR_NAME_BLACK_LIST);
        loadBlackListFile(pathBlackListFile);
    }

    public static StorageBlackList getInstance() {
        if (instance == null) {
            instance = new StorageBlackList();
        }
        return instance;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public boolean checkBlackList(String ip) {
        boolean isCheck = false;
        for (String element : getBlackList()) {
            if (element.equals(ip)) {
                isCheck = true;
                break;
            }
        }
        return isCheck;
    }

    public void loadBlackListFile(Path path) {
        createBlackListFile(path);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            getBlackList().clear();
            String value = reader.readLine();
            while (value != null) {
                if (regexUtil.isCorrect(value, RegexUtil.REGEX_IP_ADDRESS)) {
                    getBlackList().add(value);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            Log.warning(nameLog, "Error reading BlackList file", e.getStackTrace());
        }
        // if active connect in blackList
        for (String element : getBlackList()) {
            killThread(element);
        }
    }

    private void createBlackListFile(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Error creation BlackList file");
            }
        }
    }


    public void killThread(String ip) {
        Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
        for (Thread thread : setOfThread) {
            if (thread.getName().equals(ip)) {
                thread.interrupt();
            }
        }
    }

}
