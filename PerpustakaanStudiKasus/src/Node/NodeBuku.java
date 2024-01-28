package Node;

public class NodeBuku {
    int id;
    String posisiRak;
    String namaBuku;
    String penulis;
    int tahun;
    int stok;
    int jumlahHalaman;
    String penerbit;


    public NodeBuku(int id, String nama, String penulis,String penerbit, int tahun, int stok, int jumlahHalaman, String posisiRak){
        this.id = id;
        this.stok = stok;
        this.posisiRak = posisiRak;
        this.penerbit = penerbit;
        this.namaBuku = nama;
        this.penulis = penulis;
        this.tahun = tahun;
        this.jumlahHalaman = jumlahHalaman;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getId() {
        return id;
    }

    public String getPosisiRak() {
        return posisiRak;
    }

    public void setPosisiRak(String posisiRak) {
        this.posisiRak = posisiRak;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
