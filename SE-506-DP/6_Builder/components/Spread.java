package components;


public class Spread {
    private String type;

    public Spread(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}