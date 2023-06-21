package logique;

public class Lieu {
	private String code ; 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	private String desig ; 
	
	
	public Lieu(String code, String desig) {
		super();
		this.code = code;
		this.desig = desig;
	}
	
}
