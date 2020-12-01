package sistemaDeArquivos;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Header header = new Header();
		header.readlines();
		FilesStorage fileStorage = new FilesStorage();
		int option = 1;
		
		while(option != 0) {
			printInterface();
			option = scanner.nextInt();
			boolean indexIsValid = false;
			int indexFile = 0;
			
			switch (option) {
			
			case 1:
				System.out.println("\n");
				header.showFiles();
				System.out.println("\n");
				break;
			case 2:
				header.showFiles();
				indexIsValid = false;
				indexFile = 0;
				
				while(!indexIsValid) {					
					System.out.print("\nDigite o índice do arquivo que deseja ler (para voltar a listagem digite -1): ");
					indexFile = scanner.nextInt();
					
					indexIsValid = indexFile >= -1 && indexFile <= header.getListOfFilesSize() - 1;
					if(!indexIsValid) {
						System.out.println("Índice inválido");
					}
				}
				
				if(indexFile != -1) {
					String file = header.readFile(indexFile);
					
					if(file != "") {						
						readFile(fileStorage, header.getInitialPositionFile(file), header.getFinalPositionFile(file));
					}
				
				}
				
				break;
			case 3:
				System.out.print("Digite o nome do arquivo com a extensão: ");
				String fileName = scanner.next();
				writeFile(fileStorage, header, fileName);
				break;
				
			case 4:
				header.showFiles();

				while(!indexIsValid) {					
					System.out.print("\nDigite o índice do arquivo que deseja remover (para voltar a listagem digite -1): ");
					indexFile = scanner.nextInt();
					
					indexIsValid = indexFile >= -1 && indexFile <= header.getListOfFilesSize() - 1;
					if(!indexIsValid) {
						System.out.println("Índice inválido");
					}
				}
				
				if(indexFile != -1) {
					String file = header.readFile(indexFile);
					
					if(file != "") {						
						deleteFile(fileStorage, header, indexFile, header.getInitialPositionFile(file), header.getFinalPositionFile(file));
					}
				}
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
	
	public static Header loadHeader() throws IOException {
		return new Header();
	}
	
	public static boolean writeFile(FilesStorage fileStorage, Header header, String fileName) throws IOException {
		fileStorage.getUpdatedByteArray();
		int indexToWrite = fileStorage.checkIfFileFitsInBytesArray(fileName);
		int fileSize = fileStorage.getFileSize(fileName);
		System.out.println("index = "+ indexToWrite + " size = " + fileSize);
		if (indexToWrite != -1) {
			fileStorage.writeFile(fileName, indexToWrite);
			header.addFile(fileName, fileSize, indexToWrite);			
		}
		return true;
	}
	
	public static boolean readFile(FilesStorage fileStorage, int initialPosition, int finalPosition) {
		System.out.println("\n\n");
		fileStorage.getUpdatedByteArray();
		fileStorage.readFile(initialPosition, finalPosition);
		System.out.println("\n\n");
		return true;
	}
	
	public static boolean deleteFile(FilesStorage fileStorage, Header header, int indexFile, int initialPosition, int finalPosition) {
		fileStorage.getUpdatedByteArray();
		fileStorage.deleteFile(initialPosition,finalPosition);
		header.deleteFile(indexFile);
		return true;
	}
	
	
}
