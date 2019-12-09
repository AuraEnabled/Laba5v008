package sample.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Patients implements Serializable {

    private ArrayList<Patient> listOfPatients = new ArrayList<>();

    public ArrayList<Patient> getListOfPatients() {
        return listOfPatients;
    }
}
