package utils.log;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Log {
    private final String name;
    private final LogLevel level;
    private final String message;
    private final LocalDateTime date;
    private final StackTraceElement[] stacktrace;

    static LogStorage logStorage = LogStorage.getInstance();
    static LogToFile logToFile = LogToFile.getInstance();



    private static Log log;

    private Log (String name, LogLevel level, String message, LocalDateTime date, StackTraceElement[] stacktrace) {
        this.name = name;
        this.level = level;
        this.message = message;
        this.date = date;
        this.stacktrace = stacktrace;
    }

    public static void debug(String name, String message) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime + " " + LogLevel.DEBUG + " " + name + ":" + " " + message);
        log = new Log(name, LogLevel.DEBUG, message, LocalDateTime.now(), null);
        logSave(log);
    }

    public static void info(String name, String message) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime + " " + LogLevel.INFO + " " + name + ":" + " " + message);
        log = new Log(name, LogLevel.INFO, message, LocalDateTime.now(), null);
        logSave(log);
    }

    public static void error(String name, String message, StackTraceElement[] stacktrace) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime + " " + LogLevel.ERROR + " " + name + ":" + " " + message);
        log = new Log(name, LogLevel.ERROR, message, LocalDateTime.now(), stacktrace);
        logSave(log);
    }

    public static void warning(String name, String message, StackTraceElement[] stacktrace) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime + " " + LogLevel.WARNING + " " + name + ":" + " " + message);
        log = new Log(name, LogLevel.WARNING, message, LocalDateTime.now(), stacktrace);
        logSave(log);
    }

    private static void logSave (Log log) {
        if (logStorage != null) {
            logStorage.getLogList().add(log);
        }
        if (logToFile != null) {
            logToFile.saveToFile(log);
        }
    }

    public String getName() {
        return name;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public StackTraceElement[] getStacktrace() {
        return stacktrace;
    }

    @Override
    public String toString() {
        return "Log{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", stacktrace='" + Arrays.toString(stacktrace) + '\'' +
                '}' + '\n';
    }
}
