package java8StreamCheatByJavaTchie;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	
	public static void main(String[] args) {
       
		//fetch all employees
		List<Employee> empList= EmployeeDataBase.getAllEmployees();
		
	//print each using java8
		//by me
		//empList.forEach(System.out::println);
	//by JavaTechie
		//empList.forEach(e-> System.out.println(e.getName()+" : "+e.getSalary()));
		
	//filter employeess belonging to development department
		//by me
		List<Employee> empFromDevDept= empList.stream().filter(e->e.getDept().equals("Development") && e.getSalary()>65000).collect(Collectors.toList());
		Set<Employee> empFromDevDeptSet= empList.stream().filter(e->e.getDept().equals("Development") && e.getSalary()>65000).collect(Collectors.toSet());
		Map<Integer,String> empFromDevDeptMap= empList.stream().filter(e->e.getDept().equals("Development") && e.getSalary()>65000).collect(Collectors.toMap(Employee::getId, Employee::getName));
		//empFromDevDeptMap.forEach((key,value)-> System.out.println(key+" : "+value));
		//empFromDevDeptMap.entrySet().stream().filter(entry->entry.getKey()>2).forEach(entry-> System.out.println(entry.getKey()+" : "+entry.getValue()));
		//empFromDevDeptMap.entrySet().stream().sorted(Comparator.comparing(e->e.getValue())).forEach(entry-> System.out.println(entry.getKey()+" : "+entry.getValue())))).orElseThrow();
	 //map
		//important
		List<String> empDeptList=empList.stream().map(e-> e.getDept()).distinct().collect(Collectors.toList());
		 // System.out.println(empDeptList);
		  List<Stream<String>> empPrjectNameList= empList.stream().map(e-> e.getProjects().stream().map(p->p.getName())).collect(Collectors.toList());  
	//flatmap
		List<String> empProjectNameUsingFlatMap= empList.stream().flatMap(e->e.getProjects().stream()).map(p->p.getName()).collect(Collectors.toList());
	    System.out.println(empProjectNameUsingFlatMap);
	    
	  //sored    
	}
}


class Project{

	private String projectCode;
	private String name ;
	@Override
	public String toString() {
		return "Project [projectCode=" + projectCode + ", name=" + name + ", client=" + client + ", buLeadName="
				+ buLeadName + "]";
	}
	public Project(String projectCode, String name, String client, String buLeadName) {
		super();
		this.projectCode = projectCode;
		this.name = name;
		this.client = client;
		this.buLeadName = buLeadName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getBuLeadName() {
		return buLeadName;
	}
	public void setBuLeadName(String buLeadName) {
		this.buLeadName = buLeadName;
	}
	private String client;
	private String buLeadName;
}


class Employee{
	
	private int id;
	private String name;
	private String dept;
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + ", projects=" + projects + ", salary="
				+ salary + ", gender=" + gender + "]";
	}
	private List<Project> projects;
	public Employee(int id, String name, String dept, List<Project> projects, double salary, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.projects = projects;
		this.salary = salary;
		this.gender = gender;
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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	private double salary;
	private String gender;
}
