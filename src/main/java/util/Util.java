package util;

import form.MainForm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Util {

    private static File dataFile = new File("session.txt");
//
//    public static void saveDataForm(MainForm mainForm) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
//            writer.write(mainForm.getUserName().getText() + "\n");
//            writer.write(mainForm.getPassword().getText() + "\n");
//            writer.flush();
//            writer.close();
//        }catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    public static MainForm fillFormSavedData(MainForm mainForm) {

        return mainForm;
    }
}
