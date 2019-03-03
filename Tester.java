
public class Tester {

	public static void main(String args[]) {
		
		FileCSV file = new FileCSV("D:\\test.txt");
		file.parseFile();
		file.addLineToMap("Strasse", "SuperTestStrasse");
		file.printFile();
	}
	
}
