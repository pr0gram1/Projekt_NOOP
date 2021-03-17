package view;

//Imports
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

import model.Citatelj;
import model.OdabirGodineEnum;
import model.OdabirStudijaEnum;
import model.sveucilistaEnum;
import model.xicaEnum;

/*
 * import java.awt.event.KeyAdapter;
 * import java.awt.event.KeyEvent;
 */


/**
 * 
 * Klasa PodPloca koja je zaduzena za poredak elemenata kao sto su botuni te radio ili combo boxevi unutar panela
 * Ove komponent ce se dodavati uz pomoc gridbag layouta
 * Unutar ove klase se vrši aktiviranje aplikacije
 * @author tomislav
 * @since ozujak 2021.
 *
 *
 */
public class PodPloca extends JPanel{
	
	
	
	// Definiranje varijabli
	
	private static final DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	private JTextField unosImena;
	private JTextField unosPrezimena;
	private JFormattedTextField unosDatumaRod;
	private JComboBox<OdabirStudijaEnum> comboBoxOdabir;
	private JList<OdabirGodineEnum> listPosudba;
	private JComboBox<xicaEnum> posjedxica;
	private JList<sveucilistaEnum> svEnum;
	private JRadioButton radioOne;
	private JRadioButton radioTwo;
	private JRadioButton radioFive;	
	private JRadioButton radioSix;	
	private ButtonGroup vrijemeBotun;
	private ButtonGroup sportBotun;
	private JButton botunPotvrda;
	private JScrollPane paneScroll;
	private JScrollPane scrollPane;
	private PodPlocaListener ppl;
	private Citatelj citatelj;
	
	/*
	 * private JRadioButton radioThree;
	 * private JRadioButton radioFour;
	 * private ButtonGroup spolBotun;
	 */
	
	
	
	
	
	// Konstruktor klase
	public PodPloca() {
		
		Dimension dimenzija = getPreferredSize();
		dimenzija.height = 210;
		setPreferredSize(dimenzija);	
		setBackground(Color.GRAY);
		setBordere();
		inicijalizacija();
		komponente();
		aktivacija();
	}
	
	public void setPodPlocaListener(PodPlocaListener listener) {
		this.ppl = listener;
	}
	
	
	
	// metoda unutar koje se vrsi inicijalizacija 
	private void inicijalizacija() {

		unosImena = new JTextField(10);		
		unosPrezimena = new JTextField(10);
		
		comboBoxOdabir = new JComboBox<>();
		comboBoxOdabir.setForeground(Color.BLUE);
		listPosudba = new JList<>();
		svEnum = new JList<>();
		svEnum.setForeground(Color.BLUE);
		posjedxica = new JComboBox<>();
		posjedxica.setForeground(Color.BLUE);
		listPosudba.setForeground(Color.BLUE);
		radioOne = new JRadioButton("Izvanredni student");
		radioTwo = new JRadioButton("Redoviti student");
		radioFive = new JRadioButton("Rekreativac");
		radioSix = new JRadioButton("Natjecatelj");		
		vrijemeBotun = new ButtonGroup();
		sportBotun = new ButtonGroup();	
		
		/*
		 * radioThree = new JRadioButton("Musko");
		 * radioFour = new JRadioButton("Zensko");
		 * spolBotun = new ButtonGroup();
		 */
	
		
		
		
		unosDatumaRod = new JFormattedTextField();		
		unosDatumaRod.setColumns(10);	
		
		
		// Maska za unos datuma rodenja
		
		try {
            MaskFormatter maskaDatum = new MaskFormatter("##/##/####");
            maskaDatum.install(unosDatumaRod);
        } catch (ParseException ex) {
            Logger.getLogger(PodPloca.class.getName()).log(Level.SEVERE, null, ex);
            
        }
		
		
	
		
	
	
		
		/*
		 * <<<<<<<<< Combo box >>>>>>>>>>
		 */
		
		//Dodavanje elemenata sa enum class OdabirStudijaEnum unutar ComboBoxa
		
		DefaultComboBoxModel<OdabirStudijaEnum> boxOdabir = new DefaultComboBoxModel<>();
		OdabirStudijaEnum[] odabirArray = OdabirStudijaEnum.values();
		List<OdabirStudijaEnum> odabirColl = Arrays.asList(odabirArray);
		boxOdabir.addElement(OdabirStudijaEnum.IT);
		boxOdabir.addElement(OdabirStudijaEnum.Povijest);
		boxOdabir.addElement(OdabirStudijaEnum.Arheologija);
		boxOdabir.addElement(OdabirStudijaEnum.Filozofija);
		boxOdabir.addElement(OdabirStudijaEnum.Geografija);		
		boxOdabir.addElement(OdabirStudijaEnum.Nautika);
		boxOdabir.addElement(OdabirStudijaEnum.Brodostrojarstvo);	
		boxOdabir.addElement(OdabirStudijaEnum.Menadzment);				
		boxOdabir.addElement(OdabirStudijaEnum.Sestrinstvo);				
		boxOdabir.addElement(OdabirStudijaEnum.Psihologija);
		boxOdabir.addElement(OdabirStudijaEnum.Pedagogija);
		boxOdabir.addElement(OdabirStudijaEnum.Anglistika);
		boxOdabir.addElement(OdabirStudijaEnum.TalijanskiJezik);
		boxOdabir.addElement(OdabirStudijaEnum.HrvatskiJezik);
		boxOdabir.addElement(OdabirStudijaEnum.FrancuskiJezik);
		boxOdabir.addElement(OdabirStudijaEnum.RuskiJezik);
		boxOdabir.addElement(OdabirStudijaEnum.SpanjolskiJezik);
		boxOdabir.addElement(OdabirStudijaEnum.PredskolskiOdgoj);	
		//boxOdabir.addAll(odabirColl);		
		comboBoxOdabir.setModel(boxOdabir);
		comboBoxOdabir.setSelectedIndex(0);
		Dimension dimenz = comboBoxOdabir.getPreferredSize();
		dimenz.width = 110;
		comboBoxOdabir.setPreferredSize(dimenz);
				
		
		
		/*
		 * <<<<<<<<< Combo box >>>>>>>>>>
		 */
		
		
		//Dodavanje elemenata sa enum class xiucaEnum unutar ComboBoxa
		DefaultComboBoxModel<xicaEnum> boxXica = new DefaultComboBoxModel<>();
		xicaEnum[] xicaArray = xicaEnum.values();
		List<xicaEnum> xicaColl = Arrays.asList(xicaArray);
		boxXica.addElement(xicaEnum.Posjeduje);
		boxXica.addElement(xicaEnum.Neposjeduje);
		posjedxica.setModel(boxXica);
		posjedxica.setSelectedIndex(0);
		Dimension dimensio = posjedxica.getPreferredSize();
		dimensio.width = 110;
		posjedxica.setPreferredSize(dimenz);
		
		
		/*
		 * <<<<<<<<<<<<< List >>>>>>>>>>>>>
		 */
		

		//Dodavanje elemenata sa enum class OdabirGodineEnum unutar Liste
		DefaultListModel<OdabirGodineEnum> modPosudba = new DefaultListModel<>();		
		OdabirGodineEnum[] posudbaArray = OdabirGodineEnum.values();		
		List<OdabirGodineEnum> collPosudba = Arrays.asList(posudbaArray);
		//modPosudba.addAll(collPosudba);
		modPosudba.addElement(OdabirGodineEnum.PrvaGodina);
		modPosudba.addElement(OdabirGodineEnum.DrugaGodina);
		modPosudba.addElement(OdabirGodineEnum.TrecaGodina);
		modPosudba.addElement(OdabirGodineEnum.CetvrtaGodina);
		modPosudba.addElement(OdabirGodineEnum.PetaGodina);
		listPosudba.setModel(modPosudba);	
		listPosudba.setVisibleRowCount(3);
		listPosudba.setBorder(BorderFactory.createEtchedBorder());
		Dimension dime = listPosudba.getPreferredSize();
		dime.width = 110;
		listPosudba.setPreferredSize(dime);
		paneScroll = new JScrollPane(listPosudba, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		/*
		 * <<<<<<<<<<<<< List >>>>>>>>>>>>>
		 */
	
		//Dodavanje elemenata sa enum class sveucilistaEnum unutar Liste
		DefaultListModel<sveucilistaEnum> modSveuci = new DefaultListModel<>();	
		sveucilistaEnum[] sveArray = sveucilistaEnum.values();		
		List<sveucilistaEnum> collSve = Arrays.asList(sveArray);
		//modPosudba.addAll(collPosudba);
		modSveuci.addElement(sveucilistaEnum.UNI_Zadar);
		modSveuci.addElement(sveucilistaEnum.UNI_Split);
		modSveuci.addElement(sveucilistaEnum.UNI_Zagreb);
		modSveuci.addElement(sveucilistaEnum.UNI_Rijeka);
		modSveuci.addElement(sveucilistaEnum.UNI_Osijek);
		svEnum.setModel(modSveuci);	
		svEnum.setVisibleRowCount(3);
		svEnum.setBorder(BorderFactory.createEtchedBorder());
		Dimension dimz = svEnum.getPreferredSize();
		dimz.width = 110;
		svEnum.setPreferredSize(dime);
		scrollPane = new JScrollPane(svEnum, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		
		/*
		 * <<<<<<<< Radio buttons >>>>>>>>
		 */

		
		//RadioButton edit
		radioOne.setSelected(true);
		vrijemeBotun.add(radioOne);
		vrijemeBotun.add(radioTwo);
		radioOne.setActionCommand("Izvanredni student");
		radioOne.setForeground(Color.BLUE);
		
		radioTwo.setActionCommand("Redoviti student");
		radioTwo.setForeground(Color.BLUE);

			
		
		radioFive.setSelected(true);
		sportBotun.add(radioFive);
		sportBotun.add(radioSix);
		radioFive.setActionCommand("Rekrativac");
		radioFive.setForeground(Color.BLUE);
		
		radioSix.setActionCommand("Natjecatelj");
		radioSix.setForeground(Color.BLUE);
		
		botunPotvrda = new JButton("Potvrda");
		botunPotvrda.setForeground(Color.RED);
		

		/*
		 * 		 
		 * radioThree.setSelected(true);
		 * spolBotun.add(radioThree);
		 * spolBotun.add(radioFour);
		 * radioThree.setActionCommand("Musko");
		 * radioThree.setForeground(Color.BLUE);
		
		 * radioFour.setActionCommand("Zensko");
		 * radioFour.setForeground(Color.BLUE);
		
		 */
		
		
		/*
		 * 
		 
		* KeyListener za textfield unosdatuma koji sluzi da se ne unese string
		* unosDatumaRod.addKeyListener(new KeyAdapter() {
			
		* public void keyPressed(KeyEvent ke) {

		* if (ke.getKeyChar() >= '0'  && ke.getKeyChar() <= '9' ) 
		* //if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				  
	            	           		
		* {
		* unosDatumaRod.setEditable(true);
		* System.out.println(""); 
	               
	              
		* } else {
		* unosDatumaRod.setEditable(false);
		* System.out.println("Pogreska pri unosu.Unesite int a ne string");
		* }
		* }
			
		* });
		
		*/
		
	}
	
	
	

	private void setBordere() {
		Border inner = BorderFactory.createTitledBorder("Upis podataka studenta");		
		Border outter = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border brd = BorderFactory.createCompoundBorder(outter, inner);
		setBorder(brd);
	}

	

	
	
	private void aktivacija() {

		botunPotvrda.addActionListener(new ActionListener( ) {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(ppl != null) {
					citatelj = new Citatelj(unosImena.getText(), unosPrezimena.getText(),  unosDatumaRod.getText(), svEnum.getSelectedValue(), 
							 (OdabirStudijaEnum) comboBoxOdabir.getSelectedItem(), vrijemeBotun.getSelection().getActionCommand(), 
							listPosudba.getSelectedValue(),(xicaEnum) posjedxica.getSelectedItem(), 
							sportBotun.getSelection().getActionCommand());
					PodPlocaEvent podEvent = new PodPlocaEvent(PodPloca.this, citatelj);
					
					//spolBotun.getSelection().getActionCommand(),
					
					ppl.podPlocaEventOccured(podEvent);
					citatelj.description();
				}

				resetPanela();
				
				
			}
			
			
			
			
			
		});
	}
	
	public Citatelj getCitatelj() {
		return citatelj;
	}
	
	private void resetPanela() {
		unosImena.setText("");
		unosPrezimena.setText("");
		unosDatumaRod.setText("");
		unosImena.requestFocus();
		comboBoxOdabir.setSelectedIndex(0);
		posjedxica.setSelectedIndex(0);
		listPosudba.setSelectedIndex(0);
		radioOne.setSelected(true);
		
		//radioThree.setSelected(true);
	}

	
	
	// Dodavanje komponenti te pozicioniranje sa GridBagLayout
	private void komponente() {

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets dfltInst = new Insets(0, 0, 0, 0);

		gbc.weightx = 0;
		gbc.weighty = 0;

		gbc.gridx = 0;
		gbc.gridy = 0;

		add(Box.createVerticalStrut(8), gbc);

		gbc.weightx = 0;
		gbc.weighty = 0.1;

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Ime: "), gbc);
	

		gbc.gridx = 1;
		gbc.insets = dfltInst;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(unosImena, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Prezime: "), gbc);

		gbc.gridx = 1;
		gbc.insets = dfltInst;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(unosPrezimena, gbc);

		
		/*
		 * gbc.gridx = 0;
		 * gbc.gridy = 3;
		 * gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		 * gbc.insets = new Insets(0, 0, 0, 5);
		 * add(new JLabel("Spol Studenta: "), gbc);
		 * gbc.gridx = 1;
		 * gbc.gridy = 2;
		 * gbc.anchor = GridBagConstraints.LAST_LINE_START;
		 * add(radioThree, gbc);
		 * gbc.gridy = 3;
		 * add(radioFour, gbc);
		 */

		
		gbc.gridx = 3;
		Component bx = Box.createHorizontalStrut(25);
		add(bx, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;

		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Datum rodenja: "), gbc);

		gbc.gridy = 5;
		gbc.gridx = 1;
		gbc.insets = dfltInst;
		add(unosDatumaRod, gbc);

		gbc.gridx = 5;
		Component bx2 = Box.createHorizontalStrut(25);
		add(bx2, gbc);

		

		gbc.gridx = 6;
		gbc.gridy = 1;

		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Odabir fakulteta: "), gbc);

		
		
		
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = dfltInst;
		add(scrollPane, gbc);
		

		
		

		gbc.gridx = 6;
		gbc.gridy = 4;

		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Odabir Studija: "), gbc);

		gbc.gridx = 6;
		gbc.gridy = 5;
		gbc.insets = dfltInst;
		add(comboBoxOdabir, gbc);
		
	
	
		gbc.gridx = 7;
		Component bx10 = Box.createHorizontalStrut(25);
		add(bx, gbc);

		gbc.gridx = 8;
		gbc.gridy = 1;

		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Vrsta studenta: "), gbc);
		
		
		gbc.gridy = 2;
		add(radioOne, gbc);
		gbc.gridy = 3;
		add(radioTwo, gbc);
		
		

		
		gbc.gridx = 9;
		gbc.gridy = 1;

		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Godina studiranja: "), gbc);

		gbc.gridx = 9;
		gbc.gridy = 2;
		gbc.insets = dfltInst;
		add(paneScroll, gbc);
		

		
		
		gbc.gridx = 9;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Pravo na x-icu: "), gbc);

		gbc.gridy = 5;
		
		gbc.insets = dfltInst;
		add(posjedxica, gbc);
		

	
	
		
		gbc.gridx = 11;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Sport: "), gbc);
 
		
		gbc.gridx = 12;
		gbc.gridy = 1;
		
		add(radioFive, gbc);
		gbc.gridy = 2;
		add(radioSix, gbc);
		


		gbc.weighty = 0.25;
		gbc.gridx = 12;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		add(botunPotvrda, gbc);
		
		
	}

}
