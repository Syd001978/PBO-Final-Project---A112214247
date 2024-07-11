/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kehadiranPegawai.dbase;

/**
 *
 * @author ASUS
 */
import java.sql.*;

public class KoneksiDatabase {
    private String url;

    public KoneksiDatabase(String dbname) {
        this.url = "jdbc:sqlite:" + dbname; 
        buatDatabaseDanTabel();
    }

    private void buatDatabaseDanTabel() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Membuat tabel Pegawai
             String sqlPegawai = "CREATE TABLE IF NOT EXISTS Pegawai (" +
                              "idPegawai INTEGER PRIMARY KEY AUTOINCREMENT," +
                              "nama TEXT NOT NULL," +
                              "jenisKelamin TEXT," +  
                              "alamat TEXT" + 
                              ")"; 
            stmt.executeUpdate(sqlPegawai);

            // Membuat tabel Kehadiran
            String sqlKehadiran = "CREATE TABLE IF NOT EXISTS Kehadiran (" +
                                 "idKehadiran INTEGER PRIMARY KEY AUTOINCREMENT," +
                                 "idPegawai INTEGER," +
                                 "tanggal DATE," +
                                 "jamMasuk TIME," +
                                 "jamKeluar TIME," +
                                 "durasiKerja REAL," +
                                 "FOREIGN KEY (idPegawai) REFERENCES Pegawai(idPegawai)" + 
                                 ")";
            stmt.executeUpdate(sqlKehadiran);

            // Membuat tabel Absensi
            String sqlAbsensi = "CREATE TABLE IF NOT EXISTS Absensi (" +
                               "idAbsensi INTEGER PRIMARY KEY AUTOINCREMENT," +
                               "idPegawai INTEGER," +
                               "tanggal DATE," +
                               "alasan TEXT," +
                               "FOREIGN KEY (idPegawai) REFERENCES Pegawai(idPegawai)" +
                               ")";
            stmt.executeUpdate(sqlAbsensi);

            System.out.println("Database dan tabel berhasil dibuat.");

        } catch (SQLException e) {
            System.out.println("Error saat membuat database atau tabel: " + e.getMessage());
        }
    }

    public Connection getKoneksi() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Koneksi ke database berhasil.");
        } catch (SQLException e) {
            System.out.println("Gagal konek ke database: " + e.getMessage());
        }
        return conn;
    }
}
