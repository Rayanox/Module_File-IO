package TraitementFichier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FileFactory {

	public static byte[] extractionDonneesFichier(String path) throws IOException, FileNotFoundException{
		try {
			File f = new File(path);
			BufferedInputStream reader = new BufferedInputStream(
					new FileInputStream(f));
			ArrayList<Byte> datas = extractionDonneesFichier(reader);
			reader.close();
			return ListToArrayByte(datas);
		} catch (Exception e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public static ArrayList<Byte> extractionDonneesFichier(
			BufferedInputStream reader) throws IOException, FileNotFoundException{
		int length = 1000;
		ArrayList<Byte> datas = new ArrayList<Byte>();
		byte[] tmp = new byte[length];
		int i = 0;
		try {
			while ((i = reader.read(tmp)) > 0) {
				for (int y = 0; y < i; y++) {
					byte b = tmp[y];
					datas.add(b);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datas;
	}

	public static boolean ecritureDonneesFichier(String path, byte[] datas) throws IOException, FileNotFoundException{
		try {
			File f = new File(path);
			BufferedOutputStream writer = new BufferedOutputStream(
					new FileOutputStream(f));
			ecritureDonneesFichier(writer, datas);
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean ecritureDonneesFichier(BufferedOutputStream writer,
			byte[] datas) throws IOException, FileNotFoundException{
		try {
			writer.write(datas);
			writer.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ------------ Chaines de caractrères

	public static boolean ecritureStringFichier(String path,
			String chaineDeCaractere, boolean append) throws IOException, FileNotFoundException{
		try {
			File f = new File(path);
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(f, append));
			writer.write(chaineDeCaractere);
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String lectureStringFichier(String path) throws IOException, FileNotFoundException{

		String content = "";

		File f = new File(path);
		BufferedReader writer = new BufferedReader(new FileReader(f));
		String ligne;
		while ((ligne = writer.readLine()) != null) {
			content += ligne;
		}
		writer.close();
		return content;
	}
	
	public static Collection<String> lectureListStringFichier(String path) throws IOException, FileNotFoundException{

		ArrayList<String> content = new ArrayList<>();

		File f = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String ligne;
		while ((ligne = reader.readLine()) != null) {
			content.add(ligne);
		}
		reader.close();
		return content;
	}

	// ----------------- Helpers ------------

	private static byte[] ListToArrayByte(ArrayList<Byte> datas) {
		byte[] result = new byte[datas.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = datas.get(i);
		}
		return result;
	}
}
