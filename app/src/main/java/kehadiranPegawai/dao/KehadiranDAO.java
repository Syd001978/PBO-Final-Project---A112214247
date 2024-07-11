/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kehadiranPegawai.dao;

/**
 *
 * @author ASUS
 */ 
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import kehadiranPegawai.entities.Kehadiran;

public class KehadiranDAO {
    private Connection conn;

    public KehadiranDAO(Connection conn) {
        this.conn = conn;
    }

    public void catatKehadiran(Kehadiran kehadiran) {
        String sql = "INSERT INTO Kehadiran (idPegawai, tanggal, jamMasuk, jamKeluar, durasiKerja) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, kehadiran.getIdPegawai());
            stmt.setDate(2, kehadiran.getTanggal());
            stmt.setTime(3, kehadiran.getJamMasuk());
            stmt.setTime(4, kehadiran.getJamKeluar());
            stmt.setDouble(5, kehadiran.getDurasiKerja());
            stmt.executeUpdate();
            System.out.println("Kehadiran berhasil dicatat.");
        } catch (SQLException e) {
            System.out.println("Gagal mencatat kehadiran: " + e.getMessage());
        }
    }

    public Kehadiran getKehadiranById(int idKehadiran) {
        String sql = "SELECT * FROM Kehadiran WHERE idKehadiran = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idKehadiran);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Kehadiran(
                            rs.getInt("idKehadiran"),
                            rs.getInt("idPegawai"),
                            rs.getDate("tanggal"),
                            rs.getTime("jamMasuk"),
                            rs.getTime("jamKeluar"),
                            rs.getDouble("durasiKerja")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data kehadiran: " + e.getMessage());
        }
        return null; 
    }

    public List<Kehadiran> getKehadiranByPegawai(int idPegawai, Date tanggalAwal, Date tanggalAkhir) {
        List<Kehadiran> daftarKehadiran = new ArrayList<>();
        String sql = "SELECT * FROM Kehadiran WHERE idPegawai = ? AND tanggal BETWEEN ? AND ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPegawai);
            stmt.setDate(2, tanggalAwal);
            stmt.setDate(3, tanggalAkhir);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Kehadiran kehadiran = new Kehadiran(
                            rs.getInt("idKehadiran"),
                            rs.getInt("idPegawai"),
                            rs.getDate("tanggal"),
                            rs.getTime("jamMasuk"),
                            rs.getTime("jamKeluar"),
                            rs.getDouble("durasiKerja")
                    );
                    daftarKehadiran.add(kehadiran);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data kehadiran: " + e.getMessage());
        }
        return daftarKehadiran;
    }
    
    public List<Kehadiran> getAllKehadiran() {
     List<Kehadiran> daftarKehadiran = new ArrayList<>();
     String sql = "SELECT * FROM Kehadiran";

     try (Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(sql)) {
         while (rs.next()) {
             Kehadiran kehadiran = new Kehadiran(
                     rs.getInt("idKehadiran"),
                     rs.getInt("idPegawai"),
                     rs.getDate("tanggal"),
                     rs.getTime("jamMasuk"),
                     rs.getTime("jamKeluar"),
                     rs.getDouble("durasiKerja")
             );
             daftarKehadiran.add(kehadiran);
         }
     } catch (SQLException e) {
         System.out.println("Gagal mengambil data kehadiran: " + e.getMessage());
     }
     return daftarKehadiran;
    }
}
