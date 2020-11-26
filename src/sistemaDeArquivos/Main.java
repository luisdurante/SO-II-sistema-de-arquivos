package sistemaDeArquivos;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Header header = loadHeader();
		header.readlines();
		FilesStorage fileStorage = new FilesStorage();
//		writeFile(fileStorage, "HSH.sql");
		readFile(fileStorage);
//		deleteFile(fileStorage);
		int option = 1;
		
		while(option != 0) {
			printInterface();
			option = scanner.nextInt();
		}
		scanner.close();
	}
	
	public static void printInterface() {
		System.out.println("1- Listar Arquivos");
		System.out.println("2- Ler Arquivo");
		System.out.println("3- Guardar Arquivo");
		System.out.println("4- Deletar Arquivo");
		System.out.println("0- Sair");
	}
	
	public static Header loadHeader() {
		return new Header();
	}
	
	public static boolean writeFile(FilesStorage fileStorage, String fileName) {
		fileStorage.getUpdatedByteArray();
		int indexToWrite = fileStorage.checkIfFileFitsInBytesArray(fileName);
		int fileSize = fileStorage.getFileSize(fileName);
		System.out.println("index = "+ indexToWrite + " size = " + fileSize);
		fileStorage.writeFile(fileName, indexToWrite);
		return true;
	}
	
	public static boolean readFile(FilesStorage fileStorage) {
		fileStorage.getUpdatedByteArray();
		fileStorage.readFile(2,30);
		return true;
	}
	
	public static boolean deleteFile(FilesStorage fileStorage) {
		fileStorage.getUpdatedByteArray();
		fileStorage.deleteFile(1,2);
		return true;
	}
	
	
}
