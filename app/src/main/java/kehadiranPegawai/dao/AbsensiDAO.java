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
import kehadiranPegawai.entities.Absensi;

public class AbsensiDAO {
    private Connection conn;

    public AbsensiDAO(Connection conn) {
        this.conn = conn;
    }

    public void tambahAbsensi(Absensi absensi) {
        String sql = "INSERT INTO Absensi (idPegawai, tanggal, alasan) VALUES (?, ?, ?)"; 
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, absensi.getIdPegawai());
            stmt.setDate(2, absensi.getTanggal());
            stmt.setString(3, absensi.getAlasan()); 
            stmt.executeUpdate();
            System.out.println("Absensi berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan absensi: " + e.getMessage());
        }
    }

    public Absensi getAbsensiById(int idAbsensi) {
        String sql = "SELECT * FROM Absensi WHERE idAbsensi = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAbsensi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Absensi(
                            rs.getInt("idAbsensi"),
                            rs.getInt("idPegawai"),
                            rs.getDate("tanggal"),
                            rs.getString("alasan") 
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data absensi: " + e.getMessage());
        }
        return null; 
    }

    public List<Absensi> getAbsensiByPegawai(int idPegawai, Date tanggalAwal, Date tanggalAkhir) {
        List<Absensi> daftarAbsensi = new ArrayList<>();
        String sql = "SELECT * FROM Absensi WHERE idPegawai = ? AND tanggal BETWEEN ? AND ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPegawai);
            stmt.setDate(2, tanggalAwal);
            stmt.setDate(3, tanggalAkhir);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Absensi absensi = new Absensi(
                            rs.getInt("idAbsensi"),
                            rs.getInt("idPegawai"),
                            rs.getDate("tanggal"),
                            rs.getString("alasan") 
                    );
                    daftarAbsensi.add(absensi);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data absensi: " + e.getMessage());
        }
        return daftarAbsensi;
    }

    public List<Absensi> getAllAbsensi() {
        List<Absensi> daftarAbsensi = new ArrayList<>();
        String sql = "SELECT * FROM Absensi";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Absensi absensi = new Absensi(
                        rs.getInt("idAbsensi"),
                        rs.getInt("idPegawai"),
                        rs.getDate("tanggal"),
                        rs.getString("alasan") 
                );
                daftarAbsensi.add(absensi);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data absensi: " + e.getMessage());
        }
        return daftarAbsensi;
    }
}
