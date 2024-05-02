package webtest.test;

import org.openqa.selenium.WebElement;

public class Webtable {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int salary;
    private String department;
    private WebElement action;

    public Webtable() {
    }
    public Webtable(String firstName, String lastName, int age, String email, int salary, String department, WebElement action) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.action = action;
    }
    public String getFirstName() {
        return firstName;
    }

    public WebElement getAction() {
        return action;
    }

    public void setAction(WebElement action) {
        this.action = action;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Webtable { " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", action=" + action.getText() +
                '}';
    }
}
