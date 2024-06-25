import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        } else {
            return false;
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + code + ", Title: " + title + ", Description: " + description + 
               ", Capacity: " + capacity + ", Schedule: " + schedule + ", Available Slots: " + getAvailableSlots();
    }
}

class Student {
    private String id;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Course is full. Cannot register for course: " + course.getTitle());
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Successfully dropped course: " + course.getTitle());
        } else {
            System.out.println("You are not registered for course: " + course.getTitle());
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name;
    }
}

class CourseDatabase {
    private HashMap<String, Course> courses;

    public CourseDatabase() {
        courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course course : courses.values()) {
                System.out.println(course);
            }
        }
    }
}

class StudentDatabase {
    private HashMap<String, Student> students;

    public StudentDatabase() {
        students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }
}

public class StudentCourseRegistrationSystem {
    private static CourseDatabase courseDatabase = new CourseDatabase();
    private static StudentDatabase studentDatabase = new StudentDatabase();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Add some sample courses
        courseDatabase.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "Mon-Wed-Fri 10:00-11:00"));
        courseDatabase.addCourse(new Course("MA101", "Calculus I", "Differential and Integral Calculus", 30, "Tue-Thu 09:00-10:30"));
        courseDatabase.addCourse(new Course("PH101", "Physics I", "Introduction to Mechanics", 25, "Mon-Wed 14:00-15:30"));

        // Add some sample students
        studentDatabase.addStudent(new Student("S001", "Alice"));
        studentDatabase.addStudent(new Student("S002", "Bob"));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. List Student Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left after nextInt()

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    listStudentCourses();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void listCourses() {
        courseDatabase.listCourses();
    }

    private static void registerForCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.getCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(course);
    }

    private static void dropCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.getCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
    }

    private static void listStudentCourses() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Courses registered by " + student.getName() + ":");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course);
        }
    }
}
