package Summoners.Bots.RSCRevolution.util.logger;

public class Logger {
    ILogger logger;

    public Logger(ILogger logger) {
        this.logger = logger;
    }

    public void println(String text) {
        logger.writeln(text);
    }
    public void print(String text) {
        logger.write(text);
    }
}