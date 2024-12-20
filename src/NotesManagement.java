import java.util.*;
import java.util.stream.Collectors;

public class NotesManagement {
    private final List<Notes> notes = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Lecture> lectures = new ArrayList<>();

    public void storeStudent(String matricule, String nom, int age) {
        students.add(new Student(matricule, nom, age));
    }

    public void storeLecture(String code, String nom, double coef) {
        lectures.add(new Lecture(code, nom, coef));
    }

    public void addNote(String matricule, String codeMatiere, double value) {
        Student student = students.stream()
                .filter(s -> s.getMatricule().equals(matricule))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Lecture lecture = lectures.stream()
                .filter(l -> l.getCode().equals(codeMatiere))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found"));

        notes.add(new Notes(value,student, lecture));
    }



    public void printNotesInMeritOrder() {
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage).reversed())
                .forEach(student ->  System.out.println(
                        "Nom: " + student.getName() + " "
                                + "Matricule: " + student.getMatricule() + " "
                                + "Average Score : " + student.getAverage()));
    }

    public void printFirstAndLast() {
        students.stream().max(Comparator.comparingDouble(Student::getAverage))
                .ifPresent(student -> System.out.println("First : \n"
                        + "Name: " + student.getName() + " "
                        + "Matricule: " + student.getMatricule() + " "
                        + "Average score : " + student.getAverage()
                ));

        notes.stream().min(Comparator.comparingDouble(Notes::getValue))
                .ifPresent(note -> System.out.println("Dernier : \n"
                        + "Nom: " + note.getStudent().getName() + " "
                        + "Matricule: " + note.getStudent().getMatricule() + " "
                        + "Average Score : " + note.getStudent().getAverage()
                ));
    }

    public void printAdmitted() {
        long admitted = students.stream()
                .filter(student -> student.getAverage() >= 10)
                .count();
        System.out.println("Number of student admitted : " + admitted);
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage).reversed())
                .filter(student -> student.getAverage() >= 10)
                .forEach(student ->  System.out.println(
                        "Nom: " + student.getName() + " "
                        + "Matricule: " + student.getMatricule() + " "
                        + "Average Score : " + student.getAverage()));
    }

    public void printClasseAverageScore() {
        double average = notes.stream()
                .mapToDouble(Notes::getValue)
                .average()
                .orElse(0.0);
        System.out.println("The average score of the class is : " + average);
    }

    public void printStudentWithAverageScoreGreaterThanClassAverage() {
        double classAverage = notes.stream()
                .mapToDouble(Notes::getValue)
                .average()
                .orElse(0.0);

        students.stream()
                .filter(student -> student.getAverage() >= classAverage)
                .forEach(student ->  System.out.println(
                        "Nom: " + student.getName() + " "
                                + "Matricule: " + student.getMatricule() + " "
                                + "Average Score : " + student.getAverage()));

    }


    public static void main(String[] args) {
        NotesManagement notesManagement = new NotesManagement();

        Scanner scanner = new Scanner(System.in);

        System.out.println("===================================== Store Students =====================================");
        System.out.print("Enter the number of student : ");
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Matricule : ");
            String matricule = scanner.nextLine();
            System.out.print("Name : ");
            String nom = scanner.nextLine();
            System.out.print("Âge : ");
            int age = scanner.nextInt();
            scanner.nextLine();
            notesManagement.storeStudent(matricule, nom, age);
            String message = (i < n - 1) ? "Next student =>" : "Store complete!";
            System.out.println(message);
        }

        System.out.println("===================================== Store Lectures =====================================");
        System.out.print("Enter the number of lecture: ");
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            System.out.print("Code : ");
            String code = scanner.nextLine();
            System.out.print("Name : ");
            String nomMatiere = scanner.nextLine();
            System.out.print("Coefficient : ");
            double coef = scanner.nextDouble();
            scanner.nextLine();
            notesManagement.storeLecture(code, nomMatiere, coef);
            String message = (i < n - 1) ? "Next lecture =>" : "Store complete!";
            System.out.println(message);
        }

        System.out.println("===================================== Store Notes =====================================");
        for (Student student : notesManagement.students) {
            System.out.println("Print note for student: " + student.getName());
            for (Lecture lecture : notesManagement.lectures) {
                System.out.print("Note of " + lecture.getName() + " : ");
                double value = scanner.nextDouble();
                scanner.nextLine();
                notesManagement.addNote(student.getMatricule(), lecture.getCode(), value);
            }
        }

        /*
            =================== Calcul of student's average score ===============================
         */

        for (Student student : notesManagement.students){
            ArrayList<Notes> _notes = notesManagement.notes.stream()
                    .filter(note -> note.getStudent().getMatricule().equals(student.getMatricule()))
                    .collect(Collectors.toCollection(ArrayList::new));
            student.StudentMGP(_notes);
        }

        System.out.println("\n===================================== Menu =====================================\n");
        int choice;
        int end;
        do {
            System.out.println("*******************************************************************************");
            System.out.println("Welcome to Java Rappels!");
            System.out.println("1. Ordered score");
            System.out.println("2. First and last score");
            System.out.println("3. Score > 10");
            System.out.println("4. Average score ");
            System.out.println("5. Score greater than average ");
            System.out.println("6. Exit");
            System.out.println("*******************************************************************************");
            System.out.println("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 :
                    System.out.println("========== Ordered Score ==========");
                    notesManagement.printNotesInMeritOrder();
                    System.out.println("===================================");
                    break;
                case 2:
                    System.out.println("========== First and last Score ==========");
                    notesManagement.printFirstAndLast();
                    System.out.println("==========================================");
                    break;
                case 3:
                    System.out.println("========== Score > 10 ==========");
                    notesManagement.printAdmitted();
                    System.out.println("================================");
                    break;
                case 4 :
                    System.out.println("========== Average Score ==========");
                    notesManagement.printClasseAverageScore();
                    System.out.println("===================================");
                    break;
                case 5:
                    System.out.println("========== Score greater than average ==========");
                    notesManagement.printStudentWithAverageScoreGreaterThanClassAverage();
                    System.out.println("================================================");
                    break;
                case 6 :
                    System.out.println("===================");
                    System.out.println("Thanks you user!!! ");
                    System.out.println("===================");
                    break;
                default:
                    System.out.println("You have selected an incorrect value!");
            }
            System.out.println("Do you want continue? No(0)/ Yes(Other number)");
            end = scanner.nextInt();
        } while (end!=0);

        scanner.close();
    }
}