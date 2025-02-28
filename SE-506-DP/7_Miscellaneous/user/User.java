package user;

public class User {
    private String name;
    private boolean hasAccess;

    public User(String name, boolean hasAccess) {
        this.name = name;
        this.hasAccess = hasAccess;
    }

    public String getName() {
        return name;
    }

    public boolean hasAccess() {
        return hasAccess;
    }
}
