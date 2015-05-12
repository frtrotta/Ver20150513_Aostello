package gestioneostello;

import java.time.*;

public class Prenotazione {
	private Stanza stanza;
	private Cliente cliente;
	private LocalDateTime dal;
	private LocalDateTime al;
	private int postiRichiesti;
	
	public Prenotazione(Cliente cliente, LocalDateTime dal, LocalDateTime al, int postiRichiesti) {
		if(cliente != null)
			this.cliente = cliente;
		else
			throw new IllegalArgumentException("cliente cannot be null");
		
		if(dal != null)
			this.dal = dal;
		else
				throw new IllegalArgumentException("dal cannot be null");
		
		if(al != null) {
			if(al.isAfter(dal))
				this.al = al;
			else
				throw new IllegalArgumentException("al must be after dal");
		}
		else
			throw new IllegalArgumentException("al cannot be null");
		
		if(postiRichiesti > 0)
			this.postiRichiesti = postiRichiesti;
		else
				throw new IllegalArgumentException("posti richiesti must bu positive");
		
	}

	public Stanza getStanza() {
		return stanza;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDateTime getDal() {
		return dal;
	}

	public LocalDateTime getAl() {
		return al;
	}
	
	public int getPostiRichiesti() {
		return postiRichiesti;
	}
	
	public int getNumeroGiorni() {
		return Period.between(dal.toLocalDate(), al.toLocalDate()).getDays();
	}
	
	public double getRicavoTotale() {
		double r = 0;
		if(stanza != null) {
			r = getNumeroGiorni() * stanza.getCostoGiornaliero();
		}
		return r;
	}
	
	/***
	 * Conferma la prenotazione per la stanza.
	 * Registra la prenotazione tra quelle del cliente.
	 * @param s
	 */
	public void conferma(Stanza s) {
		if(s != null)
			this.stanza = s;
		else
			throw new IllegalArgumentException("stanza cannot be null");
		cliente.addPrenotazione(this);
	}

	@Override
	public String toString() {
		return "Prenotazione [stanza=" + stanza + ", cliente=" + cliente
				+ ", dal=" + dal + ", al=" + al + "]";
	}
}
