import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class NewEmployeeForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField DOBField, zipcodeField, streetNumField, ssnAreaField, ssnGroupField, ssnSerialField
			, phoneNumberField, emailTextField;
	private JTextField firstNameField, midNameField, lastNameField,streetNameField, cityField;
	private JComboBox<String> stateField, genderField, roleComboBox;

	private JLabel lblNewLabel, lblMidName, lblLastName, lblDob, lblGender, lblSsn, lblStreetAddr, lblState, lblCity,
			lblZipcode, lblPhone, lblNewLabel_2, lblstreetNumber, lblstreetName, mandatoryError, label, label_1,
			label_2;

	private JCheckBox addEditPatientCheckBox, userAdminCheckBox, ownProfileCheckBox, viewPatientCheckBox,
			viewBillCheckBox, processPaymentCheckBox;
	private JCheckBox activeCheckBox;

	private DBcontrol dbc = new DBcontrol();
	private JTextField aptNumField;
	private JButton saveNewEmployeeButton;
	private JPanel formPanel;

	private String employeeID = " ", userID = " ", firstName = " ", midName = " ", lastName = " ", DOB = " ",
			gender = " ", ssnArea = " ", ssnGroup = " ", ssnSerial = " ", phoneNumber = " ", streetNum = " ",
			aptNum = " ", streetName = " ", city = " ", state = " ", zipcode = " ", role = " ", email = " ",
			phone_number = " ";
	private int active = 1, userAdmin = 0, addEditPatient = 0, viewPatient = 0, ownProfile = 1, viewBill = 0,
			processPayment = 0;

	private NumberFormat num = new DecimalFormat("#####");
	private NumberFormatter zipFormatter = new NumberFormatter(num);
	private JTextField userNameField;
	private JTextField passwordField;
	private JLabel lblAssignUsername;
	private JLabel lblAssignPassword;



	public NewEmployeeForm() {
		setLayout(new BorderLayout(0, 0));

		formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);

		lblStreetAddr = new JLabel("Street Address");
		lblStreetAddr.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStreetAddr.setBounds(10, 141, 105, 29);
		formPanel.add(lblStreetAddr);

		firstNameField = new JTextField();
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

		midNameField = new JTextField();
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

		lastNameField = new JTextField();
		lastNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(firstNameField.getText().isEmpty()) firstNameField.setBackground(Color.white);
				if(lastNameField.getText().isEmpty()) lastNameField.setBackground(Color.white);
				if(ssnAreaField.getText().isEmpty()) ssnAreaField.setBackground(Color.white);
				if(ssnGroupField.getText().isEmpty()) ssnGroupField.setBackground(Color.white);
				if (ssnSerialField.getText().isEmpty())
					ssnSerialField.setBackground(Color.white);

				mandatoryError.setVisible(false);
			}
		});
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lastNameField.setBounds(522, 24, 130, 29);
		formPanel.add(lastNameField);
		lastNameField.setColumns(10);

		try {
			DOBField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		}catch (ParseException e) {
			e.printStackTrace();
		}
		DOBField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		DOBField.setBounds(89, 67, 94, 29);
		formPanel.add(DOBField);
		DOBField.setColumns(10);

		genderField = new JComboBox<String>();
		genderField.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Male", "Female", "Other()"}));
		genderField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		genderField.setBounds(305, 67, 86, 29);
		formPanel.add(genderField);

		streetNameField = new JFormattedTextField();
		streetNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		streetNameField.setBounds(201, 141, 234, 29);
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
		stateField.setBounds(331, 180, 74, 29);
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
		zipcodeField.setBounds(498, 180, 82, 29);
		formPanel.add(zipcodeField);
		zipcodeField.setColumns(5);


		try {
			phoneNumberField = new JFormattedTextField(	new MaskFormatter("(###) ###-####"));
		} catch (ParseException e1) {
			System.out.println("Error formating phone number");
			e1.printStackTrace();
		}

		phoneNumberField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		phoneNumberField.setBounds(500, 222, 152, 29);
		formPanel.add(phoneNumberField);
		phoneNumberField.setColumns(10);

		saveNewEmployeeButton = new JButton("ADD NEW EMPLOYEE");
		saveNewEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		saveNewEmployeeButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		saveNewEmployeeButton.setBounds(512, 369, 234, 37);
		formPanel.add(saveNewEmployeeButton);

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
		lblLastName.setBounds(442, 24, 79, 29);
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
		lblState.setBounds(281, 180, 48, 29);
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
		lblPhone.setBounds(437, 222, 58, 29);
		formPanel.add(lblPhone);

		JLabel lblApt = new JLabel("Apt#");
		lblApt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblApt.setBounds(461, 141, 40, 29);
		formPanel.add(lblApt);

		aptNumField = new JTextField();
		aptNumField.setText(" ");
		aptNumField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		aptNumField.setColumns(5);
		aptNumField.setBounds(498, 141, 64, 29);
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
		streetNumField.setBounds(120, 141, 74, 29);
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
		ssnAreaField.setBounds(543, 67, 33, 29);
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
		ssnGroupField.setBounds(577, 67, 26, 29);
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
		ssnSerialField.setBounds(604, 67, 48, 29);
		formPanel.add(ssnSerialField);

		JLabel lblNewLabel_1 = new JLabel("Staff Role");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 233, 86, 29);
		formPanel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("(Enter 10 digits)");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(654, 222, 86, 29);
		formPanel.add(lblNewLabel_2);

		lblstreetNumber = new JLabel("(street number)");
		lblstreetNumber.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblstreetNumber.setBounds(120, 120, 74, 21);
		formPanel.add(lblstreetNumber);

		lblstreetName = new JLabel("(street name)");
		lblstreetName.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblstreetName.setBounds(287, 120, 103, 21);
		formPanel.add(lblstreetName);

		mandatoryError = new JLabel("** Please fill out all the required field.");
		mandatoryError.setVisible(false);
		mandatoryError.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		mandatoryError.setBounds(512, 329, 234, 29);
		formPanel.add(mandatoryError);

		label = new JLabel("*");
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(531, 67, 14, 21);
		formPanel.add(label);

		label_1 = new JLabel("*");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setBounds(507, 20, 14, 21);
		formPanel.add(label_1);

		label_2 = new JLabel("*");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_2.setBounds(75, 20, 14, 21);
		formPanel.add(label_2);
		
		// role combo box and item state change listener
		roleComboBox = new JComboBox<String>();
		roleComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().equals("Admin")) {
					userAdminCheckBox.setEnabled(true);
					userAdminCheckBox.setSelected(true);
					addEditPatientCheckBox.setSelected(false);
					ownProfileCheckBox.setEnabled(true);
					ownProfileCheckBox.setSelected(true);
					viewBillCheckBox.setSelected(false);
					viewPatientCheckBox.setSelected(false);
					processPaymentCheckBox.setSelected(false);
					processPaymentCheckBox.setEnabled(false);

				} else if (e.getItem().equals("Doctor")) {
					userAdminCheckBox.setSelected(false);
					userAdminCheckBox.setEnabled(false);
					addEditPatientCheckBox.setEnabled(true);
					addEditPatientCheckBox.setSelected(true);
					ownProfileCheckBox.setEnabled(true);
					ownProfileCheckBox.setSelected(true);
					viewBillCheckBox.setSelected(false);
					viewBillCheckBox.setEnabled(false);
					viewPatientCheckBox.setEnabled(true);
					viewPatientCheckBox.setSelected(true);
					processPaymentCheckBox.setSelected(false);
					processPaymentCheckBox.setEnabled(false);

				} else if (e.getItem().equals("Nurse")) {
					userAdminCheckBox.setSelected(false);
					userAdminCheckBox.setEnabled(false);
					addEditPatientCheckBox.setEnabled(true);
					addEditPatientCheckBox.setSelected(true);
					ownProfileCheckBox.setEnabled(true);
					ownProfileCheckBox.setSelected(true);
					viewBillCheckBox.setSelected(false);
					viewBillCheckBox.setEnabled(false);
					viewPatientCheckBox.setEnabled(true);
					viewPatientCheckBox.setSelected(true);
					processPaymentCheckBox.setSelected(false);
					processPaymentCheckBox.setEnabled(false);

				} else if (e.getItem().equals("Secretary")) {
					userAdminCheckBox.setSelected(false);
					userAdminCheckBox.setEnabled(false);
					addEditPatientCheckBox.setEnabled(true);
					addEditPatientCheckBox.setSelected(true);
					ownProfileCheckBox.setEnabled(true);
					ownProfileCheckBox.setSelected(true);
					viewBillCheckBox.setEnabled(true);
					viewBillCheckBox.setSelected(true);
					viewPatientCheckBox.setEnabled(true);
					viewPatientCheckBox.setSelected(true);
					processPaymentCheckBox.setEnabled(true);
					processPaymentCheckBox.setSelected(true);

				} else if (e.getItem().equals("Finance")) {
					userAdminCheckBox.setSelected(false);
					userAdminCheckBox.setEnabled(false);
					addEditPatientCheckBox.setSelected(false);
					addEditPatientCheckBox.setEnabled(false);
					ownProfileCheckBox.setEnabled(true);
					ownProfileCheckBox.setSelected(true);
					viewBillCheckBox.setEnabled(true);
					viewBillCheckBox.setSelected(true);
					viewPatientCheckBox.setEnabled(false);
					viewPatientCheckBox.setSelected(false);
					processPaymentCheckBox.setEnabled(true);
					processPaymentCheckBox.setSelected(true);

				} else {
					userAdminCheckBox.setSelected(false);
					userAdminCheckBox.setEnabled(false);
					addEditPatientCheckBox.setSelected(false);
					addEditPatientCheckBox.setEnabled(false);
					ownProfileCheckBox.setSelected(false);
					ownProfileCheckBox.setEnabled(false);
					viewBillCheckBox.setSelected(false);
					viewBillCheckBox.setEnabled(false);
					viewPatientCheckBox.setSelected(false);
					viewPatientCheckBox.setEnabled(false);
					processPaymentCheckBox.setSelected(false);
					processPaymentCheckBox.setEnabled(false);

				}
			}
		});
		roleComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "N/A", "Admin", "Doctor", "Finance", "Nurse", "Secretary" }));
		roleComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		roleComboBox.setBounds(96, 233, 98, 29);
		formPanel.add(roleComboBox);
		// end role combo box
		
		addEditPatientCheckBox = new JCheckBox("Add/Edit Patient");
		addEditPatientCheckBox.setEnabled(false);
		addEditPatientCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

					if (addEditPatientCheckBox.isSelected()) {
					addEditPatient = 1;
				} else
					addEditPatient = 0;

			}
		});
		addEditPatientCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addEditPatientCheckBox.setBounds(11, 282, 163, 29);
		formPanel.add(addEditPatientCheckBox);
		
		ownProfileCheckBox = new JCheckBox("Own Profile");
		ownProfileCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (ownProfileCheckBox.isSelected()) {
					ownProfile = 1;
				} else
				ownProfile = 0;
			}
		});
		ownProfileCheckBox.setSelected(true);
		ownProfileCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ownProfileCheckBox.setBounds(188, 282, 163, 29);
		formPanel.add(ownProfileCheckBox);
		
		viewPatientCheckBox = new JCheckBox("View Patient");
		viewPatientCheckBox.setEnabled(false);
		viewPatientCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (viewPatientCheckBox.isSelected()) {
					viewPatient = 1;
				} else
				viewPatient = 0;
			}
		});
		viewPatientCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		viewPatientCheckBox.setBounds(11, 328, 163, 29);
		formPanel.add(viewPatientCheckBox);
		
		viewBillCheckBox = new JCheckBox("View Bill");
		viewBillCheckBox.setEnabled(false);
		viewBillCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (viewBillCheckBox.isSelected()) {
					viewBill = 1;
				} else
				viewBill = 0;
			}
		});
		viewBillCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		viewBillCheckBox.setBounds(188, 328, 163, 29);
		formPanel.add(viewBillCheckBox);

		JLabel lbllimited = new JLabel("* Limited");
		lbllimited.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lbllimited.setBounds(350, 286, 104, 29);
		formPanel.add(lbllimited);

		processPaymentCheckBox = new JCheckBox("Process Payment");
		processPaymentCheckBox.setEnabled(false);
		processPaymentCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (processPaymentCheckBox.isSelected()) {
					processPayment = 1;
				} else
				processPayment = 0;
			}
		});
		processPaymentCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		processPaymentCheckBox.setBounds(188, 373, 163, 29);
		formPanel.add(processPaymentCheckBox);

		userAdminCheckBox = new JCheckBox("User Admin");
		userAdminCheckBox.setEnabled(false);
		userAdminCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (userAdminCheckBox.isSelected()) {
					userAdmin = 1;
				}
				userAdmin = 0;
			}
		});
		userAdminCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		userAdminCheckBox.setBounds(11, 373, 163, 29);
		formPanel.add(userAdminCheckBox);

		activeCheckBox = new JCheckBox("Active?");
		activeCheckBox.setSelected(true);
		activeCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (activeCheckBox.isSelected()) {
					active = 1;
				} else
				active = 0;
			}
		});
		activeCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		activeCheckBox.setBounds(365, 373, 105, 29);
		formPanel.add(activeCheckBox);

		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		emailLabel.setBounds(437, 259, 58, 29);
		formPanel.add(emailLabel);

		emailTextField = new JFormattedTextField((AbstractFormatter) null);
		emailTextField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		emailTextField.setColumns(10);
		emailTextField.setBounds(500, 259, 152, 29);
		formPanel.add(emailTextField);

		userNameField = new JTextField();
		userNameField.setBounds(756, 67, 145, 35);
		formPanel.add(userNameField);
		userNameField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(756, 134, 145, 35);
		formPanel.add(passwordField);

		lblAssignUsername = new JLabel("Assign Username");
		lblAssignUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAssignUsername.setBounds(756, 39, 145, 29);
		formPanel.add(lblAssignUsername);

		lblAssignPassword = new JLabel("Assign Password");
		lblAssignPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAssignPassword.setBounds(756, 110, 145, 29);
		formPanel.add(lblAssignPassword);
		formPanel.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { firstNameField, midNameField, lastNameField, DOBField,
						genderField, ssnAreaField, ssnGroupField, ssnSerialField, streetNumField, streetNameField,
						aptNumField, cityField, stateField, zipcodeField, phoneNumberField, saveNewEmployeeButton }));

	}

	JButton getSaveButton() {
		return saveNewEmployeeButton;
	}

	boolean generateEmployeeID() {
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

		else if (dbc.checkIfEmployeeAlreadyExist(firstNameField.getText(), lastNameField.getText(),
				ssnSerialField.getText())) {
			if (JOptionPane.showConfirmDialog(null,
					"This person already exist in record, do you want to load his/her profile?", "WARNING",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				dbc.getProfile(firstNameField.getText(), lastNameField.getText(), ssnSerialField.getText(), "employee");
				return true;
			}
		}
		else {
			long time = System.currentTimeMillis();
			employeeID = Long.toString((time));

			employeeID = employeeID.substring(5, employeeID.length());

			while (dbc.checkExistingID(employeeID)) {
				time = System.currentTimeMillis();
				employeeID = Long.toString((time));
				employeeID = employeeID.substring(5, employeeID.length());
			}

			if(firstNameField.getText() != null) 	firstName = firstNameField.getText();
			if(midNameField.getText() !=null) midName = midNameField.getText();		
			if(lastNameField.getText() != null) lastName= lastNameField.getText();		
			if(DOBField.getText() != null) DOB = DOBField.getText();
			if (!(genderField.getSelectedIndex() < 1))
				gender = genderField.getSelectedItem().toString();
			if(ssnAreaField.getText() != null) ssnArea = ssnAreaField.getText();
			if(ssnGroupField.getText() != null)ssnGroup = ssnGroupField.getText();
			if(ssnSerialField.getText() != null)ssnSerial = ssnSerialField.getText();
			if(phoneNumberField.getText() != null) phoneNumber = phoneNumberField.getText();
			if(streetNumField.getText() != null) streetNum = streetNumField.getText();
			if(aptNumField.getText() != null) aptNum = aptNumField.getText();
			if(streetNameField.getText() != null) streetName = streetNameField.getText();
			if(cityField.getText() != null) city = cityField.getText();
			if (!(stateField.getSelectedIndex() < 1))
				state = stateField.getSelectedItem().toString();
			if(zipcodeField.getText() != null) zipcode = zipcodeField.getText();
			if (emailTextField.getText() != "")
				email = emailTextField.getText();

			if (!(roleComboBox.getSelectedIndex() < 1))
				role = roleComboBox.getSelectedItem().toString();
			if (addEditPatientCheckBox.isSelected())
				addEditPatient = 1;
			if (viewPatientCheckBox.isSelected())
				viewPatient = 1;
			if (ownProfileCheckBox.isSelected())
				ownProfile = 1;
			if (viewBillCheckBox.isSelected())
				viewBill = 1;
			if (processPaymentCheckBox.isSelected())
				processPayment = 1;
			if (userAdminCheckBox.isSelected())
				userAdmin = 1;
			if (activeCheckBox.isSelected())
				active = 1;

			loadEmployeeInfo();

			if (dbc.addEmployeeProfile(employeeID, userID, firstName, midName, lastName, gender, DOB, email,
					phone_number, role, ssnArea, ssnGroup, ssnSerial, userAdmin, addEditPatient, viewPatient,
					ownProfile, viewBill,
					processPayment, active)) {

				dbc.addID(employeeID);
				dbc.addNewUser(employeeID, userNameField.getText(), passwordField.getText());

			dbc.addAddress(employeeID, streetNum, aptNum, streetName, city, state, zipcode);

			dbc.addID(employeeID);


			return true;
			} else
				return false;
		}
		return false;
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
		EmployeeProfile.setStreetNum(streetNum);
		EmployeeProfile.setStreetName(streetName);
		EmployeeProfile.setAptNum(aptNum);
		EmployeeProfile.setCityName(city);
		EmployeeProfile.setStateName(state);
		EmployeeProfile.setZipCode(zipcode);
		EmployeeProfile.setRole(role);
		EmployeeProfile.setEmail(email);

		clearForm();
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

		firstNameField.setBackground(Color.white);
		lastNameField.setBackground(Color.white);
		ssnAreaField.setBackground(Color.white);
		ssnGroupField.setBackground(Color.white);
		ssnSerialField.setBackground(Color.white);
	}

	boolean isFormClear() {
		if(
		firstNameField.getText().isEmpty() && midNameField.getText().isEmpty() && 
				lastNameField.getText().isEmpty() && DOBField.getText().isEmpty() && ssnAreaField.getText().isEmpty()
				&& ssnGroupField.getText().isEmpty() && ssnSerialField.getText().isEmpty()
				&& phoneNumberField.getText().isEmpty()) {

			return true;
		}

		else
			return false;
	}

	boolean isMandatoryFieldFill() {
		if (userNameField.getText().isEmpty() || passwordField.getText().isEmpty() || firstNameField.getText().isEmpty()
				|| lastNameField.getText().isEmpty() || ssnAreaField.getText().length() < 3
				|| ssnGroupField.getText().length() < 2 || ssnSerialField.getText().length() < 4)
			return false;


		else
			return true;
	}
	boolean isDigit(char input) {
		if(input >= 48 && input <= 57) 		return true;

		return false;
	}
}
