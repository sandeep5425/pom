package com.persistent.pom.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Remark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date day;
	@Column(name = "taskperformed")
	private String taskPerformed;
	@Column(name = "joineddsm")
	private int joinedDSM;
	
	@ManyToOne
	@JoinColumn(name = "employeeid",nullable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="projectid",nullable = false)
	private Project project;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getTaskPerformed() {
		return taskPerformed;
	}
	public void setTaskPerformed(String taskPerformed) {
		this.taskPerformed = taskPerformed;
	}
	public int getJoinedDSM() {
		return joinedDSM;
	}
	public void setJoinedDSM(int joinedDSM) {
		this.joinedDSM = joinedDSM;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
