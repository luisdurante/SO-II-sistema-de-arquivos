package sistemaDeArquivos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Header {
	private Stream<String> stream;
	private static final String FILENAME = "header.txt";
	
	public Header() {
		String projectDirectory = System.getProperty("user.dir");
		String filePath = projectDirectory + "\\src\\sistemaDeArquivos\\"+ FILENAME;
		try {
			this.stream = Files.lines(Paths.get(filePath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readlines() {
		this.stream.forEach(System.out::println);
	}
}
