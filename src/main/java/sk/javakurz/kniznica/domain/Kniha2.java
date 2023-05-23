package sk.javakurz.kniznica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "kniha2")
public class Kniha2 {
	
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String nazov;
	@Column
	private String autor;
	@Column
	private int pocetKusov;
	
	protected Kniha2() {
	}
	
	public Kniha2(String nazov, String autor) {
		this.nazov = nazov;
		this.autor = autor;
	}
	
	public String getNazov() {
		return nazov;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setPocetKusov(int pocetKusov) {
		this.pocetKusov = pocetKusov;
	}
	
	public int getPocetKusov() {
		return pocetKusov;
	}
	
	public long getId() {
		return id;
	}
	
}
