package base;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import form.Data;
import form.MainForm;

import java.io.*;
import javax.swing.JOptionPane;

public class SFTPFileUpload {
    public SFTPFileUpload(Data formData) throws FileNotFoundException, IOException {
        System.out.println("Данные: \n" + formData.getUserName() + "\n" + formData.getHostName() + "\n" + formData.getPassword() + "\n" +formData.getPortNumber());
        Session session = null;
        Channel channel = null;
        ChannelSftp sftpChannel = null;
//        int port = Integer.parseInt(formData.getPortNumber().getText().trim());
        System.out.println(formData.getPortNumber().trim());
        try {
            JSch jsch = new JSch();
            //jsch.setKnownHosts("/home/user/.putty/sshhostkeys");
            session = jsch.getSession(formData.getUserName(), formData.getHostName(),22
                    /*Integer.parseInt(formData.getPortNumber().getText().trim())*/);
            session.setPassword("123"/*formData.getPassword().getText()*/);
            UserInfo ui = new MyUserInfo() {
                public void showMessage(String message) {
                    JOptionPane.showMessageDialog(null, message);
                }
                public boolean promptYesNo(String message) {
                    Object[] options = {"yes", "no"};
                    int foo = JOptionPane.showOptionDialog(null,
                            message,
                            "Warning",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    return foo == 0;
                }
            };
            session.setUserInfo(ui);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel("sftp");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();
            sftpChannel = (ChannelSftp) channel;

            byte[] bufr = new byte[(int) new File(formData.getPathFileLocal()).length()];
            FileInputStream fis = new FileInputStream(new File(formData.getPathFileLocal()));
            fis.read(bufr);
            ByteArrayInputStream fileStream = new ByteArrayInputStream(bufr);
            sftpChannel.put(fileStream, formData.getPathDirServer());
            fileStream.close();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (sftpChannel != null) {
                sftpChannel.exit();
            }
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    public static abstract class MyUserInfo
            implements UserInfo, UIKeyboardInteractive {
        public String getPassword() {
            return null;
        }
        public boolean promptYesNo(String str) {
            return false;
        }
        public String getPassphrase() {
            return null;
        }
        public boolean promptPassphrase(String message) {
            return false;
        }
        public boolean promptPassword(String message) {
            return false;
        }
        public void showMessage(String message) {
        }
        public String[] promptKeyboardInteractive(String destination,
                                                  String name,
                                                  String instruction,
                                                  String[] prompt,
                                                  boolean[] echo) {
            return null;
        }
    }

}
