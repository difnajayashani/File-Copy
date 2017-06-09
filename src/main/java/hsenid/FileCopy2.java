package hsenid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.SocketPermission;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by hsenid on 5/23/17.
 */
public class FileCopy2  {
    FileService fileService= new FileService();
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
    private JProgressBar copyProgress;

    private String copyOrMove;

    Thread copyThred= new Thread(() -> {
        copyOrMove=combo_copy_select.getSelectedItem().toString();
        fileService.startCoping(file_source_path.getText(),file_destination_path.getText(),copyOrMove,copyProgress);
    });


    public void copyMoveStart() {

        JFrame frame =new JFrame("FileCopy2");
        frame.setSize(450, 200);
        frame.setResizable(true);
        frame.setContentPane(new FileCopy2().file_copy_form);
        //frame.add(file_copy_form);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        combo_copy_select.addItem("Copy");
        combo_copy_select.addItem("Move");

        copyProgress.setVisible(false);

        btn_start.addActionListener(e ->{
            if (file_source_path.getText().equals("") || file_destination_path.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Both the paths are not selected");

            }else {
                copyThred.start();
            }
        }
        );

        btn_pause.addActionListener(e ->{
            copyThred.suspend();
            btn_pause.setText("Resume");
        });

        btn_stop.addActionListener(e ->
            copyThred.interrupt()
        );

        //get the selected source file path
        btn_choose_src.addActionListener(e ->  {
                try {
                    JFileChooser fileChoser = new JFileChooser();
                    fileChoser.setDialogTitle("Select a File");
                    fileChoser.showDialog(null, "OK");
                    String selectedFilePath = fileChoser.getSelectedFile().getAbsolutePath();
                    file_source_path.setText(selectedFilePath);
                }catch (NullPointerException e1){
                    System.out.print("NUll Pointer Exception in source file selection: "+e1);

                    JOptionPane.showMessageDialog(null, "No file selected");
                }
        });


        //get the selected destination file path
        btn_choose_dst.addActionListener(e ->  {
                try {
                    JFileChooser fileChoser = new JFileChooser();
                    fileChoser.setDialogTitle("Copy to");
                    fileChoser.showDialog(null, "Ok");
                    fileChoser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    fileChoser.setAcceptAllFileFilterUsed(false);
                    String selectedFilePath = fileChoser.getSelectedFile().getAbsolutePath();
                    file_destination_path.setText(selectedFilePath);

                }catch (NullPointerException e1){
                    System.out.print("NUll Pointer Exception in destination selection: "+e1);

                    JOptionPane.showMessageDialog(null, "Select a destination location\n" +
                            "& enter a file name(example.xxx)");

                }

        });

    }

}
