package gestioneostello;

import java.util.*;

public class Ostello {
	private String nome;
	private Vector<Stanza> stanze;
	
	public Ostello(String nome) {
		if(nome != null)
			this.nome = nome;
		else
			throw new IllegalArgumentException("nome cannot be null");
		
		this.stanze = new Vector<Stanza>();
	}

	public String getNome() {
		return nome;
	}

	public Vector<Stanza> getStanze() {
		return stanze;
	}
	
	public void addStanza(Stanza c) {
		stanze.add(c);
	}
	
	public Vector<Stanza> getStanzeDisponibili(Prenotazione p) {
		Vector<Stanza> r = new Vector<Stanza>();
		for(Stanza c : stanze) {
			if(c.isDisponibile(p))
				r.add(c);
		}
		return r;
	}

	@Override
	public String toString() {
		return "Ostello [nome=" + nome + ", numeroStanze=" + stanze.size() + "]";
	}
}
