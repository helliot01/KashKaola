// Java program to reading
// text file to HashMap

import java.io.*;
import java.util.*;

public class read_write {

	static Map<String, String> login_info = new HashMap<String, String>();
	
	
	public static void write_to_file(Map<String, String[]> login_info2) throws IOException{
		File file = new File("testFile");
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(login_info2);
        s.close();

	}
	
	public static HashMap<String,String[]> read_from_file() throws IOException, ClassNotFoundException{
		File file = new File("testFile");
	    FileInputStream f = new FileInputStream(file);
	    ObjectInputStream s = new ObjectInputStream(f);
	    HashMap<String, String[]> fileObj2 = (HashMap<String, String[]>) s.readObject();
	    s.close();

		return fileObj2;
	}
	public static void main(String[]args) throws ClassNotFoundException, IOException{
		Map<String, String[]> login_info2 = new HashMap<String, String[]>();
		String[] info_list = new String[3];	
		
		info_list[0]="meow";
    	info_list[1]="woof";
    	info_list[2]=Integer.toString(0);
    	login_info2.put("dog", info_list);
    	System.out.println(login_info2.get("dog")[0]);
    	
    	login_info2.get("dog")[0]="arfarf";
    	write_to_file(login_info2);
		
		HashMap<String, String[]> fileObj = read_from_file();
		System.out.println(fileObj.get("dog")[1]);

		
	}
}


