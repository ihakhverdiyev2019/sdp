package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Question;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.StartupJoin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {




    @Query(value = "select u from Question u where u.quiz.id= :quizId")
    List<Question> findQuestionByQuizId(long quizId);
}
