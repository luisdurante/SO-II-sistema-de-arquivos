package sistemaDeArquivos;
import java.io.*;


public class FilesStorage {
	private static final String FILENAME = "file-storage.bin";
	private static final int BUFFER_SIZE = 30 * 1024; // 16KB
	private int[] headerBytes = new int[BUFFER_SIZE];
	
	public FilesStorage() {
		this.initializeBuffer();
	}
	
	public boolean writeFile(String fileName) {
		String filePath = getCurrentDirectoryFile(fileName);
		try {
			OutputStream outputStream = new FileOutputStream(this.getCurrentDirectoryFile(FILENAME));
			InputStream fileToWrite = new FileInputStream(filePath);
			int byteRead;
			//TODO, verificar se os bytes vão caber no BUFFER. ARR de bytes
			System.out.println("uhh" + this.checkIfFileFitsInBytesArray(filePath));
			while ((byteRead = fileToWrite.read()) != -1) {
                outputStream.write(byteRead);
            }
			fileToWrite.close();
			
		} catch (IOException err) {
			System.out.println("Erro ao guardar arquivo no sistema");
			return false;
		}
		
		return true;
	}
	
	public boolean readFile(int initialBytePos, int finalBytePos) {
		try {
			InputStream inputStream = new FileInputStream(this.getCurrentDirectoryFile(FILENAME));
			for (int i = initialBytePos; i < finalBytePos; i++) {
				this.headerBytes[i] = inputStream.read();
				if (this.headerBytes[i] == -1) {
					continue;
				}
				System.out.println((char)this.headerBytes[i]);
			}
			inputStream.close();
			
		} catch (IOException err) {
			System.out.println("Erro ao ler arquivo do sistema");
			return false;
		}
		
		return true;
	}
	
	private void initializeBuffer() {
		for (int i = 0; i < BUFFER_SIZE; i++) {
			this.headerBytes[i] = -1;
		}
	}
	
	private String getCurrentDirectoryFile(String fileName) {
		String projectDirectory = System.getProperty("user.dir");
		return projectDirectory + "\\src\\sistemaDeArquivos\\" + fileName;
	}
	
	private int checkIfFileFitsInBytesArray(String filePath) {
		this.getUpdatedByteArray();
		System.out.println(this.headerBytes[0] + this.headerBytes[1] + this.headerBytes[2] + this.headerBytes[3]);
		try {
			InputStream fileToWrite = new FileInputStream(filePath);
			int fileSize = fileToWrite.available();
			if (fileToWrite.available() > BUFFER_SIZE) {
				fileToWrite.close();
				return -1;
			}
			
			System.out.println("aqu" + fileSize);
			int i = 0;
			for (i = 0; i < BUFFER_SIZE; i++) {
				if (this.headerBytes[i] < 0) {
					int j = 0;
					int size = 0;
					for (j = i; j < fileSize; j++) {
			          if (this.headerBytes[j] != -1) {
			        	fileToWrite.close();
			            break;
			          }
			          size++;
					}
					if (size == fileSize) {
						return i;
					}
				}
			}
			
			fileToWrite.close();
		} catch (IOException err) {
			System.out.println("Erro ao ler arquivo do sistema");
			return -1;
		}
		
		return -1;
	}
	
	private boolean getUpdatedByteArray() {
		try {
			InputStream binary = new FileInputStream(this.getCurrentDirectoryFile(FILENAME));
			for (int i = 0; i < 4; i++) {
//				this.headerBytes[i] = inputStream.read();
				System.out.println("aa" + binary.read());
			}
			binary.close();
		} catch (IOException err) {
			System.out.println("Erro ao atualizar o array de bytes");
			return false;
		}
		return true;
	}
	
}