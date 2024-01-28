package View;

import java.util.Scanner;

import Controller.Controller;

public class View {

    Scanner input = new Scanner(System.in);
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void TampilanMenu(){
        int pilihan;

        do{
            System.out.println("=========MENU==========");
            System.out.println("1. Login Admin");
            System.out.println("2. Masuk sebagai tamu");
            System.out.println("3. Exit");
            System.out.println("=======================");
            System.out.print("pilihan : ");
            pilihan = input.nextInt();
            System.out.println("=======================");

            switch (pilihan) {
                case 1 -> controller.cekLogin();
                case 2 -> controller.menuPelanggan();
                case 3 -> {
                    System.out.println("TERIMAKASIH SUDAH MENGGUNAKAN PERPUSTAKAAN KAMI");
                    System.out.println("             DATANGLAH KEMBALI                 ");
                    System.exit(0);
                }
                default -> System.out.println("INPUT ERROR");
            }
        } while (true);
    }
}