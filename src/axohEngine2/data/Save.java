/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 1.0
 * Date: June 15, 2015
 * 
 * 
 * Title: Saving
 * Description: This class is used to correctly serialize objects, specifically the 'Data.java' class
 *  It also creates new files when needed.
 *  
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Packages
package axohEngine2.data;

//Imports
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class Save {
	
	//Needed serializing related object variables
	private transient FileOutputStream file_out;
	private transient ObjectOutputStream obj_out;
	private transient PrintWriter writer;
	private File newfile; 
	private Data data;
	
	/********************************************************
	 * Save the Game state
	 * 
	 * @param fileName - A string name of a file
	 * @param data - 'Data.java' class object that holds the variables
	 *********************************************************/
	public void saveState(String fileName, Data data) {
		try {
			this.data = data;
			file_out = new FileOutputStream("C:/gamedata/saves/" + fileName);
			obj_out = new ObjectOutputStream(file_out);
			obj_out.writeObject(data);
			obj_out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/********************************************************
	 * Create a new file in the system directory
	 * 
	 * @param file - A String
	 ********************************************************/
	public void newFile(String file) {
		newfile = new File("C:/gamedata/saves/" + file);
		newfile.getParentFile().mkdirs();
		
		try {
			writer = new PrintWriter(newfile, "UTF-8");
		} catch (Exception e) {
			System.err.println("Unable to make new file...");
		}
		writer.close();
	}
	
	public Data getData(){ return data;}
}