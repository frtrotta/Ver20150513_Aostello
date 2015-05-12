package test;

import java.time.LocalDateTime;

import gestioneostello.*;

public class Test {

	public static void main(String[] args) throws StanzaNotDisponibileException {
		System.out.println("Test Gestione Ostello\n");
		Ostello massiano = new Ostello("Pian di Massiano Perugia");
		
		Stanza quattro = null;
		
		// -------------------------------------------------------------
		System.out.print("Test01a..");
		try {
			quattro = new StanzaPiccola("127");
		}
		catch (IllegalArgumentException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test01b..");
		
		StanzaPiccola.setCostoGiornalieroStandard(10);
		quattro = new StanzaPiccola("127");
		
		System.out.println("ok");
		
		massiano.addStanza(quattro);
		
		
		Cliente coccia = new Cliente("Rita", "Coccia", "1234567");
		
		Prenotazione p =  null;
		
		// -------------------------------------------------------------
		System.out.print("Test02a..");
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 13, 00, 00),
				2);
		
		quattro.addPrenotazione(p);
		
		if(quattro.getPrenotazioni().size() == 1)
			System.out.println("ok");
		else
			System.out.println("ERROR");
		
		// -------------------------------------------------------------
		System.out.print("Test02b..");
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 14, 00, 00),
				2);
		
		quattro.addPrenotazione(p);
		
		if(quattro.getPrenotazioni().size() == 2)
			System.out.println("ok");
		else
			System.out.println("ERROR");
		
		// -------------------------------------------------------------
		System.out.print("Test02c..");
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 14, 00, 00),
				2);
		try {
			quattro.addPrenotazione(p);
			System.out.println("ERROR");
		}
		catch(StanzaNotDisponibileException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test03a..");
		try {
			p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 11, 00, 00),
				2);
			System.out.println("ERROR");
		}
		catch(IllegalArgumentException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test03b..");
		try {
			p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 15, 00, 00),
				0);
			System.out.println("ERROR");
		}
		catch(IllegalArgumentException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test04...");
		if(coccia.getPrenotazioni().size() == 2)
			System.out.println("ok");
		else
			System.out.println("ERROR");
		
		// -------------------------------------------------------------
		System.out.print("Test05...");
		StanzaGrande.setCostoGiornalieroStandard(37.8);
		StanzaGrande otto = new StanzaGrande("250");
		massiano.addStanza(otto);
		
		/* Una prenotazioni con i medesimi dati è già stata aggiunta con successo
		 * per la stanza quattro. Ci attendiamo, quindi, che l'unica stanza disponibile
		 * risulti otto.
		 */
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 14, 00, 00),
				2);
		
		if(massiano.getStanzeDisponibili(p).size() == 1) {
			if(massiano.getStanzeDisponibili(p).get(0) == otto)
				System.out.println("ok");
			else
				System.out.println("ERROR");
		}
		else {
			System.out.println("ERROR");
		}
		
	}

}
