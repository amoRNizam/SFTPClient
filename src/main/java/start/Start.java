package start;

import base.SFTPFileUpload;
import form.MainForm;

import java.io.IOException;


public class Start {
    public static void main(String[] args) throws IOException {
        MainForm mainForm = new MainForm();
        mainForm.pack();
//        mainForm.setSize(new Dimension(400, 300));
        mainForm.setVisible(true);

//        new SFTPFileUpload(mainForm, "123", "123");
//        saveDataForm(mainForm);
    }
}
