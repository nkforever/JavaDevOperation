import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nameSearch, idSearch, ssnSearch;
	private JButton searchButton, logoutButton, homeButton, addNewButton, enterRecordButton, editProfile,
			patientHistoryButton, viewBillButton, addRoomButton, addDoctorButton, addTreatmentButton;

	private JPanel profilePanel, profileInputPanel, bottomPanel, profileSection, searchPanel, centerPanel,
			optionProfilePanel;
	private JLabel userLabel, SSNLabel, genderLabel, DOBLabel, IDLabel, firstNameLabel, midNameLabel,
			lastNameLabel, primaryLabel, lblPhone, updateStatusLabel, streetLabel, cityStateLabel,
			phoneNumberLabel, aptLabel, errormessageLabel;

	private DBcontrol dbc = new DBcontrol();

	// MVC controller group
	private NewPatientForm newPatientForm = new NewPatientForm();
	private LastPatientHistory lastPatientHistory = new LastPatientHistory();
	private PatientAssignmentForm patientAssignmentForm = new PatientAssignmentForm();
	private NewEmployeeForm newEmployeForm = new NewEmployeeForm();
	private EditEmployeeForm editEmployeeInfo = new EditEmployeeForm();
	private EditPatientForm editPatientInfo = new EditPatientForm();
	private ViewBillForm viewBillForm = new ViewBillForm();
	private AddRoomForm addRoomForm = new AddRoomForm();
	private AddTreatmentForm addTreatmentForm = new AddTreatmentForm();
	private AddDoctorForm addDoctorForm = new AddDoctorForm();
	private PaymentProcessingForm paymentForm = new PaymentProcessingForm();


	MVCcontroller mvc = new MVCcontroller(this, newPatientForm, patientAssignmentForm, editPatientInfo,
			editEmployeeInfo, paymentForm);
	// End MVC controller group

	DefaultListModel<String> model;
	private Label lblPrimaryDoctor;
	private JButton processPaymentButton;


	// Begin main panel class
	public MainPanel() {

		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 5));
		searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new GridLayout(0, 1, 0, 3));

		JPanel inputPanel = new JPanel();
		searchPanel.add(inputPanel);
		inputPanel.setLayout(new GridLayout(2, 7, 5, 5));

		JPanel blank0 = new JPanel();
		inputPanel.add(blank0);

		JLabel lblname = new JLabel("Enter Name");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(lblname);

		JLabel lblssn = new JLabel("Enter 4 digit SSN:");
		lblssn.setHorizontalAlignment(SwingConstants.CENTER);
		lblssn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(lblssn);

		JLabel lblid = new JLabel("Enter ID");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(lblid);

		JPanel blank1 = new JPanel();
		inputPanel.add(blank1);

		JPanel blank2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) blank2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		inputPanel.add(blank2);

		userLabel = new JLabel("Login as: " + OwnProfile.getUser());
		userLabel.setForeground(Color.BLUE);
		userLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		blank2.add(userLabel);

		JPanel blank3 = new JPanel();
		inputPanel.add(blank3);

		nameSearch = new JTextField();
		nameSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				ssnSearch.setBackground(Color.white);
				idSearch.setBackground(Color.white);
				nameSearch.setBackground(Color.white);
			}
		});
		nameSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						searchProfile();
					} catch (InterruptedException e1) {
						return;
					}
				}
			}
		});
		nameSearch.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		nameSearch.setHorizontalAlignment(SwingConstants.LEFT);
		inputPanel.add(nameSearch);
		nameSearch.setColumns(10);

		idSearch = new JTextField();
		idSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				ssnSearch.setBackground(Color.white);
				idSearch.setBackground(Color.white);
				nameSearch.setBackground(Color.white);
			}
		});
		idSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						searchProfile();
					} catch (InterruptedException e1) {
						return;
					}
				}
			}
		});

		ssnSearch = new JTextField();
		ssnSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ssnSearch.setBackground(Color.white);
				idSearch.setBackground(Color.white);
				nameSearch.setBackground(Color.white);
			}
		});
		ssnSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						searchProfile();
					} catch (InterruptedException e1) {
						return;
					}
				}
			}
		});
		ssnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ssnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		inputPanel.add(ssnSearch);
		ssnSearch.setColumns(10);
		inputPanel.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { nameSearch, idSearch, ssnSearch, searchButton, logoutButton }));
		idSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		idSearch.setHorizontalAlignment(SwingConstants.LEFT);
		inputPanel.add(idSearch);
		idSearch.setColumns(10);

		searchButton = new JButton("Search");
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						searchProfile();
					} catch (InterruptedException e1) {
						return;
					}
				}
			}
		});
		searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(searchButton);

		JPanel blank4 = new JPanel();
		inputPanel.add(blank4);

		model = new DefaultListModel<String>();

		bottomPanel = new JPanel();
		FlowLayout fl_bottomPanel = (FlowLayout) bottomPanel.getLayout();
		fl_bottomPanel.setHgap(50);
		fl_bottomPanel.setAlignment(FlowLayout.RIGHT);
		add(bottomPanel, BorderLayout.SOUTH);

		logoutButton = new JButton("Logout");
		logoutButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.exit(0);
				}
			}
		});
		

		homeButton = new JButton("Home");
		homeButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					backToHome();
				}
			}
		});

		processPaymentButton = new JButton("Process Payment");
		processPaymentButton.setEnabled(false);
		processPaymentButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		bottomPanel.add(processPaymentButton);

		errormessageLabel = new JLabel("...");
		bottomPanel.add(errormessageLabel);
		errormessageLabel.setVisible(false);
		errormessageLabel.setForeground(Color.RED);
		errormessageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				
		updateStatusLabel = new JLabel("-");
		updateStatusLabel.setForeground(Color.GREEN);
		updateStatusLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bottomPanel.add(updateStatusLabel);
		homeButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		bottomPanel.add(homeButton);
		logoutButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		bottomPanel.add(logoutButton);

		centerPanel = new JPanel();
		centerPanel.setBackground(Color.LIGHT_GRAY);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(5, 5));

		profilePanel = new JPanel();
		profilePanel.setBackground(Color.LIGHT_GRAY);
		centerPanel.add(profilePanel, BorderLayout.NORTH);
		profilePanel.setLayout(new GridLayout(0, 2, 5, 5));

		profileSection = new JPanel();
		profileSection.setBackground(Color.WHITE);
		profileSection.setPreferredSize(new Dimension(400, 225));
		profileSection.setMinimumSize(new Dimension(400, 225));
		profileSection.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		profilePanel.add(profileSection);
		profileSection.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setBounds(11, 0, 42, 38);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblNewLabel);

		IDLabel = new JLabel("-");
		IDLabel.setBounds(57, 0, 127, 38);
		IDLabel.setHorizontalAlignment(SwingConstants.LEFT);
		IDLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(IDLabel);

		JLabel lblDOB = new JLabel("DOB: ");
		lblDOB.setBounds(11, 62, 64, 38);
		lblDOB.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblDOB);

		DOBLabel = new JLabel("-");
		DOBLabel.setBounds(79, 62, 105, 38);
		DOBLabel.setHorizontalAlignment(SwingConstants.LEFT);
		DOBLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(DOBLabel);

		JLabel lblGender = new JLabel("Gender: ");
		lblGender.setBounds(186, 62, 59, 38);
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblGender);

		genderLabel = new JLabel("-");
		genderLabel.setBounds(245, 62, 64, 38);
		genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		genderLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(genderLabel);

		JLabel lblSSN = new JLabel("SSN: ");
		lblSSN.setBounds(342, 99, 42, 38);
		lblSSN.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblSSN);

		SSNLabel = new JLabel("    -   -       ");
		SSNLabel.setBounds(388, 99, 127, 38);
		SSNLabel.setHorizontalAlignment(SwingConstants.LEFT);
		SSNLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(SSNLabel);

		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(11, 143, 76, 38);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblAddress);

		streetLabel = new JLabel(" ");
		streetLabel.setBounds(89, 143, 201, 38);
		streetLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(streetLabel);

		lblPrimaryDoctor = new Label("Primary Doctor:");
		lblPrimaryDoctor.setBounds(341, 143, 139, 38);
		lblPrimaryDoctor.setFont(new Font("Times New Roman", Font.BOLD, 15));
		profileSection.add(lblPrimaryDoctor);

		primaryLabel = new JLabel(" ");
		primaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		primaryLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		primaryLabel.setBounds(335, 173, 145, 38);
		primaryLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(primaryLabel);

		cityStateLabel = new JLabel(" ");
		cityStateLabel.setBounds(89, 173, 234, 38);
		cityStateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(cityStateLabel);

		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(11, 24, 59, 38);
		profileSection.add(lblName);

		firstNameLabel = new JLabel("-");
		firstNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		firstNameLabel.setBounds(79, 24, 89, 38);
		profileSection.add(firstNameLabel);

		midNameLabel = new JLabel("-");
		midNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		midNameLabel.setBounds(176, 25, 89, 38);
		profileSection.add(midNameLabel);

		lastNameLabel = new JLabel("-");
		lastNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lastNameLabel.setBounds(273, 25, 117, 38);
		profileSection.add(lastNameLabel);

		aptLabel = new JLabel("Apt# ");
		aptLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		aptLabel.setBounds(11, 173, 76, 38);
		profileSection.add(aptLabel);

		lblPhone = new JLabel("Phone#");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPhone.setBounds(11, 99, 64, 38);
		profileSection.add(lblPhone);

		phoneNumberLabel = new JLabel("(   )-   -    ");
		phoneNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		phoneNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		phoneNumberLabel.setBounds(79, 99, 127, 38);
		profileSection.add(phoneNumberLabel);

		optionProfilePanel = new JPanel();
		profilePanel.add(optionProfilePanel);
		optionProfilePanel.setLayout(null);
		
		patientHistoryButton = new JButton("View History");
		patientHistoryButton.setEnabled(false);
		patientHistoryButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		patientHistoryButton.setBounds(10, 67, 155, 37);
		optionProfilePanel.add(patientHistoryButton);
		

		editProfile = new JButton("Edit Profile");
		editProfile.setEnabled(false);
		editProfile.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		editProfile.setBounds(10, 15, 155, 37);
		optionProfilePanel.add(editProfile);
		
		viewBillButton = new JButton("View Bill");
		viewBillButton.setEnabled(false);
		viewBillButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		viewBillButton.setBounds(10, 119, 155, 37);
		optionProfilePanel.add(viewBillButton);
		
		enterRecordButton = new JButton("Enter Record");
		enterRecordButton.setEnabled(false);
		enterRecordButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		enterRecordButton.setBounds(10, 171, 155, 37);
		optionProfilePanel.add(enterRecordButton);

		addRoomButton = new JButton("Add Room");
		addRoomButton.setEnabled(false);
		addRoomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (OwnProfile.getRole().equalsIgnoreCase("Admin")) {
					setAdminOptionVisible();
				}
			}
		});
		addRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAddRoomForm();
			}
		});
		addRoomButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		addRoomButton.setBounds(211, 41, 155, 37);
		optionProfilePanel.add(addRoomButton);

		addDoctorButton = new JButton("Add Doctor");
		addDoctorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (OwnProfile.getRole().equalsIgnoreCase("Admin")) {
					setAdminOptionVisible();
				}
			}
		});
		addDoctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAddDoctorForm();
			}
		});
		addDoctorButton.setEnabled(false);
		addDoctorButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		addDoctorButton.setBounds(211, 93, 155, 37);
		optionProfilePanel.add(addDoctorButton);

		addTreatmentButton = new JButton("Add Treatment");
		addTreatmentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (OwnProfile.getRole().equalsIgnoreCase("Admin")) {
					setAdminOptionVisible();
				}
			}
		});
		addTreatmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAddTreatmentForm();
			}
		});
		addTreatmentButton.setEnabled(false);
		addTreatmentButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		addTreatmentButton.setBounds(211, 148, 155, 37);
		optionProfilePanel.add(addTreatmentButton);

		profileInputPanel = new JPanel();
		profileInputPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		profileInputPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(profileInputPanel, BorderLayout.CENTER);


		addNewButton = new JButton("Add New Profile");
		addNewButton.setBackground(Color.WHITE);
		addNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		addNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		profileInputPanel.add(addNewButton);

		// add action listener to buttons
		searchButton.addActionListener(new actionListener());
		logoutButton.addActionListener(new actionListener());
		homeButton.addActionListener(new actionListener());
		addNewButton.addActionListener(new actionListener());
		enterRecordButton.addActionListener(new actionListener());
		editProfile.addActionListener(new actionListener());
		patientHistoryButton.addActionListener(new actionListener());
		viewBillButton.addActionListener(new actionListener());

	}// end panel


	JTextField getNameSearchField() {
		return nameSearch;
	}
	
	void addSaveButtonListener(ActionListener savePress) {
		newPatientForm.getSaveButton().addActionListener(savePress);
	}// end add action listener

	void addRecordButtonListener(ActionListener recordButtonPress) {
		patientAssignmentForm.getAddRecordButton().addActionListener(recordButtonPress);
	}// end add action listener
	
	void addUpdatePatientButtonListener(ActionListener updatePatientInfo) {
		editPatientInfo.getSaveUpdateButton().addActionListener(updatePatientInfo);
	}

	void addUpdateEmployeeButtonListener(ActionListener upateEmployeeInfo) {
		editEmployeeInfo.getSaveUpdateButton().addActionListener(upateEmployeeInfo);
	}

	public void addProcessPaymentButtonListener(ActionListener processPayment) {
		paymentForm.getProcessPaymentButton().addActionListener(processPayment);

	}

	private class actionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(addNewButton))
				pressAddNewProfile();

			else if (e.getSource().equals(enterRecordButton))
				loadPatientAssignmentForm();

			else if (e.getSource().equals(editProfile))
				loadEditProfile();

			else if (e.getSource().equals(homeButton))
				backToHome();

			else if (e.getSource().equals(logoutButton))
				System.exit(0);

			else if (e.getSource().equals(searchButton)) {
				try {
					searchProfile();
				} catch (InterruptedException e1) {
					return;
				}
			}
			else if (e.getSource().equals(patientHistoryButton)) {
				loadPatientHistoryForm();

			} else if (e.getSource().equals(viewBillButton)) {
				loadViewBill();
			}else if (e.getSource().equals(addRoomButton)) {
				loadAddRoomForm();
			}else if (e.getSource().equals(addDoctorButton)) {
				loadAddDoctorForm();
			}else if (e.getSource().equals(addTreatmentButton)) {
				loadAddTreatmentForm();
			}
			// TODO //need some more work to do
			// If there's more button -----------------------------------------
		}
	};// End ActionListener class

	private void loadEditProfile() {
		profileInputPanel.removeAll();

		if (OwnProfile.getRole().equalsIgnoreCase("admin")) {
			editEmployeeInfo.loadEmployeeInfoToForm();
			profileInputPanel.add(editEmployeeInfo);
			editEmployeeInfo.setVisible(true);
			profileInputPanel.repaint();
			profileInputPanel.validate();
		} else {
			editPatientInfo.loadPatinetInfoToForm();
			profileInputPanel.add(editPatientInfo);
			editPatientInfo.setVisible(true);
			profileInputPanel.repaint();
			profileInputPanel.validate();
		}
	}// end load edit profile

	private void pressAddNewProfile() {
		profileInputPanel.removeAll();
		
		if (OwnProfile.getRole().equalsIgnoreCase("admin")) {
			profileInputPanel.add(newEmployeForm);
			newEmployeForm.setVisible(true);
			profileInputPanel.repaint();
			profileInputPanel.validate();
		}
		else {
			profileInputPanel.add(newPatientForm);
			newPatientForm.setVisible(true);
			profileInputPanel.repaint();
			profileInputPanel.validate();
		}

	}// End press Add New Patient function

	private void searchProfile() throws InterruptedException {
		if (idSearch.getText().isEmpty()) {
			if (ssnSearch.getText().isEmpty() && nameSearch.getText().isEmpty()) {
				nameSearch.setBackground(Color.YELLOW);
				idSearch.setBackground(Color.YELLOW);
				ssnSearch.setBackground(Color.YELLOW);
				return;
			} else if ((!ssnSearch.getText().isEmpty() && nameSearch.getText().isEmpty())
					|| (ssnSearch.getText().isEmpty() && !nameSearch.getText().isEmpty())) {
				nameSearch.setBackground(Color.YELLOW);
				ssnSearch.setBackground(Color.YELLOW);
				return;
			}
		}
		// else do following
		
		if (!OwnProfile.getRole().equalsIgnoreCase("admin") || !OwnProfile.getRole().equalsIgnoreCase("N/A")) {
			dbc.getProfile(idSearch.getText(), nameSearch.getText(), ssnSearch.getText(), "patient");
			if (PatientProfile.found) {
				nameSearch.setText("");
				idSearch.setText("");
				ssnSearch.setText("");

				loadPatient();
				return;
			}
			else
				setNotFound(); // set not found
		} else if (OwnProfile.getRole().equalsIgnoreCase("admin")) {
			dbc.getProfile(idSearch.getText(), nameSearch.getText(), ssnSearch.getText(), "employee");
			if (EmployeeProfile.found) {
				nameSearch.setText("");
				idSearch.setText("");
				ssnSearch.setText("");

				editProfile.setEnabled(true);
				loadEmployee();
				editEmployeeInfo.loadEmployeeInfoToForm();
				return;
			}
			else
			{
				nameSearch.setText("YOU ARE");
				idSearch.setText("NOT AUTHORIZE!");
				ssnSearch.setText("");
			}
		}
	}// end search

	private void setNotFound() {
		IDLabel.setForeground(Color.RED);
		IDLabel.setText("Search Not Found!");
		firstNameLabel.setText("");
		midNameLabel.setText("");
		lastNameLabel.setText("");
		DOBLabel.setText("");
		genderLabel.setText("");
		primaryLabel.setText("");
	}

	public void loadEmployee() {

		// patient info
		IDLabel.setForeground(Color.BLACK);
		IDLabel.setText(EmployeeProfile.getEmployeeID());
		firstNameLabel.setText(EmployeeProfile.getFirstName());
		midNameLabel.setText(EmployeeProfile.getMidName());
		lastNameLabel.setText(EmployeeProfile.getLastName());
		DOBLabel.setText(EmployeeProfile.getDOB());
		genderLabel.setText(EmployeeProfile.getGender());
		lblPrimaryDoctor.setText("Primary Role");
		primaryLabel.setText(EmployeeProfile.getRole());
		phoneNumberLabel.setText(EmployeeProfile.getPhoneNumber());
		SSNLabel.setText("XXX-XX-" + EmployeeProfile.getSsnSerial());

		// address
		String address = EmployeeProfile.getStreetNum() + " " + EmployeeProfile.getStreetName();
		String cityStateZip = EmployeeProfile.getCityName() + ", " + EmployeeProfile.getStateName() + " "
				+ EmployeeProfile.getZipCode();
		streetLabel.setText(address);
		cityStateLabel.setText(cityStateZip);


	}// end load employee

	void loadPatient() {
		editProfile.setEnabled(true);
		{
			if (OwnProfile.getRole().equalsIgnoreCase("Secretary") || OwnProfile.getRole().equalsIgnoreCase("Finance"))
				viewBillButton.setEnabled(true);
			else
				viewBillButton.setEnabled(false);
		}
		{
			if (OwnProfile.getRole().equalsIgnoreCase("Finance"))
				patientHistoryButton.setEnabled(false);
			else
				patientHistoryButton.setEnabled(true);
		}
		{
			if (OwnProfile.getRole().equalsIgnoreCase("Finance")) {
					enterRecordButton.setEnabled(false);
			} else {

				enterRecordButton.setEnabled(true);
				if (PatientProfile.getActive() == 1)
					patientAssignmentForm.loadAssignmentForm();
				else loadLastPatientHistory();
			}
		}

		// patient info
		IDLabel.setForeground(Color.BLACK);
		IDLabel.setText(PatientProfile.getPatientID());
		firstNameLabel.setText(PatientProfile.getFirstName());
		midNameLabel.setText(PatientProfile.getMidName());
		lastNameLabel.setText(PatientProfile.getLastName());
		DOBLabel.setText(PatientProfile.getDOB());
		genderLabel.setText(PatientProfile.getGender());
		primaryLabel.setText(PatientProfile.getPrimaryDoctor());
		phoneNumberLabel.setText(PatientProfile.getPhoneNumber());
		SSNLabel.setText("XXX-XX-" + PatientProfile.getSsnSerial());

		// address
		String address = PatientProfile.getStreetNum() + " " + PatientProfile.getStreetName();
		String cityStateZip = PatientProfile.getCityName() + ", " + PatientProfile.getStateName() + " "
				+ PatientProfile.getZipcode();
		streetLabel.setText(address);
		aptLabel.setText("Apt#" + PatientProfile.getAptNum());
		cityStateLabel.setText(cityStateZip);

	}

	void loadLastPatientHistory() {
			profileInputPanel.removeAll();
			profileInputPanel.add(lastPatientHistory);
			lastPatientHistory.setVisible(true);
			profileInputPanel.repaint();
			profileInputPanel.validate();
	}

	void loadPatientAssignmentForm() {
		profileInputPanel.removeAll();
		{
			if (PatientProfile.getActive() == 1) {
				patientAssignmentForm.loadAssignmentForm();
			}
		}
		profileInputPanel.add(patientAssignmentForm);
		patientAssignmentForm.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	private void backToHome() {
		if (newPatientForm.isFormClear() == false) {
			if (JOptionPane.showConfirmDialog(null, "Form hasn't save, are you sure you want to leave?", "WARNING",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				nameSearch.setText("");
				idSearch.setText("");
				ssnSearch.setText("");

				IDLabel.setText(" ");
				firstNameLabel.setText(" ");
				midNameLabel.setText(" ");
				lastNameLabel.setText(" ");
				SSNLabel.setText(" ");
				phoneNumberLabel.setText(" ");
				streetLabel.setText(" ");
				aptLabel.setText(" ");
				cityStateLabel.setText(" ");
				primaryLabel.setText(" ");
				DOBLabel.setText(" ");
				genderLabel.setText(" ");
				//disable buttons 
				enterRecordButton.setEnabled(false);
				editProfile.setEnabled(false);
				patientHistoryButton.setEnabled(false);
				viewBillButton.setEnabled(false);
				errormessageLabel.setVisible(false);

				profileInputPanel.removeAll();
				profileInputPanel.add(addNewButton);
				profileInputPanel.repaint();
				profileInputPanel.validate();

				newPatientForm.clearForm();
			}
			return;
		}
		else {
			nameSearch.setText("");
			idSearch.setText("");
			ssnSearch.setText("");

			IDLabel.setText(" ");
			firstNameLabel.setText(" ");
			midNameLabel.setText(" ");
			lastNameLabel.setText(" ");
			SSNLabel.setText(" ");
			phoneNumberLabel.setText(" ");
			streetLabel.setText(" ");
			aptLabel.setText(" ");
			cityStateLabel.setText(" ");
			primaryLabel.setText(" ");
			DOBLabel.setText(" ");
			genderLabel.setText(" ");

			profileInputPanel.removeAll();
			profileInputPanel.add(addNewButton);
			profileInputPanel.repaint();
			profileInputPanel.validate();
		}
	}

	public void updateEmployeeInfoIsPressed() {
		errormessageLabel.setVisible(true);
		errormessageLabel.setText("Updated.");
		editEmployeeInfo.clearForm();

		editEmployeeInfo.setVisible(false);
		addNewButton.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();

	}

	public void updatePatientInfoIsPressed() {
		errormessageLabel.setVisible(true);
		errormessageLabel.setText("Updated.");
		editPatientInfo.clearForm();

		editPatientInfo.setVisible(false);
		addNewButton.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();

	}

	public void errorMessage(String message) {
		errormessageLabel.setText(message);
	}

	private void setAdminOptionVisible() {
		addRoomButton.setEnabled(true);
		addDoctorButton.setEnabled(true);
		addTreatmentButton.setEnabled(true);
	}

	void loadPatientHistoryForm() {
		profileInputPanel.removeAll();
		// call a method in last patient history form to load the history
		profileInputPanel.add(lastPatientHistory);
		lastPatientHistory.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	void loadViewBill() {
		profileInputPanel.removeAll();
		profileInputPanel.add(viewBillForm);
		viewBillForm.setVisible(true);
		viewBillForm.loadPaymentProfile();
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	void loadAddRoomForm() {
		profileInputPanel.removeAll();
		// call a method in last patient history form to load the history
		// -----------------------------
		profileInputPanel.add(addRoomForm);
		addRoomForm.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	void loadAddDoctorForm() {
		profileInputPanel.removeAll();
		// call a method in last patient history form to load the history
		// ------------------------------
		profileInputPanel.add(addDoctorForm);
		addDoctorForm.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	void loadAddTreatmentForm() {
		profileInputPanel.removeAll();
		// call a method in last patient history form to load the history
		// ------------------------------
		profileInputPanel.add(addTreatmentForm);
		addTreatmentForm.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	void loadPaymentForm() {
		profileInputPanel.removeAll();
		profileInputPanel.add(paymentForm);
		paymentForm.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
		if (PaymentProfile.getTotalBalance() > 0)
			processPaymentButton.setEnabled(true);
	}

}
