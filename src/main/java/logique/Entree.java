package logique;

public class Entree {
	public Entree(String depot, String sortie, String entree) {
		super();
		this.depot = depot;
		this.sortie = sortie;
		this.entree = entree;
	}
	public String getDepot() {
		return depot;
	}
	public void setDepot(String depot) {
		this.depot = depot;
	}
	public String getSortie() {
		return sortie;
	}
	public void setSortie(String sortie) {
		this.sortie = sortie;
	}
	public String getEntree() {
		return entree;
	}
	public void setEntree(String entree) {
		this.entree = entree;
	}
	private String depot ; 
	private String sortie ; 
	private String entree ; 
}
