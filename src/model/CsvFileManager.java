package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvFileManager {
	private static final String path = "./records/";
	
	public void write(String fileName, String[] contents) {
		File file = new File(path+fileName);
		file.getParentFile().mkdirs();
		PrintWriter write;
		try {
			 write = new PrintWriter(new FileWriter(file, true));
			 for(String s : contents) {
				 write.print(s+",");
			 }
			 write.print("\n");
			 write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String[]> read(String fileName) {
		List<String[]> contents = new ArrayList<String[]>();
		File file = new File(path+fileName);
		if(file.canRead()==false) return null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String line;
			while ((line = in.readLine()) != null) {
				contents.add(line.split(","));
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contents;
	}
}
