package sistemaDeArquivos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Header header = new Header();
		header.readlines();
		FilesStorage fileStorage = new FilesStorage();
		writeFile(fileStorage, header, "teste.txt");
		readFile(fileStorage);
		deleteFile(fileStorage);
		int option = 1;
		
		while(option != 0) {
			printInterface();
			option = scanner.nextInt();
			
			switch (option) {
			case 1:
				header.showFiles();
				break;

			default:
				break;
			}
		}
		header.saveHeaderFile();
		scanner.close();
	}
	
	public static void printInterface() {
		System.out.println("1- Listar Arquivos");
		System.out.println("2- Ler Arquivo");
		System.out.println("3- Guardar Arquivo");
		System.out.println("4- Deletar Arquivo");
		System.out.println("0- Sair");
	}
	
	public static Header loadHeader() throws FileNotFoundException {
		return new Header();
	}
	
	public static boolean writeFile(FilesStorage fileStorage, Header header, String fileName) throws IOException {
		fileStorage.getUpdatedByteArray();
		int indexToWrite = fileStorage.checkIfFileFitsInBytesArray(fileName);
		int fileSize = fileStorage.getFileSize(fileName);
		System.out.println("index = "+ indexToWrite + " size = " + fileSize);
		fileStorage.writeFile(fileName, indexToWrite);
		System.out.println("VOU GRAVAR NO HEADER");
		header.addFile(fileName, fileSize, indexToWrite);
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
