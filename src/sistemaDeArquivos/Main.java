package sistemaDeArquivos;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Header header = loadHeader();
		header.readlines();
		FilesStorage fileStorage = new FilesStorage();
		writeFile(fileStorage);
		fileStorage.readFile(0,3);
		int option = 1;
		
		while(option != 0) {
			printInterface();
			option = scanner.nextInt();
		}
		scanner.close();
	}
	
	public static void printInterface() {
		System.out.println("1- Listar Arquivos");
		System.out.println("2- Guardar Arquivo");
		System.out.println("0- Sair");
	}
	
	public static Header loadHeader() {
		return new Header();
	}
	
	public static boolean writeFile(FilesStorage fileStorage) {
		fileStorage.getUpdatedByteArray();
		fileStorage.writeFile("teste.txt");
		return true;
	}
}

