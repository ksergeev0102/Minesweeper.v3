package lab3.log;

public class Consolelogger implements  Logger{
    @Override
    public void printDebug(String a) {
        System.out.println("Debug: " + a);
    }
    @Override
    public void printError(String a) {
        System.out.println("Error: " + a);
    }
    @Override
    public void printTrace(String a) {
        System.out.println("Trace: " + a);
    }
    @Override
    public void printInfo(String a) {
        System.out.println("Info: " + a);
    }
    @Override
    public void printWarn(String a) {
        System.out.println("Warn: " + a);
    }
    @Override
    public void printFatal(String a) {
        System.out.println("Fatal: " + a);
    }
}
