package gestioneostello;

@SuppressWarnings("serial")
public class StanzaNotDisponibileException extends Exception {
	StanzaNotDisponibileException() {
		super();
	}
	
	StanzaNotDisponibileException(String message) {
		super(message);
	}
}
