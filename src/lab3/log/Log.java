package lab3.log;

public class Log {
    private static lab3.log.Logger logger = new lab3.log.Filelogger();

    public static void printDebug(String message) {
        logger.printDebug(message);
    }

    public static void printError(String message) {
        logger.printError(message);
    }

    public static void printTrace(String message) {
        logger.printTrace(message);
    }

    public static void printInfo(String message) {
        logger.printInfo(message);
    }

    public static void printFatal(String message) {
        logger.printFatal(message);
    }

    public static void printWarn(String message) {
        logger.printWarn(message);
    }
}
