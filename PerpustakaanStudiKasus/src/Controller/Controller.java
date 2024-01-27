package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.ModelAdmin;
import Model.ModelBuku;
import Model.ModelPinjaman;
import Node.NodeBuku;


public class Controller {
    ModelBuku modelbuku;
    ModelAdmin modeladmin;
    ModelPinjaman modelpinjaman;
    Scanner input = new Scanner(System.in);

    public Controller(ModelBuku modelbuku, ModelAdmin modeladmin, ModelPinjaman modelpinjaman){
        this.modelbuku = modelbuku;
        this.modeladmin = modeladmin;
        this.modelpinjaman = modelpinjaman;
    }

    public void inputBuku(){
        input.nextLine();
        System.out.println("        TAMBAHKAN BUKU      ");
        System.out.print("Masukkan judul buku : ");
        String judul = input.nextLine();
        System.out.print("Masukkan penulis buku : ");
        String penulis = input.nextLine();
        System.out.print("Masukkan penerbit buku : ");
        String penerbit = input.nextLine();
        System.out.print("Masukkan tahun buku dibuat : ");
        int tahun = input.nextInt();
        System.out.print("Masukkan stok buku : ");
        int stok = input.nextInt();
        System.out.print("Masukkan jumlah halaman buku : ");
        int jumlahHalaman = input.nextInt();
        input.nextLine();
        System.out.print("Masukkan posisi Rak buku (A1 - F6): ");
        String rak = input.nextLine();
        boolean posisiRakTerpakai = modelbuku.cekRak(rak);
        if (posisiRakTerpakai) {
            System.out.println("Posisi rak sudah digunakan. Pilih posisi rak lain.");
            return;
        }
        modelbuku.tambahBuku(judul, penulis, penerbit, tahun, stok, jumlahHalaman, rak);
        modelbuku.commit();
    }

    public void hapusBuku(){
        System.out.println("     HAPUS BUKU      ");
        System.out.print("Masukkan ID buku yang akan dihapus : ");
        int id = input.nextInt();
        modelbuku.hapusBuku(id);
    }

    public void updateBuku(){
            System.out.println("     UPDATE BUKU     ");
            System.out.print("Masukkan ID buku yang akan di-update: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Masukkan judul buku (kosongkan jika tidak ingin mengubah) : ");
            String nama = input.nextLine();
            System.out.print("Masukkan penulis buku (kosongkan jika tidak ingin mengubah) : ");
            String penulis = input.nextLine();
            System.out.print("Masukkan penerbit buku (kosongkan jika tidak ingin mengubah) : ");
            String penerbit = input.nextLine();
            System.out.print("Masukkan tahun buku dibuat (0 jika tidak ingin mengubah) : ");
            int tahun = input.nextInt();
            System.out.print("Masukkan stok buku (-1 jika tidak ingin mengubah) : ");
            int stok = input.nextInt();
            System.out.print("Masukkan jumlah halaman buku (-1 jika tidak ingin mengubah) : ");
            int jumlahHalaman = input.nextInt();
            input.nextLine();
            System.out.print("Masukkan posisi Rak buku (kosongkan jika tidak ingin mengubah) : ");
            String rak = input.nextLine();

            boolean success = modelbuku.updateBuku(id, nama, penulis, penerbit, tahun, stok, jumlahHalaman, rak);
            if (success) {
                System.out.println("Buku berhasil di-update.");
            } else {
                System.out.println("Gagal meng-update buku.");
            }
        }

    public void cariBuku(){
        System.out.print("Masukkan ID buku yang akan dilihat: ");
        int id = input.nextInt();
        if (id >= 0 && id < modelbuku.bukuList.size()) {
            modelbuku.cariBuku(id);
        } else {
            System.out.println("ID buku tidak valid.");
        }
    }

    public void pinjamBuku() {
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        int idBuku = input.nextInt();
        input.nextLine();

        NodeBuku buku = modelbuku.getbuku(idBuku);

        if (buku != null && buku.getStok() > 0) {
            int lamaPinjam;
            do {
                System.out.print("Masukkan lama peminjaman (contoh: '7'): ");
                lamaPinjam = input.nextInt();
            } while (lamaPinjam <= 0);

            int biaya = 5000 * lamaPinjam;

            modelpinjaman.pinjamBuku(idBuku, lamaPinjam, biaya);
            modelpinjaman.commit();
            buku.setStok(buku.getStok() - 1);
            modelbuku.commit();

            System.out.println("Buku berhasil dipinjam");
        } else {
            System.out.println("Buku tidak tersedia atau ID buku tidak valid.");
        }
    }

    public void kembalikanBuku() {
        System.out.print("Masukkan ID peminjaman buku yang ingin dikembalikan: ");
        int idPinjam = input.nextInt();
        modelpinjaman.kembalikanBuku(idPinjam);
    }

//    public void cetakDetailPeminjaman() {
//        ArrayList<NodePinjaman> listPinjaman = modelpinjaman.getDetailPinjaman();
//        System.out.println("===== DETAIL PEMINJAMAN =====");
//        for (int i = 0; i < listPinjaman.size(); i++) {
//            NodePinjaman pinjaman = listPinjaman.get(i);
//            NodeBuku buku = modelbuku.getbuku(pinjaman.getBukuId());
//
//            System.out.println("ID Peminjaman: " + i);
//            System.out.println("Judul Buku: " + buku.getNamaBuku());
//            System.out.println("Lama Peminjaman: " + pinjaman.getLamaPinjam());
//            System.out.println("Biaya Peminjaman: " + pinjaman.getBiaya());
//            System.out.println("==============================");
//        }
//    }

    public void cekLogin() {
        input.nextLine();
        System.out.println("      LOGIN      ");
        System.out.print("Masukkan username : ");
        String username = input.nextLine();
        System.out.print("Masukkan password : ");
        String password = input.nextLine();

        if (modeladmin.login(username, password)) {
            System.out.println("LOGIN BERHASIL");
            adminMenu();
        } else {
            System.out.println("LOGIN GAGAL");
        }
    }

    private void adminMenu() {
        int pilihan1;

        do {
            System.out.println("===========ADMIN===========");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Update Buku");
            System.out.println("4. Lihat Buku");
            System.out.println("5. Cetak detail Peminjaman");
            System.out.println("6. Kembali ke Menu Utama");
            System.out.println("===========================");
            System.out.print("pilihan : ");
            pilihan1 = input.nextInt();
            System.out.println("===========================");

            switch (pilihan1) {
                case 1: {
                    inputBuku();
                    break;
                }
                case 2: {
                    hapusBuku();
                    break;
                }
                case 3: {
                    updateBuku();
                    break;
                }
                case 4: {
                    modelbuku.lihatSemuaBuku();
                    break;
                }
                case 5: {
                    //cetakDetailPeminjaman();
                    break;
                }
                case 6: {
                    break;
                }
                default: {
                    System.out.println("INPUT ERROR");
                    break;
                }
            }
        } while (pilihan1 != 6);
    }

    public void menuPelanggan(){
        int pilihan2;

        do {
            System.out.println("==========MENU==========");
            System.out.println("1. Cari Buku");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.println("========================");
            System.out.print("pilihan : ");
            pilihan2 = input.nextInt();
            System.out.println("========================");

            switch (pilihan2){
                case 1:{
                    cariBuku();
                    break;
                }
                case 2:{
                    pinjamBuku();
                    break;
                }
                case 3:{
                    kembalikanBuku();
                    break;
                }
                case 4:{
                    break;
                }
            }
        }while(pilihan2 != 4);
    }
}
