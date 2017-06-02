package hsenid;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Created by hsenid on 5/31/17.
 */
public class FileService {


    public void startCoping(String from, String to, String copyOrMove) {


        File sourceFile=new File(from);
        File destinationFile=new File(to);
        Path sourcePath=sourceFile.toPath();
        Path destinationPath=destinationFile.toPath();

        try {
            if (copyOrMove.equals("Copy")) {
                Files.copy(sourcePath, destinationPath);
            }
            else  if (copyOrMove.equals("Move")){
                Files.move(sourcePath,destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e1) {
            System.out.print("Exception in copying the file"+e1);
        }
    }

    public void showProgress(){

    }

}
