package hsenid;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Created by hsenid on 5/31/17.
 */
public class FileService {


    public void startCoping(String from, String to, String copyOrMove,JProgressBar progressBar) {

        //source file and destination files
        File sourceFile=new File(from);
        File destinationFile=new File(to);

        //source file path and destination file paths
        Path sourcePath=sourceFile.toPath();
        Path destinationPath=destinationFile.toPath();

        try {
            if (copyOrMove.equals("Copy")) {

                InputStream in=new FileInputStream(sourceFile);
                OutputStream out=new FileOutputStream(destinationFile);

                destinationFile.createNewFile();
                long expectedBytes = sourceFile.length();

                long totalBytesCopied = 0;
                byte[] buf = new byte[256];
                int len = 0;
                progressBar.setVisible(true);
                while ((len = in.read(buf)) > 0) {

                    out.write(buf, 0, len);
                    totalBytesCopied += len;
                    int sizeofProgressBar = (int) ((totalBytesCopied / expectedBytes) * 100);

                    progressBar.setVisible(true);
                    progressBar.setValue(sizeofProgressBar);
                }
                //Files.copy(sourcePath, destinationPath);
            }
            else  if (copyOrMove.equals("Move")){
                Files.move(sourcePath,destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e1) {
            System.out.print("Exception in copying the file"+e1);
        }
    }
}
