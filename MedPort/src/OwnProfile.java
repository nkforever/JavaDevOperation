
public class OwnProfile {

	private static String user = " ", role = " ";
	private static String employeeID = " ", firstName = " ", midName = " ", lastName = " ", streetNum = " ",
			aptNum = " ", streetName = " ", cityName = " ", stateName = " ", zipCode = " ", gender = " ", email = " ",
			phoneNumber = " ", DOB = " ", ssnArea = " ", ssnGroup = " ", ssnSerial = " ";

	private static int userAdmin = 0, addEditPatient = 0, viewPatient = 0, ownProfile = 1, viewBill = 0,
			processPayment = 0, active = 1;

	public static int getActive() {
		return active;
	}

	public static void setActive(int active) {
		OwnProfile.active = active;
	}

	public static int getUserAdmin() {
		return userAdmin;
	}

	public static void setUserAdmin(int userAdmin) {
		OwnProfile.userAdmin = userAdmin;
	}

	public static int getAddEditPatient() {
		return addEditPatient;
	}

	public static void setAddEditPatient(int addEditPatient) {
		OwnProfile.addEditPatient = addEditPatient;
	}

	public static int getViewPatient() {
		return viewPatient;
	}

	public static void setViewPatient(int viewPatient) {
		OwnProfile.viewPatient = viewPatient;
	}

	public int getOwnProfile() {
		return ownProfile;
	}

	public static void setOwnProfile(int ownProfile) {
		OwnProfile.ownProfile = ownProfile;
	}

	public static int getViewBill() {
		return viewBill;
	}

	public static void setViewBill(int viewBill) {
		OwnProfile.viewBill = viewBill;
	}

	public static int getProcessPayment() {
		return processPayment;
	}

	public static void setProcessPayment(int processPayment) {
		OwnProfile.processPayment = processPayment;
	}
	
	public static String getSsnArea() {
		return ssnArea;
	}

	public static void setSsnArea(String ssnArea) {
		OwnProfile.ssnArea = ssnArea;
	}

	public static String getSsnGroup() {
		return ssnGroup;
	}

	public static void setSsnGroup(String ssnGroup) {
		OwnProfile.ssnGroup = ssnGroup;
	}

	public static String getSsnSerial() {
		return ssnSerial;
	}

	public static void setSsnSerial(String ssnSerial) {
		OwnProfile.ssnSerial = ssnSerial;
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
		OwnProfile.email = email;
	}

	public static String getZipCode() {
		return zipCode;
	}

	public static void setZipCode(String zipCode) {
		OwnProfile.zipCode = zipCode;
	}

	public static String getAptNum() {
		return aptNum;
	}

	public static void setAptNum(String aptNum) {
		OwnProfile.aptNum = aptNum;
	}

	public static String getStreetNum() {
		return streetNum;
	}

	public static void setStreetNum(String addressNum) {
		OwnProfile.streetNum = addressNum;
	}

	public static String getStreetName() {
		return streetName;
	}

	public static void setStreetName(String streeName) {
		OwnProfile.streetName = streeName;
	}

	public static String getCityName() {
		return cityName;
	}

	public static void setCityName(String cityName) {
		OwnProfile.cityName = cityName;
	}

	public static String getStateName() {
		return stateName;
	}

	public static void setStateName(String stateName) {
		OwnProfile.stateName = stateName;
	}

	public static String getGender() {
		return gender;
	}

	public static void setGender(String gender) {
		OwnProfile.gender = gender;
	}

	public static String getPhoneNumber() {
		return phoneNumber;
	}

	public static void setPhoneNumber(String phoneNumber) {
		OwnProfile.phoneNumber = phoneNumber;
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
		OwnProfile.role = role;
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
