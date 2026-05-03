import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("============ DAFTAR MENU ============");
        System.out.println("[MAKANAN]");
        System.out.println("Nasi Uduk    : 10000 | Ikan Gurame : 25000");
        System.out.println("Sayur Asem   : 15000 | Ayam Goreng : 20000");
        System.out.println("\n [MINUMAN]");
        System.out.println("Es Teh       : 3000  | Es jeruk    : 5000");
        System.out.println("Kopi         : 7000  | Air Mineral : 4000");
        System.out.println("=====================================");

        String p1 = "", p2 = "", p3 = "", p4 = "";
        int j1 = 0, j2 = 0, j3 = 0, j4 = 0;
        double h1 = 0, h2 = 0, h3 = 0, h4 = 0;

        System.out.println("\n--- Input Pesanan (Format: Nama Menu) ---");

        System.out.print("Pesanan 1 : "); p1 = input.nextLine();
        System.out.print("Jumlah    : "); j1 = input.nextInt(); input.nextLine();
        h1 = ambilHarga(p1);

        System.out.print("Tambah menu lain? (y/t): ");
        if (input.nextLine().equalsIgnoreCase("y")){
            System.out.print("Pesanan 2 : "); p2 = input.nextLine();
            System.out.print("Jumlah    : "); j2 = input.nextInt(); input.nextLine();
            h2 = ambilHarga(p2);

            System.out.print("Tambah menu lain? (y/t): ");
            if (input.nextLine().equalsIgnoreCase("y")){
                System.out.print("Pesanan 3 : "); p3 = input.nextLine();
                System.out.print("Jumlah    : "); j3 = input.nextInt(); input.nextLine();
                h3 = ambilHarga(p3);

                System.out.print("Tambah menu lain? (y/t): ");
                if (input.nextLine().equalsIgnoreCase("y")){
                    System.out.print("Pesanan 4 : "); p4 = input.nextLine();
                    System.out.print("Jumlah    : "); j4 = input.nextInt(); input.nextLine();
                    h4 = ambilHarga(p4);
                }
            }
        }
        double subtotal = (h1 * j1) + (h2 * j2) + (h3 * j3) + (h4 * j4);

        String itemGratis = "";
        if (subtotal > 50000) {
            if (isMinuman(p1)) itemGratis = p1;
            else if (isMinuman(p2)) itemGratis = p2;
            else if (isMinuman(p3)) itemGratis = p3;
            else if (isMinuman(p4)) itemGratis = p4;
        }

        double pajak = subtotal * 0.10;
        double pelayanan = 20000;
        double totalBiaya = subtotal + pajak + pelayanan;

        double diskon = 0;
        if (totalBiaya > 100000){
            diskon = totalBiaya * 0.10;
        }
        double totalAkhir = totalBiaya - diskon;

        System.out.println("\n======== STRUK PESANAN ========");
        if (h1 > 0) System.out.println(p1 + "x" + j1 + " \t: " + (int)(h1 * j1));
        if (h2 > 0) System.out.println(p2 + "x" + j2 + " \t: " + (int)(h2 * j2));
        if (h3 > 0) System.out.println(p3 + "x" + j3 + " \t: " + (int)(h3 * j3));
        if (h4 > 0) System.out.println(p4 + "x" + j4 + " \t: " + (int)(h4 * j4));

        if (!itemGratis.equals("")){
            System.out.println("FREE " + itemGratis + "x1 \t: 0 (Promo > 50rb)");
        }

        System.out.println("---------------------------------");
        System.out.println("Subtotal     : " + (int)subtotal);
        System.out.println("Pajak (10%)  : " + (int)pajak);
        System.out.println("Pelayanan    : " + (int)pelayanan);

        if (diskon > 0) {
            System.out.println("Diskon (10%)  : -" + (int)diskon);
        }

        System.out.println("------------------------------------");
        System.out.println("TOTAL AKHIR   : Rp " + (int)totalAkhir);
        System.out.println("====================================");

        input.close();
    }
    public static double ambilHarga(String nama) {
        if (nama.equalsIgnoreCase("Nasi Uduk")) return 10000;
        if (nama.equalsIgnoreCase("Ikan Gurame")) return 25000;
        if (nama.equalsIgnoreCase("Sayur Asem")) return 15000;
        if (nama.equalsIgnoreCase("Ayam Goreng")) return 20000;
        if (nama.equalsIgnoreCase("Es Teh")) return 3000;
        if (nama.equalsIgnoreCase("Es Jeruk")) return 5000;
        if (nama.equalsIgnoreCase("Kopi")) return 7000;
        if (nama.equalsIgnoreCase("Air Mineral")) return 4000;
        return 0;
    }

    public static boolean isMinuman(String nama) {
        return nama.equalsIgnoreCase("Es Teh") ||
               nama.equalsIgnoreCase("Es Jeruk") ||
               nama.equalsIgnoreCase("Kopi") ||
               nama.equalsIgnoreCase("Air Mineral");
    }

}
