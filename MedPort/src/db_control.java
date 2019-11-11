
import java.sql.*;
import java.util.ArrayList;

public class db_control {

	private Connection mpCon ;

	db_control() {	}

	Connection getcon() {	return mpCon;	}

	boolean checkConnection() {
//		String hostName = "nk.database.windows.net"; // update me
//        String dbName = "medport"; // update me
//        String user = "nainnn01"; // update me
//        String password = "Nkforever2019"; // update me
//        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
//            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
		//String url = "jdbc:sqlserver://nk.database.windows.net:1433;database=medport;user=nainnn01@nk;password=Nkforever2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		
		try {
//			mpCon = DriverManager.getConnection("jdbc:sqlserver://medport.database.windows.net:1433", "munov01", "soumya98Wii-00");
//			mpCon = DriverManager.getConnection("jdbc:sqlserver://medport.database.windows.net:1433/medport", "munov01", "soumya98Wii-00");
//			mpCon = DriverManager.getConnection("jdbc:");
			mpCon = DriverManager.getConnection("jdbc:sqlserver://nk.database.windows.net:1433", "nainnn01", "Nkforever2019");
//			mpCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/metportdb", "root", "nkforever2019");
			
			return true;
		} catch (SQLException  e) {

			return false;
		}
	}

	boolean validate(String user, String pass) {
		checkConnection();

		String checkuser = "SELECT * FROM employee_table WHERE username = \"" + user + "\" AND password = \"" + pass
				+ "\";";
		

		Statement statement;
		ResultSet resultSet;
		
		try {
			
			statement = mpCon.createStatement();
			resultSet = statement.executeQuery(checkuser);

			if (resultSet.next()) {
				String employeeID = resultSet.getNString("employee_id");
				String getProfile = "SELECT * FROM employee_info WHERE ee_id = \"" + employeeID	+ "\";";
				Statement statement2 = mpCon.createStatement();
				ResultSet resultSet2 = statement2.executeQuery(getProfile);
				if(resultSet.next()) {
					userProfile.setEmployeeID(resultSet2.getNString("ee_id"));
					userProfile.setFirstName(resultSet2.getNString("first_name"));
					userProfile.setLastName(resultSet2.getNString("last_name"));
					userProfile.setRole(resultSet2.getNString("position"));
				}
						
				mpCon.close();
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;

	}

	void getProfile(String id, String name, String ssn) {
		checkConnection();

		if(userProfile.getRole() == "admin") {
			searchByAdmin(id, name, ssn);
		}
		
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
							
						patientProfile.found = false;
						}
					}
				
			} // end else

		} catch (SQLException e) {

			patientProfile.found = false;
		}

	}// end get patient profile
	
	void searchByAdmin(String id, String name, String ssn) {
			checkConnection();
			
			String checkFirstName = "SELECT * FROM employee_info where first_Name = \"" + name + "\" AND ssnSerial = \"" + ssn + "\";";
			String checkLastName = "SELECT * FROM employee_info where last_Name = \"" + name + "\" AND ssnSerial = \"" + ssn + "\";";
			String checkID = "SELECT * FROM employee_info where ee_id = \"" + id + "\" OR user_id = \"" + id + "\";" ;

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
								
							patientProfile.found = false;
							}
						}
					
				} // end else

			} catch (SQLException e) {

				patientProfile.found = false;
			}
	}

	void patientFound(ResultSet resultSet) {
		String id = "";
		patientProfile.found = true;
		
		try {
			id = resultSet.getNString("patient_id");
			patientProfile.setID(id);
			patientProfile.setFName(resultSet.getNString("first_name"));
			patientProfile.setMName(resultSet.getNString("mid_name"));
			patientProfile.setLName(resultSet.getNString("last_name"));
			patientProfile.setDOB(resultSet.getNString("DOB"));
			patientProfile.setGender(resultSet.getNString("gender"));
			patientProfile.setPDoctor(resultSet.getNString("primaryDoctor"));
			patientProfile.setSSNArea(resultSet.getNString("ssnArea"));
			patientProfile.setSSNGroup(resultSet.getNString("ssnGroup"));
			patientProfile.setSSNSerial(resultSet.getNString("ssnSerial"));
			patientProfile.setPhoneNumber(resultSet.getNString("phone_num"));
			patientProfile.setActive(resultSet.getInt("active"));

			// getting address from address table
			setAddress(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// end patient found

	void setAddress(String id) {
		try {
			PreparedStatement checkAddress = mpCon.prepareStatement("SELECT * FROM address_table where address_id = ? ;");
			checkAddress.setString(1, id);
			ResultSet resultset5 = checkAddress.executeQuery();

			if (resultset5.next()) {
				patientProfile.setStreetNum(resultset5.getNString("street_num"));
				patientProfile.setAptNum(resultset5.getNString("apt_num"));
				patientProfile.setStreetName(resultset5.getNString("street_name"));
				patientProfile.setCity(resultset5.getNString("city"));
				patientProfile.setState(resultset5.getNString("state").toUpperCase());
				patientProfile.setZipcode(resultset5.getNString("zipcode"));
			} // end if
			else {
				patientProfile.setStreetNum("N/A");
				patientProfile.setAptNum(" ");
				patientProfile.setStreetName("        ");
				patientProfile.setCity("N/A   ");
				patientProfile.setState("   ");
				patientProfile.setZipcode("     ");
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}// end setAddress

	
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
