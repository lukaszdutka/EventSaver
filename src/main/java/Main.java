import eventsaver.EventProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException { //Maybe this could be designed better
        log.info("Starting EventSaver application.");

        checkLength(args);

        Path filePath = Paths.get(args[0]);
        checkPath(filePath);

        EventProcessor eventProcessor = EventProcessor.create();
        eventProcessor.process(filePath);
    }

    private static void checkLength(String[] args){
        log.debug("Length of array: {}", args.length);
        if (args.length != 1) {
            log.error("Invalid input. Should be only path to file.");
            throw new IllegalArgumentException("Invalid input. Should be only path to file.");
        }
    }

    private static void checkPath(Path filePath) throws IOException{
        log.debug("FilePath: {}", filePath);

        if (!filePath.toFile().isFile()) {
            throw new IOException("Invalid input. This path is not a file.");
        }
    }
}