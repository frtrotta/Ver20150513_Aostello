package gestioneostello;

import java.util.*;

public abstract class Stanza {
	private String numero;
	private double costoGiornaliero;
	private int postiLetto;
	private Vector<Prenotazione> prenotazioni;
	
	public Stanza(String numero, double costoGiornaliero, int postiLetto) {
		if (numero != null)
			this.numero = numero;
		else
			throw new IllegalArgumentException("numero cannot be null");
		
		if(costoGiornaliero > 0)
			this.costoGiornaliero = costoGiornaliero;
		else
			throw new IllegalArgumentException("costoGiornaliero must be positive");
		
		if(postiLetto > 0)
			this.postiLetto = postiLetto;
		else
			throw new IllegalArgumentException("postiLetto must be positive");
		
		this.prenotazioni = new Vector<Prenotazione>();
	}

	public String getNumero() {
		return numero;
	}

	public double getCostoGiornaliero() {
		return costoGiornaliero;
	}

	public int getPostiLetto() {
		return postiLetto;
	}

	public Vector<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	
	public void addPrenotazione(Prenotazione p) throws StanzaNotDisponibileException {
		if(isDisponibile(p)) {
			prenotazioni.add(p);
			p.conferma(this);
		}
		else
			throw new StanzaNotDisponibileException("Posti non disponibili");
		return;
	}
	
	public boolean isDisponibile(Prenotazione p) {
		/* La strategia di calcolo implementata in questo metodo non è perfetta,
		 * poichè sottostima la disponibilità. In taluni casi la disponibilità
		 * potrebbe essere garantita, ma il metodo restituisce falso. D'altra parte
		 * non avviene mai che il metodo risponda vero quando non ci sono posti disponibili.
		 * 
		 * La strategia è stata comunque adottata per due ragioni.
		 * 
		 * La prima ragione è che è decisamente più intuitiva rispetto ad un calcolo preciso.
		 * La seconda ragione è che sottostima la disponibilità: i guadagni potrebbero essere
		 * incrementati con un calcolo più preciso, d'altra parte la clientela non sarà mai
		 * insoddisfatta, poichè, se il metodo restituisce vero, la disponibilità è certa.
		 */
		Vector<Prenotazione> prenotazioniSovrapposte = new Vector<Prenotazione>();
		int postiDisponibili = this.postiLetto;
		
		for(Prenotazione q : prenotazioni) {
			if ( !p.getDal().isAfter(q.getAl()) && !p.getAl().isBefore(q.getDal()))
			{
				prenotazioniSovrapposte.addElement(q);
			}
		}
		
		for(Prenotazione q : prenotazioniSovrapposte) {
			postiDisponibili -= q.getPostiRichiesti();
		}
			
		return (postiDisponibili >= p.getPostiRichiesti());
	}

	@Override
	public String toString() {
		return "Camera [numero=" + numero + ", costoGiornaliero="
				+ costoGiornaliero + ", postiLetto=" + postiLetto
				+ ", numeroPrenotazioni=" + prenotazioni.size() + "]";
	}	
}
