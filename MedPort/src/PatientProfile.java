
public class PatientProfile {
	
	private static String patientID = "________", ssnArea = "  ", ssnGroup = "  ", ssnSerial = "    ",
			phoneNumber = "(   )-   -    ", streetNum = "_____", aptNum = "", zipcode = "_____", firstName = "",
			midName = "", lastName = "", streetName = "_____ __", cityName = "________", stateName = "__",
			PrimaryDoctor = "________", dateOfBirth = "", gender = "", DOB = " ";
	private static int active = 0;
	static boolean found = false;
	
	public static int getActive() {
		return active;
	}

	public static void setActive(int active) {
		PatientProfile.active = active;
	}



	PatientProfile(){
		
	}

	public static String getPatientID() {
		return patientID;
	}

	public static void setPatientID(String clientID) {
		PatientProfile.patientID = clientID;
	}

	public static String getSsnArea() {
		return ssnArea;
	}

	public static void setSsnArea(String ssnArea) {
		PatientProfile.ssnArea = ssnArea;
	}

	public static String getSsnGroup() {
		return ssnGroup;
	}

	public static void setSsnGroup(String ssnGroup) {
		PatientProfile.ssnGroup = ssnGroup;
	}

	public static String getSsnSerial() {
		return ssnSerial;
	}

	public static void setSsnSerial(String ssnSerial) {
		PatientProfile.ssnSerial = ssnSerial;
	}

	public static String getPhoneNumber() {
		return phoneNumber;
	}

	public static void setPhoneNumber(String phoneNumber) {
		PatientProfile.phoneNumber = phoneNumber;
	}

	public static String getStreetNum() {
		return streetNum;
	}

	public static void setStreetNum(String streetNum) {
		PatientProfile.streetNum = streetNum;
	}

	public static String getAptNum() {
		return aptNum;
	}

	public static void setAptNum(String aptNum) {
		PatientProfile.aptNum = aptNum;
	}

	public static String getZipcode() {
		return zipcode;
	}

	public static void setZipcode(String zipcode) {
		PatientProfile.zipcode = zipcode;
	}

	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String firstName) {
		PatientProfile.firstName = firstName;
	}

	public static String getMidName() {
		return midName;
	}

	public static void setMidName(String midName) {
		PatientProfile.midName = midName;
	}

	public static String getLastName() {
		return lastName;
	}

	public static void setLastName(String lastName) {
		PatientProfile.lastName = lastName;
	}

	public static String getStreetName() {
		return streetName;
	}

	public static void setStreetName(String streetName) {
		PatientProfile.streetName = streetName;
	}

	public static String getCityName() {
		return cityName;
	}

	public static void setCityName(String cityName) {
		PatientProfile.cityName = cityName;
	}

	public static String getStateName() {
		return stateName;
	}

	public static void setStateName(String stateName) {
		PatientProfile.stateName = stateName;
	}

	public static String getPrimaryDoctor() {
		return PrimaryDoctor;
	}

	public static void setPrimaryDoctor(String primaryDoctor) {
		PrimaryDoctor = primaryDoctor;
	}

	public static String getDateOfBirth() {
		return dateOfBirth;
	}

	public static void setDateOfBirth(String dateOfBirth) {
		PatientProfile.dateOfBirth = dateOfBirth;
	}

	public static String getGender() {
		return gender;
	}

	public static void setGender(String gender) {
		PatientProfile.gender = gender;
	}

	public static String getDOB() {
		return DOB;
	}

	public static void setDOB(String DOB) {
		PatientProfile.DOB = DOB;
	}

	public static boolean isFound() {
		return found;
	}

	public static void setFound(boolean found) {
		PatientProfile.found = found;
	}
	
}