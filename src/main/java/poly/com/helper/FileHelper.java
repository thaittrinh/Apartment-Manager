package poly.com.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHelper {

    @Autowired
    private FireBase fireBase;

    public String saveFile(MultipartFile photo, String folderName, String newName) throws IOException {
        String fileName = newName + "." + photo.getOriginalFilename().split("\\.")[1];
        fireBase.uploadImg("photo/user", photo, fileName);
        return fileName;
    }

    public void deleteFile(String fileName) throws IOException {
//        fireBase.deleteImg();
//        String urlFile = System.getProperty("user.dir") + photoFolder + fileName;
//        File myFile = new File(urlFile);
//        myFile.delete();

    }

}
