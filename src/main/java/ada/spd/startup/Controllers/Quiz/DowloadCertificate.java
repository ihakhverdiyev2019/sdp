package ada.spd.startup.Controllers.Quiz;


import ada.spd.startup.Domains.QuizCertificate;
import ada.spd.startup.Repositories.QuizCertificateRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DowloadCertificate {

    private QuizCertificateRepository quizCertificateRepository;


    public DowloadCertificate(QuizCertificateRepository quizCertificateRepository) {
        this.quizCertificateRepository = quizCertificateRepository;
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable String id, HttpServletResponse response) {
        QuizCertificate quizCertificate = quizCertificateRepository.findById(Long.parseLong(id)).get();

        String fileName = quizCertificate.getCertificateURL();

        System.out.println("Downloading file :- " + fileName);

        Path file = Paths.get("", fileName);
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/pdf");
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                // copies all bytes from a file to an output stream
                Files.copy(file, response.getOutputStream());
                // flushes output stream
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error :- " + e.getMessage());
            }
        } else {
            System.out.println("Sorry File not found!!!!");
        }

    }

}
