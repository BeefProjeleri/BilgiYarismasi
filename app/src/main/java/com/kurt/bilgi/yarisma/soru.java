package com.kurt.bilgi.yarisma;

/**
 * Created by User on 16.10.2017.
 */

public class soru {
    String soru,dogru,yanlis1,yanlis2,yanlis3;

    public soru(String soru, String dogru, String yanlis1, String yanlis2, String yanlis3) {
        this.soru = soru;
        this.dogru = dogru;
        this.yanlis1 = yanlis1;
        this.yanlis2 = yanlis2;
        this.yanlis3 = yanlis3;
    }
    public soru(){

    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getDogru() {
        return dogru;
    }

    public void setDogru(String dogru) {
        this.dogru = dogru;
    }

    public String getYanlis1() {
        return yanlis1;
    }

    public void setYanlis1(String yanlis1) {
        this.yanlis1 = yanlis1;
    }

    public String getYanlis2() {
        return yanlis2;
    }

    public void setYanlis2(String yanlis2) {
        this.yanlis2 = yanlis2;
    }

    public String getYanlis3() {
        return yanlis3;
    }

    public void setYanlis3(String yanlis3) {
        this.yanlis3 = yanlis3;
    }
}
