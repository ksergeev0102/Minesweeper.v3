package lab3.log;

public interface Logger {
    void printTrace(String message);
    void printDebug(String message);
    void printInfo(String message);
    void printWarn(String message);
    void printError(String message);
    void printFatal(String message);
}
