
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBcontrol {

	private Connection mpCon ;

	DBcontrol() {	}

	Connection getcon() {	return mpCon;	}

	boolean checkConnection() {

		try {
//			mpCon = DriverManager.getConnection("jdbc:sqlserver://medport.database.windows.net:1433", "munov01",
//					"soumya98Wii-00");
//			mpCon = DriverManager.getConnection("jdbc:sqlserver://nk.database.windows.net:1433", "nainnn01",
//					"Nkforever2019");
			mpCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/medport", "root", "nkforever2019");
			
			return true;
		} catch (SQLException  e) {

			return false;
		}
	}

	public boolean validate(String user, String pass) {
		checkConnection();
		String checkuser = "SELECT * FROM employee_table WHERE username = \"" + user + "\" AND password = \"" + pass
				+ "\" ;";

		Statement statement;
		ResultSet resultSet;
		
		try {
			
			statement = mpCon.createStatement();
			resultSet = statement.executeQuery(checkuser);

			if (resultSet.next() & pass.equals(resultSet.getNString("password"))) {

				String employeeID = resultSet.getNString("employee_id");
				OwnProfile.setEmployeeID(employeeID);
				LoadOwnProfile(employeeID);

				mpCon.close();
				return true;
			} else {
				mpCon.close();
				return false;
			}
		} catch (SQLException e) {
			return false;
		}

	}

	public void getProfile(String id, String name, String ssn, String client) {

		if (client.equals("employee")) {
			searchByAdmin(id, name, ssn);
		} else {
			searchByStaff(id, name, ssn);
		}
	}

	// staff search
	public void searchByStaff(String id, String name, String ssn) {

		String checkFirstName = "SELECT * FROM patient_table where first_name = \"" + name + "\" AND ssnSerial = \""
				+ ssn + "\";";
		String checkLastName = "SELECT * FROM patient_table where last_name = \"" + name + "\" AND ssnSerial = \"" + ssn
				+ "\";";
		String checkID = "SELECT * FROM patient_table where patient_id = \"" + id + "\";";

		Statement statement2, statement3;
		ResultSet resultSet, resultSet2, resultSet3;
		try {
			checkConnection();

			Statement statement = mpCon.createStatement();
			resultSet = statement.executeQuery(checkFirstName);

			if (resultSet.next()) {
				PatientProfile.found = true;
				patientFound(resultSet);
				
			} // end if
			else {
				statement2 = mpCon.createStatement();
				resultSet2 = statement2.executeQuery(checkLastName);
				if (resultSet2.next()) {
					PatientProfile.found = true;
					patientFound(resultSet2);
					
				} else {
					statement3 = mpCon.createStatement();
					resultSet3 = statement3.executeQuery(checkID);
					if (resultSet3.next()) {
						PatientProfile.found = true;
						patientFound(resultSet3);
					}
				}
				
			} // end else

		} catch (SQLException e) {
			e.printStackTrace();
			PatientProfile.found = false;
		}

	}// end get patient profile
	
	public void searchByAdmin(String id, String name, String ssn) {
			
		String checkFirstName = "SELECT * FROM employee_info WHERE first_name = \"" + name + "\" AND ssnSerial = \""
				+ ssn + "\";";
		String checkLastName = "SELECT * FROM employee_info WHERE last_name = \"" + name + "\" AND ssnSerial = \"" + ssn
				+ "\";";
		String checkID = "SELECT * FROM employee_info WHERE employee_id = \"" + id + "\" OR user_id = \"" + id + "\";";

		Statement statement, statement2, statement3;
		ResultSet resultSet, resultSet2, resultSet3;

		try {
			checkConnection();

			statement = mpCon.createStatement();
			resultSet = statement.executeQuery(checkFirstName);
			if (resultSet.next()) {
				employeeFound(resultSet);

			} // end if
			else {
				statement2 = mpCon.createStatement();
				resultSet2 = statement2.executeQuery(checkLastName);
				if (resultSet2.next()) {
					employeeFound(resultSet2);
				} else {
					statement3 = mpCon.createStatement();
					resultSet3 = statement3.executeQuery(checkID);
					if (resultSet3.next()) {
						employeeFound(resultSet3);
					}
					else
						EmployeeProfile.found = false;
				} // end else
			}
		} catch (SQLException e) {
			EmployeeProfile.found = false;
		}
	}

	private void patientFound(ResultSet resultSet) {
		String id = "";
		try {
			id = resultSet.getNString("patient_id");
			PatientProfile.setPatientID(id);
			PatientProfile.setFirstName(resultSet.getNString("first_name"));
			PatientProfile.setMidName(resultSet.getNString("mid_name"));
			PatientProfile.setLastName(resultSet.getNString("last_name"));
			PatientProfile.setDOB(resultSet.getNString("DOB"));
			PatientProfile.setGender(resultSet.getNString("gender"));
			PatientProfile.setPrimaryDoctor(resultSet.getNString("primaryDoctor"));
			PatientProfile.setSsnArea(resultSet.getNString("ssnArea"));
			PatientProfile.setSsnGroup(resultSet.getNString("ssnGroup"));
			PatientProfile.setSsnSerial(resultSet.getNString("ssnSerial"));
			PatientProfile.setPhoneNumber(resultSet.getNString("phone_num"));
			PatientProfile.setActive(resultSet.getInt("active"));

			// getting address from address table
			setPatientAddress(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// end patient found

	private void employeeFound(ResultSet resultSet) {
		String id = "";
		EmployeeProfile.found = true;
		try {
			id = resultSet.getNString("employee_id");
			EmployeeProfile.setEmployeeID(id);
			EmployeeProfile.setRole(resultSet.getNString("role"));
			EmployeeProfile.setAddEditPatient(resultSet.getInt("addEditPatient"));
			EmployeeProfile.setViewPatient(resultSet.getInt("viewPatient"));
			EmployeeProfile.setUserAdmin(resultSet.getInt("userAdmin"));
			EmployeeProfile.setOwnProfile(resultSet.getInt("ownProfile"));
			EmployeeProfile.setViewBill(resultSet.getInt("viewBill"));
			EmployeeProfile.setProcessPayment(resultSet.getInt("processPayment"));
			EmployeeProfile.setActive(resultSet.getInt("active"));

			EmployeeProfile.setFirstName(resultSet.getNString("first_name"));
			EmployeeProfile.setMidName(resultSet.getNString("mid_name"));
			EmployeeProfile.setLastName(resultSet.getNString("last_name"));
			EmployeeProfile.setDOB(resultSet.getNString("DOB"));
			EmployeeProfile.setGender(resultSet.getNString("gender"));
			EmployeeProfile.setSsnArea(resultSet.getNString("ssnArea"));
			EmployeeProfile.setSsnGroup(resultSet.getNString("ssnGroup"));
			EmployeeProfile.setSsnSerial(resultSet.getNString("ssnSerial"));
			EmployeeProfile.setPhoneNumber(resultSet.getNString("phone_number"));
			EmployeeProfile.setEmail(resultSet.getNString("email"));

			// getting address from address table
			setEmployeeAddress(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// end user found

	public void LoadOwnProfile(String employeeID) {

		String loadProfile = "SELECT * FROM employee_info where employee_id = \"" + employeeID + "\" OR user_id = \""
				+ employeeID + "\";";
		Statement statement;
		ResultSet resultSet;

		try {
			checkConnection();
			statement = mpCon.createStatement();
			resultSet = statement.executeQuery(loadProfile);

			if (resultSet.next()) {
				OwnProfile.setEmployeeID(employeeID);
				OwnProfile.setAddEditPatient(resultSet.getInt("addEditPatient"));
				OwnProfile.setViewPatient(resultSet.getInt("viewPatient"));
				OwnProfile.setUserAdmin(resultSet.getInt("userAdmin"));
				OwnProfile.setOwnProfile(resultSet.getInt("ownProfile"));
				OwnProfile.setViewBill(resultSet.getInt("viewBill"));
				OwnProfile.setProcessPayment(resultSet.getInt("processPayment"));
				OwnProfile.setActive(resultSet.getInt("active"));

				OwnProfile.setFirstName(resultSet.getNString("first_name"));
				OwnProfile.setMidName(resultSet.getNString("mid_name"));
				OwnProfile.setLastName(resultSet.getNString("last_name"));
				OwnProfile.setDOB(resultSet.getNString("DOB"));
				OwnProfile.setGender(resultSet.getNString("gender"));
				OwnProfile.setSsnArea(resultSet.getNString("ssnArea"));
				OwnProfile.setSsnGroup(resultSet.getNString("ssnGroup"));
				OwnProfile.setSsnSerial(resultSet.getNString("ssnSerial"));
				OwnProfile.setPhoneNumber(resultSet.getNString("phone_number"));
				OwnProfile.setRole(resultSet.getNString("role"));
				OwnProfile.setEmail(resultSet.getNString("email"));

				// getting address from address table
				setOwnAddress(employeeID);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// end user found

	void setPatientAddress(String id) {
		try {
			PreparedStatement getAddress = mpCon.prepareStatement("SELECT * FROM address_table where address_id = ? ;");
			getAddress.setString(1, id);
			ResultSet resultset = getAddress.executeQuery();

			if (resultset.next()) {
				PatientProfile.setStreetNum(resultset.getNString("street_num"));
				PatientProfile.setAptNum(resultset.getNString("apt_num"));
				PatientProfile.setStreetName(resultset.getNString("street_name"));
				PatientProfile.setCityName(resultset.getNString("city"));
				PatientProfile.setStateName(resultset.getNString("state").toUpperCase());
				PatientProfile.setZipcode(resultset.getNString("zipcode"));
				mpCon.close();
			} // end if
			else {
				PatientProfile.setStreetNum("N/A");
				PatientProfile.setAptNum(" ");
				PatientProfile.setStreetName("        ");
				PatientProfile.setCityName("N/A   ");
				PatientProfile.setStateName("   ");
				PatientProfile.setZipcode("     ");
				mpCon.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}// end set Patient Address

	void setEmployeeAddress(String id) {
		try {
			PreparedStatement getAddress = mpCon.prepareStatement("SELECT * FROM address_table where address_id = ? ;");
			getAddress.setString(1, id);
			ResultSet resultset = getAddress.executeQuery();

			if (resultset.next()) {
				EmployeeProfile.setStreetNum(resultset.getNString("street_num"));
				EmployeeProfile.setAptNum(resultset.getNString("apt_num"));
				EmployeeProfile.setStreetName(resultset.getNString("street_name"));
				EmployeeProfile.setCityName(resultset.getNString("city"));
				EmployeeProfile.setStateName(resultset.getNString("state").toUpperCase());
				EmployeeProfile.setZipCode(resultset.getNString("zipcode"));
			} // end if
			else {
				EmployeeProfile.setStreetNum("N/A");
				EmployeeProfile.setAptNum(" ");
				EmployeeProfile.setStreetName("        ");
				EmployeeProfile.setCityName("N/A   ");
				EmployeeProfile.setStateName("   ");
				EmployeeProfile.setZipCode("     ");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}// end set Employee Address

	void setOwnAddress(String id) {
		try {
			PreparedStatement getAddress = mpCon.prepareStatement("SELECT * FROM address_table where address_id = ? ;");
			getAddress.setString(1, id);
			ResultSet resultset = getAddress.executeQuery();

			if (resultset.next()) {
				OwnProfile.setStreetName(resultset.getNString("street_num"));
				OwnProfile.setAptNum(resultset.getNString("apt_num"));
				OwnProfile.setStreetName(resultset.getNString("street_name"));
				OwnProfile.setCityName(resultset.getNString("city"));
				OwnProfile.setStateName(resultset.getNString("state").toUpperCase());
				OwnProfile.setZipCode(resultset.getNString("zipcode"));
			} // end if
			else {
				OwnProfile.setStreetNum("N/A");
				OwnProfile.setAptNum(" ");
				OwnProfile.setStreetName("        ");
				OwnProfile.setCityName("N/A   ");
				OwnProfile.setStateName("   ");
				OwnProfile.setZipCode("     ");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}// end set Employee Address
	
	void addPatientProfile(String patientID, String firstName, String midName, String lastName, String dateOfBirth,
			String gender, String primaryDoctor, String ssnArea, String ssnGroup, String ssnSerial, String phone_num) {

			try {
				checkConnection();

				String insert = "INSERT INTO patient_table (patient_id, first_name, mid_name, last_name, DOB, gender, "
						+ "primaryDoctor, ssnArea, ssnGroup, ssnSerial, phone_num, active, last_update) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())";
				
				PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
				preparedStatement.setString(1, patientID);
				preparedStatement.setString(2, firstName);
				preparedStatement.setString(3, midName);
				preparedStatement.setString(4, lastName);
				preparedStatement.setString(5, dateOfBirth);
				preparedStatement.setString(6, gender);
				preparedStatement.setString(7, primaryDoctor);
				preparedStatement.setString(8, ssnArea);
				preparedStatement.setString(9, ssnGroup);
				preparedStatement.setString(10, ssnSerial);
				preparedStatement.setString(11, phone_num);
			preparedStatement.setInt(12, 1);
				
			preparedStatement.executeUpdate();
				mpCon.close();
			} catch (SQLException e) {
				return;
			}

	}// add patient profile

	boolean updatePatientProfile(String firstName, String midName, String lastName, String dateOfBirth,
			String gender, String primaryDoctor, String ssnArea, String ssnGroup, String ssnSerial, String phone_num) {

		Statement preparedStatement;
		try {
			checkConnection();
			preparedStatement = mpCon.createStatement();

			String insert = "UPDATE patient_table "
					+ "SET first_name = '" + firstName + "', mid_name = '" + midName + "', last_name = '" + lastName
					+ "', DOB = '" + dateOfBirth + "', gender = '" + gender + "', primaryDoctor = '" + primaryDoctor
					+ "', ssnArea = '" + ssnArea + "', ssnGroup = '" + ssnGroup + "', ssnSerial = '" + ssnSerial
					+ "', phone_num = '" + phone_num + "', last_update = CURDATE(), update_by = '"
					+ OwnProfile.getUser() + "' WHERE patient_id = \"" + PatientProfile.getPatientID() + "\";";

			preparedStatement.executeUpdate(insert);
			mpCon.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}// add patient profile

	boolean updateEmployeeProfile(String firstName, String midName, String lastName,
			String gender, String dateOfBirth, String email, String phonenumber, String role, String ssnArea,
			String ssnGroup, String ssnSerial, int userAdmin, int addEditPatient, int viewPatient, int ownProfile,
			int viewBill, int processPayment, int active) {

		Statement statement;
		try {
			checkConnection();
			statement = mpCon.createStatement();

			String update = "UPDATE employee_info "
					+ "SET first_name = '" + firstName + "', mid_name = '" + midName + "', last_name = '"
					+ lastName + "', gender = '" + gender + "', DOB = '" + dateOfBirth + "', email = '" + email
					+ "', phone_number = '" + phonenumber + "', role = '" + role + "', ssnArea = '" + ssnArea
					+ "', ssnGroup = '" + ssnGroup + "', ssnSerial = '" + ssnSerial + "', last_update = CURDATE(), "
					+ "update_by = '" + OwnProfile.getUser() + "', userAdmin = '" + userAdmin + "' , addEditPatient = '"
					+ addEditPatient + "', viewPatient = '" + viewPatient + "', ownProfile = '" + ownProfile + "' "
					+ ", viewBill = '" + viewBill + "', processPayment = '" + processPayment + "' , active = '" + active
					+ "' WHERE employee_id = \"" + EmployeeProfile.getEmployeeID() + "\";";

			statement.executeUpdate(update);
			mpCon.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}// update employee profile

	void addAddress(String id, String streetNum, String aptNum,String streetName,  String cityName, String stateName, String zipcode ) {
		try {
		checkConnection();
		
		String insert = "INSERT INTO address_table (address_id, street_num, apt_num, street_name, city, state, zipcode, lastupdate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE())";
		
		PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
		preparedStatement.setString(1, id);
		preparedStatement.setString(2, streetNum);
		preparedStatement.setString(3, aptNum);
		preparedStatement.setString(4, streetName);
		preparedStatement.setString(5, cityName);
		preparedStatement.setString(6, stateName);
		preparedStatement.setString(7, zipcode);
		
		preparedStatement.executeUpdate();
		
		mpCon.close();
		}catch (SQLException e) {
			return;
		}
	}

	boolean updateAddress(String id, String streetNum, String aptNum, String streetName, String cityName,
			String stateName, String zipcode) {
		try {
			checkConnection();
			Statement statement = mpCon.createStatement();
		
			String updateAddress = "UPDATE address_table "
					+ "SET street_num = '" + streetNum + "', apt_num = '" + aptNum + "', street_name = '" + streetName
					+ "', city = '" + cityName + "', state = '" + stateName
					+ "', zipcode = '" + zipcode + "', lastupdate = CURDATE() , update_by = '" + OwnProfile.getUser()
					+ "' WHERE address_id = \"" + id + "\";";
		

		
			statement.executeUpdate(updateAddress);
		
			mpCon.close();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	void addID(String id) {
		try {
			checkConnection();
			String insert = "INSERT INTO id_table (id) "
					+ "VALUES (?);";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
			
			mpCon.close();
			}catch (SQLException e) {
				return;
			}
	}
	
	boolean addEmployeeProfile(String eeID, String userID, String firstName, String midName, String lastName,
			String gender, String dob, String email, String phone_number, String role, String ssnArea, String ssnGroup,
			String ssnSerial, int userAdmin,
			int addEditPatient, int viewPatient, int ownProfile, int viewBill, int processPayment, int active) {

		if (checkConnection()) {
			String insertProfile = "INSERT INTO employee_table (employee_id, user_id, first_name, mid_name, last_name"
					+ ", gender , DOB, email, phone_number, role, ssnArea, ssnGroup, ssnSerial, userAdmin, addEditPatient"
					+ ", viewPatient, ownProfile, viewBill, processPayment, active) "
					+ "VALUES ('" + eeID + "', '" + userID + "','" + firstName + "', '" + midName + "','" + "', '"
					+ lastName + "', '" + gender + "', '" + dob + "', '" + ssnArea + "','" + ssnGroup + "','"
					+ ssnSerial + "', 'CURDATE()'+ '" + userAdmin + "', '" + addEditPatient + "','" + viewPatient
					+ "','" + ownProfile + "','" + viewBill + "','" + processPayment + "','" + active + "';)";
			
			try {
				Statement	statement = mpCon.createStatement();
					statement.executeQuery(insertProfile);
				
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
		return false;

	}
	
	boolean checkIfPatientAlreadyExist(String firstName, String LastName, String ssnSerial) {
		checkConnection();
		
		try {
			PreparedStatement checkExisting = mpCon.prepareStatement("SELECT * FROM patient_table where (first_Name = ? OR last_name = ?) AND ssnSerial = ? ;");
			checkExisting.setString(1, firstName);
			checkExisting.setString(2, LastName);
			checkExisting.setString(3, ssnSerial);
			ResultSet resultSet = checkExisting.executeQuery();
		
			if(resultSet.next()) return true;
			
			
		} catch(SQLException e) {
			return false;
		}
		return false;
	}//end of check if patient already exist before adding new patient

	boolean checkIfEmployeeAlreadyExist(String firstName, String LastName, String ssnSerial) {
		checkConnection();

		try {
			PreparedStatement checkExisting = mpCon.prepareStatement(
					"SELECT * FROM employee_table where (first_Name = ? OR last_name = ?) AND ssnSerial = ? ;");
			checkExisting.setString(1, firstName);
			checkExisting.setString(2, LastName);
			checkExisting.setString(3, ssnSerial);
			ResultSet resultSet = checkExisting.executeQuery();

			if (resultSet.next())
				return true;

		} catch (SQLException e) {
			return false;
		}
		return false;
	}// end of check if patient already exist before adding new patient
	
	boolean checkExistingID(String id) {
		String checkID = "SELECT id FROM id_table where id = \"" + id + "\";";
		
		try {
			checkConnection();
			Statement statement11 = mpCon.createStatement();
			ResultSet resultSet11 = statement11.executeQuery(checkID);

			if (resultSet11.next()) {
				mpCon.close();
				return true;
			}
			mpCon.close();
			return false;
			
		} catch (SQLException e) {
			return false;
		}
	}//end check existing
	
	ArrayList<String> getDoctorList() {
		ArrayList<String> al = new ArrayList<String>();
		checkConnection();
		String list = "SELECT * FROM doctor_list ;";
		try {

			Statement statement = mpCon.createStatement();
			ResultSet rs = statement.executeQuery(list);
			while(rs.next()) {
				al.add("Dr. " + rs.getString(2) + " " + rs.getString(3));
				
			}

			mpCon.close();
			return al;
		} catch (SQLException e) {
			return null;
		}
		
	}//end get doctor array list
	
	boolean addDoctorToList(String id, String firstName, String lastName, int active) {

		try {
			checkConnection();
			String insert = "INSERT INTO doctor_list (dr_id, dr_firstname, doctor_lastname, active) "
					+ "VALUES (?, ?, ?, ?);";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setInt(4, 1);

			preparedStatement.executeUpdate();

			mpCon.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	boolean removeDoctorFromList(String firstName, String lastName) {
		try {
			checkConnection();

			String insert = "DELETE FROM doctor_list WHERE (dr_firstname = '" + firstName + "' AND doctor_lastname = '"
					+ lastName + "');";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);

			preparedStatement.executeUpdate();

			mpCon.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	ArrayList<String> getRoomList() {

		ArrayList<String> al = new ArrayList<String>();
		checkConnection();
		String list = "SELECT * FROM room_list ;";
		try {

			Statement statement = mpCon.createStatement();
			ResultSet rs = statement.executeQuery(list);
			while(rs.next()) {
				al.add(rs.getString(2) + " " +rs.getString(3));	
			}
			mpCon.close();
			return al;
		} catch (SQLException e) {
			return null;
		}
		
	}

	boolean addRoomToList(String name, int roomNum) {
		try {
			checkConnection();
			String check = "Select * FROM room_list WHERE name = \"" + name.toUpperCase() + "\" AND room_num = \""
					+ roomNum + "\"; ";
			Statement checkStatement = mpCon.createStatement();

			ResultSet rs = checkStatement.executeQuery(check);
			if (rs.next()) {
				mpCon.close();
				return false;
			} else {
			String insert = "INSERT INTO room_list (name, room_num) " + "VALUES (?, ?);";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
			preparedStatement.setString(1, name);
				preparedStatement.setInt(2, roomNum);

			preparedStatement.executeUpdate();

			mpCon.close();
			return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	boolean removeRoomFromList(String name, int num) {
		try {
			checkConnection();

			String insert = "DELETE FROM room_list WHERE (name = '" + name + "' AND room_num = '" + num + "');";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);

			preparedStatement.executeUpdate();

			mpCon.close();
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	ArrayList<String> getTreatmentList() {

		ArrayList<String> al = new ArrayList<String>();
		checkConnection();
		String list = "SELECT * FROM treatment_list ;";
		try {

			Statement statement = mpCon.createStatement();
			ResultSet rs = statement.executeQuery(list);
			while(rs.next()) {
				al.add(rs.getString(1) + " " + rs.getString(2));
			}
			mpCon.close();
			return al;
		} catch (SQLException e) {
			System.out.print("unable to get treatment list");
			return null;
		}
		
	}

	ArrayList<String> getTreatmentCostList() {

		ArrayList<String> al = new ArrayList<String>();
		checkConnection();
		String list = "SELECT * FROM treatment_list ;";
		try {

			Statement statement = mpCon.createStatement();
			ResultSet rs = statement.executeQuery(list);
			while (rs.next()) {
				al.add(rs.getString(4));
			}
			mpCon.close();
			return al;
		} catch (SQLException e) {
			System.out.print("unable to get treatment list");
			return null;
		}

	}

	boolean removeTreatmentFromList(String id, String description, double cost) {
		try {
			checkConnection();
			String insert = "DELETE FROM treatment_list WHERE (description = '" + description + "' AND cost = '" + cost
					+ "');";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);

			preparedStatement.executeUpdate();

			mpCon.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	boolean addTreatmentToList(String id, String description, double cost) {
		try {
			checkConnection();
			String insert = "INSERT INTO treatment_list (treatment_id, description, cost) " + "VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, description);
			preparedStatement.setDouble(3, cost);

			preparedStatement.executeUpdate();

			mpCon.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean addPatientHistory(String invoice, String doctor, String room, int upperBP, int lowerBP,
			int heartRate, String visitReason, String treatmentType, String note, String checkInDate,
			String checkOutDate, int active, double cost) {
		
		try {
			checkConnection();
			String record = "INSERT INTO patient_history ("
					+ "account_id, invoice, check_in_date, check_out_date, doctor, room,"
					+ "upper_bp, lower_bp, heart_rate, visit_reason, treatment_type, note, last_update, change_by, cost) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?);";

			PreparedStatement statement = mpCon.prepareStatement(record);
			
			statement.setString(1, PatientProfile.getPatientID());
			statement.setString(2, invoice);
			statement.setString(3, checkInDate);
			statement.setString(4, checkOutDate);
			statement.setString(5, doctor);
			statement.setString(6, room);
			statement.setInt(7, upperBP);
			statement.setInt(8, lowerBP);
			statement.setInt(9, heartRate);
			statement.setString(10, visitReason);
			statement.setString(11, treatmentType);
			statement.setString(12, note);
			statement.setString(13, OwnProfile.getUser());
			statement.setDouble(14, cost);

			statement.executeUpdate();

			Statement update = mpCon.createStatement();

			String updateActive = "UPDATE patient_table SET active = '" + active + "' WHERE patient_id = \""
					+ PatientProfile.getPatientID() + "\";";

			update.executeUpdate(updateActive);

			String addInvoice = "INSERT INTO invoice_table ("
					+ "id, invoice, previous_bal, balance, credit, debit, last_update, update_by) "
					+ "VALUES (?, ?, ?, ?, ?, ?, CURDATE(), ?);";
			PreparedStatement updateInvoice = mpCon.prepareStatement(addInvoice);
			updateInvoice.setString(1, PatientProfile.getPatientID());
			updateInvoice.setString(2, invoice);
			updateInvoice.setDouble(3, PaymentProfile.getPreviousBalance());
			updateInvoice.setDouble(4, PaymentProfile.getTotalBalance() + cost);
			updateInvoice.setDouble(5, 0);
			updateInvoice.setDouble(6, 0);
			updateInvoice.setString(7, OwnProfile.getUser());
			updateInvoice.executeUpdate();

			mpCon.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean checkExistingInvoice(String invoice) {
		checkConnection();
		String inv = "SELECT * FROM invoice_table WHERE invoice = \"" +invoice+ "\";";
		
		Statement statement;
		try {
			statement = mpCon.createStatement();

			ResultSet rs = statement.executeQuery(inv);
			if (rs.next()) {
				mpCon.close();
				return true;
			} else {
				mpCon.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}// end check


	public ResultSet loadPatientAssignment(String id) {
		String search = "SELECT * FROM patient_history where account_id = \"" + id + "\";";

		try {
			checkConnection();
			Statement statement = mpCon.createStatement();
			ResultSet resultSet = statement.executeQuery(search);

			if (resultSet.next()) {
				return resultSet;
			}
			else {
			mpCon.close();
			return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}


	public void addNewUser(String employeeID, String text, String text2) {
		try {
			checkConnection();
			String insert = "INSERT INTO employee_table (employee_id, username, password, lastupdate, changeby) "
					+ "VALUES (?, ?, ?, CURDATE(), ?);";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
			preparedStatement.setString(1, employeeID);
			preparedStatement.setString(2, text);
			preparedStatement.setString(3, text2);
			preparedStatement.setString(4, OwnProfile.getUser());

			preparedStatement.executeUpdate();

			mpCon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getInvoiceBalance(String id) {
		
		checkConnection();
		String inv = "SELECT * FROM invoice_table WHERE id = \"" + id + "\" AND invoice > '0' ;";

		Statement statement;
		try {
			statement = mpCon.createStatement();

			ResultSet rs = statement.executeQuery(inv);
			if (rs.next()) {
				mpCon.close();
				return rs;
			} else {
				mpCon.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean paymentPosting(String id, String invoice, double amount) {

		double prevBal = 0.00;
		prevBal = PaymentProfile.getPreviousBalance();
		prevBal -= amount;
		PaymentProfile.setPreviousBalance(prevBal);

		double balance = prevBal;
		PaymentProfile.setTotalBalance(balance);

		try {
			checkConnection();
			String insert = "INSERT INTO invoice_table (id, invoice, previous_bal, balance, credit, last_update, update_by) "
					+ "VALUES (?, ?, ?, ?, ?, CURDATE(), ?);";
			PreparedStatement preparedStatement = mpCon.prepareStatement(insert);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, invoice);
			preparedStatement.setDouble(3, prevBal);
			preparedStatement.setDouble(4, balance);
			preparedStatement.setDouble(5, 0.00);
			preparedStatement.setString(6, OwnProfile.getUser());

			preparedStatement.executeUpdate();

			mpCon.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		return false;
		}
	}

	public boolean addPaymentHistory(String invoice, String ccNumb, double ccAmount, String checkNumb,
			double checkAmount, String checkRoutine, String checkAccount, double cashAmount) {
		

		return false;
	}

	public ResultSet getInvoiceRecord(String invoice) {
		checkConnection();
		String inv = "SELECT * FROM patient_history WHERE invoice = \"" + invoice + "\";";

		Statement statement;
		try {
			statement = mpCon.createStatement();

			ResultSet rs = statement.executeQuery(inv);
			if (rs.next()) {
				mpCon.close();
				return rs;
			} else {
				mpCon.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
