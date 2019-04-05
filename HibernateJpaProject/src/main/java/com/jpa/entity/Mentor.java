package com.jpa.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mentorId;
    private String mentorName;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getMentorId() {
        return mentorId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return mentorId == mentor.mentorId &&
                Objects.equals(mentorName, mentor.mentorName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mentorId, mentorName);
    }
}
