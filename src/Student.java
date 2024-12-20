import java.util.ArrayList;

public class Student {
    private String matricule;
    private String name;
    private int age;
    private double average;


    public Student(String matricule, String name, int age) {
        this.matricule = matricule;
        this.name = name;
        this.age = age;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public double getAverage() {
        return average;
    }

    public void StudentMGP(ArrayList<Notes> notes){
        double sumNotes = 0;
        int sumCoef = 0;
        for (Notes note : notes) {
             sumNotes += (note.getValue() * note.getLecture().getCoef());
             sumCoef += (int) note.getLecture().getCoef();
        }
        average = sumNotes / sumCoef;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
