package Model;

import com.google.gson.reflect.TypeToken;
import ModelJSON.ModelJSON;
import java.util.ArrayList;
import java.util.Scanner;
import Node.NodeBuku;

public class ModelBuku{
    public ArrayList<NodeBuku> bukuList;

    Scanner input = new Scanner(System.in);
    private final ModelJSON<NodeBuku> modelJSON;

    public ModelBuku() {
        bukuList = new ArrayList<>();
        modelJSON = new ModelJSON<NodeBuku>("src\\Database\\dataBuku.json");
        loadBuku();
    }

    public void loadBuku(){
        bukuList = modelJSON.readFromFile(new TypeToken<ArrayList<NodeBuku>>(){}.getType());
    }

    public void commit(){
        modelJSON.writeToFile(bukuList);
    }

    public boolean updateBuku(int id, String nama, String penulis, String penerbit, int tahun, int stok, int jumlahHalaman, String posisiRak) {
        System.out.println("Masukkan id buku yang akan diupdate : ");
        int pilihan = input.nextInt();
        NodeBuku buku = getbuku(pilihan);
        if (buku != null){
            buku.setNamaBuku(nama);
            buku.setPenulis(penulis);
            buku.setPenerbit(penerbit);
            buku.setTahun(tahun);
            buku.setStok(stok);
            buku.setJumlahHalaman(jumlahHalaman);
            if (posisiRak != null && !cekRak(posisiRak)) {
                buku.setPosisiRak(posisiRak);
            } else if (posisiRak != null) {
                System.out.println("Posisi rak sudah digunakan. Pilih posisi rak yang lain.");
                return false;
            }
            commit();
            return true;
        }
        System.out.println("ID buku tidak valid");
        return false;
    }

    public NodeBuku getbuku(int id){
        NodeBuku BUKU = null;
        for (NodeBuku buku : bukuList){
            if (buku.getId() == id){
                return buku;
            }
        }
        return BUKU;
    }

    public void tambahBuku(String nama, String penulis,String penerbit, int tahun, int stok, int jumlahHalaman, String posisiRak){
        int id = getLastKode() + 1;
        NodeBuku buku = new NodeBuku(id, nama, penulis, penerbit, tahun, stok, jumlahHalaman, posisiRak);
        bukuList.add(buku);
    }

    public boolean cekRak(String posisiRak) {
        for (NodeBuku book : bukuList) {
            if (posisiRak != null && posisiRak.equals(book.getPosisiRak())) {
                return true;
            }
        }
        return false;
    }

    private int getLastKode(){
        int last = bukuList.size() - 1;
        return bukuList.get(last).getId();
    }

    public void cariBuku(int id){
        NodeBuku buku = bukuList.get(id);
        System.out.println("===== DETAIL BUKU =====");
        System.out.println("Judul Buku: " + buku.getNamaBuku());
        System.out.println("Penulis: " + buku.getPenulis());
        System.out.println("Tahun Terbit: " + buku.getTahun());
        System.out.println("Stok: " + buku.getStok());
        System.out.println("Jumlah Halaman: " + buku.getJumlahHalaman());
        System.out.println("Posisi Rak: " + buku.getPosisiRak());
        System.out.println("=======================");
    }

    public void hapusBuku(int id){
        bukuList.remove(id);
    }

    public void lihatSemuaBuku() {
        System.out.println("===== SEMUA BUKU =====");
        for (NodeBuku book : bukuList) {
            System.out.println("ID Buku: " + bukuList.indexOf(book));
            System.out.println("Judul Buku: " + book.getNamaBuku());
            System.out.println("Penulis: " + book.getPenulis());
            System.out.println("Tahun Terbit: " + book.getTahun());
            System.out.println("Stok: " + book.getStok());
            System.out.println("Jumlah Halaman: " + book.getJumlahHalaman());
            System.out.println("Posisi Rak: " + book.getPosisiRak());
            System.out.println("=======================");
        }
    }
}