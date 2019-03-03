import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileCSV {
	private String path;
	private String delimiter = ",";
	private String[] headers;
	
	private boolean isValid = true;
	
	private List<Map<String,String>> file = new ArrayList<Map<String,String>>();
	private Scanner scanner;
	
	public FileCSV(String path) {
		this.path = path;
	}
	
	public void printFile(){
		file.forEach(System.out::println);
	}
	
	public void parseFile() {
		readFile();
		if (isValid) try {
			readHeader();
			while(scanner.hasNextLine()) readLineToMap();
		}
		finally {
			scanner.close();		
		}
	}
	
	private void printMessage(String text) {
		isValid = false;
		System.out.println(text);
	}
	
	private void readFile(){
		try {
			scanner = new Scanner(new File(path));
			scanner.useDelimiter(delimiter);
		} catch (FileNotFoundException e) {
			printMessage("parseFile(" + path + ") - FileNotFoundException");
		}
	}
	
	private void readHeader(){
		try {
			headers = scanner.nextLine().split(delimiter);
		}
		catch (NoSuchElementException e) {
			printMessage("setHeader() - NoSuchElementException");
		}
	}
	
	private void readLineToMap(){
		Map<String,String> map = new HashMap<String,String>();
		String[] line;
		try {
			line = scanner.nextLine().split(delimiter);	
			for(int i = 0; i < line.length; i++)
				map.put(headers[i], line[i]);
			file.add(map);
		}
		catch (NoSuchElementException e) {
			printMessage("readLineToMap() - NoSuchElementException");
		}
	}
	
	public void addLineToMap(String header, String value) {
		try {
			header.length();
			for(int i = 0; i < file.size(); i++)
				file.get(i).put(header, value);
		}
		catch (NullPointerException e) {
			printMessage("addLineToMap() - NullPointerException");
		}
	}
	
}
