package com.tushar.learning.camel.bindy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CamelBindyTest extends CamelTestSupport {

	@Test
	public void testMock() throws Exception {
		template.sendBody("direct:bindyStart", "test"); // start route
		Thread.sleep(2000);
		File target = new File("target/outbox/Result.csv");
		assertTrue("File not moved", target.exists());
	}

	public void setUp() throws Exception {		
		deleteDirectory("target/outbox");
		super.setUp();
	}

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:bindyStart").log("Start Test").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						List<Student> students = new ArrayList<Student>();
						Student t = new Student("1", "Tushar", "1", 51);
						Student s = new Student("2", "Shilpa", "1", 60);
						Student p = new Student("3", "Prafull", "1", 60);
						students.add(t);
						students.add(s);
						students.add(p);
						exchange.setProperty("students", students);

						List<Teacher> teachers = new ArrayList<Teacher>();
						Teacher n = new Teacher("1", "Nilima", "Computer");
						Teacher d = new Teacher("2", "Dipak", "Mechanics");
						teachers.add(n);
						teachers.add(d);
						exchange.setProperty("teachers", teachers);
					}
				}).filter().method(FilterProcessor.class, "isStudentsNotEmpty").process(new Processor() {
					@SuppressWarnings("unchecked")
					public void process(Exchange exchange) throws Exception {
						List<Student> records = (List<Student>) exchange.getProperty("students");
						exchange.getIn().setBody(records, List.class);
					}
				}).marshal().bindy(BindyType.Csv, Student.class).split().tokenize("\n", 1).streaming().bean(new LastElementRemovalBean())
						.to("file://target/outbox?fileName=Result.csv&fileExist=Append").end().filter()
						.method(FilterProcessor.class, "isTeachersNotEmpty").process(new Processor() {
							public void process(Exchange exchange) throws Exception {
								@SuppressWarnings("unchecked")
								List<Teacher> records = (List<Teacher>) exchange.getProperty("teachers");
								exchange.getIn().setBody(records, List.class);
							}
						}).marshal().bindy(BindyType.Csv, Teacher.class).split().tokenize("\n", 1).streaming()
						.bean(new LastElementRemovalBean()).to("file://target/outbox?fileName=Result.csv&fileExist=Append").end();

			}
		};
	}
}
