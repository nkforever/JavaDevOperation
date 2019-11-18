
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
			mpCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/metportdb", "root", "nkforever2019");
			
			return true;
		} catch (SQLException  e) {

			return false;
		}
	}

	boolean validate(String user, String pass) {
		checkConnection();
		boolean valid = false;
		String checkuser = "SELECT * FROM employee_table WHERE username = \"" + user + "\" AND password = \"" + pass
				+ "\" ;";

		Statement statement;
		ResultSet resultSet;
		
		try {
			
			statement = mpCon.createStatement();
			resultSet = statement.executeQuery(checkuser);

			if (resultSet.next()) {
				valid = true;
				// get employee info
				String employeeID = resultSet.getNString("employee_id");
				OwnProfile.setEmployeeID(employeeID);

				String getProfile = "SELECT * FROM employee_info WHERE employee_id = \"" + employeeID + "\";";
				Statement statement2 = mpCon.createStatement();
				ResultSet resultSet2 = statement2.executeQuery(getProfile);
				if (resultSet2.next()) {
					OwnProfile.setFirstName(resultSet2.getNString("first_name"));
					OwnProfile.setMidName(resultSet2.getNString("mid_name"));
					OwnProfile.setLastName(resultSet2.getNString("last_name"));
					OwnProfile.setGender(resultSet2.getNString("gender"));
					OwnProfile.setEmail(resultSet2.getNString("email"));
					OwnProfile.setPhoneNumber(resultSet2.getNString("phone_number"));
					OwnProfile.setRole(resultSet2.getNString("role"));

					// get address info
					String getAddress = "SELECT * FROM address_table WHERE address_id = \"" + employeeID + "\";";
					Statement statement3 = mpCon.createStatement();
					ResultSet resultSet3 = statement3.executeQuery(getAddress);

					if (resultSet3.next()) {
						OwnProfile.setAddressNum(resultSet3.getNString("street_num"));
						OwnProfile.setAptNum(resultSet3.getNString("apt_num"));
						OwnProfile.setStreeName(resultSet3.getNString("street_name"));
						OwnProfile.setCityName(resultSet3.getNString("city"));
						OwnProfile.setStateName(resultSet3.getNString("state"));
						OwnProfile.setZipCode(resultSet3.getNString("state"));

						mpCon.close();
						return valid;
					}
					mpCon.close();
					return valid;
				}
						
				mpCon.close();
				return valid;
			}
			mpCon.close();
			return false;
		} catch (SQLException e) {
			return false;
		}

	}

	void getProfile(String id, String name, String ssn, String client) {
		checkConnection();

		if (client.equalsIgnoreCase("employee")) {
			searchByAdmin(id, name, ssn);
		} else {
			searchByStaff(id, name, ssn);
		}
	}

	// staff search
	void searchByStaff(String id, String name, String ssn) {

		String checkFirstName = "SELECT * FROM patient_table where first_Name = \"" + name + "\" AND ssnSerial = \"" + ssn + "\";";
		String checkLastName = "SELECT * FROM patient_table where last_Name = \"" + name + "\" AND ssnSerial = \"" + ssn + "\";";
		String checkID = "SELECT * FROM patient_table where patient_id = \"" + id + "\";";

		try {
			Statement statement, statement2, statement4;
			ResultSet resultSet, resultSet2, resultSet4;
			statement = mpCon.createStatement();
			statement2 = mpCon.createStatement();
			statement4 = mpCon.createStatement();

			resultSet = statement.executeQuery(checkFirstName);

			if (resultSet.next()) {
				patientFound(resultSet);
				
			} // end if
			else {
				resultSet2 = statement2.executeQuery(checkLastName);
				if (resultSet2.next()) {
					patientFound(resultSet2);
					
				} else {
						resultSet4 = statement4.executeQuery(checkID);
						if (resultSet4.next()) {
						patientFound(resultSet4);
							
						}else {
							
						PatientProfile.found = false;
						}
					}
				
			} // end else

		} catch (SQLException e) {

			PatientProfile.found = false;
		}

	}// end get patient profile
	
	void searchByAdmin(String id, String name, String ssn) {
			
		String checkFirstName = "SELECT * FROM employee_info WHERE first_Name = \"" + name + "\" AND ssnSerial = \""
				+ ssn + "\";";
		String checkLastName = "SELECT * FROM employee_info WHERE last_Name = \"" + name + "\" AND ssnSerial = \"" + ssn
				+ "\";";
		String checkID = "SELECT * FROM employee_info WHERE employee_id = \"" + id + "\" OR user_id = \"" + id + "\";";

		Statement statement, statement2 = null;
		ResultSet resultSet, resultSet2;

		try {
			checkConnection();

			statement = mpCon.createStatement();
			if (!name.isEmpty() && !ssn.isEmpty()) {
				resultSet = statement.executeQuery(checkFirstName);
			}
			else {
				resultSet = statement.executeQuery(checkID);
			}

				if (resultSet.next()) {
				employeeFound(resultSet);
					
				} // end if
				else {
				statement2 = mpCon.createStatement();
					resultSet2 = statement2.executeQuery(checkLastName);
					if (resultSet2.next()) {
					employeeFound(resultSet2);
						
					}else {
					EmployeeProfile.found = false;
					}
				} // end else

			} catch (SQLException e) {
			EmployeeProfile.found = false;
			}
	}

	void patientFound(ResultSet resultSet) {
		String id = "";
		PatientProfile.found = true;
		
		try {
			id = resultSet.getNString("patient_id");
			PatientProfile.setClientID(id);
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

	void employeeFound(ResultSet resultSet) {
		String id = "";
		PatientProfile.found = true;

		try {
			id = resultSet.getNString("employee_id");
			EmployeeProfile.setEmployeeID(id);
			EmployeeProfile.setFirstName(resultSet.getNString("first_name"));
			EmployeeProfile.setMidName(resultSet.getNString("mid_name"));
			EmployeeProfile.setLastName(resultSet.getNString("last_name"));
			EmployeeProfile.setDOB(resultSet.getNString("DOB"));
			EmployeeProfile.setGender(resultSet.getNString("gender"));
			EmployeeProfile.setSsnArea(resultSet.getNString("ssnArea"));
			EmployeeProfile.setSsnGroup(resultSet.getNString("ssnGroup"));
			EmployeeProfile.setSsnSerial(resultSet.getNString("ssnSerial"));
			EmployeeProfile.setPhoneNumber(resultSet.getNString("phone_number"));

			// getting address from address table
			setEmployeeAddress(id);

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
			} // end if
			else {
				PatientProfile.setStreetNum("N/A");
				PatientProfile.setAptNum(" ");
				PatientProfile.setStreetName("        ");
				PatientProfile.setCityName("N/A   ");
				PatientProfile.setStateName("   ");
				PatientProfile.setZipcode("     ");
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
				PatientProfile.setStreetNum(resultset.getNString("street_num"));
				PatientProfile.setAptNum(resultset.getNString("apt_num"));
				PatientProfile.setStreetName(resultset.getNString("street_name"));
				PatientProfile.setCityName(resultset.getNString("city"));
				PatientProfile.setStateName(resultset.getNString("state").toUpperCase());
				PatientProfile.setZipcode(resultset.getNString("zipcode"));
			} // end if
			else {
				PatientProfile.setStreetNum("N/A");
				PatientProfile.setAptNum(" ");
				PatientProfile.setStreetName("        ");
				PatientProfile.setCityName("N/A   ");
				PatientProfile.setStateName("   ");
				PatientProfile.setZipcode("     ");
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
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())";
				
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

	void updatePatientProfile(String patientID, String firstName, String midName, String lastName, String dateOfBirth,
			String gender, String primaryDoctor, String ssnArea, String ssnGroup, String ssnSerial, String phone_num) {

		try {
			checkConnection();

			String insert = "UPDATE patient_table (patient_id, first_name, mid_name, last_name, DOB, gender, "
					+ "primaryDoctor, ssnArea, ssnGroup, ssnSerial, phone_num, active, last_update) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())";

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

	void addAddress(String id, String streetNum, String aptNum,String streetName,  String cityName, String stateName, String zipcode ) {
		try {
		checkConnection();
		
		String insert = "INSERT INTO address_table (address_id, street_num, apt_num, street_name, city, state, zipcode, lastupdate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())";
		
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

	void updateAddress(String id, String streetNum, String aptNum,String streetName,  String cityName, String stateName, String zipcode ) {
		try {
		checkConnection();
		
		String insert = "INSERT INTO address_table (address_id, street_num, apt_num, street_name, city, state, zipcode, lastupdate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())"
		
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
	
	boolean addEmployeeProfile(String eeID, String firstName, String midName, String lastName, String dob,
			String gender, String SSN, String doctor, String streetNum, String aptNum, String streetName, String city,
			String state, String zipcode) {

		if (checkConnection()) {
			String insertProfile = "INSERT INTO `metportdb`.`patient_table` (`patient_id`, `first_name`, `mid_name`, `last_name`, `DOB`, `gender`, `primaryDoctor`, `ssnArea`, `ssnGroup`, `ssnSerial`, ) "
					+ "VALUES ('" + eeID + "', '" + firstName + "', '" + midName + "'" + "		'" + lastName + "', '"
					+ dob + "', '" + gender + "', '" + SSN + "', '" + doctor + "')";
			String insertAddress = "INSERT INTO `metportdb`.`address_table` (`address_id`, `street_num`, `apt_num`, `street_name`, `city`, `state`, `zipcode`, "
					+ "VALUES ('" + eeID + "', '" + streetNum + "', '" + aptNum + "'" + "		'" + streetName + "', '"
					+ city + "', '" + state + "', '" + zipcode + "');";
			
			try {
				Statement	statement9 = mpCon.createStatement();
					statement9.executeQuery(insertProfile);
				Statement	statement10 = mpCon.createStatement();
				statement10.executeQuery(insertAddress);
				
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
				al.add("Dr. " + rs.getString(2).substring(0,1) + ". "+rs.getString(3));
				
			}
			mpCon.close();
			return al;
		} catch (SQLException e) {
			return null;
		}
		
	}//end get doctor array list
	
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
	
	
	ArrayList<String> getTreatmentList() {

		ArrayList<String> al = new ArrayList<String>();
		checkConnection();
		String list = "SELECT * FROM treatment_list ;";
		try {

			Statement statement = mpCon.createStatement();
			ResultSet rs = statement.executeQuery(list);
			while(rs.next()) {
				al.add(rs.getString(2));	
			}
			mpCon.close();
			return al;
		} catch (SQLException e) {
			System.out.print("unable to get treatment list");
			return null;
		}
		
	}	
}
