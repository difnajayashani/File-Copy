package hsenid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by hsenid on 5/23/17.
 */
public class FileCopy2 extends JFrame {
    private JPanel file_copy_form;
    private JTextField file_source_path;
    private JTextField file_destination_path;
    private JButton btn_start;
    private JButton btn_pause;
    private JButton btn_stop;
    private JLabel file_source;
    private JLabel file_destination;
    private JButton btn_choose_src;
    private JButton btn_choose_dst;
    private JComboBox combo_copy_select;
    private JLabel lbl_copy_move;

    public FileCopy2() {

        JFrame frame =new JFrame("FileCopy2");
        frame.setSize(450, 200);
        frame.setResizable(true);
        frame.add(file_copy_form);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        combo_copy_select.addItem("Copy");
        combo_copy_select.addItem("Move");


        //get the selected source file path
        btn_choose_src.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChoser = new JFileChooser();
                    fileChoser.setDialogTitle("Select a File");
                    fileChoser.showDialog(null, "OK");
                    String selectedFilePath = fileChoser.getSelectedFile().getAbsolutePath();
                    file_source_path.setText(selectedFilePath);
                }catch (NullPointerException e1){
                    JOptionPane.showMessageDialog(null, "No file selected");
                }

            }
        });


        //get the selected destination file path
        btn_choose_dst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChoser = new JFileChooser();
                    fileChoser.setDialogTitle("Copy to");
                    fileChoser.showDialog(null, "Ok");
                    fileChoser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    fileChoser.setAcceptAllFileFilterUsed(false);
                    String selectedFilePath = fileChoser.getSelectedFile().getAbsolutePath();
                    file_destination_path.setText(selectedFilePath);

                }catch (NullPointerException e1){
                    JOptionPane.showMessageDialog(null, "Select a destination location\n" +
                            "& enter a file name(example.xxx)");

                }
            }
        });


        btn_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile=new File(file_source_path.getText());
                File destinationFile=new File(file_destination_path.getText());
                Path sourcePath=sourceFile.toPath();
                Path destinationPath=destinationFile.toPath();

                try {
                    Files.copy(sourcePath,destinationPath);
                } catch (IOException e1) {
                    System.out.print("Exception in copying the file"+e1);
                }

            }
        });

    }

    public static void main(String[] args) {

      //  frame.setContentPane(new FileCopy2().file_copy_form);
       FileCopy2 fileCopy=new FileCopy2();
       fileCopy.setSize(400,150);

    }

}
