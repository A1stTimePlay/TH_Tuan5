package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LichGiangDay {
    private int STT;
    private String TenGV;
    private String MaLop;
    private String TenMon;
    private int SiSo;
    private String BatDau;
    private String KetThuc;
    private int SoTiet;

    public LichGiangDay(int STT, String tenGV, String maLop, String tenMon, int siSo, String batDau, String ketThuc, int soTiet) {
        this.STT = STT;
        TenGV = tenGV;
        MaLop = maLop;
        TenMon = tenMon;
        SiSo = siSo;
        BatDau = batDau;
        KetThuc = ketThuc;
        SoTiet = soTiet;
    }

    public LichGiangDay() {
    }

    public LichGiangDay(ResultSet resultSet){
        try {
            STT = resultSet.getInt(1);
            TenGV = resultSet.getString(2);
            MaLop = resultSet.getString(3);
            TenMon = resultSet.getString(4);
            SiSo = resultSet.getInt(5);
            BatDau = resultSet.getString(6);
            KetThuc = resultSet.getString(7);
            SoTiet = resultSet.getInt(8);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String tenGV) {
        TenGV = tenGV;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public int getSiSo() {
        return SiSo;
    }

    public void setSiSo(int siSo) {
        SiSo = siSo;
    }

    public String getBatDau() {
        return BatDau;
    }

    public String getKetThuc() {
        return KetThuc;
    }

    public int getSoTiet() {
        return SoTiet;
    }

    public void setSoTiet(int soTiet) {
        SoTiet = soTiet;
    }
}
