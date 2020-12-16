package com.site.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Expose
    @Column(name = "name")
    private String name;

    @Expose
    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    private String sex;

    @Column(name = "salary")
    private int salary;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date hired;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date fired;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "messenger_name")
    private String messengerName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messenger_id")
    private Messenger messenger;

    @Expose
    @Column(name = "premium", nullable = false, columnDefinition = "real default 0.0")
    private double premium;

    @Expose
    @Column(name = "count_bonuses", nullable = false, columnDefinition = "int default 0")
    private int countBonuses;

    @Expose
    @Column(name = "count_fines", nullable = false, columnDefinition = "int default 0")
    private int countFines;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Employee() { }

    public Employee(String name, String surname,
                    String patronymic, String email,
                    String phone, String sex, int salary,
                    String hobby, String messengerName,
                    Date hired, Date fired, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.salary = salary;
        this.hobby = hobby;
        this.messengerName = messengerName;
        this.hired = hired;
        this.fired = fired;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
    }

    public Date getFired() {
        return fired;
    }

    public void setFired(Date fired) {
        this.fired = fired;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void addTask(Task task) {
        task.setEmployee(this);
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getMessengerName() {
        return messengerName;
    }

    public void setMessengerName(String messengerName) {
        this.messengerName = messengerName;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public int getCountBonuses() {
        return countBonuses;
    }

    public void setCountBonuses(int countBonuses) {
        this.countBonuses = countBonuses;
    }

    public int getCountFines() {
        return countFines;
    }

    public void setCountFines(int countFines) {
        this.countFines = countFines;
    }
}
