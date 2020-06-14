package poly.com.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHelper {

  public String saveFile(MultipartFile photo, String newName) throws IOException{	
	String fileName = newName + "." + photo.getOriginalFilename().split("\\.")[1];
	String local = System.getProperty("user.dir");	
	
	File convertFile = new File(local+"\\src\\main\\resources\\static\\assets\\photo\\"+ fileName );
	convertFile.createNewFile();
	FileOutputStream fout = new FileOutputStream(convertFile);
	fout.write(photo.getBytes());// 
	fout.close();	
	return fileName;
  }
	    
  public void deleteFile(String fileName) throws IOException{	
	String urlFile = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\photo\\" + fileName;	
  	File myFile = new File(urlFile);
  	myFile.delete();
	  
  }
		
}
