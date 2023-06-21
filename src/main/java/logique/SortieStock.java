package logique;

import java.math.BigDecimal;
import java.sql.Date;

public class SortieStock {
    private int annee;
    private int num;
    private String unom;
    private Date datep;
    private String lieu;
    private String desig;
    private String motifs;
    private BigDecimal totalttc;
    private String valide;
    
    public SortieStock() {}
    // Constructor
    public SortieStock(int annee, int num, String unom, Date datep, String lieu, String desig,
                       String motifs, BigDecimal totalttc, String valide) {
        this.annee = annee;
        this.num = num;
        this.unom = unom;
        this.datep = datep;
        this.lieu = lieu;
        this.desig = desig;
        this.motifs = motifs;
        this.totalttc = totalttc;
        this.valide = valide;
    }

    // Getters and Setters

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUnom() {
        return unom;
    }

    public void setUnom(String unom) {
        this.unom = unom;
    }

    public Date getDatep() {
        return datep;
    }

    public void setDatep(Date datep) {
        this.datep = datep;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getMotifs() {
        return motifs;
    }

    public void setMotifs(String motifs) {
        this.motifs = motifs;
    }

    public BigDecimal getTotalttc() {
        return totalttc;
    }

    public void setTotalttc(BigDecimal totalttc) {
        this.totalttc = totalttc;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }
}
