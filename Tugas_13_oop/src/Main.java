/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Aplikasi Perpustakaan ===");
            System.out.println("1. Tampilkan Data Buku");
            System.out.println("2. Tambah Data Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    tampilkanDataBuku();
                    break;
                case 2:
                    tambahDataBuku(scanner);
                    break;
                case 0:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void tampilkanDataBuku() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM buku";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n=== Daftar Buku ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Judul: " + rs.getString("judul"));
                System.out.println("Penulis: " + rs.getString("penulis"));
                System.out.println("Penerbit: " + rs.getString("penerbit"));
                System.out.println("Tahun Terbit: " + rs.getInt("tahun_terbit"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error saat menampilkan data buku: " + e.getMessage());
        }
    }

    private static void tambahDataBuku(Scanner scanner) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.print("Judul: ");
            String judul = scanner.nextLine();
            System.out.print("Penulis: ");
            String penulis = scanner.nextLine();
            System.out.print("Penerbit: ");
            String penerbit = scanner.nextLine();
            System.out.print("Tahun Terbit: ");
            int tahun = scanner.nextInt();
            scanner.nextLine(); // buang newline

            String query = "INSERT INTO buku (judul, penulis, penerbit, tahun_terbit) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, judul);
            pstmt.setString(2, penulis);
            pstmt.setString(3, penerbit);
            pstmt.setInt(4, tahun);
            pstmt.executeUpdate();

            System.out.println("Data buku berhasil ditambahkan!");
        } catch (SQLException e) {
            System.err.println("Error saat menambahkan buku: " + e.getMessage());
        }
    }
    
}
