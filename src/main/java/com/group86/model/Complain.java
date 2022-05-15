package com.group86.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complain")
public class Complain implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
	
	private String complaincategory;
	
	private String complain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComplaincategory() {
		return complaincategory;
	}

	public void setComplaincategory(String complaincategory) {
		this.complaincategory = complaincategory;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

	@Override
	public String toString() {
		return "Complain [id=" + id + ", complaincategory=" + complaincategory + ", complain=" + complain + "]";
	}
	
	
}
