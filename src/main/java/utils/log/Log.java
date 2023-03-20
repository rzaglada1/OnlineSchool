package utils.log;

import utils.MenuUtils;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class Log {
    private final String name;
    private final LogLevel level;
    private final String message;
    private final LocalDateTime date;
    private static final String DATE_LOG_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";
    private static final Locale locale = Locale.US;
    private final StackTraceElement[] stacktrace;



    static LogStorage logStorage = LogStorage.getInstance();
    static LogToFile logToFile = LogToFile.getInstance();



    private final static String nameLog = "Log OnlineSchool";
    private static Log log;
    private static final LogLevel logLevelDefault = LogLevel.INFO;
    private static LogLevel logLevel;

    private Log (String name, LogLevel level, String message, LocalDateTime date, StackTraceElement[] stacktrace) {
        this.name = name;
        this.level = level;
        this.message = message;
        this.date = date;
        this.stacktrace = stacktrace;
    }

    private static String formatDate (LocalDateTime dateTime, String strFormat, Locale locale) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(strFormat, locale);
        return dateTime.format(df);
    }

    public static void debug(String name, String message) {
        if (checkLevelLog(LogLevel.DEBUG, loadLogLevel() )) {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(formatDate(localDateTime, DATE_LOG_FORMAT, locale) + " " + LogLevel.DEBUG + " " + name + ":" + " " + message);
            log = new Log(name, LogLevel.DEBUG, message, LocalDateTime.now(), null);
            logSave(log);
        }
    }

    public static void info(String name, String message) {
        if (checkLevelLog(LogLevel.INFO, loadLogLevel() )) {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(formatDate(localDateTime, DATE_LOG_FORMAT, locale) + " " + LogLevel.INFO + " " + name + ":" + " " + message);
            log = new Log(name, LogLevel.INFO, message, LocalDateTime.now(), null);
            logSave(log);
        }

    }

    public static void error(String name, String message, StackTraceElement[] stacktrace) {
        if (checkLevelLog(LogLevel.ERROR, loadLogLevel() )) {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(formatDate(localDateTime, DATE_LOG_FORMAT, locale) + " " + LogLevel.ERROR + " " + name + ":" + " " + message);
            log = new Log(name, LogLevel.ERROR, message, LocalDateTime.now(), stacktrace);
            logSave(log);
        }

    }

    public static void warning(String name, String message, StackTraceElement[] stacktrace) {
        if (checkLevelLog(LogLevel.WARNING, loadLogLevel() )) {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(formatDate(localDateTime, DATE_LOG_FORMAT, locale) + " " + LogLevel.WARNING + " " + name + ":" + " " + message);
            log = new Log(name, LogLevel.WARNING, message, LocalDateTime.now(), stacktrace);
            logSave(log);
        }

    }

    private static void logSave (Log log) {
        if (logStorage != null) {
            logStorage.getLogList().add(log);
        }
        if (logToFile != null) {
            logToFile.saveToLogFile(log);
        }
    }

    private static LogLevel loadLogLevel () {
        if (getLogLevel() == null) {
            try {
                Path pathServiceFile = Path.of(MenuUtils.STR_PATH_DIRECTORY, MenuUtils.STR_NAME_SERVICE);
                logLevel = LogToFile.getInstance().loadFromServiceFile(LogProperty.LOG_LEVEL,pathServiceFile);
            } catch (Exception e) {
                System.out.println("Data service file error");
                logLevel = logLevelDefault;
            }
        }
        return logLevel;
    }

    public static boolean checkLevelLog (LogLevel lg, LogLevel level) {
        return lg.getCheckNumber() <= level.getCheckNumber() && level.getCheckNumber() != 5;
    }

    public String getName() {
        return name;
    }

    public LogLevel getLevel() {
        return level;
    }

    public static void setLogLevel(LogLevel logLevel) {
        Log.logLevel = logLevel;
        Log.info( nameLog,"Changed settings LOG_LEVEL = " + logLevel);
    }

    public static LogLevel getLogLevel() {
        return logLevel;
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
