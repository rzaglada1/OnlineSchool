package utils.log;

import com.online_school.utils.log.Log;
import com.online_school.utils.log.LogLevel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @Test
    void testCheckLevelLogExpectedBooleanForLevelLogInfo() {
        boolean actual;
        LogLevel logLevel = LogLevel.INFO;

        actual = Log.checkLevelLog(logLevel, LogLevel.DEBUG);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.INFO);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.WARNING);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.ERROR);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.OFF);
        assertFalse(actual);
    }

    @Test
    void testCheckLevelLogExpectedBooleanForLevelLogDebug() {
        boolean actual;
        LogLevel logLevel = LogLevel.DEBUG;

        actual = Log.checkLevelLog(logLevel, LogLevel.DEBUG);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.INFO);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.WARNING);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.ERROR);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.OFF);
        assertFalse(actual);
    }

    @Test
    void testCheckLevelLogExpectedBooleanForLevelLogWarning() {
        boolean actual;
        LogLevel logLevel = LogLevel.WARNING;

        actual = Log.checkLevelLog(logLevel, LogLevel.DEBUG);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.INFO);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.WARNING);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.ERROR);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.OFF);
        assertFalse(actual);
    }

    @Test
    void testCheckLevelLogExpectedBooleanForLevelLogError() {
        boolean actual;
        LogLevel logLevel = LogLevel.ERROR;

        actual = Log.checkLevelLog(logLevel, LogLevel.DEBUG);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.INFO);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.WARNING);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.ERROR);
        assertTrue(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.OFF);
        assertFalse(actual);
    }

    @Test
    void testCheckLevelLogExpectedBooleanForLevelLogOff() {
        boolean actual;
        LogLevel logLevel = LogLevel.OFF;

        actual = Log.checkLevelLog(logLevel, LogLevel.DEBUG);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.INFO);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.WARNING);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.ERROR);
        assertFalse(actual);

        actual = Log.checkLevelLog(logLevel, LogLevel.OFF);
        assertFalse(actual);
    }



}