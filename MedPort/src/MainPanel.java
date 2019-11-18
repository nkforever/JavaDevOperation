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
	private JButton searchButton, logoutButton, homeButton, addNewButton, checkinButton, editPatientProfile,
			patientHistoryButton, viewBillButton;

	private JPanel profilePanel, profileInputPanel, bottomPanel, profileSection, searchPanel, centerPanel,
			optionProfilePanel;
	private JLabel userLabel, SSNLabel, genderLabel, DOBLabel, IDLabel, firstNameLabel, midNameLabel,
			lastNameLabel, primaryLabel, lblPhone, updateStatusLabel, streetLabel, cityStateLabel,
			phoneNumberLabel, aptLabel;

	private DBcontrol dbc = new DBcontrol();

	// MVC controller group
	NewPatientForm npf = new NewPatientForm();
	lastPatientHistory lph = new lastPatientHistory();
	patientAssignmentForm paf = new patientAssignmentForm();
	NewEmployeeForm nef = new NewEmployeeForm();

	MVCcontroller mvc = new MVCcontroller(this, npf, paf);
	// End MVC controller group

	private DefaultListModel<String> model;
	private OwnProfile profile = new OwnProfile();
	private Label lblPrimaryDoctor;

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

		userLabel = new JLabel("Login as: " + profile.getUser());
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
		patientHistoryButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		patientHistoryButton.setBounds(10, 67, 155, 37);
		optionProfilePanel.add(patientHistoryButton);
		
		editPatientProfile = new JButton("Edit Profile");
		editPatientProfile.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		editPatientProfile.setBounds(10, 15, 155, 37);
		optionProfilePanel.add(editPatientProfile);
		
		viewBillButton = new JButton("View Bill");
		viewBillButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		viewBillButton.setBounds(10, 119, 155, 37);
		optionProfilePanel.add(viewBillButton);
		
		checkinButton = new JButton("Check-in");
		checkinButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		checkinButton.setBounds(10, 171, 155, 37);
		optionProfilePanel.add(checkinButton);

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
		checkinButton.addActionListener(new actionListener());
		editPatientProfile.addActionListener(new actionListener());
		patientHistoryButton.addActionListener(new actionListener());
		viewBillButton.addActionListener(new actionListener());



	}// end panel


	JTextField getNameSearchField() {
		return nameSearch;
	}
	
	
	void addSaveButtonListener(ActionListener savePress) {
		npf.getSaveButton().addActionListener(savePress);
	}// end add action listener

	void addRecordButtonListener(ActionListener recordButtonPress) {
		paf.getAddRecordButton().addActionListener(recordButtonPress);
	}// end add action listener
	

	private class actionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(addNewButton))
				pressAddNewProfile();

			else if (e.getSource().equals(checkinButton))
				loadPatientAssignmentForm();

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

			// TODO Auto-generated method stub
			// If there's more button -----------------------------------------
		}
	};// End ActionListener class


	void pressAddNewProfile() {
		profileInputPanel.removeAll();
		
		if (OwnProfile.getRole().equalsIgnoreCase("admin")) {
			profileInputPanel.add(nef);
			nef.setVisible(true);
			profileInputPanel.repaint();
			profileInputPanel.validate();
		}
		else {
			profileInputPanel.add(npf);
			npf.setVisible(true);
			profileInputPanel.repaint();
			profileInputPanel.validate();
		}

	}// End press Add New Patient function

	void searchProfile() throws InterruptedException {
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
		
		if (!OwnProfile.getRole().equalsIgnoreCase("admin")) {
			dbc.getProfile(idSearch.getText(), nameSearch.getText(), ssnSearch.getText(), "patient");
			if (PatientProfile.found) {
				nameSearch.setText("");
				idSearch.setText("");
				ssnSearch.setText("");

				loadPatient();
				return;
			}
		} else if (OwnProfile.getRole().equalsIgnoreCase("admin")) {
			dbc.getProfile(idSearch.getText(), nameSearch.getText(), ssnSearch.getText(), "employee");
			if (EmployeeProfile.found) {
				nameSearch.setText("");
				idSearch.setText("");
				ssnSearch.setText("");

				loadEmployee();
			}
		} else {

			IDLabel.setForeground(Color.RED);
			IDLabel.setText("Search Not Found!");
			firstNameLabel.setText("");
			midNameLabel.setText("");
			lastNameLabel.setText("");
			DOBLabel.setText("");
			genderLabel.setText("");
			primaryLabel.setText("");
		}

	}// end search

	void loadEmployee() {

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

		if (PatientProfile.getActive() == 0) {
			loadLastPatientHistory();

		} else {
			loadPatientAssignmentForm();
		}

	}

	void loadLastPatientHistory() {
		profileInputPanel.removeAll();
		profileInputPanel.add(lph);
		lph.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	void loadPatientAssignmentForm() {
		profileInputPanel.removeAll();
		profileInputPanel.add(paf);
		paf.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
	}

	void backToHome() {
		if (npf.isFormClear() == false) {
			if (JOptionPane.showConfirmDialog(null, "Form hasn't save, are you sure you want to leave?", "WARNING",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				nameSearch.setText("");
				idSearch.setText("");
				ssnSearch.setText("");

				IDLabel.setText("_");
				firstNameLabel.setText("_");
				midNameLabel.setText("_");
				lastNameLabel.setText("_");
				SSNLabel.setText("   -  -    ");
				phoneNumberLabel.setText("(   ) -    ");
				streetLabel.setText("_____" + " _____ __");
				aptLabel.setText("");
				cityStateLabel.setText("________" + ", __" + " _____");
				primaryLabel.setText("________");
				DOBLabel.setText("_");
				genderLabel.setText("_");

				profileInputPanel.removeAll();
				profileInputPanel.add(addNewButton);
				profileInputPanel.repaint();
				profileInputPanel.validate();

				npf.clearForm();
			}
			return;
		}
		nameSearch.setText("");
		idSearch.setText("");
		ssnSearch.setText("");

		IDLabel.setText("_");
		firstNameLabel.setText("_");
		midNameLabel.setText("_");
		lastNameLabel.setText("_");
		SSNLabel.setText("   -  -    ");
		phoneNumberLabel.setText("(   ) -    ");
		streetLabel.setText("_____" + " _____ __");
		aptLabel.setText("");
		cityStateLabel.setText("________" + ", __" + " _____");
		primaryLabel.setText("________");
		DOBLabel.setText("_");
		genderLabel.setText("_");

		profileInputPanel.removeAll();
		profileInputPanel.add(addNewButton);
		profileInputPanel.repaint();
		profileInputPanel.validate();

	}
}
