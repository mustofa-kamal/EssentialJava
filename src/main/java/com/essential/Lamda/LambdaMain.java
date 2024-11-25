package com.essential.Lamda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}

class EmployeeAdjuster {
    private List<Employee> employees;

    public EmployeeAdjuster(List<Employee> employees) {
        this.employees = employees;
    }

    // Chainable method to apply a salary adjustment logic
    public EmployeeAdjuster applySalaryAdjustment(Function<Employee, Employee> adjustmentLogic) {
        employees.forEach(adjustmentLogic::apply); // Apply the logic to each employee
        return this; // Return the current instance for chaining
    }

    // Method to get the final adjusted list of employees
    public List<Employee> getResult() {
        return employees;
    }
}


public class LambdaMain {

    // A generic method to apply business logic to employees
    public static List<Employee> applySalaryAdjustment(List<Employee> employees, Function<Employee, Employee> adjustmentLogic) {
        return employees.stream()
                .map(adjustmentLogic) // Apply the passed logic to each employee
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("Alice", 5000),
                new Employee("Bob", 7000),
                new Employee("Charlie", 6000)
        ));

        // Logic for applying a 10% bonus
        Function<Employee, Employee> bonusLogic = employee -> {
            employee.setSalary(employee.getSalary() * 1.10);
            return employee;
        };

        // Logic for deducting a 20% tax
        Function<Employee, Employee> taxLogic = employee -> {
            employee.setSalary(employee.getSalary() * 0.80);
            return employee;
        };

        // Logic for rewarding high performers with an extra $1000
        Function<Employee, Employee> highPerformerBonus = employee -> {
            if (employee.getSalary() > 6000) {
                employee.setSalary(employee.getSalary() + 1000);
            }
            return employee;
        };

        // Apply bonus logic
        //List<Employee> bonusApplied = applySalaryAdjustment(employees, bonusLogic);
        //System.out.println("After Bonus: " + bonusApplied);

        // Apply tax deduction
        //List<Employee> taxApplied = applySalaryAdjustment(employees, taxLogic);
        //System.out.println("After Tax: " + taxApplied);

        // Apply high performer bonus
        //List<Employee> highPerformerAdjusted = applySalaryAdjustment(employees, highPerformerBonus);
       //System.out.println("After High Performer Adjustment: " + highPerformerAdjusted);

        List<Employee> finalResult = new EmployeeAdjuster(employees)
                .applySalaryAdjustment(bonusLogic)
                .applySalaryAdjustment(taxLogic)
                .applySalaryAdjustment(highPerformerBonus)
                .getResult();

        System.out.println("Final Adjusted Employees: " + finalResult);





    }
}