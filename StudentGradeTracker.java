import java.util.*;

class Student {
    String name;
    ArrayList<Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return grades.isEmpty() ? 0 : (double) sum / grades.size();
    }

    public int findHighest() {
        return grades.stream().max(Integer::compare).orElse(0);
    }

    public int findLowest() {
        return grades.stream().min(Integer::compare).orElse(0);
    }

    public void displayGrades() {
        System.out.println("Grades: " + grades);
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Student> students = new HashMap<>();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Student Report");
            System.out.println("4. View Class Summary");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    students.put(name, new Student(name));
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter student name: ");
                    name = scanner.nextLine();
                    if (students.containsKey(name)) {
                        System.out.print("Enter grade: ");
                        int grade = scanner.nextInt();
                        students.get(name).addGrade(grade);
                        System.out.println("Grade added successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student name: ");
                    name = scanner.nextLine();
                    if (students.containsKey(name)) {
                        Student student = students.get(name);
                        student.displayGrades();
                        System.out.println("Highest Grade: " + student.findHighest());
                        System.out.println("Lowest Grade: " + student.findLowest());
                        System.out.printf("Average Grade: %.2f\n", student.calculateAverage());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    if (students.isEmpty()) {
                        System.out.println("No students available.");
                    } else {
                        double totalAverage = 0;
                        int count = 0;
                        for (Student student : students.values()) {
                            totalAverage += student.calculateAverage();
                            count++;
                        }
                        System.out.printf("Class Average: %.2f\n", totalAverage / count);
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
