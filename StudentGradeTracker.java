import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();

        System.out.println("Enter student grades (type -1 to stop):");
        while (true) {
            int grade = scanner.nextInt();
            if (grade == -1) break;
            grades.add(grade);
        }

        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            int highest = findHighest(grades);
            int lowest = findLowest(grades);
            double average = calculateAverage(grades);

            System.out.println("\nGrade Summary:");
            System.out.println("Highest Grade: " + highest);
            System.out.println("Lowest Grade: " + lowest);
            System.out.printf("Average Grade: %.2f\n", average);
        }

        scanner.close();
    }

    public static int findHighest(ArrayList<Integer> grades) {
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public static int findLowest(ArrayList<Integer> grades) {
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }
}
