package sistemaDeArquivos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Header {
	private static final String FILENAME = "header.txt";
	private PrintWriter pw;
	private Scanner sc;
	private File file;
	private ArrayList<String> contentFile = new ArrayList<>();
	
	public Header() throws FileNotFoundException {
		String projectDirectory = System.getProperty("user.dir");
		String filePath = projectDirectory + "//src//sistemaDeArquivos//"+ FILENAME;
		this.file = new File(filePath);

	}
	
	public void readlines() throws FileNotFoundException {
		this.sc = new Scanner(this.file);
		this.pw = new PrintWriter(this.file);
		
		while(this.sc.hasNextLine()) {
			this.contentFile.add(this.sc.nextLine());
		}
	}
	
	public void showFiles() {
		for (int i = 0; i < this.contentFile.size(); i++) {
			String filename = this.contentFile.get(i).split(";")[0];
			System.out.println(i + " - " + filename);
		}
	}
	
	public void addFile(String filename, int size, int position)throws IOException {
		String file = filename + ";" + size + ";" + position;
		this.contentFile.add(file);
	}
	
	public int getInitialPositionFile(String file) {
		return Integer.parseInt(file.split(";")[2]);
	}
	
	public int getFinalPositionFile(String file) {
		return Integer.parseInt(file.split(";")[1]) + getInitialPositionFile(file);
	}
	
	public String getFilename(String file) {
		return file.split(";")[0];
	}
	
	public void deleteFile(int index) {
		this.contentFile.remove(index);
	}
	
	public String readFile(int index) {
		String file = this.contentFile.get(index);
		if(!this.getFilename(file).contains(".txt")) {
			System.out.println("Arquivo precisa ser .txt");
			return "";
		}
		return this.contentFile.get(index);
	}
	
	public void saveHeaderFile() {
		this.contentFile.forEach(file -> this.pw.println(file));
		this.close();
	}
	
	public int getListOfFilesSize() {
		return this.contentFile.size();
	}
	
	private void close() {
		sc.close();
		pw.close();
	}
}
