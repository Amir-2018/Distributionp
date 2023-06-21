package logique;

public class UserDepot {
private String nom ; 
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getLieu() {
	return lieu;
}	
public void setLieu(String lieu) {
	this.lieu = lieu;
}
private String lieu ;
public UserDepot() {

}
public UserDepot(String nom, String lieu) {
	super();
	this.nom = nom;
	this.lieu = lieu;
}

 
}
