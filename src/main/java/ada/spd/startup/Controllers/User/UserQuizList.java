package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.*;
import ada.spd.startup.Repositories.QuestionRepository;
import ada.spd.startup.Repositories.QuizCertificateRepository;
import ada.spd.startup.Repositories.QuizRepository;
import ada.spd.startup.Repositories.UserRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserQuizList {

    private QuizRepository quizRepository;

    private QuestionRepository questionRepository;

    private UserRepository userRepository;

    private QuizCertificateRepository quizCertificateRepository;


    public UserQuizList(QuizRepository quizRepository, QuestionRepository questionRepository, UserRepository userRepository, QuizCertificateRepository quizCertificateRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.quizCertificateRepository = quizCertificateRepository;
    }


    @RequestMapping("/quizes")
    public String listOfquizes(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";

        model.addAttribute("user", user);
        model.addAttribute("quizes", quizRepository.findAll());


        return "ListOfQuizzes";
    }


    @RequestMapping("/quiz/{id}")
    public String EnterQuize(@PathVariable String id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        ListGetResult listGetResult = new ListGetResult();

        for (int i = 0; i < 4; i++) {
            listGetResult.addResult(new GetResult());
        }

        model.addAttribute("form", listGetResult);
        QuizCertificate quizCertificate = new QuizCertificate();
        quizCertificate.setQuiz(quizRepository.findById(Long.parseLong(id)).get());
        quizCertificate.setUser(user);
        model.addAttribute("user", user);
        model.addAttribute("questions", questionRepository.findQuestionByQuizId(Long.parseLong(id)));
        model.addAttribute("quiz", id);
        model.addAttribute("resultList", new ArrayList<GetResult>());

        quizCertificateRepository.save(quizCertificate);

        return "QuizPage";
    }


//    @RequestMapping("/quiz/{id}/question/{queID}/save")
//    public void EnterQuize(@PathVariable String id, @PathVariable String queID, @RequestParam("answer") String answer, Model model, HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        QuizCertificate quizCertificate = quizCertificateRepository.findQuestionByQuizId(Long.parseLong(id), user.getId());
//        Question question = questionRepository.findById(Long.parseLong(queID)).get();
//
//        if (question.getCorrectAsnwer().equals(answer)) {
//            quizCertificate.setGrade(quizCertificate.getGrade() + 1);
//        } else {
//
//        }
//
//        quizCertificateRepository.save(quizCertificate);
//
//    }


    @RequestMapping("/quiz/{id}/submit")
    public String submitQuiz(@PathVariable String id, HttpSession httpSession) throws IOException, DocumentException {
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";


        QuizCertificate quizCertificate = new QuizCertificate();
        quizCertificate.setQuiz(quizRepository.findById(Long.parseLong(id)).get());
        quizCertificate.setUser(user);


        String dest = user.getName() + quizCertificate.getQuiz().getName() + ".pdf";
        quizCertificate.setCertificateURL(dest);
        quizCertificateRepository.save(quizCertificate);


        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));


        document.open();


        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance("https://certificate-template.com/wp-content/uploads/2019/03/certificate-of-appreciation.png");
        image.scaleAbsolute(PageSize.A4.rotate());
        image.setAbsolutePosition(0, 0);
        canvas.addImage(image);
        Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
                Font.BOLD, BaseColor.BLACK);

        Font dateFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                Font.BOLD, BaseColor.BLACK);
        ColumnText ct = new ColumnText(canvas);

        ct.setSimpleColumn(600f, 200f, 300f, 300f);

        Paragraph p = new Paragraph(user.getName() + " " + user.getSurname(), redFont);
        ct.addElement(p);
        ct.go();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now).toString();

        ColumnText ctdate = new ColumnText(canvas);

        ctdate.setSimpleColumn(400f, 20f, 210f, 135f);
        Paragraph pdate = new Paragraph(date, dateFont);
        ctdate.addElement(pdate);
        ctdate.go();


        document.close();

        return "";
    }
}
