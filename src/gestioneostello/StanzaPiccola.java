package gestioneostello;

public class StanzaPiccola extends Stanza {
	private static double costoGiornalieroStandard;
	
	public static void setCostoGiornalieroStandard(double c) {
		if(c > 0)
			costoGiornalieroStandard = c;
		else
			throw new IllegalArgumentException("c must be positive");
	}
	
	public static double getCostoGiornaleroStandard() {
		return costoGiornalieroStandard; 
	}
	
	public StanzaPiccola(String numero) {
		super(numero, costoGiornalieroStandard, 4);
	}

}
