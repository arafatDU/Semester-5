package components;

public class Bread {
    private String type;

    public Bread(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}