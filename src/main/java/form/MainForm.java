package form;

import base.SFTPFileUpload;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainForm extends JFrame {

    private JPanel myPanel;
    private JLabel labelHostName;
    private JLabel labelUserName;
    private JLabel labelPassword;
    private JLabel labelPort;
    private JPasswordField password;
    private JTextField userName;
    private JTextField hostName;
    private JTextField portNumber;
    private JButton btnGo;
    private JLabel labelPathDirServer;
    private JTextField pathDirServer;
    private JLabel labelPathDirLocal;
    private JTextField pathFileLocal;
    private JButton btnSelectFile;

    private Data data;

    public MainForm() {
        //TODO перенести лиссенеры в отдельные классы (убрать из конструктора)
        //TODO Добавить метод сохранения сессии в текстовый файл и загрузки из него
        super("SFTP Client");
        data = new Data();
        setData(data);
//        btnSelectFile.setIcon(new ImageIcon("resources/Icon_addFile.png"));
        this.getContentPane().add(myPanel);
//        this.setBounds(100, 100, 400, 300);
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += getUserName().getText() + "\n";
                message += getHostName().getText() + "\n";
                message += getPortNumber().getText() + "\n";
                message += getPathDirServer().getText() + "\n";
                message += getPathFileLocal().getText() + "\n";
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
                new SaveData().saveDataForm(message);

                getData(data);
                try {
                    new SFTPFileUpload(data);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnSelectFile.addActionListener(new ActionListener() {  //обработка нажатия кнопки "выбор файла"
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Выбор файла");
                int result = chooser.showOpenDialog(myPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    System.out.println(chooser.getSelectedFile());
                    pathFileLocal.setText(chooser.getSelectedFile().getAbsolutePath());
//                    btnOk.setEnabled(true);
                }
            }
        });
    }

    public void setData(Data data) {
        password.setText(data.getPassword());
        userName.setText(data.getUserName());
        hostName.setText(data.getHostName());
        portNumber.setText(data.getPortNumber());
        pathDirServer.setText(data.getPathDirServer());
        pathFileLocal.setText(data.getPathFileLocal());
    }

    public void getData(Data data) {
        data.setPassword(password.getText());
        data.setUserName(userName.getText());
        data.setHostName(hostName.getText());
        data.setPortNumber(portNumber.getText());
        data.setPathDirServer(pathDirServer.getText());
        data.setPathFileLocal(pathFileLocal.getText());
    }

    public boolean isModified(Data data) {
        if (password.getText() != null ? !password.getText().equals(data.getPassword()) : data.getPassword() != null)
            return true;
        if (userName.getText() != null ? !userName.getText().equals(data.getUserName()) : data.getUserName() != null)
            return true;
        if (hostName.getText() != null ? !hostName.getText().equals(data.getHostName()) : data.getHostName() != null)
            return true;
        if (portNumber.getText() != null ? !portNumber.getText().equals(data.getPortNumber()) : data.getPortNumber() != null)
            return true;
        if (pathDirServer.getText() != null ? !pathDirServer.getText().equals(data.getPathDirServer()) : data.getPathDirServer() != null)
            return true;
        if (pathFileLocal.getText() != null ? !pathFileLocal.getText().equals(data.getPathFileLocal()) : data.getPathFileLocal() != null)
            return true;
        return false;
    }

    class SaveData {
        private File dataFile = new File("session.txt");

        public void saveDataForm(String data) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
                writer.write(data);
//                writer.write(getUserName().getText() + "\n");
//                writer.write(getPassword().getText() + "\n");
                writer.flush();
                writer.close();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JTextField getUserName() {
        return userName;
    }

    public void setUserName(JTextField userName) {
        this.userName = userName;
    }

    public JTextField getHostName() {
        return hostName;
    }

    public void setHostName(JTextField hostName) {
        this.hostName = hostName;
    }

    public JTextField getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(JTextField portNumber) {
        this.portNumber = portNumber;
    }

    public JTextField getPathDirServer() {
        return pathDirServer;
    }

    public void setPathDirServer(JTextField pathDirServer) {
        this.pathDirServer = pathDirServer;
    }

    public JTextField getPathFileLocal() {
        return pathFileLocal;
    }

    public void setPathFileLocal(JTextField pathFileLocal) {
        this.pathFileLocal = pathFileLocal;
    }
}
