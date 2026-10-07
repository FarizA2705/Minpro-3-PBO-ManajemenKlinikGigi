package view;

import controller.KlinikManager;
import model.DokterGigi;
import java.util.Scanner;

public class KlinikGigiManajemen3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KlinikManager manager = new KlinikManager();
        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n===========================================");
            System.out.println("    SISTEM MANAJEMEN KLINIK GIGI (DENTAL)  ");
            System.out.println("===========================================");
            System.out.println("1. Tambah Data Pasien Baru");
            System.out.println("2. Tampilkan Semua Pasien");
            System.out.println("3. Ubah Data Pasien");
            System.out.println("4. Hapus Data Pasien");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("--> Error: Input menu harus berupa angka!");
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan ID Pasien           : ");
                    String idPasien = scanner.nextLine();
                    System.out.print("Masukkan Nama Pasien         : ");
                    String namaPasien = scanner.nextLine();

                    // 1. Input Rekam Medis Terlebih Dahulu
                    System.out.print("Masukkan ID Rekam Medis      : ");
                    String idRM = scanner.nextLine();
                    System.out.print("Masukkan Diagnosa            : ");
                    String diagnosa = scanner.nextLine();
                    System.out.print("Masukkan Catatan Tindakan    : ");
                    String tindakan = scanner.nextLine();

                    // 2. Tampilkan Daftar Dokter & Input ID Dokter PJ
                    manager.tampilkanDokter();
                    System.out.print("Pilih ID Dokter PJ           : ");
                    String idDokter = scanner.nextLine();

                    DokterGigi dokterPilihan = manager.cariDokter(idDokter);
                    if (dokterPilihan == null) {
                        System.out.println("--> Error: ID Dokter tidak ditemukan!");
                        break;
                    }

                    if (idPasien.isEmpty() || namaPasien.isEmpty()) {
                        System.out.println("--> Error: ID dan Nama Pasien tidak boleh kosong!");
                        break;
                    }

                    // Memanggil method controller
                    manager.tambahPasien(idPasien, namaPasien, dokterPilihan, idRM, diagnosa, tindakan);
                    break;

                case 2:
                    manager.tampilkanPasien();
                    break;

                case 3:
                    System.out.print("Masukkan ID Pasien yang ingin diubah : ");
                    String idUpdate = scanner.nextLine();
                    System.out.print("Masukkan Nama Pasien Baru            : ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan Diagnosa Baru               : ");
                    String diagnosaBaru = scanner.nextLine();
                    System.out.print("Masukkan Catatan Tindakan Baru       : ");
                    String tindakanBaru = scanner.nextLine();

                    manager.updatePasien(idUpdate, namaBaru, diagnosaBaru, tindakanBaru);
                    break;

                case 4:
                    System.out.print("Masukkan ID Pasien yang akan dihapus : ");
                    String idHapus = scanner.nextLine();
                    manager.hapusPasien(idHapus);
                    break;

                case 5:
                    berjalan = false;
                    System.out.println("--> Terima kasih telah menggunakan Sistem Klinik Gigi.");
                    break;

                default:
                    System.out.println("--> Pilihan menu tidak valid, silakan pilih angka 1-5.");
            }
        }
        scanner.close();
    }
}