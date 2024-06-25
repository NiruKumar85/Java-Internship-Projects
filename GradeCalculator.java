import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input student details
        System.out.println("Enter student details:");
        System.out.print("Name: ");
        String studentName = scanner.nextLine();
        System.out.print("Class: ");
        String studentClass = scanner.nextLine();
        System.out.print("School Name: ");
        String schoolName = scanner.nextLine();

        // Input number of subjects and marks for each subject
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume newline left after nextInt()

        String[] subjects = new String[numSubjects];
        int[] marks = new int[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter name of subject " + (i + 1) + ": ");
            subjects[i] = scanner.nextLine();
            System.out.print("Enter marks obtained in " + subjects[i] + " (out of 100): ");
            marks[i] = scanner.nextInt();
            scanner.nextLine(); // Consume newline left after nextInt()
        }

        // Calculate total marks
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Assign grade based on average percentage
        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display results
        System.out.println("\n\n----- Student Result -----");
        System.out.println("Name: " + studentName);
        System.out.println("Class: " + studentClass);
        System.out.println("School: " + schoolName);
        System.out.println("\nSubject-wise Marks:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
        }
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
