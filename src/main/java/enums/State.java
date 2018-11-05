package enums;

public enum State { //no remarks

    STARTED("STARTED"),
    FINISHED("FINISHED");

    private final String state;

    State(final String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}