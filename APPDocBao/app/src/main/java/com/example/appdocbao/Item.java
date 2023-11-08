package com.example.appdocbao;

public class Item {
    private String tieude;
    private String ngaydang;

    private String mota;

    public Item(String tieude, String ngaydang, String mota) {
        this.tieude = tieude;
        this.ngaydang = ngaydang;
        this.mota = mota;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
