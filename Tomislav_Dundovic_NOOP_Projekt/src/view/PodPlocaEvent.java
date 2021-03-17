package view;
//Imports
import java.util.EventObject;

import model.Citatelj;
import model.OdabirGodineEnum;
import model.OdabirStudijaEnum;
import model.sveucilistaEnum;
import model.xicaEnum;


/**
 * 
 * Klasa PodPlocaEvent koja extenda EventObject te ih handle-a
 * Getters
 * @author tomislav
 * @since ozujak, 2021.
 *
 */
public class PodPlocaEvent extends EventObject {
	
	private int id;
	private String ime;
	private String prezime;
	private OdabirStudijaEnum odabir;
	private OdabirGodineEnum posudb;
	private String VrstaStudenta;
	private xicaEnum xica;
	//private String spolStud;
	private String datumRod;
	private sveucilistaEnum sveUci;
	private String bavljSport;

	public PodPlocaEvent(Object source, Citatelj citat) {
		super(source);
		
		
		id = citat.getId();
		ime = citat.getIme();
		prezime = citat.getPrezime();
		odabir = citat.getOdabir();
		posudb = citat.getPosudb();
		VrstaStudenta = citat.getVrstaStudenta();
		xica = citat.getXicaEnum();
		//spolStud = citat.getSpolStud();
		datumRod = citat.getDatumRod();
		sveUci = citat.getSveUci();
		bavljSport = citat.getBavljSport();
		
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

	public OdabirStudijaEnum getOdabir() {
		return odabir;
	}

	public OdabirGodineEnum getPosudb() {
		return posudb;
	}

	public String getVrstaStudenta() {
		return VrstaStudenta;
	}
	
	public xicaEnum getXicaEnum() {
		return xica;
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
	
	public String getBavljSport() {
		return bavljSport;
		
	}


	

}
