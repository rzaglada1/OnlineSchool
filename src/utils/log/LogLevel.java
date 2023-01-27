package utils.log;

public enum LogLevel {
    ERROR(1), WARNING(2), INFO(3), DEBUG(4), OFF(5);

    private final int checkNumber;


    LogLevel(int checkNumber) {
        this.checkNumber = checkNumber;
    }

    public int getCheckNumber() {
        return checkNumber;
    }
}
