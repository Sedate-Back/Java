package com.huang.note;

import javax.swing.*;
//import java.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class note extends JFrame implements ActionListener{
    JTextArea jTextArea = null;
    JMenuBar jMenuBar = null; //菜单栏
    //菜单列表
    JMenu file = null;
    JMenu editor = null;
    JMenu help = null;
    //菜单的选项
    // swing 中的方法
    JMenuItem newFile = null;
    JMenuItem open = null;
    JMenuItem save = null;
    JMenuItem saveAs = null;
    JMenuItem exit = null;
    JFileChooser jFileChooser = null; //打开
    JFileChooser jFileChooser2 = null;//另存为
    FileReader fileReader = null;//输入流
    FileWriter fileWriter = null;//输出流
    BufferedReader bufferedReader = null; //缓冲输入流
    BufferedWriter bufferedWriter = null; //缓冲输出流
    String address = null;

    //构造器 new组件
    public note(){
        jTextArea = new JTextArea();
        jMenuBar = new JMenuBar();
        file = new JMenu("文件");
        editor = new JMenu("编辑");
        help = new JMenu("帮助");
        newFile = new JMenuItem("新建");
        newFile.addActionListener(this);
        newFile.setActionCommand("新建");
        open = new JMenuItem("打开");
        open.addActionListener(this);
        open.setActionCommand("打开");
        save = new JMenuItem("保存");
        save.addActionListener(this);
        save.setActionCommand("保存");
        saveAs = new JMenuItem("另存为");
        saveAs.addActionListener(this);
        saveAs.setActionCommand("另存为");
        exit = new JMenuItem("退出");
        exit.addActionListener(this);
        exit.setActionCommand("退出");
        jTextArea.setBackground(Color.white);
        this.setJMenuBar(jMenuBar);
        jMenuBar.add(file);
        jMenuBar.add(editor);
        jMenuBar.add(help);
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(exit);
        this.add(jTextArea);

        this.setTitle("记事本");
        this.setBounds(200,100,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void open(){  //打开的方法

        jFileChooser = new JFileChooser();//实例化文件选择框
        jFileChooser.setDialogTitle("打开");
        jFileChooser.showOpenDialog(null);  //设置父组件为空
        //定义变量保存用户编辑的绝对路径
        address = jFileChooser.getSelectedFile().getAbsolutePath();
        try {
            //实例化文件输入流 从选择这个文件中读取数据
            fileReader = new FileReader(address);
            //实例化字符缓冲输入流
            bufferedReader = new BufferedReader(fileReader);
            //定义str判断输入的字符是否已经为空
            String str = "";
            //定义strAll接受文件的全部信息
            String strAll = "";
            //去缓冲区里面拿我的数据并保存到strAll中
            while((str = bufferedReader.readLine())!=null){ //readLine一行行读取 read 全部读取
                strAll += str + System.lineSeparator();
            }
            //把拿到的数据放到文本框
            jTextArea.setText(strAll);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void save(){                     //保存的方法
        try {
            fileWriter = new FileWriter(address);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jTextArea.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveAs(){                  //另存为的方法
        jFileChooser2 = new JFileChooser();
        jFileChooser2.setDialogTitle("保存");
        if (jFileChooser2.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            address = jFileChooser2.getSelectedFile().getAbsolutePath();
        }
        jFileChooser2.setVisible(true);
        save();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("新建")){
            if (jTextArea.getText().length()!=0){
                if (address==null){
                    saveAs();
                }else {
                    save();
                }
                jTextArea.setText("");
                address = null;
            }
        }

        if (e.getActionCommand().equals("打开")){
            if (jTextArea.getText().length()!=0){
                if (address==null){
                    saveAs();
                }else {
                    save();
                }
                jTextArea.setText("");
            }
            open();
        }

        if (e.getActionCommand().equals("保存")){
            if(address == null){
                saveAs();
            }else{
                save();
            }
        }

        if (e.getActionCommand().equals("另存为")){
            saveAs();
        }

        if (e.getActionCommand().equals("退出")){
            int value=JOptionPane.showConfirmDialog(null,"你确定要退出吗?");
            if (value==JOptionPane.YES_OPTION) {
                if (address != null) {
                    save();
                }
                if (address == null) {
                    if (jTextArea.getText().length() != 0) {
                        saveAs();
                    }
                }
                System.exit(0);
            }
        }
    }

}