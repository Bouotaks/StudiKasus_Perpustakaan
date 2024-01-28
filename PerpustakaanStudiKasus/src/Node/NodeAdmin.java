package Node;

public abstract class NodeAdmin {
    protected String username;
    protected String password;

    public NodeAdmin() {
        this.username = "dean";
        this.password = "07506";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract boolean login(String username, String password);
}
