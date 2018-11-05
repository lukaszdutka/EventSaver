package eventsaver;

import database.MyDatabase;
import database.MyDatabaseImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class EventProcessor {

    private static final Logger log = LogManager.getLogger(EventProcessor.class);
    private MyDatabase con;
    static private EventProcessor eventProcessor;

    private EventProcessor() { }

    public void process(Path filePath) throws IOException {
        log.info("Starting processing EventProcessor");
        try (Stream<String> stream = Files.lines(filePath)) {
            stream.forEach(s -> {
                try {
                    log.debug("Processing line: {}", s);
                    processJSON(new JSONObject(s)); // maybe could be extracted to new Thread for thread solution
                } catch (JSONException e) {
                    log.error("Error while parsing JSON", e);
                }
            });
        }

    }

    private void processJSON(JSONObject json) {
        try {
            Event event = Event.createEvent(json);
            con.insertOrUpdate(event);
        } catch (JSONException e) {
            log.error("Error while creating Event object", e);
        }

    }

    static public EventProcessor create() {
        log.info("Starting creating EventProcessor");
        if (eventProcessor == null) {
            EventProcessor temp = new EventProcessor();
            temp.setDatabaseAccessor(MyDatabaseImpl.create());
            eventProcessor = temp;
        }

        return eventProcessor;
    }

    private void setDatabaseAccessor(MyDatabase myDatabase) {
        this.con = myDatabase;
    }


}
