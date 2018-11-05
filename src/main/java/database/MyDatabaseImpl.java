package database;

import eventsaver.Event;

public class MyDatabaseImpl implements MyDatabase {

    static private MyDatabaseImpl con;

    private MyDatabaseImpl() { }

    public static MyDatabase create() {
        if (con == null) {
            con = new MyDatabaseImpl();
        }
        return con;
    }

    @Override
    public void insertOrUpdate(Event event) {

        //I have no experience with hsql so all I got is some concepts of this part of program.

        //There would be something like this:
        //result = select * from events where id = 'event.getId()'

        //if 0 rows:
        //  create row and fill ID, START_TIMESTAMP (or END_TIMESTAMP) and TYPE/HOST if present
        //if there will be 1 row, then check if it has opposite type of message (eg. if our event is FINISHED
        //  then check if this row has only STARTED fields)
        //  update what is necessary, calculate difference between start timestamp and end timestamp and add flag if necessary

        //table structure would be something like this:
        //ID, START_TIMESTAMP, END_TIMESTAMP, TYPE, HOST, FLAG


        //I was thinking on extracting some database operations to new threads, however I didn't come up with any solution that
        //wouldn't break anything (eg. extracting SELECT to threads would interfere with some INSERTs. I don't know.)
    }

}
