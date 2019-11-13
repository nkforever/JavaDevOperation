
public class ClientProfile {
	
	static String patientID = "________", ssnArea ="  ", ssnGroup = "  ", ssnSerial = "    ", phoneNumber = "(   )-   -    ";
	static String streetNum = "_____", aptNum = "", zipcode = "_____";
	static String firstName= "", midName= "", lastName= "", streetName= "_____ __", cityName= "________", stateName= "__", PrimaryDoctor= "________", dateOfBirth= "", gender= "";
	static int active = 0;
	
	static boolean found = false; 
	ClientProfile(){
		
	}
	
	static void setID(String id){
		patientID = id;
	}
	static void setFName(String fn){
		firstName = fn;
	}
	static void setMName(String id){
		midName = id;
	}
	static void setLName(String ln){
		lastName = ln;
	}
	static void setDOB(String dob){
		dateOfBirth = dob;
	}
	static void setGender(String gd){
		gender = gd;
	}
	static void setPDoctor(String pd){
		PrimaryDoctor = pd;
	}
	static void setSSNArea(String ssn) {
		ssnArea = ssn;
	}
	static void setSSNGroup(String ssn) {
		ssnGroup = ssn;
	}
	static void setSSNSerial(String ssn) {
		ssnSerial = ssn;
	}
	
	static void setStreetNum(String stnum) {
		streetNum = stnum;
	}static void setAptNum(String aptnum) {
		aptNum = aptnum;
	}static void setStreetName(String stname) {
		streetName = stname;
	}static void setCity(String city) {
		cityName = city;
	}static void setState(String state) {
		stateName = state;
	}static void setZipcode(String zip) {
		zipcode = zip;
	}static void setPhoneNumber(String phone) {
		phoneNumber = phone;
	}
	static void setActive(int n) {
		active = n;
	}
	
	public String toString(){
		return "ID: "+patientID +",  Name: "+firstName +" " + midName +" "+lastName +",  Address: "+ streetNum +" "+streetName+", "+cityName+", "+stateName +" "+zipcode;
		
	}
}
