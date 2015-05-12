package gestioneostello;

import java.util.*;

public class Cliente {
	private String nome;
	private String cognome;
	private String telefono;
	private Vector<Prenotazione> prenotazioni;
	
	public Cliente(String nome, String cognome, String telefono) {
		if(nome != null)
			this.nome = nome;
		else
			throw new IllegalArgumentException("nome cannot be null");
		
		if(cognome != null)
			this.cognome = cognome;
		else
				throw new IllegalArgumentException("cognome cannot be null");
		
		if(telefono != null)
			this.telefono = telefono;
		else
			throw new IllegalArgumentException("telefono cannot be null");
		
		this.prenotazioni = new Vector<Prenotazione>();
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public Vector<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	
	public void addPrenotazione(Prenotazione p) {
		prenotazioni.addElement(p);
	}
	
	public int getNumeroTotaleGiorni() {
		int r=0;
		for (Prenotazione p : prenotazioni) {
			r += p.getNumeroGiorni();
		}
		return r;
	}
	
	public double getRicavoTotalePrenotazioni() {
		double r=0;
		for (Prenotazione p : prenotazioni) {
			r += p.getRicavoTotale();
		}
		return r;
	}
}
