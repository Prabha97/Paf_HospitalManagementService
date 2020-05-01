package bean;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class DepartmentBean {

	private int departmentID;
	private int hospitalID;
	private String departmentName;
	private int head;
	private int staffVacancies;
	
	public DepartmentBean() {
		
	}
	
	public DepartmentBean(int hospitalID, int departmentID, String departmentName,int head, int staffVacancies) {
		this.hospitalID = hospitalID;
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.head = head;
		this.staffVacancies = staffVacancies;
	}
	
	public int getDepartment_ID() {
		return departmentID;
	}
	
	public int getHospital_ID() {
		return hospitalID;
	}
	
	public String getDepartment_Name() {
		return departmentName;
	}
	
	public int getHead() {
		return head;
	}
	
	public int getStaff_Vacancies() {
		return staffVacancies;
	}
	
	public void setDepartment_ID(int departmentID) {
		this.departmentID = departmentID;
	}
	
	public void setHospital_ID(int hospitalID) {
		this.hospitalID = hospitalID;
	}
	
	public void setDepartment_Name(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public void setHead(int head) {
		this.head = head;
	}
	
	public void setStaff_Vacancies(int staffVacancies) {
		this.staffVacancies = staffVacancies;
	}
}