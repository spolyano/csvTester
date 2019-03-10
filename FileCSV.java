import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	
	private List<Map<String,String>> file = new ArrayList<Map<String,String>>();
	
	public FileCSV(String path) {
		this.path = path;
	}
	
	public void printFile(){
		file.forEach(System.out::println);
	}
	
	public void parseFile() {
		try (Scanner scanner = new Scanner(new File(path));) {
			scanner.useDelimiter(delimiter);
			headers = scanner.nextLine().split(delimiter);		
			while(scanner.hasNextLine())
				readLineToMap(scanner.nextLine().split(delimiter));
		}
		catch (NoSuchElementException e) {
			usingBufferedWritter("Can not read new line from file");
		}
		catch (FileNotFoundException e) {
			usingBufferedWritter("Can not find a file");
		}

	}
	
	public static void usingBufferedWritter(String fileContent)
	{   
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/log.txt"))) {
		    writer.write(fileContent);
	    }
		catch (IOException e) {
			System.out.print("Can not write to log");
		}
	}
	
	private void readLineToMap(String[] line){
		Map<String,String> map = new HashMap<String,String>();
		for(int i = 0; i < line.length; i++)
			map.put(headers[i], line[i]);
		file.add(map);
	}
	
	public void addLineToMap(String header, String value) {
		try {
			header.length();
			for(int i = 0; i < file.size(); i++)
				file.get(i).put(header, value);
		}
		catch (NullPointerException e) {
			usingBufferedWritter("Can not add new column");
		}
	}
	
}
