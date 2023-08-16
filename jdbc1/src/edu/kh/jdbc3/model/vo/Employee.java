package edu.kh.jdbc3.model.vo;

public class Employee {

	private String empName;
	private String jobName;
	private int salary;
	private String annulIncome;
	
	private String hireDate;
	private char gender; // 남자면 M, 여자는 F
	
	public Employee() {}

	public Employee(String empName, String hireDate, char gender) {
		super();
		this.empName = empName;
		this.hireDate = hireDate;
		this.gender = gender;
	}

	public String getAnnulIncome() {
		return annulIncome;
	}
	public void setAnnulIncome(String annulIncome) {
		this.annulIncome = annulIncome;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}

	
	
	public Employee(String empName, String jobName, int salary, String annulIncome) {
		super();
		this.empName = empName;
		this.jobName = jobName;
		this.salary = salary;
		this.annulIncome = annulIncome;
	}


	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getAnnulincome() {
		return annulIncome;
	}
	public void setAnnulincome(String annulincome) {
		this.annulIncome = annulincome;
	}

	
	@Override
	public String toString() {
		return "사원명 : "  + empName + " / 입사일 : " + hireDate + " / 성별 : " + gender;
	}
	
	
	
}

