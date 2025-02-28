package components;


public class Filling {
    private String type;

    public Filling(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}