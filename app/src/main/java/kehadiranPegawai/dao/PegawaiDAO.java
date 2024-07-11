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
import kehadiranPegawai.entities.Pegawai;


public class PegawaiDAO {
    private Connection conn;

    public PegawaiDAO(Connection conn) {
        this.conn = conn;
    }

    public void tambahPegawai(Pegawai pegawai) {
        String sql = "INSERT INTO Pegawai(nama, jenisKelamin, alamat) VALUES(?,?,?)"; 
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pegawai.getNama());
            stmt.setString(2, pegawai.getJenisKelamin()); 
            stmt.setString(3, pegawai.getAlamat()); 
            stmt.executeUpdate();
            System.out.println("Pegawai berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan pegawai: " + e.getMessage());
        }
    }

    public void editPegawai(Pegawai pegawai) {
        String sql = "UPDATE Pegawai SET nama = ?, jenisKelamin = ?, alamat = ? WHERE idPegawai = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pegawai.getNama());
            stmt.setString(2, pegawai.getJenisKelamin()); 
            stmt.setString(3, pegawai.getAlamat()); 
            stmt.setInt(4, pegawai.getIdPegawai());
            stmt.executeUpdate();
            System.out.println("Data pegawai berhasil diupdate.");
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate data pegawai: " + e.getMessage());
        }
    }

    public void hapusPegawai(int idPegawai) {
        String sql = "DELETE FROM Pegawai WHERE idPegawai = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPegawai);
            stmt.executeUpdate();
            System.out.println("Data pegawai berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data pegawai: " + e.getMessage());
        }
    }
    
    public List<Pegawai> cariPegawai(String keyword) {
        List<Pegawai> hasilCari = new ArrayList<>();
        String sql = "SELECT * FROM Pegawai WHERE idPegawai LIKE ? OR nama LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pegawai pegawai = new Pegawai(
                            rs.getInt("idPegawai"),
                            rs.getString("nama"),
                            rs.getString("jenisKelamin"),
                            rs.getString("alamat")
                    );
                    hasilCari.add(pegawai);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari pegawai: " + e.getMessage());
        }
        return hasilCari;
    }

    public Pegawai getPegawaiById(int idPegawai) {
        String sql = "SELECT * FROM Pegawai WHERE idPegawai = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPegawai);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pegawai(
                            rs.getInt("idPegawai"), 
                            rs.getString("nama"),
                            rs.getString("jenisKelamin"), 
                            rs.getString("alamat") 
                    );
                }
            } 
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pegawai: " + e.getMessage());
        }
        return null; // Pegawai tidak ditemukan
    }

    public List<Pegawai> getAllPegawai() {
        List<Pegawai> daftarPegawai = new ArrayList<>();
        String sql = "SELECT * FROM Pegawai";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pegawai pegawai = new Pegawai(
                        rs.getInt("idPegawai"),
                        rs.getString("nama"),
                        rs.getString("jenisKelamin"), 
                        rs.getString("alamat") 
                );
                daftarPegawai.add(pegawai);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pegawai: " + e.getMessage());
        }
        return daftarPegawai;
    }
}
