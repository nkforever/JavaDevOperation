
public class EmployeeProfile {

	private static String user, role;
	private static String employeeID = "", firstName = "", midName = "", lastName = "", streetNum = "", aptNum = "",
			streetName = "", cityName = "", stateName = "", zipCode = "", gender = "", email = "", phoneNumber = "",
			DOB = "", ssnArea = "", ssnGroup = "", ssnSerial = "";
	static boolean found = false;

	private int addEditPatient = 0, viewPatient = 0, ownProfile = 1, viewBill = 0, processPayment = 0;
	
	public static String getSsnArea() {
		return ssnArea;
	}

	public static void setSsnArea(String ssnArea) {
		EmployeeProfile.ssnArea = ssnArea;
	}

	public static String getSsnGroup() {
		return ssnGroup;
	}

	public static void setSsnGroup(String ssnGroup) {
		EmployeeProfile.ssnGroup = ssnGroup;
	}

	public static String getSsnSerial() {
		return ssnSerial;
	}

	public static void setSsnSerial(String ssnSerial) {
		EmployeeProfile.ssnSerial = ssnSerial;
	}

	public static String getDOB() {
		return DOB;
	}

	public static void setDOB(String dOB) {
		DOB = dOB;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		EmployeeProfile.email = email;
	}

	public static String getZipCode() {
		return zipCode;
	}

	public static void setZipCode(String zipCode) {
		EmployeeProfile.zipCode = zipCode;
	}

	public static String getAptNum() {
		return aptNum;
	}

	public static void setAptNum(String aptNum) {
		EmployeeProfile.aptNum = aptNum;
	}

	public static String getStreetNum() {
		return streetNum;
	}

	public static void setStreetNum(String addressNum) {
		EmployeeProfile.streetNum = addressNum;
	}

	public static String getStreetName() {
		return streetName;
	}

	public static void setStreetName(String streeName) {
		EmployeeProfile.streetName = streeName;
	}

	public static String getCityName() {
		return cityName;
	}

	public static void setCityName(String cityName) {
		EmployeeProfile.cityName = cityName;
	}

	public static String getStateName() {
		return stateName;
	}

	public static void setStateName(String stateName) {
		EmployeeProfile.stateName = stateName;
	}

	public static String getGender() {
		return gender;
	}

	public static void setGender(String gender) {
		EmployeeProfile.gender = gender;
	}

	public static String getPhoneNumber() {
		return phoneNumber;
	}

	public static void setPhoneNumber(String phoneNumber) {
		EmployeeProfile.phoneNumber = phoneNumber;
	}

	public static String getEmployeeID() {
		return employeeID;
	}

	public static void setEmployeeID(String eID) {
		employeeID = eID;
	}
	
	public static void setUser(String u) {
		user = u;
	}

	public static String getUser() {
		return user;
	}

	public static String getRole() {
		return role;
	}

	public static void setRole(String role) {
		EmployeeProfile.role = role;
	}

	public static void setFirstName(String fn) {
		firstName = fn;
	}

	public static String getFirstName() {
		return firstName;
	}

	public static void setMidName(String fn) {
		midName = fn;
	}

	public static String getMidName() {
		return midName;
	}

	public static void setLastName(String ln) {
		lastName = ln;
	}

	public static String getLastName() {
		return lastName;
	}

}
