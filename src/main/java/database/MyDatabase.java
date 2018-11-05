package database;

import eventsaver.Event;

public interface MyDatabase { // no remarks. However it would be probably extended

    void insertOrUpdate(Event event);

}
