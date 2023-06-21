package logique;

public class DSortieStock {
    private int annee;
    private int num;
    private int artn;
    private String article;
    private String desig;
    private String unite;
    private float qte;
    private float prix;
    
    public DSortieStock() {
    }
    
    public DSortieStock(int annee, int num, int artn, String article, String desig, String unite, float qte, float prix) {
        this.annee = annee;
        this.num = num;
        this.artn = artn;
        this.article = article;
        this.desig = desig;
        this.unite = unite;
        this.qte = qte;
        this.prix = prix;
    }
    
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
    
    public int getArtn() {
        return artn;
    }
    
    public void setArtn(int artn) {
        this.artn = artn;
    }
    
    public String getArticle() {
        return article;
    }
    
    public void setArticle(String article) {
        this.article = article;
    }
    
    public String getDesig() {
        return desig;
    }
    
    public void setDesig(String desig) {
        this.desig = desig;
    }
    
    public String getUnite() {
        return unite;
    }
    
    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    public float getQte() {
        return qte;
    }
    
    public void setQte(float qte) {
        this.qte = qte;
    }
    
    public float getPrix() {
        return prix;
    }
    
    public void setPrix(float prix) {
        this.prix = prix;
    }
}

