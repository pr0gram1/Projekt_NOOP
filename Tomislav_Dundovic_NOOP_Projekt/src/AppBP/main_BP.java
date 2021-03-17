package AppBP;

import java.sql.SQLException;

import model.BazaPodataka;
import model.Citatelj;
import model.OdabirGodineEnum;
import model.OdabirStudijaEnum;
import model.sveucilistaEnum;
import model.xicaEnum;

/**
 * Testna Klasa main_BP
 * Testna Klasa koja je zaduzena da se tri vrste studenata direktno posalju na bazu podataka bez upisivanja unutar same aplikacije 
 * @author tomislav
 * @since ozujak, 2021.
 *
 */
public class main_BP {
	
	public static void main(String[] args) {
		
		
		
		BazaPodataka baza = new BazaPodataka();
		Citatelj student1 = new Citatelj("Tomee","Tomic","1998",sveucilistaEnum.UNI_Zadar,OdabirStudijaEnum.IT,"Izvanredni student",OdabirGodineEnum.TrecaGodina,xicaEnum.Neposjeduje, "Rekreativac");
		Citatelj student2 = new Citatelj("Stipee","Stipetic","1991",sveucilistaEnum.UNI_Zagreb,OdabirStudijaEnum.Menadzment,"Izvanrednistudent",OdabirGodineEnum.PetaGodina,xicaEnum.Posjeduje,"Natjecatelj");
		Citatelj student3 = new Citatelj("Matee","Caleta","1995",sveucilistaEnum.UNI_Split,OdabirStudijaEnum.Nautika,"Redoviti student",OdabirGodineEnum.CetvrtaGodina,xicaEnum.Neposjeduje,"Rekreativac");
		


		
		baza.setCitateljNaBazu(student1);
		baza.setCitateljNaBazu(student2);
		baza.setCitateljNaBazu(student3);
		
		
		try {
			baza.spajanje();
			baza.spremiNaBazu();
			baza.ucitajZaBazuPodataka();
			baza.izlistSveZaBazuPod();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			baza.odspajanje();
			System.out.println("Odspojeno!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}


































































