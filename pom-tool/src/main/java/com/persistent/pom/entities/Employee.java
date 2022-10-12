package com.persistent.pom.entities;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_master")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String empcode;
	private String name;
	private String email;
	
	@Column(name = "isactive")
	private int isActive;
	
	private int contributing;
	@Column(name = "joiningdate")
	private Date joiningDate;
	@Column(name = "leavingdate")
	private Date leavingDate;
	@Column(name="skillset")
	private String skillSet;
	
	@ManyToOne
	@JoinColumn(name = "roleid",nullable = false)
	private Roles roles;
	
	@ManyToOne
	@JoinColumn(name = "locationid",nullable = false)
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "projectid",nullable = false)
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getContributing() {
		return contributing;
	}

	public void setContributing(int contributing) {
		this.contributing = contributing;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public Roles getRole() {
		return roles;
	}

	public void setRole(Roles roles) {
		this.roles = roles;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
}
