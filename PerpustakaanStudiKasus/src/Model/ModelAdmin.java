package Model;

import Node.NodeAdmin;
import Node.NodePinjaman;

import java.util.ArrayList;

public class ModelAdmin {

    private NodeAdmin admin;

    public ModelAdmin() {
        admin = new NodeAdmin();
    }

    public boolean login(String username, String password) {
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }
}
