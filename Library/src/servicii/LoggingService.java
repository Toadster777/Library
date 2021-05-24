package servicii;

import io.SingletonWriter;

import java.sql.Timestamp;

public class LoggingService {
    private static LoggingService instance = new LoggingService("log.csv");
    private SingletonWriter writer = SingletonWriter.getInstance();
    private final String fileName;


    private LoggingService(String fileName) {
        this.fileName = fileName;
    }

    public static LoggingService getInstance() {
        return instance;
    }

    public void log(String acction) {
        writer.writeToFile("\n" + acction + ", " + new Timestamp(System.currentTimeMillis()), fileName);
    }
}