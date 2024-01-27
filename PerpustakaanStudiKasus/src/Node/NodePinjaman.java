package Node;

public class NodePinjaman {
    int lamaPinjam;
    int idPinjam;
    int biaya;
    int idBuku;

    public NodePinjaman(int idPinjam, int idBuku,int lamaPinjam, int biaya) {
        this.idPinjam = idPinjam;
        this.lamaPinjam = lamaPinjam;
        this.biaya = biaya;
        this.idBuku = idBuku;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public int getIdPinjam() {
        return idPinjam;
    }

    public void setIdPinjam(int idPinjam) {
        this.idPinjam = idPinjam;
    }

    public int getLamaPinjam() {
        return lamaPinjam;
    }

    public void setLamaPinjam(int lamaPinjam) {
        this.lamaPinjam = lamaPinjam;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }
}
