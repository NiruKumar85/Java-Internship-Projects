import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

class Quiz {
    private Question[] questions;
    private int currentQuestionIndex;
    private int score;
    private Scanner scanner;
    private Timer timer;
    private boolean answerSubmitted;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.scanner = new Scanner(System.in);
        this.timer = new Timer();
        this.answerSubmitted = false;
    }

    public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            askQuestion(questions[currentQuestionIndex]);
        }

        // Display final results
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
    }

    private void askQuestion(Question question) {
        System.out.println("\n" + question.question);
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((i + 1) + ". " + question.options[i]);
        }

        answerSubmitted = false;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answerSubmitted) {
                    System.out.println("\nTime's up! Moving to the next question.");
                    submitAnswer(-1); // No answer given
                }
            }
        }, 10000); // 10 seconds for each question

        System.out.print("Enter your choice (1-4): ");
        int userAnswer = scanner.nextInt();
        submitAnswer(userAnswer - 1);
    }

    private void submitAnswer(int userAnswer) {
        answerSubmitted = true;
        timer.cancel();
        timer = new Timer();

        Question currentQuestion = questions[currentQuestionIndex];
        if (userAnswer == currentQuestion.correctAnswer) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Wrong! The correct answer was: " + (currentQuestion.correctAnswer + 1));
        }
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        Question[] questions = new Question[]{
            new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2),
            new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
            new Question("What is the largest planet in our Solar System?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2),
            new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 1)
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
