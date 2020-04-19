package ada.spd.startup.Domains;

import ada.spd.startup.ENUMS.QuizLevel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double price;

    private QuizLevel quizLevel;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<QuizCertificate> quizCertificates;


    public Quiz() {
    }

    public Quiz(String name, double price, QuizLevel quizLevel) {
        this.name = name;
        this.price = price;
        this.quizLevel = quizLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public QuizLevel getQuizLevel() {
        return quizLevel;
    }

    public void setQuizLevel(QuizLevel quizLevel) {
        this.quizLevel = quizLevel;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
