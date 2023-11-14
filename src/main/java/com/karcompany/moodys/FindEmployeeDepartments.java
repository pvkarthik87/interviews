package com.karcompany.moodys;

import java.util.ArrayList;
import java.util.List;

public class FindEmployeeDepartments {

    class Emp {
        private final String id;
        private final String name;
        private final String departmentId;

        public Emp(String id, String name, String dId) {
            this.id = id;
            this.name = name;
            this.departmentId = dId;
        }
    }

    class Department {
        private final String id;
        private final String name;

        public Department(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }


    private static List<String> findEmployeesDepartment(List<Emp> employees, List<Department> departments) {
        return employees
                .stream()
                .filter(emp -> emp.name.startsWith("A"))
                .map(emp -> findEmployeeDepartmentName(emp.departmentId, departments))
                .toList();
    }

    private static String findEmployeeDepartmentName(String departmentId, List<Department> departments) {
        return departments
                .stream()
                .filter(department -> department.id.equals(departmentId))
                .map(department -> department.name)
                .toList().stream().findFirst().orElseThrow();
    }

    public static void main(String[] args) {
        System.out.println(findEmployeesDepartment(new ArrayList<>(), new ArrayList<>()));
    }
}
