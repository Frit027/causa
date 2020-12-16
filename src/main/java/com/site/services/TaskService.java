package com.site.services;

import com.site.dao.TaskDao;
import com.site.entities.Employee;
import com.site.entities.Task;
import com.site.utils.DateUtil;

import java.util.List;

public class TaskService {
    private final TaskDao taskDao = new TaskDao();

    public Task getTask(int id) {
        return taskDao.get(id);
    }

    public void saveTask(Task task) {
        taskDao.save(task);
    }

    public void deleteTask(Task task) {
        taskDao.delete(task);
    }

    public void deleteTasks(List<Task> tasks) {
        for (Task task : tasks) {
            deleteTask(task);
        }
    }

    public void updateTask(Task task) {
        taskDao.update(task);
    }

    public List<Task> gelAllTasks() {
        return taskDao.getAll();
    }

    public void setExecutor(List<Task> tasks, Employee emp) {
        for (Task task : tasks) {
            task.setEmployee(emp);
            updateTask(task);
        }
    }

    public boolean isTaskOverdue(Task task) {
        return !DateUtil.isDateNotExpired(task.getDeadline());
    }
}
