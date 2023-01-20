package utils.log;

import java.util.ArrayList;
import java.util.List;

public class LogStorage {

    private static LogStorage instance;
    private final List<Log> logList;

    private LogStorage() {
        logList = new ArrayList<>();
    }

    public static LogStorage getInstance() {
        if (instance == null) {
            instance = new LogStorage();
        }
        return instance;
    }

    public List<Log> getLogList() {
        return logList;
    }

}
