import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.omg.CORBA.Environment;

import Logs.LogFactory;
import Logs.LogFactory.Separators;


public class Tests {


	private static String slash = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";
	
	
	
	
	@Test
	public void testWithSeparatorTiret() {
		//Arrange
		String PathFileLog = System.getProperty("user.dir")+ slash + "Log"+ slash + "logTestRayane";
		String phrase1 = "Login=Test\nPass=BG";
		String phrase2 = "Login=BEUU\nPass=EUHH";
		BufferedReader reader = null;
		
		
		//Action
		if(!EffacerContenuFichier(PathFileLog))
			fail();
		try{
			LogFactory.Logguer("logTestRayane", phrase1, Separators.Tirets);	
			LogFactory.Logguer("logTestRayane", phrase2, Separators.Tirets);	
		}catch(Exception e) {			
			fail();
		}	
		try {
			reader = new BufferedReader(new FileReader(new File(PathFileLog)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			fail();
		}
		String contenu = "", tmp;
		try {
			while ((tmp = reader.readLine()) != null) {
				contenu += tmp + "\n";
			}
			contenu = (contenu.length() >= 1 ? contenu.substring(0, contenu.length()-1): contenu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		//Assert
		assertEquals(phrase1 + "\n\n\t------------------------------------\n\n"+phrase2, contenu);
		
	}
	
	private boolean EffacerContenuFichier(String path){
		File fichier = new File(path);
		if(fichier.exists()) {
			try {			
				BufferedWriter writer = new BufferedWriter(new FileWriter(fichier));
				writer.write("");
				writer.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}		
		return true;
	}

}
