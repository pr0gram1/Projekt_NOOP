package view;


//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.Citatelj;
import model.OdabirGodineEnum;
import model.OdabirStudijaEnum;
import model.sveucilistaEnum;
import model.xicaEnum;


/**
 * 
 * Klasa AppProzor
 * Unutar klase se definira njezina velicina, postavljaju se paneli te implementira se JMenuBar sa njegovim opcijama
 * @author tomislav
 * @since ozujak, 2021.
 *
 */
public class AppProzor extends JFrame {
	
	
	
	//Definiranje varijabli
	private PodPloca podPloca;
	private PlocaPres ploPres;
	private Controller controller;
	private JMenuBar meni;
	private JFileChooser biracPodat;
	
	
	
	
	// Konstruktor klase
	public AppProzor () {
		super("Popis Studenata - Projekt NOOP Tomislav");
		setLayout(new BorderLayout());
		setSize(950, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);	
		inicijalizacija();
		izgled();
		aktivacijaAplikacije();		
				
		
	}
	
	
	
	
	// Metoda za namjestanje panela po frameu
	private void izgled() {
		
		add(podPloca, BorderLayout.SOUTH);
		add(ploPres, BorderLayout.CENTER);
	}
	
	
	

	// Unutar ove metode se kreira menubar sa svojim svojstima
	private JMenuBar kreacijaMeniBara() {
		
		
		//Dva vidljiva taba na frame Opcije te Server
		JMenuBar meniBar = new JMenuBar();
		JMenu meniJ = new JMenu("Opcije");
		meniJ.setForeground(Color.RED);
		JMenu meniSer = new JMenu("Server");
		meniSer.setForeground(Color.RED);
		
		// Dodavanje sadrzaja za srpemanje, otvaranje, izlazak, objavu , preuzimanje te iskljucenje unutar menbara
		JMenuItem spremanje = new JMenuItem("Spremanje podataka u dokument...");
		spremanje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
		spremanje.setForeground(Color.RED);
		JMenuItem otvaranje = new JMenuItem("Importiranje podataka sa dokumenta...");
		otvaranje.setForeground(Color.RED);
		JMenuItem izlazak = new JMenuItem("Izlazak iz aplikacije");
		izlazak.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK));
		izlazak.setForeground(Color.RED);

		JMenuItem objava = new JMenuItem("Spremi podatke na server");
		objava.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
		objava.setForeground(Color.RED);
		JMenuItem preuzimanje = new JMenuItem("Importiranje podataka sa servera");
		preuzimanje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.SHIFT_MASK));
		preuzimanje.setForeground(Color.RED);
		JMenuItem iskljucenje = new JMenuItem("Odspajanje sa servera");
		iskljucenje.setForeground(Color.RED);
		
		
		meniJ.add(spremanje);
		meniJ.add(otvaranje);
		meniJ.addSeparator();
		meniJ.add(izlazak);
		
		
		// opcija za izlazak
		izlazak.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = JOptionPane.showConfirmDialog(AppProzor.this, "Da li ste sigurni da bi ste izašli iz app", "Exit dialog",
						JOptionPane.OK_CANCEL_OPTION);
				if (val == JOptionPane.OK_OPTION) {
					System.exit(0);
				} else {
					System.out.println("Izlazak je odbijen od korisnika");
				}

			}
			
			
		});
		
		
		// opcija za spremanje
		spremanje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = biracPodat.showSaveDialog(AppProzor.this);

				if (val == JFileChooser.APPROVE_OPTION) {
					File datot = biracPodat.getSelectedFile();
					try {
						controller.spremiPodNaDat(datot);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(AppProzor.this, "Spremnaje podataka unutar dokumenta nije uspjelo!", "Save error",
								JOptionPane.ERROR_MESSAGE);
				
					}
				}

			}
		});
		
		
		
		
		// opcija za otvaranje
		otvaranje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = biracPodat.showOpenDialog(AppProzor.this);

				if (val == JFileChooser.APPROVE_OPTION) {
					File datot = biracPodat.getSelectedFile();
					try {
						controller.impPodZaDat(datot);
						controller.prikaziCitateljPodatak(ploPres);
						controller.prikaziImpPodUTxtPanel(ploPres);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(AppProzor.this, "Citanje podataka sa dokumenta nije uspjelo!", "Otvaranje error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		meniSer.add(objava);
		meniSer.add(preuzimanje);
		meniSer.addSeparator();
		meniSer.add(iskljucenje);
		
		
		
		
		// opcija za objavljivanje na bazu
		objava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.spajanjeNaBazu();
					controller.spremiNaBazu();
					controller.odspajanjeOdBaze();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(AppProzor.this, "BP server - pogreska pri spajanju! ", "Spremanje BP error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
		// opcija za preuzimanje
		preuzimanje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.spajanjeNaBazu();
				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(AppProzor.this, "Konekcija sa serverom prekinuta od strane korisnika!", "Spajanje BP error",
							JOptionPane.ERROR_MESSAGE);
				}
				try {
					controller.ucitajZaBazu();
					controller.prikaziImpPodUTxtPanel(ploPres);
					controller.prikaziCitateljPodatak(ploPres);
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(AppProzor.this, "Ucitavanje podataka sa DB servera nije moguce!", "Ucitavanje BP error",
							JOptionPane.ERROR_MESSAGE);
				}
				try {
					controller.odspajanjeOdBaze();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(AppProzor.this, "Odspajanje sa DB servera nije moguce!", "Odspajanje BP error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
		
		// opcija za disconnect ili iskljucivanje
		iskljucenje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.odspajanjeOdBaze();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(AppProzor.this, "Odspajanje sa DB servera nije moguce!", "Odspajanje BP error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		meniBar.add(meniJ);
		meniBar.add(meniSer);
		
		return meniBar;
		
		
		
		
	}
	
	
	
	// metoda unutar koje se vrsi inicijalizacija
	private void inicijalizacija() {
		
		
		podPloca = new PodPloca();
	    ploPres = new PlocaPres();
		controller = new Controller();
		controller.setPodZaTab(ploPres);
		meni = kreacijaMeniBara();
		setJMenuBar(meni);
		biracPodat = new JFileChooser();
		
		
		setDatEks();
		
		
		
	}
	
	private void setDatEks() {
		FileNameExtensionFilter filtar = new FileNameExtensionFilter("Studentovi dokumenti (*.stud)", "stud");
		biracPodat.setFileFilter(filtar);
		biracPodat.setAcceptAllFileFilterUsed(false);
		
		
	}
	
	
	
	
	private void aktivacijaAplikacije() {
		
		podPloca.setPodPlocaListener(new PodPlocaListener() {

			@Override
			public void podPlocaEventOccured(PodPlocaEvent podEvent) {
				int id = podEvent.getId();
				String ime = podEvent.getIme();
				String prezime = podEvent.getPrezime();
				OdabirStudijaEnum odabir = podEvent.getOdabir();
				OdabirGodineEnum posudb = podEvent.getPosudb();
				String VrstaStudenta = podEvent.getVrstaStudenta();
				xicaEnum xica = podEvent.getXicaEnum();
				//String spolStud = podEvent.getSpolStud();
				String datumRod = podEvent.getDatumRod();
				sveucilistaEnum sveUci = podEvent.getSveUci();
				String bavljSport = podEvent.getBavljSport();

				Citatelj citat = podPloca.getCitatelj();
				controller.dodajNovogCitateljNaBazu(citat);
				controller.prikaziNaPloPres(citat, ploPres);
				controller.prikaziCitateljPodatak(ploPres);
			}
		});
	}
}
