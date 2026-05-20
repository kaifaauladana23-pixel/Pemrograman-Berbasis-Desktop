import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Menggunakan ArrayList agar menu bisa ditambah, diubah, dan dihapus
    static ArrayList<String> daftarMenu = new ArrayList<>();
    static ArrayList<Integer> hargaMenu = new ArrayList<>();
    static ArrayList<Boolean> tipeMinuman = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Inisialisasi data menu awal
        inisialisasiMenu();

        // Perulangan Aplikasi
        while (true) {
            System.out.println("\n========== APLIKASI RESTORAN ==========");
            System.out.println("1. Tampilkan Daftar Menu");
            System.out.println("2. Menu Pemesanan (Pelanggan)");
            System.out.println("3. Pengelolaan Menu (Pemilik)");
            System.out.println("4. Keluar Aplikasi");
            System.out.println("=======================================");
            System.out.print("Pilih Menu Utama: ");
            String pilihan = input.nextLine();

            if (pilihan.equals("1")) {
                cetakDaftarMenu();
            } else if (pilihan.equals("2")) {
                menuPelanggan();
            } else if (pilihan.equals("3")) {
                menuPemilik();
            } else if (pilihan.equals("4")) {
                System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                break;
            } else {
                System.out.println("Pilihan tidak tersedia! Silakan input kembali.");
            }
        }
    }

    public static void inisialisasiMenu() {
        String[] namaAwal = {"Nasi Uduk", "Ikan Gurame", "Sayur Asem", "Ayam Goreng", "Es Teh", "Es Jeruk", "Kopi", "Air Mineral"};
        int[] hargaAwal = {10000, 25000, 15000, 20000, 3000, 5000, 7000, 4000};
        boolean[] IsMinumanAwal = {false, false, false, false, true, true, true, true};

        for (int i = 0; i < namaAwal.length; i++) {
            daftarMenu.add(namaAwal[i]);
            hargaMenu.add(hargaAwal[i]);
            tipeMinuman.add(IsMinumanAwal[i]);
        }
    }

    public static void cetakDaftarMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("\n[ Peringatan: Daftar menu saat ini kosong! ]");
            return;
        }

        System.out.println("\n============ DAFTAR MENU ============");
        System.out.println("[MAKANAN]");
        int counterMakanan = 0;
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (!tipeMinuman.get(i)) {
                System.out.println((i + 1) + ". " + daftarMenu.get(i) + " \t: Rp " + hargaMenu.get(i));
                counterMakanan++;
            }
        }
        if (counterMakanan == 0) System.out.println("(Tidak ada menu makanan)");

        System.out.println("\n[MINUMAN]");
        int counterMinuman = 0;
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (tipeMinuman.get(i)) {
                System.out.println((i + 1) + ". " + daftarMenu.get(i) + " \t: Rp " + hargaMenu.get(i));
                counterMinuman++;
            }
        }
        if (counterMinuman == 0) System.out.println("(Tidak ada menu minuman)");
        System.out.println("=====================================");
    }

    // 1. MENU PELANGGAN (PEMESANAN)
    public static void menuPelanggan() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Tidak bisa melakukan pemesanan karena menu kosong!");
            return;
        }

        ArrayList<String> pesananNama = new ArrayList<>();
        ArrayList<Integer> pesananJumlah = new ArrayList<>();
        ArrayList<Integer> pesananHarga = new ArrayList<>();
        double subtotal = 0;

        while (true) {
            cetakDaftarMenu();
            System.out.println("\n--- Input Pesanan (Format: Nama Menu) ---");
            
            String namaPesanan = "";
            int indeksMenu = -1;

            // Validasi nama menu harus sesuai dengan daftar
            while (indeksMenu == -1) {
                System.out.print("Nama Menu : ");
                namaPesanan = input.nextLine();
                for (int i = 0; i < daftarMenu.size(); i++) {
                    if (daftarMenu.get(i).equalsIgnoreCase(namaPesanan)) {
                        indeksMenu = i;
                        break;
                    }
                }
                if (indeksMenu == -1) {
                    System.out.println("Menu tidak ditemukan! Silakan input kembali.");
                }
            }

            // Validasi jumlah pesanan harus angka dan positif
            int jumlah = 0;
            while (true) {
                System.out.print("Jumlah    : ");
                try {
                    jumlah = Integer.parseInt(input.nextLine());
                    if (jumlah > 0) break;
                    System.out.println("Jumlah harus lebih dari 0!");
                } catch (Exception e) {
                    System.out.println("Input harus berupa angka!");
                }
            }

            pesananNama.add(daftarMenu.get(indeksMenu));
            pesananJumlah.add(jumlah);
            pesananHarga.add(hargaMenu.get(indeksMenu));
            subtotal += (hargaMenu.get(indeksMenu) * jumlah);

            System.out.print("Tambah menu lain? (y/t): ");
            if (!input.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }

        // Cari promo item gratis jika subtotal > 50.000
        String itemGratis = "";
        if (subtotal > 50000) {
            for (int i = 0; i < pesananNama.size(); i++) {
                // cek apakah item yang dipesan merupakan minuman
                int idx = daftarMenu.indexOf(pesananNama.get(i));
                if (idx != -1 && tipeMinuman.get(idx)) {
                    itemGratis = pesananNama.get(i);
                    break;
                }
            }
        }

        double pajak = subtotal * 0.10;
        double pelayanan = 20000;
        double totalBiaya = subtotal + pajak + pelayanan;

        double diskon = 0;
        if (totalBiaya > 100000) {
            diskon = totalBiaya * 0.10;
        }
        double totalAkhir = totalBiaya - diskon;

        // Cetak Struk
        System.out.println("\n======== STRUK PESANAN ========");
        for (int i = 0; i < pesananNama.size(); i++) {
            System.out.println(pesananNama.get(i) + " x" + pesananJumlah.get(i) + " \t: " + (pesananHarga.get(i) * pesananJumlah.get(i)));
        }

        if (!itemGratis.equals("")) {
            System.out.println("FREE " + itemGratis + " x1 \t: 0 (Promo > 50rb)");
        }

        System.out.println("---------------------------------");
        System.out.println("Subtotal     : " + (int)subtotal);
        System.out.println("Pajak (10%)  : " + (int)pajak);
        System.out.println("Pelayanan    : " + (int)pelayanan);

        if (diskon > 0) {
            System.out.println("Diskon (10%) : -" + (int)diskon);
        }

        System.out.println("------------------------------------");
        System.out.println("TOTAL AKHIR  : Rp " + (int)totalAkhir);
        System.out.println("====================================");
    }

    // 2. MENU PEMILIK (PENGELOLAAN MENU)
    public static void menuPemilik() {
        while (true) {
            System.out.println("\n--- PENGELOLAAN MENU RESTORAN ---");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.println("---------------------------------");
            System.out.print("Pilih Opsi Pengelolaan: ");
            String opsi = input.nextLine();

            if (opsi.equals("1")) {
                tambahMenu();
            } else if (opsi.equals("2")) {
                ubahHargaMenu();
            } else if (opsi.equals("3")) {
                hapusMenu();
            } else if (opsi.equals("4")) {
                break; // Kembali ke menu utama (parent)
            } else {
                System.out.println("Pilihan tidak tersedia! Silakan input kembali.");
            }
        }
    }

    public static void tambahMenu() {
        System.out.println("\n--- Tambah Menu Baru ---");
        System.out.print("Masukkan Nama Menu: ");
        String nama = input.nextLine();

        int harga = 0;
        while (true) {
            System.out.print("Masukkan Harga     : ");
            try {
                harga = Integer.parseInt(input.nextLine());
                if (harga > 0) break;
                System.out.println("Harga harus lebih besar dari 0!");
            } catch (Exception e) {
                System.out.println("Input harga harus berupa angka!");
            }
        }

        boolean isMinuman = false;
        while (true) {
            System.out.print("Kategori Menu (1: Makanan, 2: Minuman): ");
            String kat = input.nextLine();
            if (kat.equals("1")) {
                isMinuman = false;
                break;
            } else if (kat.equals("2")) {
                isMinuman = true;
                break;
            } else {
                System.out.println("Pilihan kategori salah! Masukkan angka 1 atau 2.");
            }
        }

        daftarMenu.add(nama);
        hargaMenu.add(harga);
        tipeMinuman.add(isMinuman);
        System.out.println("Menu '" + nama + "' berhasil ditambahkan!");
    }
    // 3. Ubah harga menu
    public static void ubahHargaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Daftar menu kosong!");
            return;
        }

        cetakDaftarMenu();
        int nomorMenu = ambilNomorMenuValid();

        System.out.println("Menu yang dipilih: " + daftarMenu.get(nomorMenu) + " (Harga saat ini: " + hargaMenu.get(nomorMenu) + ")");
        
        int hargaBaru = 0;
        while (true) {
            System.out.print("Masukkan Harga Baru: ");
            try {
                hargaBaru = Integer.parseInt(input.nextLine());
                if (hargaBaru > 0) break;
                System.out.println("Harga baru harus lebih besar dari 0!");
            } catch (Exception e) {
                System.out.println("Input harga harus berupa angka!");
            }
        }

        System.out.print("Apakah Anda yakin ingin mengubah harga? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();

        if (confrimasiSesuai(konfirmasi)) {
            hargaMenu.set(nomorMenu, hargaBaru);
            System.out.println("Harga menu berhasil diubah!");
        } else {
            System.out.println("Perubahan harga dibatalkan.");
        }
    }

    public static void hapusMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Daftar menu kosong!");
            return;
        }

        cetakDaftarMenu();
        int nomorMenu = ambilNomorMenuValid();

        System.out.println("Menu yang dipilih untuk dihapus: " + daftarMenu.get(nomorMenu));
        System.out.print("Apakah Anda yakin ingin menghapus menu ini? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();

        if (confrimasiSesuai(konfirmasi)) {
            daftarMenu.remove(nomorMenu);
            hargaMenu.remove(nomorMenu);
            tipeMinuman.remove(nomorMenu);
            System.out.println("Menu berhasil dihapus dari sistem!");
        } else {
            System.out.println("Penghapusan menu dibatalkan.");
        }
    }

    public static int ambilNomorMenuValid() {
        int nomor = 0;
        while (true) {
            System.out.print("Masukkan Nomor Menu: ");
            try {
                nomor = Integer.parseInt(input.nextLine());
                if (nomor >= 1 && nomor <= daftarMenu.size()) {
                    return nomor - 1; 
                }
                System.out.println("Nomor tidak ada dalam daftar menu!");
            } catch (Exception e) {
                System.out.println("Input harus berupa nomor angka!");
            }
        }
    }

    //cek konfirmasi Ya/Tidak
    public static boolean confrimasiSesuai(String konf) {
        return konf.equalsIgnoreCase("Ya") || konf.equalsIgnoreCase("Y");
    }
}
