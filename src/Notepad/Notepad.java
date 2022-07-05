package Notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class Notepad extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Notepad().setVisible(true);

    }

    public Notepad() {
        //frame


        setTitle("Notepad");
        setSize(750, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //text
        JTextArea write = new JTextArea();
        JScrollPane scroll = new JScrollPane(write);
        write.setFont(write.getFont().deriveFont(Font.BOLD, 18));


        add(scroll);
        //panel
        // JPanel panel = new JPanel();
        JMenuBar mb = new JMenuBar();

        //buttons
        JMenu file = new JMenu("File");
        mb.add(file);
        JButton save = new JButton("Save");


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int fileChose = fc.showOpenDialog(null);
                if (fileChose == JFileChooser.APPROVE_OPTION) {
                    File selected = fc.getSelectedFile();

                    String nameOfFile = JOptionPane.showInputDialog("Name");

                    String files = selected + "\\" + nameOfFile + ".txt";
                    File f = new File(files);
                    String content = write.getText();
                    try (BufferedWriter fw = new BufferedWriter(new FileWriter(f))) {
                        fw.write(content);
                        fw.flush();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }
        });
        file.add(save);

        JButton open = new JButton("Open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int fileChose = fc.showOpenDialog(null);
                if (fileChose == JFileChooser.APPROVE_OPTION) {
                    File selected = fc.getSelectedFile();


                    try (BufferedReader fr = new BufferedReader(new FileReader(selected))) {
                        /**Reading*/
                        StringBuilder sb = new StringBuilder();
                        String s = fr.readLine();
                        while (s != null) {
                            sb.append(s);
                            s = fr.readLine();
                        }
                        write.setText(sb.toString());
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }
        });
        file.add(open);


        JButton copy = new JButton("Copy");
        copy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                write.copy();

            }
        });
        mb.add(copy);


        JButton cut = new JButton("Cut");
        cut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                write.cut();

            }
        });
        mb.add(cut);


        JButton paste = new JButton("Paste");
        paste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                write.paste();
            }
        });

        mb.add(paste);

        JButton NEW = new JButton("New");
        NEW.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                write.setText("");

            }
        });
        file.add(NEW);

        JTextField field = new JTextField();
        write.setFont(new Font("", Font.PLAIN, 18));
        field.setText("18");

        JButton fontChange = new JButton("Font");
        fontChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = field.getText();

                int r = Integer.parseInt(q);

                write.setFont(new Font("Times New Roman", Font.PLAIN, r));
                field.setSize(30, 30);

            }
        });

        mb.setBorderPainted(true);
        mb.setBackground(Color.cyan);
        mb.add(fontChange);
        mb.add(field);

        add(mb, BorderLayout.BEFORE_FIRST_LINE);

    }
}