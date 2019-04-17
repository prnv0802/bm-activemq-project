package com.techm.bm.messaging.amq.model;

import java.io.Serializable;

public class DummyModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private int age;

	public DummyModel(String name, String id, int age) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
	}

	public DummyModel(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "DummyModel [name=" + name + ", id=" + id + ", age=" + age + "]";
	}

}
