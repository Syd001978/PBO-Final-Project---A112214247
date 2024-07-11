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

public class Absensi {
    private int idAbsensi;
    private int idPegawai;
    private Date tanggal;
    private String alasan;

    // Constructor
    public Absensi(int idAbsensi, int idPegawai, Date tanggal, String alasan) {
        this.idAbsensi = idAbsensi;
        this.idPegawai = idPegawai;
        this.tanggal = tanggal;
        this.alasan = alasan;
    }

    // Getters and Setters
    public int getIdAbsensi() {
        return idAbsensi;
    }

    public void setIdAbsensi(int idAbsensi) {
        this.idAbsensi = idAbsensi;
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


    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }
}
