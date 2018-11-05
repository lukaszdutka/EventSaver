package eventsaver;

import enums.State;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Event { // no remarks.

    private String id;
    private State state;
    private long timestamp;

    private String type;
    private String host;

    static Event createEvent(JSONObject json) throws JSONException {
        String id;
        State state;
        long timestamp;
        String type = null;
        String host = null;

        id = (String) json.get("id");
        state = parseState((String) json.get("state"));
        timestamp = Long.parseLong((String) json.get("timestamp"));

        if(json.has("type")) type = (String) json.get("type");
        if(json.has("host")) host = (String) json.get("host");

        return new Event(id, state, timestamp, type, host);
    }

    static private State parseState(String state) {
        if(Objects.equals(state, State.STARTED.toString())){
            return State.STARTED;
        }
        if(Objects.equals(state, State.FINISHED.toString())){
            return State.FINISHED;
        }
        throw new IllegalArgumentException("Wrong state in one of jsons");
    }

    private Event(String id, State state, long timestamp, String type, String host) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
        this.type = type;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }
}
