import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
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
	private JTextField firstNameField, lastNameField;
	private JButton RemoveDoctorButton, addNewDoctorButton;
	private JComboBox<String> comboBox;

	ArrayList<String> al = new ArrayList<>();
	private JLabel addMessageLabel;
	private JLabel removeMessageLabel;
	private JLabel lblEmployeeId;
	private JTextField idField;

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
		
		al = dbc.getDoctorList();
		comboBox = new JComboBox(al.toArray());
		comboBox.setBounds(37, 201, 398, 35);
		formPanel.add(comboBox);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(208, 80, 179, 35);
		formPanel.add(firstNameField);
		firstNameField.setColumns(10);
		
		addNewDoctorButton = new JButton("Add New Doctor");
		addNewDoctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewDoctor(idField.getText(), firstNameField.getText(), lastNameField.getText());
			}
		});
		addNewDoctorButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addNewDoctorButton.setBounds(457, 118, 223, 29);
		formPanel.add(addNewDoctorButton);
		
		RemoveDoctorButton = new JButton("Remove Doctor");
		RemoveDoctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selection = comboBox.getSelectedItem().toString();
				StringTokenizer st = new StringTokenizer(selection);
				st.nextToken();
				String fn = st.nextToken();
				String ln = st.nextToken();

				removeDoctorFromList(fn, ln);
			}
		});
		RemoveDoctorButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		RemoveDoctorButton.setBounds(457, 206, 223, 32);
		formPanel.add(RemoveDoctorButton);

		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(413, 80, 181, 35);
		formPanel.add(lastNameField);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(208, 50, 179, 29);
		formPanel.add(lblNewLabel_1);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblLastName.setBounds(413, 46, 181, 29);
		formPanel.add(lblLastName);

		addMessageLabel = new JLabel("");
		addMessageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addMessageLabel.setBounds(36, 118, 383, 29);
		formPanel.add(addMessageLabel);

		removeMessageLabel = new JLabel("");
		removeMessageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		removeMessageLabel.setBounds(37, 235, 383, 29);
		formPanel.add(removeMessageLabel);

		lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmployeeId.setBounds(22, 46, 179, 29);
		formPanel.add(lblEmployeeId);

		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(22, 80, 179, 35);
		formPanel.add(idField);

	}
	
	void loadDoctorList() {
		al = dbc.getDoctorList();
		comboBox.setModel(new DefaultComboBoxModel(al.toArray()));

	}

	void addNewDoctor(String id, String firstName, String lastName) {
		if (fillAllField() && dbc.addDoctorToList(id, firstName, lastName, 1)) {
			addMessageLabel.setText("Successfully added.");
			loadDoctorList();
			firstNameField.setText("");
			lastNameField.setText("");
		} else {
			addMessageLabel.setText("Fail to add new doctor to list.");
		}
	}

	void removeDoctorFromList(String fn, String ln) {
		if (dbc.removeDoctorFromList(fn, ln)) {
			removeMessageLabel.setText("Successfully removed.");
			loadDoctorList();
		} else {
			removeMessageLabel.setText("Fail to remove dctor from list.");
		}
	}

	boolean fillAllField() {
		if (idField.getText() != null && firstNameField.getText() != null && lastNameField.getText() != null) {
			return true;
		} else
			return false;
	}
}
