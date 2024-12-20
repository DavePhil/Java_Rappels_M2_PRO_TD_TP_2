public class Notes {
    private double value;
    private Student student;
    private Lecture lecture;

    public Notes(double value, Student student, Lecture lecture) {
        this.value = value;
        this.student = student;
        this.lecture = lecture;
    }

    public Notes(){}

    public double getValue() {
        return value;
    }

    public void setNote(double value) {
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Lecture getLecture() {
        return lecture;
    }
}

