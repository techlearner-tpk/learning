package com.tushar.learning.camel.bindy;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = "\\,")
public class Student {

	@DataField(pos = 1, required = true)
	private String Id;

	@DataField(pos = 2, required = true)
	private String name;

	@DataField(pos = 3, required = true, trim= true)
	private String semester;

	@DataField(pos = 4)
	private long marks;
	
	public Student() {
	
	}
	

	public Student(String id, String name, String semester, long marks) {
		super();
		Id = id;
		this.name = name;
		this.semester = semester;
		this.marks = marks;
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
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public long getMarks() {
		return marks;
	}

	public void setMarks(long marks) {
		this.marks = marks;
	}
	
	@Override
	public String toString() {
		return "Student [Id=" + Id + ", name=" + name + ", semester="+ semester + "]";
	}	 
}