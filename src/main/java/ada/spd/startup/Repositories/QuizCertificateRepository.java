package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Question;
import ada.spd.startup.Domains.QuizCertificate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizCertificateRepository extends CrudRepository<QuizCertificate, Long> {


    @Query(value = "select u from QuizCertificate u where u.quiz.id= :quizId and u.user.id=:userId")
    QuizCertificate findQuestionByQuizId(long quizId, long userId);


    @Query(value = "select u from QuizCertificate u where u.grade>=73 and u.user.id=:userId")
    List<QuizCertificate> findQuizCertificateByUserIdAAndGrade(long userId);

}
