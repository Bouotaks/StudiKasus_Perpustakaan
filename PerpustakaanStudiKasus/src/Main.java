import Model.ModelAdmin;
import Model.ModelPinjaman;
import View.View;
import View.Frame;
import Controller.Controller;
import Model.ModelBuku;

public class Main {
    public static void main(String[] args) {
        ModelBuku modelBuku = new ModelBuku();
        ModelAdmin modelAdmin = new ModelAdmin();
        Frame frame = new Frame();
        ModelPinjaman modelPinjaman = new ModelPinjaman(modelBuku);
        Controller controller = new Controller(modelBuku, modelAdmin, modelPinjaman);
        View view = new View();

        frame.Frame();
        view.setController(controller);
        view.TampilanMenu();
    }
}
