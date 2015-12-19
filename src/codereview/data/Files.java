package codereview.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import codereview.views.MainScreen;

public class Files {
	
	private static final String FILENAME = "user_data_file.dat";
	
	public static String[] loadLoginFile(){
		String [] temp;
		try {
			FileInputStream inPath = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(inPath);
			temp = (String[]) in.readObject();
			in.close();
			System.out.println("USER FILE LOADED");
		} catch (Exception e) {
			System.err.println("DATA FILE LOAD ERROR");
			temp = null;
		}
		return temp;
	}
	
	public static void saveData(String mail, String password){
		String [] data = new String[2];
		data[MainScreen.MAIL] = mail;
		data[MainScreen.PASSWORD] = password;
		try {
			FileOutputStream outPath = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(outPath);
			out.writeObject(data);
			out.close();
			System.out.println("USER FILE SAVED");
		} catch (Exception e) {
			System.out.println("SAVE ERROR");
			e.printStackTrace();
		}
	}
}
