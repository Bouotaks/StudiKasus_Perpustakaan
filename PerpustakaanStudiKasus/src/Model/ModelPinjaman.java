package Model;

import ModelJSON.ModelJSON;
import Node.NodeBuku;
import Node.NodePinjaman;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class ModelPinjaman {
    private final ModelBuku modelbuku;

    private ArrayList<NodePinjaman> listPinjaman;
    private final ModelJSON<NodePinjaman> modelJSON;

    public ModelPinjaman(ModelBuku modelbuku){
        this.modelbuku = modelbuku;
        listPinjaman = new ArrayList<>();
        modelJSON = new ModelJSON<NodePinjaman>("src\\Database\\pinjaman.json");
        loadPinjaman();
    }

    public void loadPinjaman(){
        listPinjaman = modelJSON.readFromFile(new TypeToken<ArrayList<NodePinjaman>>(){}.getType());
    }

    public ArrayList<NodePinjaman> getListPinjaman(){
        return listPinjaman;
    }

    public void commit(){
        modelJSON.writeToFile(listPinjaman);
    }

    private int getLastKode(){
        int last = listPinjaman.size() - 1;
        return listPinjaman.get(last).getIdPinjam();
    }

    public void pinjamBuku(int idBuku, int lamaPinjam, int biaya) {
        int idPinjam = getLastKode() + 1;
        NodePinjaman pinjaman = new NodePinjaman(idPinjam, idBuku, lamaPinjam, biaya);
        listPinjaman.add(pinjaman);
    }

    public NodePinjaman getPinjaman(int idPinjam){
        NodePinjaman PINJAMAN = null;
        for(NodePinjaman pinjaman : listPinjaman){
            if (pinjaman.getIdPinjam()==idPinjam){
                return pinjaman;
            }
        }
        return PINJAMAN;
    }

    public void cekDetailPinjaman(ModelPinjaman modelPinjaman, ModelBuku modelBuku) {
        ArrayList<NodePinjaman> listPinjaman = modelPinjaman.getListPinjaman();

        System.out.println("===== DETAIL PEMINJAMAN =====");
        for (NodePinjaman pinjaman : listPinjaman) {
            System.out.println("ID Pinjam: " + pinjaman.getIdPinjam());
            System.out.println("ID Buku: " + pinjaman.getIdBuku());
            System.out.println("Lama Pinjam: " + pinjaman.getLamaPinjam());
            System.out.println("Biaya: " + pinjaman.getBiaya());

            NodeBuku buku = modelBuku.getbuku(pinjaman.getIdBuku());
            if (buku != null) {
                System.out.println("Buku yang dipinjam:");
                System.out.println("  Judul Buku: " + buku.getNamaBuku());
                System.out.println("  Penulis: " + buku.getPenulis());
                System.out.println("  Tahun Terbit: " + buku.getTahun());
                System.out.println("  Stok: " + buku.getStok());
                System.out.println("  Jumlah Halaman: " + buku.getJumlahHalaman());
                System.out.println("  Posisi Rak: " + buku.getPosisiRak());
            }
            System.out.println("=============================");
        }
    }

    public void kembalikanBuku(int idPinjam) {
        NodePinjaman pinjaman = getPinjaman(idPinjam);
        NodeBuku buku = modelbuku.getbuku(pinjaman.getIdBuku());
        if (buku != null) {
            buku.setStok(buku.getStok() + 1);
            listPinjaman.remove(pinjaman);
            commit();
            modelbuku.commit();
            System.out.println("Buku berhasil dikembalikan");
        } else {
            System.out.println("Gagal mengembalikan buku. ID buku tidak valid");
        }
    }
}
