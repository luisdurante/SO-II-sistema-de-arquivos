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
		this.contentFile.forEach(file -> System.out.println(file));
	}
	
	public void addFile(String filename, int size, int position)throws IOException {
		String file = filename + ";" + size + ";" + position;
		this.contentFile.add(file);
	}
	
	public void deleteFile(int position) {
		this.contentFile.remove(position);
	}
	
	public void saveHeaderFile() {
		this.contentFile.forEach(file -> this.pw.println(file));
		this.close();
	}
	
	private void close() {
		sc.close();
		pw.close();
	}
}
