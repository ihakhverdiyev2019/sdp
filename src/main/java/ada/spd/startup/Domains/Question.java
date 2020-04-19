package ada.spd.startup.Domains;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;
    private String[] questionOption;

    private int correctAsnwer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    public Question() {
    }

    public Question(String question, String[] questionOption, int correctAsnwer) {
        this.question = question;
        this.questionOption = questionOption;
        this.correctAsnwer = correctAsnwer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(String[] questionOption) {
        this.questionOption = questionOption;
    }

    public int getCorrectAsnwer() {
        return correctAsnwer;
    }

    public void setCorrectAsnwer(int correctAsnwer) {
        this.correctAsnwer = correctAsnwer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
