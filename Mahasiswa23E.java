
import java.util.Scanner;

public class Mahasiswa23E {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mahasiswa dao = new Mahasiswa(); // DAO untuk DB
        int pilihan = 0;

        do {
            System.out.println("\n=== MENU OPERASI DATABASE MAHASISWA ===");
            System.out.println("1. Tampilkan Data Mahasiswa");
            System.out.println("2. Input Data Mahasiswa");
            System.out.println("3. Cari Data Mahasiswa");
            System.out.println("4. Ubah Data Mahasiswa");
            System.out.println("5. Hapus Data Mahasiswa");
            System.out.println("6. Exit");
            System.out.print("Pilih menu [1-6]: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // konsumsi newline

            switch (pilihan) {
                case 1:
                    System.out.println("\n== Data Mahasiswa ==");
                    dao.readMahasiswa();
                    break;
                case 2:
                    System.out.println("\n== Input Data Baru ==");
                    System.out.print("Masukkan NIM: ");
                    String NIM = scanner.nextLine();
                    if (dao.findMahasiswaByNim(NIM)) {
                        System.out.println("NIM sudah dipakai.");
                        break;
                    }
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Alamat: ");
                    String alamat = scanner.nextLine();
                    System.out.print("Masukkan Gender (L/P): ");
                    String gender = scanner.nextLine().toUpperCase();
                    dao.insertMahasiswa(NIM, nama, alamat, gender);
                    break;
                case 3:
                    System.out.println("\n== Cari Data Mahasiswa ==");
                    System.out.print("Masukkan NIM: ");
                    NIM = scanner.nextLine();
                    if (dao.findMahasiswaByNim(NIM)) {
                        System.out.println("Mahasiswa dengan NIM " + NIM + " ditemukan.");
                    } else {
                        System.out.println("Mahasiswa tidak ditemukan.");
                    }
                    break;
                case 4:
                    System.out.println("\n== Ubah Data Mahasiswa ==");
                    System.out.print("Masukkan NIM: ");
                    NIM = scanner.nextLine();
                    if (!dao.findMahasiswaByNim(NIM)) {
                        System.out.println("NIM tidak ditemukan.");
                        break;
                    }
                    System.out.print("Nama Baru: ");
                    nama = scanner.nextLine();
                    System.out.print("Alamat Baru: ");
                    alamat = scanner.nextLine();
                    System.out.print("Gender Baru (L/P): ");
                    gender = scanner.nextLine().toUpperCase();
                    dao.updateMahasiswa(nama, alamat, gender, NIM);
                    break;
                case 5:
                    System.out.println("\n== Hapus Data Mahasiswa ==");
                    System.out.print("Masukkan NIM: ");
                    NIM = scanner.nextLine();
                    if (!dao.findMahasiswaByNim(NIM)) {
                        System.out.println("NIM tidak ditemukan.");
                        break;
                    }
                    dao.deleteMahasiswa(NIM);
                    break;
                case 6:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 6);

        scanner.close();
    }   
}  
