import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class EditEmployeeForm extends JPanel{
	/**
	 * 
	 */
	// variables
	private static final long serialVersionUID = 1L;
	private JFormattedTextField DOBField, zipcodeField, streetNumField, ssnAreaField, ssnGroupField, ssnSerialField,
			phoneNumberField, emailTextField;
	private JTextField firstNameField, midNameField, lastNameField, streetNameField, cityField, aptNumField;
	private JComboBox<String> stateField, genderField, roleComboBox;
	private JButton saveUpdateButton;

	private JLabel lblNewLabel, lblMidName, lblLastName, lblDob, lblGender, lblSsn, lblStreetAddr, lblState, lblCity,
			lblZipcode, lblPhone, lblNewLabel_2, lblstreetNumber, lblstreetName, mandatoryError, label, label_1,
			label_2;

	private JCheckBox addEditPatientCheckBox, editOwnProfilecheckBox, viewPatientCheckBoc, viewBillCheckBox,
			userAdminCheckBox, activeCheckBox, processPaymentCheckBox;

	private DBcontrol dbc = new DBcontrol();


	private String employeeID = " ", firstName = " ", midName = " ", lastName = " ", DOB = " ", gender = " ",
			ssnArea = " ", ssnGroup = " ", ssnSerial = " ", phoneNumber = " ", streetNum = " ", aptNum = " ",
			streetName = " ", city = " ", state = " ", zipcode = " ", role = " ", email = " ";
	private int active = 1, userAdmin = 0, addEditPatient = 0, viewPatient = 0, ownProfile = 1, viewBill = 0,
			processPayment = 0;
	// end of variables

	// declaration
	DateFormat  dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
	DateFormatter dateFormatter  = new DateFormatter(dateFormat); 	

	NumberFormat num = new DecimalFormat("#####"); 
	NumberFormatter zipFormatter  = new NumberFormatter(num); 
	private JLabel emailLabel;
	// end of declaration

	// class begin
	public EditEmployeeForm() {
		setLayout(new BorderLayout(0, 0));

		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);

		lblStreetAddr = new JLabel("Street Address");
		lblStreetAddr.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStreetAddr.setBounds(11, 141, 105, 29);
		formPanel.add(lblStreetAddr);

		firstNameField = new JTextField(); // first name field
		firstNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.white);
				if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.white);
				if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.white);
				if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.white);
				if(ssnSerialField.getText().isEmpty()) ssnSerialField.setBackground(Color.white);

				mandatoryError.setVisible(false);
			}
		});
		firstNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		firstNameField.setBounds(87, 24, 130, 29);
		formPanel.add(firstNameField);
		firstNameField.setColumns(10);

		midNameField = new JTextField(); // middle name field
		midNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.white);
				if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.white);
				if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.white);
				if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.white);
				if(ssnSerialField.getText().isEmpty()) ssnSerialField.setBackground(Color.white);

				mandatoryError.setVisible(false);
			}
		});
		midNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		midNameField.setBounds(304, 24, 121, 29);
		formPanel.add(midNameField);
		midNameField.setColumns(10);

		lastNameField = new JTextField(); // last name field
		lastNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.white);
				if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.white);
				if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.white);
				if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.white);
				if(ssnSerialField.getText().isEmpty()) ssnSerialField.setBackground(Color.white);

				mandatoryError.setVisible(false);
			}
		});
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lastNameField.setBounds(523, 24, 130, 29);
		formPanel.add(lastNameField);
		lastNameField.setColumns(10);

		try { // date of birth field
			DOBField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		}catch (ParseException e) {
			e.printStackTrace();
		}
		DOBField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		DOBField.setBounds(89, 67, 94, 29);
		formPanel.add(DOBField);
		DOBField.setColumns(10);

		genderField = new JComboBox<String>(); // gender combo box
		genderField.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Male", "Female", "Other()"}));
		genderField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		genderField.setBounds(305, 67, 86, 29);
		formPanel.add(genderField);

		streetNameField = new JFormattedTextField();
		streetNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		streetNameField.setBounds(202, 141, 234, 29);
		formPanel.add(streetNameField);
		streetNameField.setColumns(10);

		cityField = new JTextField();
		cityField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cityField.setBounds(120, 180, 152, 29);
		formPanel.add(cityField);
		cityField.setColumns(10);

		stateField = new JComboBox<String>();
		stateField.setModel(new DefaultComboBoxModel<String>(new String[] {"", "AK", "AL", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"}));
		stateField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		stateField.setBounds(344, 180, 74, 29);
		formPanel.add(stateField);

		zipcodeField = new JFormattedTextField(zipFormatter);
		zipcodeField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
				if(!isDigit(e.getKeyChar())) e.consume();
				if (zipcodeField.getText().length() >= 5 ) // limit textfield to 5 characters
					e.consume(); 
			}  
		});
		zipcodeField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		zipcodeField.setBounds(500, 180, 94, 29);
		formPanel.add(zipcodeField);
		zipcodeField.setColumns(5);


		try {
			phoneNumberField = new JFormattedTextField(	new MaskFormatter("(###) ###-####"));
		} catch (ParseException e1) {
			System.out.println("Error formating phone number");
			e1.printStackTrace();
		}

		phoneNumberField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		phoneNumberField.setBounds(502, 220, 152, 29);
		formPanel.add(phoneNumberField);
		phoneNumberField.setColumns(10);

		saveUpdateButton = new JButton("SAVE UPDATE");
		saveUpdateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		saveUpdateButton.setBounds(534, 368, 226, 37);
		formPanel.add(saveUpdateButton);

		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 24, 79, 29);
		formPanel.add(lblNewLabel);

		lblMidName = new JLabel("Mid Name");
		lblMidName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMidName.setBounds(225, 24, 79, 29);
		formPanel.add(lblMidName);

		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLastName.setBounds(443, 24, 79, 29);
		formPanel.add(lblLastName);

		lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(47, 67, 40, 29);
		formPanel.add(lblDob);

		lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGender.setBounds(247, 67, 58, 29);
		formPanel.add(lblGender);

		lblSsn = new JLabel("SSN#");
		lblSsn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSsn.setBounds(486, 67, 43, 29);
		formPanel.add(lblSsn);

		lblState = new JLabel("State");
		lblState.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblState.setBounds(294, 180, 48, 29);
		formPanel.add(lblState);

		lblCity = new JLabel("City");
		lblCity.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCity.setBounds(75, 180, 40, 29);
		formPanel.add(lblCity);

		lblZipcode = new JLabel("Zipcode");
		lblZipcode.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblZipcode.setBounds(440, 180, 64, 29);
		formPanel.add(lblZipcode);

		lblPhone = new JLabel("Phone#");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPhone.setBounds(439, 220, 58, 29);
		formPanel.add(lblPhone);

		JLabel lblApt = new JLabel("Apt#");
		lblApt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblApt.setBounds(464, 141, 48, 29);
		formPanel.add(lblApt);

		aptNumField = new JTextField();
		aptNumField.setText(" ");
		aptNumField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		aptNumField.setColumns(5);
		aptNumField.setBounds(500, 141, 58, 29);
		formPanel.add(aptNumField);

		streetNumField = new JFormattedTextField();
		streetNumField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {		
				if(!isDigit(e.getKeyChar())) e.consume();
				if (streetNumField.getText().length() >= 6) // limit textfield to 4 characters
					e.consume(); 
			}  
		});
		streetNumField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		streetNumField.setColumns(5);
		streetNumField.setBounds(121, 141, 74, 29);
		formPanel.add(streetNumField);

		ssnAreaField = new JFormattedTextField();
		ssnAreaField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
				if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.white);
				if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.white);
				if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.white);
				if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.white);
				if(ssnSerialField.getText().isEmpty()) ssnSerialField.setBackground(Color.white);

				mandatoryError.setVisible(false);

				if(!isDigit(e.getKeyChar())) e.consume();
				if (ssnAreaField.getText().length() >= 3) // limit textfield to 3 characters
					e.consume(); 
			}  
		});
		ssnAreaField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ssnAreaField.setBounds(545, 67, 33, 29);
		formPanel.add(ssnAreaField);

		ssnGroupField = new JFormattedTextField();
		ssnGroupField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
				if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.white);
				if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.white);
				if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.white);
				if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.white);
				if(ssnSerialField.getText().isEmpty()) ssnSerialField.setBackground(Color.white);

				mandatoryError.setVisible(false);

				if(!isDigit(e.getKeyChar())) e.consume();
				if (ssnGroupField.getText().length() >= 2) // limit textfield to 2 characters
					e.consume(); 
			}  
		});
		ssnGroupField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ssnGroupField.setColumns(2);
		ssnGroupField.setBounds(579, 67, 26, 29);
		formPanel.add(ssnGroupField);

		ssnSerialField = new JFormattedTextField();
		ssnSerialField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.white);
				if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.white);
				if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.white);
				if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.white);
				if(ssnSerialField.getText().isEmpty()) ssnSerialField.setBackground(Color.white);

				mandatoryError.setVisible(false);

				if(!isDigit(e.getKeyChar())) e.consume();
				if (ssnSerialField.getText().length() >= 4 ) // limit textfield to 4 characters
					e.consume(); 

			}  
		});
		ssnSerialField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ssnSerialField.setColumns(4);
		ssnSerialField.setBounds(606, 67, 48, 29);
		formPanel.add(ssnSerialField);

		JLabel lblNewLabel_1 = new JLabel("Staff Role");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(11, 233, 86, 29);
		formPanel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("(Enter 10 digits)");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(656, 220, 104, 29);
		formPanel.add(lblNewLabel_2);

		lblstreetNumber = new JLabel("(street number)");
		lblstreetNumber.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblstreetNumber.setBounds(121, 120, 74, 21);
		formPanel.add(lblstreetNumber);

		lblstreetName = new JLabel("(street name)");
		lblstreetName.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblstreetName.setBounds(288, 120, 103, 21);
		formPanel.add(lblstreetName);

		mandatoryError = new JLabel("** Please fill out all the required field.");
		mandatoryError.setVisible(false);
		mandatoryError.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		mandatoryError.setBounds(534, 329, 226, 29);
		formPanel.add(mandatoryError);

		label = new JLabel("*");
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(533, 71, 14, 21);
		formPanel.add(label);

		label_1 = new JLabel("*");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setBounds(508, 20, 14, 29);
		formPanel.add(label_1);

		label_2 = new JLabel("*");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_2.setBounds(75, 20, 14, 21);
		formPanel.add(label_2);
		
		roleComboBox = new JComboBox<String>();
		{
			if (!OwnProfile.getUser().equalsIgnoreCase(EmployeeProfile.getUser())) {

				roleComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getItem().equals("Admin")) {
							userAdminCheckBox.setEnabled(true);
							userAdminCheckBox.setSelected(true);
							addEditPatientCheckBox.setEnabled(false);
							addEditPatientCheckBox.setSelected(false);
							editOwnProfilecheckBox.setEnabled(true);
							editOwnProfilecheckBox.setSelected(true);
							viewBillCheckBox.setSelected(false);
							viewBillCheckBox.setEnabled(false);
							viewPatientCheckBoc.setSelected(false);
							viewPatientCheckBoc.setEnabled(false);
							processPaymentCheckBox.setSelected(false);
							processPaymentCheckBox.setEnabled(false);

						} else if (e.getItem().equals("Doctor")) {
							userAdminCheckBox.setSelected(false);
							userAdminCheckBox.setEnabled(false);
							addEditPatientCheckBox.setEnabled(true);
							addEditPatientCheckBox.setSelected(true);
							editOwnProfilecheckBox.setEnabled(true);
							editOwnProfilecheckBox.setSelected(true);
							viewBillCheckBox.setSelected(false);
							viewBillCheckBox.setEnabled(false);
							viewPatientCheckBoc.setEnabled(true);
							viewPatientCheckBoc.setSelected(true);
							processPaymentCheckBox.setSelected(false);
							processPaymentCheckBox.setEnabled(false);

						} else if (e.getItem().equals("Nurse")) {
							userAdminCheckBox.setSelected(false);
							userAdminCheckBox.setEnabled(false);
							addEditPatientCheckBox.setEnabled(true);
							addEditPatientCheckBox.setSelected(true);
							editOwnProfilecheckBox.setEnabled(true);
							editOwnProfilecheckBox.setSelected(true);
							viewBillCheckBox.setSelected(false);
							viewBillCheckBox.setEnabled(false);
							viewPatientCheckBoc.setEnabled(true);
							viewPatientCheckBoc.setSelected(true);
							processPaymentCheckBox.setSelected(false);
							processPaymentCheckBox.setEnabled(false);

						} else if (e.getItem().equals("Secretary")) {
							userAdminCheckBox.setSelected(false);
							userAdminCheckBox.setEnabled(false);
							addEditPatientCheckBox.setEnabled(true);
							addEditPatientCheckBox.setSelected(true);
							editOwnProfilecheckBox.setEnabled(true);
							editOwnProfilecheckBox.setSelected(true);
							viewBillCheckBox.setEnabled(true);
							viewBillCheckBox.setSelected(true);
							viewPatientCheckBoc.setEnabled(true);
							viewPatientCheckBoc.setSelected(true);
							processPaymentCheckBox.setEnabled(true);
							processPaymentCheckBox.setSelected(true);

						} else if (e.getItem().equals("Finance")) {
							userAdminCheckBox.setSelected(false);
							userAdminCheckBox.setEnabled(false);
							addEditPatientCheckBox.setSelected(false);
							addEditPatientCheckBox.setEnabled(false);
							editOwnProfilecheckBox.setEnabled(true);
							editOwnProfilecheckBox.setSelected(true);
							viewBillCheckBox.setEnabled(true);
							viewBillCheckBox.setSelected(true);
							viewPatientCheckBoc.setEnabled(false);
							viewPatientCheckBoc.setSelected(false);
							processPaymentCheckBox.setEnabled(true);
							processPaymentCheckBox.setSelected(true);

						} else {
							userAdminCheckBox.setSelected(false);
							userAdminCheckBox.setEnabled(false);
							addEditPatientCheckBox.setSelected(false);
							addEditPatientCheckBox.setEnabled(false);
							editOwnProfilecheckBox.setSelected(false);
							editOwnProfilecheckBox.setEnabled(false);
							viewBillCheckBox.setSelected(false);
							viewBillCheckBox.setEnabled(false);
							viewPatientCheckBoc.setSelected(false);
							viewPatientCheckBoc.setEnabled(false);
							processPaymentCheckBox.setSelected(false);
							processPaymentCheckBox.setEnabled(false);
						}
				}
				});
			}
		}
		roleComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Admin", "Doctor", "Finance", "Nurse", "Secretary" }));
		roleComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		roleComboBox.setBounds(98, 233, 98, 29);
		formPanel.add(roleComboBox);
		
		addEditPatientCheckBox = new JCheckBox("Add/Edit Patient");
		addEditPatientCheckBox.setEnabled(false);
		addEditPatientCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addEditPatientCheckBox.setBounds(11, 282, 163, 29);
		formPanel.add(addEditPatientCheckBox);
		
		editOwnProfilecheckBox = new JCheckBox("Own Profile");
		editOwnProfilecheckBox.setEnabled(false);
		editOwnProfilecheckBox.setSelected(true);
		editOwnProfilecheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		editOwnProfilecheckBox.setBounds(188, 282, 163, 29);
		formPanel.add(editOwnProfilecheckBox);
		
		viewPatientCheckBoc = new JCheckBox("View Patient");
		viewPatientCheckBoc.setEnabled(false);
		viewPatientCheckBoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		viewPatientCheckBoc.setBounds(11, 328, 163, 29);
		formPanel.add(viewPatientCheckBoc);
		
		viewBillCheckBox = new JCheckBox("View Bill");
		viewBillCheckBox.setEnabled(false);
		viewBillCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		viewBillCheckBox.setBounds(188, 328, 163, 29);
		formPanel.add(viewBillCheckBox);

		JLabel lbllimited = new JLabel("* Limited");
		lbllimited.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lbllimited.setBounds(350, 286, 104, 29);
		formPanel.add(lbllimited);

		processPaymentCheckBox = new JCheckBox("Process Payment");
		processPaymentCheckBox.setEnabled(false);
		processPaymentCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		processPaymentCheckBox.setBounds(188, 373, 163, 29);
		formPanel.add(processPaymentCheckBox);

		userAdminCheckBox = new JCheckBox("User Admin");
		userAdminCheckBox.setEnabled(false);
		userAdminCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		userAdminCheckBox.setBounds(11, 373, 163, 29);
		formPanel.add(userAdminCheckBox);

		activeCheckBox = new JCheckBox("Active?");
		activeCheckBox.setSelected(true);
		activeCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		activeCheckBox.setBounds(374, 373, 94, 29);
		formPanel.add(activeCheckBox);

		emailLabel = new JLabel("E-mail");
		emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		emailLabel.setBounds(439, 256, 58, 29);
		formPanel.add(emailLabel);

		emailTextField = new JFormattedTextField((AbstractFormatter) null);
		emailTextField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		emailTextField.setColumns(10);
		emailTextField.setBounds(502, 256, 152, 29);
		formPanel.add(emailTextField);
		formPanel.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { firstNameField, midNameField, lastNameField, DOBField,
						genderField, ssnAreaField, ssnGroupField, ssnSerialField, streetNumField, streetNameField,
						aptNumField, cityField, stateField, zipcodeField, phoneNumberField, saveUpdateButton }));

	}

	JButton getSaveUpdateButton() {
		return saveUpdateButton;
	}
// TODO 
//	need fix for
//	editing employee

	boolean updateEmployeeInfo() {
		if(!isMandatoryFieldFill()) {
			if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.YELLOW);
			if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.yellow);
			if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.YELLOW);
			if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.YELLOW);
			if(ssnSerialField.getText().isEmpty()) ssnSerialField.setBackground(Color.YELLOW);

			mandatoryError.setVisible(true);
			mandatoryError.setBackground(Color.YELLOW);

			return false;
		}	
		else {

			if (firstNameField.getText() != null)
				firstName = firstNameField.getText();
			if (midNameField.getText() != null)
				midName = midNameField.getText();
			if (lastNameField.getText() != null)
				lastName = lastNameField.getText();
			if (DOBField.getText() != null)
				DOB = DOBField.getText();
			if (!(genderField.getSelectedIndex() < 1))
				gender = genderField.getSelectedItem().toString();
			if (ssnAreaField.getText() != null)
				ssnArea = ssnAreaField.getText();
			if (ssnGroupField.getText() != null)
				ssnGroup = ssnGroupField.getText();
			if (ssnSerialField.getText() != null)
				ssnSerial = ssnSerialField.getText();
			if (!(roleComboBox.getSelectedIndex() < 1))
				role = roleComboBox.getSelectedItem().toString();
			if (phoneNumberField.getText() != null)
				phoneNumber = phoneNumberField.getText();
			if (streetNumField.getText() != null)
				streetNum = streetNumField.getText();
			if (aptNumField.getText() != null)
				aptNum = aptNumField.getText();
			if (streetNameField.getText() != null)
				streetName = streetNameField.getText();
			if (cityField.getText() != null)
				city = cityField.getText();
			if (!(stateField.getSelectedIndex() < 1))
				state = stateField.getSelectedItem().toString();
			if(zipcodeField.getText() != null) zipcode = zipcodeField.getText();
			if (emailTextField.getText() != null)
				email = emailTextField.getText();

			if (addEditPatientCheckBox.isSelected())
				addEditPatient = 1;
			if (viewPatientCheckBoc.isSelected())
				viewPatient = 1;
			if (editOwnProfilecheckBox.isSelected())
				ownProfile = 1;
			if (viewBillCheckBox.isSelected())
				viewBill = 1;
			if (processPaymentCheckBox.isSelected())
				processPayment = 1;
			if (userAdminCheckBox.isSelected())
				userAdmin = 1;
			if (activeCheckBox.isSelected())
				active = 1;

			if (dbc.updateEmployeeProfile(firstName, midName, lastName, gender, DOB, email, phoneNumber, role, ssnArea,
					ssnGroup, ssnSerial, userAdmin, addEditPatient, viewPatient, ownProfile, viewBill, processPayment,
					active)) {
				dbc.updateAddress(EmployeeProfile.getEmployeeID(), streetNum, aptNum, streetName, city, state, zipcode);

				loadEmployeeInfo();
				return true;
			} else {
				return false;
			}
		}
	}

	void loadEmployeeInfo() {

		EmployeeProfile.setEmployeeID(employeeID);
		EmployeeProfile.setFirstName(firstName);
		EmployeeProfile.setMidName(midName);
		EmployeeProfile.setLastName(lastName);
		EmployeeProfile.setDOB(DOB);
		EmployeeProfile.setGender(gender);
		EmployeeProfile.setSsnArea(ssnArea);
		EmployeeProfile.setSsnGroup(ssnGroup);
		EmployeeProfile.setSsnSerial(ssnSerial);
		EmployeeProfile.setPhoneNumber(phoneNumber);
		EmployeeProfile.setEmail(email);
		EmployeeProfile.setStreetNum(streetNum);
		EmployeeProfile.setStreetName(streetName);
		EmployeeProfile.setAptNum(aptNum);
		EmployeeProfile.setCityName(city);
		EmployeeProfile.setStateName(state);
		EmployeeProfile.setZipCode(zipcode);
		EmployeeProfile.setRole(role);
		EmployeeProfile.setActive(active);
		EmployeeProfile.setAddEditPatient(addEditPatient);
		EmployeeProfile.setOwnProfile(ownProfile);
		EmployeeProfile.setUserAdmin(userAdmin);
		EmployeeProfile.setViewPatient(viewPatient);
		EmployeeProfile.setViewBill(viewBill);
		EmployeeProfile.setProcessPayment(processPayment);

		clearForm();
	}

	public void loadEmployeeInfoToForm() {

		firstNameField.setText(EmployeeProfile.getFirstName());
		midNameField.setText(EmployeeProfile.getMidName());
		lastNameField.setText(EmployeeProfile.getLastName());
		DOBField.setText(EmployeeProfile.getDOB());
		genderField.setSelectedItem(EmployeeProfile.getGender());
		ssnAreaField.setText(EmployeeProfile.getSsnArea());
		ssnGroupField.setText(EmployeeProfile.getSsnGroup());
		ssnSerialField.setText(EmployeeProfile.getSsnSerial());
		phoneNumberField.setText(EmployeeProfile.getPhoneNumber());
		streetNumField.setText(EmployeeProfile.getStreetNum());
		streetNameField.setText(EmployeeProfile.getStreetName());
		aptNumField.setText(EmployeeProfile.getAptNum());
		cityField.setText(EmployeeProfile.getCityName());
		stateField.setSelectedItem(EmployeeProfile.getStateName());
		zipcodeField.setText(EmployeeProfile.getZipCode());
		roleComboBox.setSelectedItem(EmployeeProfile.getRole());
		emailTextField.setText(EmployeeProfile.getEmail());
		if (EmployeeProfile.getAddEditPatient() == 1)
			addEditPatientCheckBox.setSelected(true);
		if (EmployeeProfile.getViewPatient() == 1)
			viewPatientCheckBoc.setSelected(true);
		if (EmployeeProfile.getOwnProfile() == 1)
			editOwnProfilecheckBox.setSelected(true);
		if (EmployeeProfile.getViewBill() == 1)
			viewBillCheckBox.setSelected(true);
		if (EmployeeProfile.getProcessPayment() == 1)
			processPaymentCheckBox.setSelected(true);
		if (EmployeeProfile.getUserAdmin() == 1)
			userAdminCheckBox.setSelected(true);
		if (EmployeeProfile.getActive() == 1)
			activeCheckBox.setSelected(true);
	}

	void clearForm() {
		//clear the form
		firstNameField.setText("");
		midNameField.setText("");
		lastNameField.setText("");
		DOBField.setText("");
		genderField.setSelectedIndex(-1);
		streetNumField.setText("");
		streetNameField.setText("");
		cityField.setText("");
		zipcodeField.setText("");
		ssnAreaField.setText("");
		ssnGroupField.setText("");
		ssnSerialField.setText("");
		phoneNumberField.setText("");
		stateField.setSelectedIndex(0);
		emailTextField.setText("");
		roleComboBox.setSelectedIndex(0);
		userAdminCheckBox.setSelected(false);

		firstNameField.setBackground(Color.white);
		lastNameField.setBackground(Color.white);
		ssnAreaField.setBackground(Color.white);
		ssnGroupField.setBackground(Color.white);
		ssnSerialField.setBackground(Color.white);
	}

	boolean isFormClear() {
		if(
		firstNameField.getText().isEmpty() && midNameField.getText().isEmpty() && lastNameField.getText().isEmpty()
				&& DOBField.getText().isEmpty() && ssnAreaField.getText().isEmpty() && ssnGroupField.getText().isEmpty()
				&& ssnSerialField.getText().isEmpty()
		) {

			return true;
		}

		return false;
	}

	boolean isMandatoryFieldFill() {
		if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || ssnAreaField.getText().length() < 3 || 
				ssnGroupField.getText().length() <2|| ssnSerialField.getText().length() < 4 ) return false;


		return true;
	}
	boolean isDigit(char input) {
		if(input >= 48 && input <= 57) 		return true;

		return false;
	}
}
