package com.tushar.learning.camel.bindy;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = "\\,")
public class Teacher {

	@DataField(pos = 1, required = true)
	private String Id;

	@DataField(pos = 2, required = true)
	private String name;

	@DataField(pos = 3, required = true, trim = true)
	private String subject;


	public Teacher() {
	}

	public Teacher(String id, String name, String subject) {
		super();
		Id = id;
		this.name = name;
		this.subject = subject;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSemester() {
		return subject;
	}

	public void setSemester(String semester) {
		this.subject = semester;
	}

	@Override
	public String toString() {
		return "Teacher [Id=" + Id + ", name=" + name + ", subject=" + subject +"]";
	}

}
