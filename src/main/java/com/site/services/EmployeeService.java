package com.site.services;

import com.site.dao.EmployeeDao;
import com.site.dao.ValidMonthDao;
import com.site.entities.Employee;
import com.site.entities.Task;
import com.site.entities.ValidMonth;
import com.site.utils.DateUtil;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final ValidMonthDao validMonthDao = new ValidMonthDao();

    public Employee getEmployee(int id) {
        return employeeDao.get(id);
    }

    public void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeDao.delete(getEmployee(id));
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    public List<Employee> getWorkedEmployees() {
        return employeeDao.getWorkedEmployees();
    }

    public List<Employee> getFiredEmployees() {
        return employeeDao.getFiredEmployees();
    }

    public List<Task> getAllTasksById(int id) {
        return employeeDao.gelAllTasks(id);
    }

    public void setFired(int id) {
        Employee emp = getEmployee(id);
        emp.setFired(new Date());
        updateEmployee(emp);
    }

    public void returnToWork(int id) {
        Employee emp = getEmployee(id);
        emp.setFired(null);
        updateEmployee(emp);
    }

    public List<Employee> getWorkedEmployeesWithoutEmp(int id) {
        List<Employee> list = getWorkedEmployees();
        list.removeIf(emp -> emp.getId() == id);
        return list;
    }

    public void addPremium(Employee emp, int percent) {
        checkMonth();
        double premium = (double) emp.getSalary() * percent / 100;
        emp.setPremium(emp.getPremium() + premium);
    }

    public void addBonus(Employee emp) {
        checkMonth();
        emp.setCountBonuses(emp.getCountBonuses() + 1);
        updateEmployee(emp);
    }

    public void addFine(Employee emp) {
        checkMonth();
        emp.setCountFines(emp.getCountFines() + 1);
        updateEmployee(emp);
    }

    public double getTotalSalary() {
        double res = 0;
        for (Employee emp : getWorkedEmployees()) {
            res += emp.getSalary();
        }
        return res;
    }

    public double getTotalPremium() {
        double res = 0;
        for (Employee emp : getWorkedEmployees()) {
            res += emp.getPremium();
        }
        return res;
    }

    private void checkMonth() {
        List<ValidMonth> months = validMonthDao.getAll();

        if (months.isEmpty()) {
            validMonthDao.save(new ValidMonth(new Date()));
        } else {
            ValidMonth month = months.get(0);
            if (!DateUtil.isSameMonth(month.getMonth())) {
                month.setMonth(new Date());
                validMonthDao.update(month);
                updateMonetaryData();
            }
        }
    }

    private void updateMonetaryData() {
        employeeDao.updateMonetaryData();
    }

    public List<Employee> getTop5Premium(List<Employee> employees) {
        employees.sort(Comparator.comparing(Employee::getPremium).reversed());
        return getTop5(employees);
    }

    public List<Employee> getTop5Bonus(List<Employee> employees) {
        employees.sort(Comparator.comparing(Employee::getCountBonuses).reversed());
        return getTop5(employees);
    }

    public List<Employee> getTop5Fine(List<Employee> employees) {
        employees.sort(Comparator.comparing(Employee::getCountFines).reversed());
        return getTop5(employees);
    }

    private List<Employee> getTop5(List<Employee> employees) {
        return employees.stream().limit(5).collect(Collectors.toList());
    }
}
