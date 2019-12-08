package sample.classes;

import java.util.ArrayList;

public class Patients {

    private ArrayList<Patient> listOfPatients = new ArrayList<>();

    public ArrayList<Patient> getListOfPatients() {
        return listOfPatients;
    }

//    пациент по фамилии
    public String getPatientBySurname(String surname){
        String patients="";
        for(Patient patient: listOfPatients){
            if(patient.getName()!=null){
                if(patient.getName().contains(surname)) {
                    patients += ("Ф.И.О: " + patient.getName() +
                            "\nНомер учётной карточки: " + patient.getId() +
                            "\nВид работы: " + patient.getProcedure() +
                            "\nСтоимость выполненой работы: " + patient.getPrice() +
                            "\nОтметка об оплате: " + patient.isPaid() +
                            "\nСумма задолженности за лечение: " + patient.getDebt() + "\n\n");
                    return patients;
                }
            }
        }
        return "Такой пациент не найден";
    }
//    справка о должниках
    public String getDebtors(){
        String debtors = "";
        for (Patient patient : listOfPatients){
            if(!patient.isPaid().equals("Оплачено")){
                debtors +=("Ф.И.О: " + patient.getName() +
                        "\nНомер учётной карточки: " + patient.getId() +
                        "\nВид работы: " + patient.getProcedure() +
                        "\nСтоимость выполненой работы: " + patient.getPrice() +
                        "\nОтметка об оплате: " + patient.isPaid() +
                        "\nСумма задолженности за лечение: " + patient.getDebt() + "\n\n");
            }
        }
     return debtors;
    }
//    удаление записей без задолженности
    public void removeAllPaidNotes(){
        for (int i =0;i<listOfPatients.size(); i++) {
            if (listOfPatients.get(i).isPaid().equals("Оплачено")) {
                System.out.println(listOfPatients.toString());
                listOfPatients.remove(i);
                i--;
            }
        }

        System.out.println("Delete succeed");
    }

    @Override
    public String toString() {
        String patients="";
        for (Patient patient:listOfPatients) {
            if(
                    (patient.getName() == null) && (patient.getId() == 0) && /*(patient.getProcedures() == null) &&*/ (patient.getPrice() == 0) && (!patient.isPaid().equals("Оплачено")) && (patient.getDebt() == 0)
            )
                continue;

            patients+=("Ф.И.О: "+ patient.getName()+
                    "\nНомер учётной карточки: "+patient.getId()+
                    "\nВид работы: "+patient.getProcedure()+
                    "\nСтоимость выполненой работы: "+patient.getPrice()+
                    "\nОтметка об оплате: "+patient.isPaid()+
                    "\nСумма задолженности за лечение: "+patient.getDebt()+"\n\n");
        }
        return patients;
    }
}
