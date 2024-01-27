package Model;

import Node.NodeAdmin;

public class ModelAdmin {

    private NodeAdmin admin;

    public ModelAdmin() {
        admin = new NodeAdmin();
    }

    public boolean login(String username, String password) {
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }
}
