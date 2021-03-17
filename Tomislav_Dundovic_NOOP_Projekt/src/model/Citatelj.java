package model;
//Imports
import java.io.Serializable;

/**
 * Klasa Citatelj, implemnts Seriaziable
 * Getters i sysout
 * @author tomislav
 * @since ozujak, 2021.
 *
 */
public class Citatelj implements Serializable  {
	
	
	
	private static int cnt = 1;
	private int id;
	private String ime;
	private String prezime;	
	private String datumRod;
	private sveucilistaEnum sveUci;
	private OdabirStudijaEnum odabir;
	private String vrstaStudenta;
	private OdabirGodineEnum posudb;
	private xicaEnum xica;
	private String bavljSport;
	//private String spolStud;


	// Konstruktor klase
	public Citatelj () {
		// TODO Auto-generated constructor stub
		
	}
	
	public Citatelj(String ime, String prezime, String datumRod, sveucilistaEnum sveUci, OdabirStudijaEnum odabir, String vrstaStudenta,
			OdabirGodineEnum posudb, xicaEnum xica, String bavljSport ) {
		
		//String spolStud;

		this.id = cnt;
		this.ime = ime;
		this.prezime = prezime;
		//this.spolStud = spolStud;
		this.datumRod = datumRod;
		this.sveUci = sveUci;
		this.odabir = odabir;
		this.vrstaStudenta = vrstaStudenta;
		this.posudb = posudb;
		this.xica = xica;
		this.bavljSport = bavljSport;
		cnt++;
	}
	
	public Citatelj(int id, String ime, String prezime, String datumRod, sveucilistaEnum sveUci, OdabirStudijaEnum odabir, String vrstaStudenta,
			OdabirGodineEnum posudb, xicaEnum xica, String bavljSport) {
		
		
		//String spolStud;

		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		//	this.spolStud = spolStud;
		this.datumRod = datumRod;
		this.sveUci = sveUci;
		this.odabir = odabir;
		this.vrstaStudenta = vrstaStudenta;
		this.posudb = posudb;
		this.xica = xica;
		this.bavljSport = bavljSport;
		cnt = id+1;
	}

	
	
	
	public int getId() {
		return id;
	}


	public String getIme() {
		return ime;
	}

	
	public String getPrezime() {
		return prezime;
	}


	
	/*
	 * public String getSpolStud() {
	 * return spolStud;
	 * }
	 */
	
	
	public String getDatumRod() {
		return datumRod;
	}
	
	public sveucilistaEnum getSveUci() {
		return sveUci;
					
	}
	

	public OdabirStudijaEnum getOdabir() {
		return odabir;
	}
	

	public String getVrstaStudenta() {
		return vrstaStudenta;
	}
	

	public OdabirGodineEnum getPosudb() {
		return posudb;
	}


	public xicaEnum getXicaEnum() {
		return xica;
	}
	
	public String getBavljSport() {
		return bavljSport;
		
	}

	
	//sysout sve elemente na ploci toString
	
	@Override
	public String toString() {
		return "Citatelj [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", datumRodenja=" + datumRod
				+ ", odabirFakulteta=" + sveUci + ", odabirStudija=" + odabir + ", vrstaStudenta=" + vrstaStudenta + ", godinaStudija=" + posudb +
						 ", xica=" + xica + ", bavljenjeSportom=" + bavljSport + "]";
		
		 //odabirSpola=" + spolStud + ",
	}
	
	public void description() {
		System.out.println(toString());
	}

	public static void setCounter(int i) {
		cnt = i;
		
	}
}