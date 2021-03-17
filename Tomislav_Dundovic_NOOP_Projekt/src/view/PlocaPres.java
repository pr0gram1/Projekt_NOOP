package view;
//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.BazaPodataka;
import model.Citatelj;

/**
 * 
 * Klasa PlocaPres unutar koje se dodaje JTable sa njezinim elementima
 * Svi podaci i informacije studenata ce se prezentirati na tablici
 * @author tomislav
 * @since ozujak, 2021.
 *
 */
public class PlocaPres extends JPanel{
	
	
	// Definiranje varijabli
	private JTextPane textPanel;
	private JScrollPane scrollPanel;
	private StyledDocument styleDocum;
	private SimpleAttributeSet jednAtrib;
	private JTable tablica;
	private List<Citatelj> citatelji;
	private JScrollPane scrollTablica;
	private AbstractTableModel absTablica;

	
	
	
	

	// Konstuktor klase 
	public PlocaPres() {
		
		setLayout(new BorderLayout());
		inicijalizacija();
		add(scrollTablica, BorderLayout.CENTER);
		add(scrollPanel, BorderLayout.SOUTH);
		
	}
	
	public void setBPPodat (BazaPodataka baza) {
		citatelji = baza.getSveZaBazu();
	}
	

	// metoda u koj se vrsi inicijalizacija
	private void inicijalizacija () {
		
		textPanel = new JTextPane();
		textPanel.setEditable(false);
		styleDocum = textPanel.getStyledDocument();
		textPanel.setBackground(Color.CYAN);
		Color bojaFont = new Color(50, 15, 130);
		textPanel.setForeground(bojaFont);
		Dimension dimenzije = textPanel.getPreferredSize();
		dimenzije.height = 155;
		textPanel.setPreferredSize(dimenzije);
		jednAtrib = new SimpleAttributeSet();
		StyleConstants.setFontFamily(jednAtrib, "Konzola");
		StyleConstants.setFontSize(jednAtrib, 14);
		jednAtrib.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
	    scrollPanel = new JScrollPane(textPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.tablica = setTablica();
		scrollTablica = new JScrollPane(tablica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablica.getTableHeader().setBackground(Color.RED);
		
		
	}
	

	
	
	/**
	 * Metoda za kreiranje sadrzaja za tablicu JTable tablica
	 * @return 
	 * Kada se unutar ove metode definirala imena stupaca, ona ce ih returnati te ih prikazati na tablici
	 */
	private JTable setTablica() {
		
		JTable tablica = new JTable();
		
		
		absTablica = new AbstractTableModel() {
		
			
		
		String[] colNames = {"id", "ime", "prezime", "datumRodenja", "odabirFakulteta", "odabirStudija", "vrstaStudenta", "godinaStudiranja", "xica", "bavljenjeSportom"};
		
		
		//"odabirSpola"

		
		@Override
		public String  getColumnName(int column) {
			return colNames[column];
		}
		
		

		// dodavanje case-ova
		@Override
		public Object getValueAt(int row, int col) {
			
			Citatelj citatelj = citatelji.get(row);
			switch (col) {
			case 0: return citatelj.getId();
			case 1: return citatelj.getIme();
			case 2: return citatelj.getPrezime();
			//case 3: return citatelj.getSpolStud();
			case 3: return citatelj.getDatumRod();
			case 4: return citatelj.getSveUci();
			case 5: return citatelj.getOdabir();
			case 6: return citatelj.getVrstaStudenta();
			case 7: return citatelj.getPosudb();
			case 8: return citatelj.getXicaEnum();
			case 9: return citatelj.getBavljSport();
			
			default:
				throw new IllegalArgumentException("Takva vrijednost za ulazne podatke ne postoji !");
			}
		}
		
		public int getRowCount() {
			return citatelji.size();
		}
		
		@Override
		public int getColumnCount() {
			Citatelj citat = new Citatelj();
			return citat.getClass().getDeclaredFields().length-1;
		}

		
	};
	
	tablica.setModel(absTablica);
	return tablica;
}


public void prikaziNaPlocePres(Citatelj citatelj) {
	try {
		styleDocum.insertString(styleDocum.getLength(), citatelj.toString() +"\n\n", jednAtrib);
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void prikaziPodatNaTab() {
	absTablica.fireTableDataChanged();
}

public void prikaziImpPodUTxtPanel(List<Citatelj> sveZaBazuPod) {
	for(Citatelj cita : sveZaBazuPod) {
		prikaziNaPlocePres(cita);
	}
	
}

}