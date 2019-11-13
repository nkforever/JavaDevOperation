
public class OwnProfile {

	private String user, role;
	private String employeeID = "", firstName = "", midName = "", lastName = "", addressNum = "", streeName = "",
			cityName = "", stateName = "", gender = "", phoneNumber = "";
	
	
	OwnProfile(){
		
	}

	String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String eID) {
		employeeID = eID;
	}
	
	public void setUser(String u) {
		user = u;
	}

	public String getUser() {
		return user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String ln) {
		lastName = ln;
	}

	public String getLastName() {
		return lastName;
	}
	
	
}
