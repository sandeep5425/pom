package com.persistent.pom.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name="startdate")
	private Date startDate;
	@Column(name="enddate")
	private Date endDate;
	
//	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
//	private List<Employee> employees;
//	
//	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
//	private List<Remarks> remarks;
//	
//	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
//	private List<Module> modules;
	
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
//	public List<Employee> getEmployees() {
//		return employees;
//	}
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}
//	public List<Remarks> getRemarks() {
//		return remarks;
//	}
//	public void setRemarks(List<Remarks> remarks) {
//		this.remarks = remarks;
//	}
//	public List<Module> getModules() {
//		return modules;
//	}
//	public void setModules(List<Module> modules) {
//		this.modules = modules;
//	}
	
}
