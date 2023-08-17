package edu.kh.emp.model.vo;

//vo = 값 저장용 객체 =DB 조회 결과 한 행을 저장
public class Employee {

	private int empId; // 사원 번호
	private String empName; //사원명
	private String empNo; //주민번호
	private String email; // 이메일
	private String phone; // 전화번호
	private String departmentTitle; // 부서명
	private String jobName; // 직급명
	private int salary; // 급여
	
	private String deptCode; // 부서코드
	private String jobCode; // 직급코드
	private String salLevel; // 급여등급
	private double bouns; // 보너스
	private int managementId; // 사수번호
	private int input;
	
	
	public Employee() {}
	
	public Employee(String departmentTitle, int salary) {
		super();
		this.departmentTitle = departmentTitle;
		this.salary = salary;
	}
	
	public Employee(int empId2, String empName2, String empNo2, String email2, String phone2, String deptCode2, String jobCode2, String salaryLevel, int salary2, double bonus, int managementId2, int input) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bouns = bonus;
		this.managementId = managementId;
		this.input = input;
	}
	
	public Employee(int empId) {
		this.empId = empId;
	}
	
	public Employee(int empId, String empName, String empNo, String email, String phone, String deptCode, String jobCode,
			 String salLevel, int salary, double bonus, int managementId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bouns = bonus;
		this.managementId = managementId;
		
	}

	public Employee(int empId, String empName, String empNo, String email, String phone, String departmentTitle,
			String jobName, int salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.departmentTitle = departmentTitle;
		this.jobName = jobName;
		this.salary = salary;
	}
	

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDepartmentTitle() {
		return departmentTitle;
	}
	public void setDepartmentTitle(String departmentTitle) {
		this.departmentTitle = departmentTitle;
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
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getSalLevel() {
		return salLevel;
	}
	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}
	public double getBouns() {
		return bouns;
	}
	public void setBouns(double bouns) {
		this.bouns = bouns;
	}
	public int getManagementId() {
		return managementId;
	}
	public void setManagementId(int managementId) {
		this.managementId = managementId;
	}


	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
				+ ", phone=" + phone + ", departmentTitle=" + departmentTitle + ", jobName=" + jobName + ", salary="
				+ salary + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", bouns="
				+ bouns + ", managementId=" + managementId + "]";
	}
	
	
	
}
