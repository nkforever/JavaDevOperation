import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddDoctorForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNewLabel, lblDob;
	
	DBcontrol dbc = new DBcontrol();
	private JTextField firstNameField;
	private JTextField lastNameField;

	public AddDoctorForm() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Enter New Doctor Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(103, 11, 250, 29);
		formPanel.add(lblNewLabel);
		
		lblDob = new JLabel("Current Doctor List");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(37, 172, 250, 29);
		formPanel.add(lblDob);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(37, 201, 398, 35);
		formPanel.add(comboBox);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(37, 76, 179, 35);
		formPanel.add(firstNameField);
		firstNameField.setColumns(10);
		
		JButton addNewDoctorButton = new JButton("Add New Doctor");
		addNewDoctorButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addNewDoctorButton.setBounds(458, 82, 223, 29);
		formPanel.add(addNewDoctorButton);
		
		JButton RemoveDoctorButton = new JButton("Remove Doctor");
		RemoveDoctorButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		RemoveDoctorButton.setBounds(457, 206, 223, 32);
		formPanel.add(RemoveDoctorButton);

		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(238, 76, 181, 35);
		formPanel.add(lastNameField);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(37, 46, 179, 29);
		formPanel.add(lblNewLabel_1);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblLastName.setBounds(238, 42, 181, 29);
		formPanel.add(lblLastName);

	}
	
	void loadDoctorList() {


	}

	void addNewDoctor(String id, String firstName, String lastName) {
		dbc.addDoctorToList(id, firstName, lastName, 1);
	}
}
