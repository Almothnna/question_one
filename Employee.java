import java.util.Scanner;

public class Employee {

    private String name;
    private String position;
    private double salary;
    private int experience;
    private String educationalLevel;
    private EmployeeType employeeType;

    public Employee(String name, String position, double salary, int experience, String educationalLevel, EmployeeType employeeType) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.experience = experience;
        this.educationalLevel = educationalLevel;
        this.employeeType = employeeType;
    }

    public double calculateSalary() {
        double salary = this.salary + (this.salary * 5 / 100 * experience) + getEducationalLevelBonus();
        return salary;
    }

    public double calculateBonus() {
        double bonus = 0;
        if (employeeType == EmployeeType.FULL_TIME) {
            bonus = salary * 3 / 100;
        } else {
            bonus = salary * 1.5 / 100;
        }
        return bonus;
    }

    public double getEducationalLevelBonus() {
        double bonus = 0;
        if (educationalLevel.equals("Bachelor's degree")) {
            bonus = 500;
        } else if (educationalLevel.equals("Diploma")) {
            bonus = 250;
        }
        return bonus;
    }

    public double getTotalCompensation() {
        return calculateSalary() + calculateBonus();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.println("Enter employee position: ");
        String position = scanner.nextLine();

        System.out.println("Enter employee basic salary: ");
        double salary = scanner.nextDouble();

        System.out.println("Enter employee experience: ");
        int experience = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter employee educational level (1 for Bachelor's degree, 2 for Diploma): ");
        int educationalLevelCode = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        String educationalLevel;
        if (educationalLevelCode == 1) {
            educationalLevel = "Bachelor's degree";
        } else if (educationalLevelCode == 2) {
            educationalLevel = "Diploma";
        } else {
            educationalLevel = "Unknown";
            System.out.println("Invalid educational level code. Setting educational level to Unknown.");
        }

        System.out.println("Enter employee type (fulltime or parttime): ");
        String employeeTypeString = scanner.nextLine();
        EmployeeType employeeType;
        try {
            employeeType = EmployeeType.valueOf(employeeTypeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            employeeType = EmployeeType.UNKNOWN;
            System.out.println("Invalid employee type. Setting employee type to Unknown.");
        }

        Employee employee = new Employee(name, position, salary, experience, educationalLevel, employeeType);

        System.out.println("Employee name: " + employee.name);
        System.out.println("Employee position: " + employee.position);
        System.out.println("Employee basic salary: " + employee.salary);
        System.out.println("Employee experience: " + employee.experience);
        System.out.println("Employee educational level: " + employee.educationalLevel);
        System.out.println("Employee salary: " + employee.calculateSalary());
        System.out.println("Employee bonus: " + employee.calculateBonus());
        System.out.println("Employee total compensation: " + employee.getTotalCompensation());
    }
}

enum EmployeeType {
    FULLTIME,
    PARTTIME,
    UNKNOWN
}
