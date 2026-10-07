# Dokumentasi Sistem Manajemen Klinik Gigi
Sistem Manajemen Klinik Gigi adalah aplikasi berbasis Java yang dirancang untuk mengelola data operasional klinik gigi secara efisien. 
Program ini menerapkan arsitektur **Model-View-Controller (MVC)** untuk memisahkan logika bisnis, struktur data, dan antarmuka pengguna. 
Fitur utama meliputi pencatatan data pasien, pengikatan pasien ke dokter, pembuat rekam medis, serta operasi **CRUD (Create, Read, Update, Delete)** data pasien.

---

## 1. Deskripsi Singkat Program
Program ini dirancang untuk mengelola data operasional pada klinik gigi secara terstruktur. Fitur utama aplikasi mencakup:
* Manajemen data pasien (pendaftaran, penayangan, pembaruan data, dan penghapusan).
* Pengikatan pasien dengan Dokter yang menangani.
* Pencatatan rekam medis pasien (diagnosa dan catatan tindakan).
* Penanganan validasi data untuk mencegah data kosong/spasi

---

## 2. Penjelasan Struktur Package

Seluruh *source code* dipisah ke dalam 3 package utama sesuai dengan prinsip arsitektur **MVC**:

| Package | Nama Class / Interface | Deskripsi Tanggung Jawab |
| :--- | :--- | :--- |
| **`model`** | `Orang.java`<br>`DokterGigi.java`<br>`Pasien.java`<br>`RekamMedis.java`<br>`StatusLayanan.java` | Menampung cetak biru data, entitas bisnis, atribut, *getter/setter*, hubungan pewarisan (*inheritance*), serta *interface* layanan. |
| **`controller`** | `KlinikManager.java` | Bertindak sebagai pemroses logika bisnis, manipulasi **ArrayList** data pasien & dokter, pencarian data, serta penerapan *method overloading*. |
| **`view`** | `KlinikGigiManajemen3.java` | Menjadi antarmuka *Console CLI* utama (*Main Class*), menangani *input* dari pengguna, dan memanggil fungsi pada *controller*. |

---

## 3. Penjelasan Alur Program

### A. Inisialisasi Sistem
1. Program dijalankan melalui method main pada **KlinikGigiManajemen3** (**view**).
2. KlinikManager (**controller**) diinisialisasi dan otomatis memuat dummy data awal.

### B. Eksekusi Menu Utama
1. **Tambah Pasien Baru (Menu 1)**:
   * Pengguna menginputkan ID Pasien, Nama Pasien, ID Rekam Medis, Diagnosa, dan Catatan Tindakan.
   * Sistem memvalidasi agar tidak ada *input* berupa *string* kosong atau spasi saja.
   * Sistem menampilkan daftar dokter yang tersedia, lalu pengguna memilih ID Dokter.
   * Controller memvalidasi keberadaan Dokter dan menyimpan objek pasien baru.
2. **Tampilkan Semua Pasien (Menu 2)**:
   * Controller melintas (*looping*) seluruh data pasien yang sudah dibuat.
   * Sistem mencetak profil pasien, dokter, serta rincian rekam medisnya.
3. **Ubah Data Pasien (Menu 3)**:
   * Pengguna memasukkan ID Pasien yang ingin diperbarui.
   * Jika ID ditemukan, sistem memperbarui nama pasien, diagnosa, dan catatan tindakan medis.
4. **Hapus Data Pasien (Menu 4)**:
   * Pengguna memasukkan ID Pasien yang akan dihapus dari sistem memori.
5. **Keluar (Menu 5)**:
   * Menghentikan perulangan menu dan mengakhiri eksekusi program.

---

## 4. Penjelasan Penerapan Encapsulation dan Inheritance

### Encapsulation (Pengapsulan Data)
Encapsulation adalah mekanisme menyembunyikan data internal suatu objek dari akses langsung luar kelas. Pada proyek ini, seluruh atribut/variabel dalam package `model` diberi *access modifier* `private` atau `protected`. Akses pembacaan maupun modifikasi nilai atribut wajib melewati method *Getter* dan *Setter*.

```
public class RekamMedis {
    private String idRekamMedis;
    private String diagnosa;
    private String catatanTindakan;

    public RekamMedis(String idRekamMedis, String diagnosa, String catatanTindakan) {
        this.idRekamMedis = idRekamMedis;
        this.diagnosa = diagnosa;
        this.catatanTindakan = catatanTindakan;
    }

    public String getIdRekamMedis() {
        return idRekamMedis;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }
  ```


### Inheritance (Pewarisan)
Inheritance memungkinkan pembuatan struktur hierarki kelas di mana kelas anak (subclass) mewarisi atribut dan method dari kelas induk (superclass). Konsep ini merepresentasikan hubungan is-a (adalah seorang/sebuah).
* **Superclass**: Kelas `Orang` berfungsi sebagai kelas induk yang menampung atribut umum seperti `id` dan `nama`.
* **Subclass**: Kelas `DokterGigi` dan `Pasien` mewarisi (*extends*) kelas `Orang`. Kedua *subclass* ini otomatis memiliki atribut `id` dan `nama`, serta menambahkan atribut spesifiknya masing-masing (misal: `spesialisasi` pada `DokterGigi`).

```
public class DokterGigi extends Orang {
    private String spesialisasi;

    public DokterGigi(String idDokter, String namaDokter, String spesialisasi) {
        super(idDokter, namaDokter);
        this.spesialisasi = spesialisasi;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    @Override
    public void tampilkanProfil() {
        System.out.println("[Dokter] ID: " + id + " | Nama: " + nama + " | Spesialisasi: " + spesialisasi);
    }
}
```
```
public class Pasien extends Orang implements StatusLayanan {
    private DokterGigi dokter;
    private RekamMedis rekamMedis;

    public Pasien(String idPasien, String namaPasien, DokterGigi dokter, RekamMedis rekamMedis) {
        super(idPasien, namaPasien);
        this.dokter = dokter;
        this.rekamMedis = rekamMedis;
    }

    public DokterGigi getDokter() {
        return dokter;
    }

    public void setDokter(DokterGigi dokter) {
        this.dokter = dokter;
    }

    public RekamMedis getRekamMedis() {
        return rekamMedis;
    }

    @Override
    public void tampilkanProfil() {
        System.out.println("[Pasien] ID: " + id + " | Nama: " + nama + " | Dokter : " + dokter.getNama());
    }

    @Override
    public void cetakKartuBerobat() {
        System.out.println("--> KARTU BEROBAT AKTIF: Pasien " + nama + " (ID: " + id + ")");
    }
}
```

## 5. Penjelasan Penerapan Polymorphism dan Abstraction

### Abstraction (Abstraksi)
**Abstract Class**: Kelas `Orang` dideklarasikan sebagai `abstract class` sehingga tidak dapat menciptakan objek secara langsung (`new Orang()`). Kelas ini memuat *abstract method* `tampilkanProfil()`.
```
package model;

public abstract class Orang {
    protected String id;
    protected String nama;

    public Orang(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public abstract void tampilkanProfil();
}
```

### Polymorphism (Banyak Bentuk)
* **Method Overriding**: Kelas `DokterGigi` dan `Pasien` meng-override *abstract method* `tampilkanProfil()` dari kelas induk `Orang` untuk menyajikan format tampilan profil yang berbeda.
  * Override DokterGigi
    ```
    @Override
    public void tampilkanProfil() {
        System.out.println("[Dokter] ID: " + id + " | Nama: " + nama + " | Spesialisasi: " + spesialisasi);
        }
    }
    ```
  * Override Pasien
    ```
    @Override
    public void tampilkanProfil() {
        System.out.println("[Pasien] ID: " + id + " | Nama: " + nama + " | Dokter : " + dokter.getNama());
        }

    @Override
    public void cetakKartuBerobat() {
        System.out.println("--> KARTU BEROBAT AKTIF: Pasien " + nama + " (ID: " + id + ")");
        }
    }
    ``` 

  
* **Method Overloading**: Kelas `KlinikManager` menerapkan *overloading* pada *method* `tambahPasien()`:`tambahPasien(PasienGigi pasien)`: Menerima langsung objek `PasienGigi`.
```
  public void tambahPasien(Pasien pasien) {
        daftarPasien.add(pasien);
        System.out.println("--> Pasien berhasil ditambahkan!");
        pasien.cetakKartuBerobat();

  public void tambahPasien(String id, String nama, DokterGigi dokter, String idRM, String diagnosa, String tindakan) {
        RekamMedis rm = new RekamMedis(idRM, diagnosa, tindakan);
        Pasien pasienBaru = new Pasien(id, nama, dokter, rm);
        tambahPasien(pasienBaru);
    }
```
## 6. Penerapan Interface (Nilai Tambah & Abstraksi Murni)

Interface `StatusLayanan` digunakan untuk menyediakan bentuk abstraksi murni berupa kontrak *method* `cetakKartuBerobat()`. Interface ini diimplementasikan oleh kelas `Pasien` untuk merealisasikan pencetakan status kartu berobat aktif tanpa terikat langsung pada struktur hirarki utama kelas induk (`Orang`).

**1. Definisi Interface (`model/StatusLayanan.java`):**
```
public interface StatusLayanan {
    void cetakKartuBerobat();
}
public class Pasien extends Orang implements StatusLayanan {
    private DokterGigi dokter;
    private RekamMedis rekamMedis;
@Override
    public void cetakKartuBerobat() {
        System.out.println("--> KARTU BEROBAT AKTIF: Pasien " + nama + " (ID: " + id + ")");
    }
}
```

## 7. Running Program
### Menu Utama
<img width="330" height="131" alt="image" src="https://github.com/user-attachments/assets/ec426e3c-8d07-4cb4-a0bb-81b90550a87b" />

---

### 1. Tambah Data Pasien Baru
<img width="488" height="176" alt="image" src="https://github.com/user-attachments/assets/7d94061b-247e-4c8f-8d5e-ccbc7e1ff519" /><br>
Pada menu 1 yaitu menambahkan data pasien baru, lalu KlinikManager yang mengatur dokter mana yang akan di datangi oleh Pasien.

---

### 2. Tampilkan Semua Pasien
<img width="546" height="228" alt="image" src="https://github.com/user-attachments/assets/7c05b0f4-ebc8-4bf8-96bf-682bc74d1d96" /><br>
Pada menu 2 ini menampilkan seluruh Data Pasien yang telah terdaftar sebelumnya mauapun dummy data pada program akan ditampilakn pada output.

---

### 3. Ubah Data Pasien


---
