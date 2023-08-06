package com.example.damii_exo_lucianobacab;

public class DatosTabla {
    public int ivProduc;
    public String tvNombre;
    public String tvPreci;
    public String tvCant;

    public DatosTabla(int ivProduc, String tvNombre, String tvPreci, String tvCant) {
        this.ivProduc = ivProduc;
        this.tvNombre = tvNombre;
        this.tvPreci = tvPreci;
        this.tvCant = tvCant;
    }


    public int getIvProduc() {
        return ivProduc;
    }

    public void setIvProduc(int ivProduc) {
        this.ivProduc = ivProduc;
    }

    public String getTvNombre() {
        return tvNombre;
    }

    public void setTvNombre(String tvNombre) {
        this.tvNombre = tvNombre;
    }

    public String getTvPreci() {
        return tvPreci;
    }

    public void setTvPreci(String tvPreci) {
        this.tvPreci = tvPreci;
    }

    public String getTvCant() {
        return tvCant;
    }

    public void setTvCant(String tvCant) {
        this.tvCant = tvCant;
    }
}
