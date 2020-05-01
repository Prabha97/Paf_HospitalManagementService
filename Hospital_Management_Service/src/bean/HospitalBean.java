package bean;

public class HospitalBean {

	private int hospitalID;
	private String hospitalName;
	private String hospitalAddress;
	private String hospitalCity;
	private String hospitalPhone;
	private String hospitalEmail;
	private String hospitalDescription;
	private int openHours;

	public int getHospital_ID() {
		return hospitalID;
	}
	
	public String getHospital_Name() {
		return hospitalName;
	}
	
	public String getHospital_Address() {
		return hospitalAddress;
	}
	
	public String getHospital_City() {
		return hospitalCity;
	}
	
	public String getHospital_Phone() {
		return hospitalPhone;
	}
	
	public String getHospital_Email() {
		return hospitalEmail;
	}
	
	public String getHospital_Description() {
		return hospitalDescription;
	}
	
	public int getOpen_Hours() {
		return openHours;
	}
	
	public void setHospital_ID(int hospitalID) {
		this.hospitalID = hospitalID;
	}
	
	public void setHospital_Name(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	public void setHospital_Address(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	
	public void setHospital_City(String hospitalCity) {
		this.hospitalCity = hospitalCity;
	}
	
	public void setHospital_Phone(String hospitalPhone) {
		this.hospitalPhone = hospitalPhone;
	}
	
	public void setHospital_Email(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}
	
	public void setHospital_Description(String hospitalDescription) {
		this.hospitalDescription = hospitalDescription;
	}
	
	public void setOpen_Hours(int openHours) {
		this.openHours = openHours;
	}
}