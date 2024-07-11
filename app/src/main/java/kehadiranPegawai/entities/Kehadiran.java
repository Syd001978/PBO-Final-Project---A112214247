/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kehadiranPegawai.entities;

/**
 *
 * @author ASUS
 */
import java.sql.Date;
import java.sql.Time;

public class Kehadiran {
    private int idKehadiran;
    private int idPegawai;
    private Date tanggal;
    private Time jamMasuk;
    private Time jamKeluar;
    private double durasiKerja;

    // Constructor
    public Kehadiran(int idKehadiran, int idPegawai, Date tanggal, Time jamMasuk, Time jamKeluar, double durasiKerja) {
        this.idKehadiran = idKehadiran;
        this.idPegawai = idPegawai;
        this.tanggal = tanggal;
        this.jamMasuk = jamMasuk;
        this.jamKeluar = jamKeluar;
        this.durasiKerja = durasiKerja;
    }

    // Getters and Setters
    public int getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(int idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    public int getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(int idPegawai) {
        this.idPegawai = idPegawai;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Time getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(Time jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public Time getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(Time jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public double getDurasiKerja() {
        return durasiKerja;
    }

    public void setDurasiKerja(double durasiKerja) {
        this.durasiKerja = durasiKerja;
    }
}
