import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddTreatmentForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNewLabel, lblDob;
	
	DBcontrol dbc = new DBcontrol();
	private JTextField idField;
	private JTextField descriptionField;
	private JFormattedTextField costField;
	private NumberFormat num = new DecimalFormat("#.00");

	public AddTreatmentForm() {
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
		
		lblDob = new JLabel("Current Treatment List");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(37, 172, 250, 29);
		formPanel.add(lblDob);
		
		JComboBox treatmentComboBox = new JComboBox();
		treatmentComboBox.setBounds(37, 201, 398, 35);
		formPanel.add(treatmentComboBox);
		
		idField = new JTextField();
		idField.setBounds(37, 76, 113, 35);
		formPanel.add(idField);
		idField.setColumns(10);
		
		JButton addNewTreatmentButton = new JButton("Add New Treatment");
		addNewTreatmentButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addNewTreatmentButton.setBounds(487, 82, 194, 29);
		formPanel.add(addNewTreatmentButton);
		
		JButton removeTreatmentButton = new JButton("Remove Treatment");
		removeTreatmentButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		removeTreatmentButton.setBounds(486, 206, 194, 32);
		formPanel.add(removeTreatmentButton);

		descriptionField = new JTextField();
		descriptionField.setColumns(10);
		descriptionField.setBounds(159, 76, 181, 35);
		formPanel.add(descriptionField);

		JLabel lblNewLabel_1 = new JLabel("Treatment ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(37, 46, 113, 29);
		formPanel.add(lblNewLabel_1);

		JLabel lblLastName = new JLabel("Description");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblLastName.setBounds(159, 46, 181, 29);
		formPanel.add(lblLastName);

		costField = new JFormattedTextField(num);
		costField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!isDigit(e.getKeyChar())) e.consume();
			}  
		});
		costField.setColumns(10);
		costField.setBounds(362, 76, 115, 35);
		formPanel.add(costField);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblCost.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCost.setBounds(362, 46, 113, 29);
		formPanel.add(lblCost);

	}
	
	public void loadDoctorList() {
		ArrayList<String> al = new ArrayList<>();
		al = dbc.getTreatmentList();

	}

	public void addNewDoctor(String id, String firstName, String lastName) {
		dbc.addDoctorToList(id, firstName, lastName, 1);
	}

	public void clearForm() {
		idField.setText("");
		descriptionField.setText("");
		costField.setText("");
	}

	boolean isDigit(char input) {
		if ((input >= 48 && input <= 57) || (input == '.'))
			return true;

		return false;
	}
}
