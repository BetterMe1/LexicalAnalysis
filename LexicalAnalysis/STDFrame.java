package LexicalAnalysis;

import java.awt. *;
import javax.swing. *;

import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class STDFrame extends JFrame {
    private STDControl std;
    int resultLength;
    private String temp = new String();
    private String[] resultSet = new String[50];
    private JLabel label1 = new JLabel("目标字符串: ");
    private JLabel label2 = new JLabel("输入字符串: ");
    private JTextField textfield1 = new JTextField(15);
    private JTextField textfield2 = new JTextField(30);
    private JButton button1 = new JButton("打开文件");
    private JButton button2 = new JButton( "确定");
    private JRadioButton radiobutton1 = new JRadioButton("打开");
    private JRadioButton radiobutton2 = new JRadioButton("输入");
    private ButtonGroup buttongroup1 = new ButtonGroup();
    private JTextArea textarea1 = new JTextArea();
    private FileDialog openDia = new FileDialog(this, "打开文件", FileDialog.LOAD);
    private File file;
    //private JScrollPane jp;
    public STDFrame( String title){
        super( title );
        std = new STDControl(temp);
        //jp = new JScrollPane(textarea1);
        //this. getContentPane().add(ie);
        this. setLayout(null);
        myListener ml = new myListener();
        this . setSize(800, 900);
        this. setVisible(true);
        this . setResizable(false);

        label1. setBounds(30, 30, 80, 30);
        this . add(label1);

        textfield1. setBounds(120, 30, 300, 30);
        this . add(textfield1);
        textfield1. setEnabled(false);

        button1.setBounds(470, 30, 100, 30);
        this . add( button1);
        button1.addActionListener(ml);
        button1. setEnabled(false);

        label2. setBounds(30, 100, 80, 30);
        this . add(label2);

        textfield2. setBounds(120, 100, 300, 30);
        this. add(textfield2);
        textfield2. setEnabled(false);


        button2.setBounds(470, 100, 100, 30);
        this . add(button2);
        button2.addActionListener(ml);
        button2. setEnabled(false);

        buttongroup1. add( radiobutton1);
        buttongroup1. add( radiobutton2);
        radiobutton1. setBounds(600, 67, 80, 30);
        radiobutton2. setBounds(700, 67, 80, 30);
        this.add( radiobutton1);
        this.add( radiobutton2);
        radiobutton1.addActionListener(ml);
        radiobutton2.addActionListener(ml);

        textarea1.setBounds(30, 150, 720, 700);
        this.add(textarea1);
    }
    private class myListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == button1){
                openDia.setVisible( true );
                String dirPath = openDia.getDirectory();
                String fileName = openDia.getFile();//获取文件名称
                textfield1.setText( dirPath + fileName );
                if(dirPath == null || fileName == null){
                    return ;
                }
                file = new File(dirPath, fileName);
                try{
                    BufferedReader bufr = new BufferedReader( new FileReader(file));
                    String line = null;
                    try{
                        while( (line = bufr.readLine()) != null){
                            temp = temp + line;
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                std.setString(temp);
                textarea1. append(temp+"\r\n");
                resultLength = std.getLength();
                std. getStore(resultSet);
                new Thread(new Runnable(){
                    public void run() {
                        repaint();
                        for( int i = 0; i < resultLength; i++){
                            try{
                                Thread. sleep(500);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            textarea1. append(resultSet[i]+"\r\n");
                        }
                    }
                }).start();
            }

            if(e.getSource() == button2 ){
                temp = textfield2 .getText();
                std.setString(temp);
                textarea1. append(temp+"\r\n");
                resultLength = std.getLength();
                std. getStore(resultSet);
                new Thread(new Runnable(){
                    public void run() {
                        repaint();
                        for( int i = 0; i < resultLength; i++){
                            try{
                                Thread. sleep(500);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            textarea1. append(resultSet[i]+"\r\n");
                        }
                    }
                }).start();
            }
            if( e.getSource() == radiobutton1 ){
                textfield1.setEnabled(true);
                button1.setEnabled(true);
                textfield2.setText("");
                textfield2.setEnabled(false);
                button2. setEnabled(false);
                textarea1.setText("");
                temp = "";
            }
            if( e.getSource() == radiobutton2 ){
                textfield2.setEnabled(true);
                button2.setEnabled(true);
                textfield1.setText("");
                textfield1.setEnabled(false);
                button1.setEnabled(false);
                textarea1.setText("");
                temp = "";
            }
        }
    }

}


