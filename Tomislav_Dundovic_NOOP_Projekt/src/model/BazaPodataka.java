package model;
//Imports
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa za Bazu podataka
 * Podaci koji su zapisan unutar aplikacije mogu biti zapisani na bazi podataka db4free
 * 
 * @author tomislav
 * @since ozujak, 2021.
 * @version 1.3
 *
 */


public class BazaPodataka {

	// Variables
	private List<Citatelj> citatelji;
	private Connection con;
	
	// Konstrukor klase
	public BazaPodataka() {
		citatelji = new LinkedList<Citatelj>();
		con = null;
	}
	
	public void setCitateljNaBazu(Citatelj citatelj) {
		
		citatelji.add(citatelj);
		
	}
	
	public List<Citatelj> getSveZaBazu(){
		return citatelji;
	}
	
	
	
	
	/**
	 * Metoda koja je za zasluzna za spremanje na datoteku
	 * @param file
	 * 			objekt tipa file koji prezentira datoteku
	 * @throws IOException
	 * 			ovaj izuzetak se pojavljuje ako datoteka nije dostupna
	 */
	public void spremiBPNaDat(File file) throws IOException {
		
		Citatelj[] citati = citatelji.toArray(new Citatelj[citatelji.size()]);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(citati); 
		oos.close();
	}
	
	

	/**
	 * 
	 * @param file
	 * 			objekt tipa file koji prezentira datoteku
	 * @throws IOException
	 */
	public void citajPodZaDat(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Citatelj[] citat = (Citatelj[]) ois.readObject();
			citatelji.clear();
			citatelji.addAll(Arrays.asList(citat));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ois.close();
	}
	
	
	
	
	/**
	 * Metoda koja je zasluzena za spajanje baze podataka db4free
	 * Unutar metode se upisuju korisnikovi podaci kao sto su url baze, korisnicko ime te lozinka
	 * @throws SQLException 
	 * 			
	 */
	public void spajanje() throws SQLException {
		System.out.println("Spajanje na bazu podataka");

		try {
			// load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// obtain connection
			String url = "jdbc:mysql://db4free.net:3306/tdundovic"; // your database
			String user = "tdundovic"; // your user name
			String password = "dundovicseget12"; // your password 
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Spajanje na >>>> " + con.toString());
		} catch (ClassNotFoundException e) {
			System.out.println("Driver se nije mogao ucitati");
		}
		
	}
	
	
	/**
	 * Metoda za odspajanje od baze podataka
	 * @throws SQLException
	 */
	public void odspajanje() throws SQLException {
		con.close();
		System.out.println("Odspajanje sa baze podataka");
	}
	
	
	/**
	 * Metoda za spremane podataka unutar baze
	 * Zapisuje se Queries
	 * @throws SQLException
	 */
	public void spremiNaBazu() throws SQLException {
		
		// assume that connection is successfully established
		if(con != null) {
			
			/*
			 *Queries
			 *odabirSpola,
			 *, odabirSpola = ?
			 */
			
			String cntSql = "select count(*) as count from Studenti where id = ?";
			String insSql = "insert into Studenti (id, ime, prezime, datumRodenja, odabirFakulteta, odabirStudija, vrstaStudenta, godinaStudiranja, xica, bavljenjeSportom) values (?,?,?,?,?,?,?,?,?,?)";////--
			String updSql = "update Studenti set ime = ?, prezime = ?, datumRodenja = ?, odabirFakulteta = ?, odabirStudija = ?, vrstaStudenta = ?, godinaStudiranja = ?, xica = ?, bavljenjeSportom = ? where id = ?";//--
			PreparedStatement cntStm = con.prepareStatement(cntSql);
			PreparedStatement insrStm = con.prepareStatement(insSql);
			PreparedStatement updStm = con.prepareStatement(updSql);
			
			
			//Potvrda student
			
			
			for(Citatelj cita: citatelji) {
				int id = cita.getId();
				String ime = cita.getIme();
				String prezime = cita.getPrezime();
				//String spolStud = cita.getSpolStud();
				String datumRod = cita.getDatumRod();
				sveucilistaEnum sveUci = cita.getSveUci();
				OdabirStudijaEnum posudb = cita.getOdabir();
				String vrsta = cita.getVrstaStudenta();
				OdabirGodineEnum godina = cita.getPosudb();				
				xicaEnum xica = cita.getXicaEnum();
				String bavljSport = cita.getBavljSport();
				
				cntStm.setInt(1, id);
				ResultSet result = cntStm.executeQuery();
				result.next();
				
				
				
				int cnt = result.getInt(1);
				System.out.println("Cnt -> " + cnt);

				
				// unos komandi
				if(cnt == 0) {
					System.out.println("Dodavanje novog studenta >>> " + id);
					int col = 1;
					insrStm.setInt(col++, id);
					insrStm.setString(col++, ime);
					insrStm.setString(col++, prezime);
					//insrStm.setString(col++, spolStud);
					insrStm.setString(col++, datumRod);
					insrStm.setString(col++, sveUci.name());
					insrStm.setString(col++, posudb.name());
					insrStm.setString(col++, vrsta);
					insrStm.setString(col++, godina.name());					
					insrStm.setString(col++, xica.name());											
					insrStm.setString(col++, bavljSport);		
					
					insrStm.executeUpdate();
					
					
					
					
						// azuriranje komandi
				} else {
													
					System.out.println("Azuriranje studenta >>> " + id);
					int col = 1;
					updStm.setString(col++, ime);
					updStm.setString(col++, prezime);
					//updStm.setString(col++, spolStud);
					updStm.setString(col++, datumRod);
					updStm.setString(col++, sveUci.name());
					updStm.setString(col++, posudb.name());
					updStm.setString(col++, vrsta);
					updStm.setString(col++, godina.name());					
					updStm.setString(col++, xica.name());											
					updStm.setString(col++, bavljSport);
					
					updStm.setInt(col++, id);

					updStm.executeUpdate();
				}
			}
			
			cntStm.close();
			insrStm.close();
			updStm.close();


		}
		
	}
	
	
	
	
	/**
	 * Metoda za ucitavanje na bazu podataka
	 * @throws SQLException
	 */
	public void ucitajZaBazuPodataka() throws SQLException {
		if(con != null) {
			System.out.println("Ucitavanje sa baze podataka");
			String slctSQL = "select id, ime, prezime, datumRodenja, odabirFakulteta, odabirStudija, vrstaStudenta, godinaStudiranja, xica, bavljenjeSportom from Studenti order by id ";
			PreparedStatement slcStm = con.prepareStatement(slctSQL);
			
			//odbabirSpola
			
			ResultSet slcResult = slcStm.executeQuery();
			//citatelji.clear();
			while(slcResult.next()) {
				int id = slcResult.getInt(1);
				String ime = slcResult.getString(2);
				String prezime = slcResult.getString(3);
				//String spolStud = slcResult.getString(4);
				String datumRod = slcResult.getString(4);
				sveucilistaEnum sveUci = sveucilistaEnum.valueOf(slcResult.getString(5));
				OdabirStudijaEnum odabir = OdabirStudijaEnum.valueOf(slcResult.getString(6));
				String vrsta = slcResult.getString(7);
				OdabirGodineEnum posudb = OdabirGodineEnum.valueOf(slcResult.getString(8));
				xicaEnum xica = xicaEnum.valueOf(slcResult.getString(9));			
				String bavljSport = slcResult.getString(10);	
				Citatelj citatelj = new Citatelj(id, ime, prezime, datumRod, sveUci, odabir, vrsta, posudb, xica, bavljSport);
				citatelji.add(citatelj);
				
				//,spolStud
				
				System.out.println("Podaci spremljeni na server");
			}
			
			slcResult.close();
			slcStm.close();
		}
	}
	
	public void izlistSveZaBazuPod() {
		System.out.println("-------------- Listiranje svega sa baze podataka --------------");
		for(Citatelj cit : citatelji) {
			cit.description();
		}
	}

}
