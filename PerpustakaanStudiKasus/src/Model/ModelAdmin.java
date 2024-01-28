package Model;

import Node.NodeAdmin;

public class ModelAdmin extends NodeAdmin{

    private final NodeAdmin admin;

    public ModelAdmin() {
        super();
        admin = new NodeAdmin() {
            @Override
            public boolean login(String username, String password) {
                return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
            }
        };
    }

    @Override
    public boolean login(String username, String password) {
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }
}
