package Logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import TraitementFichier.FileFactory;

public class LogFactory {

	private static String slash = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/"; 
	
	public static final String pathLog = System.getProperty("user.dir")+ slash + "Log";
	
	public enum Separators {LineJump, LineReturn, Tirets, Nothing};
	
	public static void Logguer(String logName, String caractersChain, Separators separator) throws Exception {
			
		String path = pathLog + slash + logName;
		createFolders(path);
		long tailleFichier = new File(path).length();
		String chain = (tailleFichier > 0 ? getSeparation(separator) : "") + getDate() + " : " + caractersChain;
		FileFactory.ecritureStringFichier(path, chain, true);
	}
	
	
	

	private static void createFolders(String path) {
		File f = new File(path);
		f.getParentFile().mkdirs();
	}




	private static String getSeparation(Separators sep) throws SeparatinoNotImplementedException {
		switch (sep) {
		case LineJump:
			return "\n\n";
		case LineReturn:
			return "\n";
		case Tirets:
			return "\n\n\t------------------------------------\n\n";
		case Nothing:
			return "";
		default:
			throw new SeparatinoNotImplementedException();
		}
	}

	private static String getDate() {
		Calendar cal = Calendar.getInstance();
		String date = cal.get(cal.DAY_OF_MONTH) + "/" + cal.get(cal.MONTH)
				+ "/" + cal.get(cal.YEAR) + " a  " + cal.get(cal.HOUR_OF_DAY)
				+ "h" + cal.get(cal.MINUTE) + "min" + cal.get(cal.SECOND)
				+ "sec";
		return date;
	}
	
	
	static class SeparatinoNotImplementedException extends Exception{
		public SeparatinoNotImplementedException() {
			super();
		}
	}
	
}
