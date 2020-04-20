//package ada.spd.startup.Controllers.User;
//
//import ada.spd.startup.Domains.Quiz;
//import ada.spd.startup.Domains.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class UserQuizList {
//
//    @GetMapping(path = "/quizzes")
//    public String showAllQuizzes(HttpSession httpSession, Model model) {
//        User user = getLoggedInUser(request);
//        if (userToLogin == null) {
//            return "redirect:/login";
//        }
//
//        List<Quiz> allQuizzes = quizService.getAllQuizzes();
//
//        model.addAttribute("loggedInUser", userToLogin);
//        model.addAttribute("allQuizzes", allQuizzes);
//
//        return "quizzes";
//    }
//
//    @GetMapping(path = "/quiz/{id}")
//    public String showQuiz(HttpServletRequest request, Model model, @PathVariable int id) {
//        UserToLogin userToLogin = getLoggedInUser(request);
//        if (userToLogin == null) {
//            return "redirect:/login";
//        }
//
//        Quiz quiz = quizService.getQuiz(id);
//        List<Question> questions = quiz.getQuestions();
//
//        Collections.shuffle(questions);
//
//        int count = 10;
//        List<Question> questionsByCount = new ArrayList<>();
//
//        for (int i = 0; i < count; i++) {
//            questionsByCount.add(questions.get(i));
//        }
//
//        for(Question question : questionsByCount) {
//            Collections.shuffle(question.getAnswers());
//        }
//
//        List<QuestionsJson> questionsJsons = new ArrayList<>();
//
//        for(Question question : questionsByCount) {
//            String text = question.getText();
//            List<String> options = new ArrayList<>();
//            int correctIndex = 0;
//
//            for(Answer answer : question.getAnswers()) {
//                options.add(answer.getText());
//                if (answer.getIsCorrect() == 1) {
//                    correctIndex = question.getAnswers().indexOf(answer);
//                }
//            }
//
//            String correctResponse = "True";
//            String incorrectResponse = "False";
//            QuestionsJson questionsJson = new QuestionsJson(text, options, correctIndex, correctResponse, incorrectResponse);
//            questionsJsons.add(questionsJson);
//        }
//
//        Gson gson = new Gson();
//
//        model.addAttribute("loggedInUser", userToLogin);
//        model.addAttribute("questionsJson", gson.toJson(questionsJsons));
//        model.addAttribute("quiz", quiz);
//
//        return "quiz";
//    }
//
//    @PostMapping(path = "/results")
//    @ResponseBody
//    public String getResults(HttpServletRequest request, Model model, @RequestBody ResultsJson data) {
//        Map<String, String> responseMap = new HashMap<>();
//
//        Gson gson = new Gson();
//
//        UserToLogin userToLogin = getLoggedInUser(request);
//
//        if (userToLogin == null) {
//            responseMap.put("error", "login");
//            return gson.toJson(responseMap);
//        }
//
//        int quizId = data.getQuizId();
//        long timeNow = data.getTimeNow();
//        String username = data.getUsername();
//        int correctAnswers = data.getCorrectAnswers();
//        int incorrectAnswers = data.getIncorrectAnswers();
//
//        Date startTime = new Date(timeNow);
//
//        Quiz quiz = quizService.getQuiz(quizId);
//
//        if(quiz == null) {
//            return null;
//        }
//
//        User user = userService.getUserByUsername(username);
//
//        if (user == null) {
//            return null;
//        }
//
//        user.setTotalCorrectAnswers(user.getTotalCorrectAnswers() + correctAnswers);
//        user.setTotalIncorrectAnswers(user.getTotalIncorrectAnswers() + incorrectAnswers);
//
//        Result result = new Result();
//
//        result.setQuiz(quiz);
//        result.setUser(user);
//        result.setCorrectAnswers(correctAnswers);
//        result.setIncorrectAnswers(incorrectAnswers);
//        result.setStartTime(startTime);
//
//        resultService.saveResult(result);
//        userService.updateUser(user);
//
//
//        responseMap.put("status", "all done");
//
//        return gson.toJson(responseMap);
//    }
//}
