package controller;

import model.*;
import java.util.ArrayList;

public class KlinikManager {
    private ArrayList<Pasien> daftarPasien;
    private ArrayList<DokterGigi> daftarDokter;

    public KlinikManager() {
        daftarPasien = new ArrayList<>();
        daftarDokter = new ArrayList<>();
        isiDummyData();
    }

    private void isiDummyData() {
        DokterGigi d1 = new DokterGigi("DOC-01", "drg. Budi Santoso", "Ortodonti");
        DokterGigi d2 = new DokterGigi("DOC-02", "drg. Siti Rahma", "Konservasi Gigi");
        daftarDokter.add(d1);
        daftarDokter.add(d2);

        RekamMedis rm1 = new RekamMedis("RM-01", "Karies Gigi Molar", "Penambalan Komposit");
        Pasien p1 = new Pasien("PAS-01", "Andi Pratama", d1, rm1);
        daftarPasien.add(p1);
    }

    public void tambahPasien(Pasien pasien) {
        daftarPasien.add(pasien);
        System.out.println("--> Pasien berhasil ditambahkan!");
        pasien.cetakKartuBerobat();
    }

    public void tambahPasien(String id, String nama, DokterGigi dokter, String idRM, String diagnosa, String tindakan) {
        RekamMedis rm = new RekamMedis(idRM, diagnosa, tindakan);
        Pasien pasienBaru = new Pasien(id, nama, dokter, rm);
        tambahPasien(pasienBaru);
    }

    public void tampilkanDokter() {
        System.out.println("\n--- DAFTAR DOKTER TERSEDIA ---");
        for (DokterGigi d : daftarDokter) {
            d.tampilkanProfil();
        }
    }

    public DokterGigi cariDokter(String idDokter) {
        for (DokterGigi d : daftarDokter) {
            if (d.getId().equalsIgnoreCase(idDokter)) {
                return d;
            }
        }
        return null;
    }

    public void tampilkanPasien() {
        if (daftarPasien.isEmpty()) {
            System.out.println("--> Belum ada data pasien terdaftar.");
            return;
        }
        System.out.println("\n==================== DAFTAR PASIEN & REKAM MEDIS ====================");
        for (int i = 0; i < daftarPasien.size(); i++) {
            Pasien p = daftarPasien.get(i);
            DokterGigi d = p.getDokter();
            RekamMedis rm = p.getRekamMedis();

           System.out.println((i + 1) + ". ID Pasien     : " + p.getId());
            System.out.println("   Nama Pasien   : " + p.getNama());
            System.out.println("   Dokter        : " + d.getNama() + " (" + d.getSpesialisasi() + ")");
            System.out.println("   ID Rekam Medis: " + rm.getIdRekamMedis());
            System.out.println("   Diagnosa      : " + rm.getDiagnosa());
            System.out.println("   Tindakan      : " + rm.getCatatanTindakan());
            System.out.println("=====================================================================");
        }
    }

    public void updatePasien(String id, String namaBaru, String diagnosaBaru, String tindakanBaru) {
        for (Pasien p : daftarPasien) {
            if (p.getId().equalsIgnoreCase(id)) {
                p.setNama(namaBaru);
                p.getRekamMedis().setDiagnosa(diagnosaBaru);
                p.getRekamMedis().setCatatanTindakan(tindakanBaru);
                System.out.println("--> Data pasien berhasil diperbarui!");
                return;
            }
        }
        System.out.println("--> ID Pasien tidak ditemukan!");
    }

    public void hapusPasien(String id) {
        for (int i = 0; i < daftarPasien.size(); i++) {
            if (daftarPasien.get(i).getId().equalsIgnoreCase(id)) {
                daftarPasien.remove(i);
                System.out.println("--> Data pasien berhasil dihapus!");
                return;
            }
        }
        System.out.println("--> ID Pasien tidak ditemukan!");
    }
}