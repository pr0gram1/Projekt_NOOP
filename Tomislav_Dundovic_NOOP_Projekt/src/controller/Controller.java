package controller;


// Imports

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.BazaPodataka;
import model.Citatelj;
import view.PlocaPres;


/**
 * 
 * Klasa Contoller
 * Unutar klase se nalazu metode u kojima se podaci studenata mogu manipulirati 
 * Dodavanje, importiranje, spremanje te ucitavanje podataka
 * @author tomislav
 * @since ozujak, 2021.
 *
 */

public class Controller {

	private BazaPodataka baza;
	
	
// Konstruktor klase	
	public Controller() {
		baza = new BazaPodataka();
	}
	
	public void dodajNovogCitateljNaBazu(Citatelj citatelj) {
		baza.setCitateljNaBazu(citatelj);
	}
	
	public List<Citatelj> getSveCitatelje(){
		return baza.getSveZaBazu();
	}
	
	public void prikaziNaPloPres(Citatelj citat, PlocaPres ploPres) {
		ploPres.prikaziNaPlocePres(citat);
	}
	
	public void setPodZaTab(PlocaPres ploca) {
		ploca.setBPPodat(baza);
	}
	
	public void prikaziCitateljPodatak(PlocaPres ploca) {
		ploca.prikaziPodatNaTab();
	}
	
	public void spremiPodNaDat(File file) throws IOException {
		baza.spremiBPNaDat(file);
	}
	
	public void impPodZaDat(File file) throws IOException {
		baza.citajPodZaDat(file);
		int num = baza.getSveZaBazu().get(baza.getSveZaBazu().size()-1).getId();
		Citatelj.setCounter(num+1);
	}

	public void prikaziImpPodUTxtPanel(PlocaPres ploPres) {
		ploPres.prikaziImpPodUTxtPanel(baza.getSveZaBazu());
	}
	
	public void spajanjeNaBazu() throws SQLException {
		baza.spajanje();
	}
	public void odspajanjeOdBaze() throws SQLException {
		baza.odspajanje();
	}
	
	public void spremiNaBazu() throws SQLException {
		baza.spremiNaBazu();
	}
	
	public void ucitajZaBazu() throws SQLException {
		baza.ucitajZaBazuPodataka();
	}

}