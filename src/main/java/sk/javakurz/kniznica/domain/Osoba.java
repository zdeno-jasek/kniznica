package sk.javakurz.kniznica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/*
 * Trieda Osoba je ukladaná do databázovej tabuľky "person" v SQL databáze PostgreSQL.
 * Riadky, ktoré začínajú zavináčom (@) sa volajú "anotácie".
 * Pomocou anotácií je možné namapovať triedu Osoba do databázovej tabuľky Person.
 * Anotácie sú uložené v knižnici "jakarta.persistence".
 * Takémuto mapovaniu sa hovorí aj "JPA" = Java Persistence API (Application Programming Interface).
 */
@Entity(name="person")
public class Osoba {

	/* Atribút je umelý kľúč v databázovej tabuľke, ktorý presne identifikuje túto osobu. */
	@Id
	@GeneratedValue
	private long id;
	
	/* Nasledujúce atribúty sú mapované do obyčajných stĺpcov v databáze (column). */
	@Column
	private String meno;
	@Column
	private String priezvisko;
	@Column
	private String rodneCislo;
	
	/* Tento prázdny konštruktor je zbytočný, ale vyžaduje si ho mapovací nástroj. */
	protected Osoba() {
	}
	
	public Osoba( String meno, String priezvisko, String rodneCislo ) {
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.rodneCislo = rodneCislo;
	}

	public String getMeno() {
		return meno;
	}

	public String getPriezvisko() {
		return priezvisko;
	}

	public String getRodneCislo() {
		return rodneCislo;
	}

	public long getId() {
		return id;
	}
	
}
