package models;

import java.util.List;

public class Department {
    private int id;
    private String departmentName;
    private String description;
    private List<User> departmentUsers;
    private int noOfUsers;
    private List<DepartmentNews> departmentNews;


    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }
}
