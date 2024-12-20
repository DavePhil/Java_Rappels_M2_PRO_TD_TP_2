public class Lecture {
    private String code;
    private String name;
    private double coef;

    public Lecture(String code, String name, double coef) {
        this.code = code;
        this.name = name;
        this.coef = coef;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }
}
