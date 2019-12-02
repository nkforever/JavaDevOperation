import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class PatientAssignmentForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> assignDoctor, assignRoom;
	private JTextPane noteField;
	JComboBox<?> typeTreatment;
	private JFormattedTextField upperBPField, lowerBPField, heartRateField;
	JTextField reasonVisitField;
	
	private JLabel lblNewLabel, lblMidName, lblLastName, lblDob, lblSsn, lblStreetAddr, lblZipcode, label;
	
	private JScrollPane scrollPane;
	DefaultListModel<String> dm;
	private ArrayList<String> al, rl, tl;
	
	private JFormattedTextField checkinDateField, checkoutDateField;
	private JButton addRecordButton, resetFormButton;
	

	private DBcontrol dbc = new DBcontrol();
	
	private DateFormat  dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
	private NumberFormat num = new DecimalFormat("###"); 
	private NumberFormatter UB  = new NumberFormatter(num); 
	private NumberFormatter LB  = new NumberFormatter(num);
	private NumberFormatter HR  = new NumberFormatter(num); 
	
	private long date = System.currentTimeMillis();

	private String invoice = "", doctor = "", room = "", visitReason = "", treatmentType = "", note = "";
	private String checkInDate, checkOutDate;
	private int upperBP = 0, lowerBP = 0, HeartRate = 0, active = 0;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PatientAssignmentForm() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		al = new ArrayList<>();
		al = dbc.getDoctorList(); //calling server to get arraylist
		assignDoctor = new JComboBox<Object>(al.toArray());
		assignDoctor.setModel(new DefaultComboBoxModel(
				new String[] { "", "Dr. V. Vinny", "Dr. J. Lynn", "Dr. N. Nyi", "Dr. D. Dale" }));
		assignDoctor.setEditable(true);
		assignDoctor.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		assignDoctor.setBounds(11, 34, 171, 29);
		formPanel.add(assignDoctor);
		
		rl = new ArrayList<>();
		rl = dbc.getRoomList();
		assignRoom = new JComboBox<Object>(rl.toArray());
		assignRoom.setModel(new DefaultComboBoxModel(new String[] { "", "ICU 101", "ICU 102", "ICU 103", "ER 201",
				"ER 202", "ER 203", "HS 301", "HS 302", "HS 303" }));
		assignRoom.setEditable(true);
		assignRoom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		assignRoom.setBounds(226, 34, 166, 29);
		formPanel.add(assignRoom);
		
		upperBPField = new JFormattedTextField(UB);
		upperBPField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		upperBPField.setBounds(478, 34, 41, 29);
		formPanel.add(upperBPField);
		upperBPField.setColumns(10);
		
		reasonVisitField = new JTextField();
		reasonVisitField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		reasonVisitField.setBounds(11, 99, 319, 29);
		formPanel.add(reasonVisitField);
		reasonVisitField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(11, 166, 501, 98);
		formPanel.add(scrollPane);
		
		noteField = new JTextPane();
		scrollPane.setViewportView(noteField);
		noteField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		try {
			checkoutDateField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		checkoutDateField.setText(" ");
		checkoutDateField.setEditable(true);
		checkoutDateField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		checkoutDateField.setBounds(643, 206, 100, 29);
		formPanel.add(checkoutDateField);
		
		tl = new ArrayList<>();
		tl = dbc.getTreatmentList();
		typeTreatment = new JComboBox<Object>(tl.toArray());
		typeTreatment.setModel(new DefaultComboBoxModel(new String[] {"", "Surgrey: Brain", "Surgery: Spine", "Surgery: Knee", "Lab: Blood Basic Test", "Lab: Urine Test", "Lab: Stool Test", "Lab: Xray", "Lab: Ultra Sound", "Lab: CT Scan", "General: IV", "General: Pain Relieve med", "General: First Aid", "General: Anti-bliotic med", "General: Asprin", "General: Wound Dressing", "General: Wax Removal", "General: Consultation", "General: Diagnosis", "Lab: Allergry Test", "Lab: Blood Micro Test", "Dummy Testing"}));
		typeTreatment.setEditable(true);
		typeTreatment.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		typeTreatment.setBounds(414, 99, 329, 29);
		formPanel.add(typeTreatment);
		
		addRecordButton = new JButton("Add Record");
		addRecordButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addRecordButton.setBounds(594, 290, 149, 29);
		formPanel.add(addRecordButton);
		
		lblNewLabel = new JLabel("Assign Doctor");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(11, 8, 171, 29);
		formPanel.add(lblNewLabel);
		
		lblMidName = new JLabel("Assign Room");
		lblMidName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMidName.setBounds(226, 8, 121, 29);
		formPanel.add(lblMidName);
		
		lblLastName = new JLabel("Blood Pressure             Heart Rate");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLastName.setBounds(479, 8, 252, 29);
		formPanel.add(lblLastName);
		
		lblDob = new JLabel("Reason for visit:");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(11, 73, 121, 29);
		formPanel.add(lblDob);
		
		lblSsn = new JLabel("Type of treatment:");
		lblSsn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSsn.setBounds(414, 73, 179, 29);
		formPanel.add(lblSsn);
		
		lblStreetAddr = new JLabel("Enter notes:");
		lblStreetAddr.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStreetAddr.setBounds(11, 136, 171, 29);
		formPanel.add(lblStreetAddr);
		
		lblZipcode = new JLabel("Check-out Date");
		lblZipcode.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblZipcode.setBounds(534, 206, 111, 29);
		formPanel.add(lblZipcode);
		
		JLabel lblApt = new JLabel("Check-in Date");
		lblApt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblApt.setBounds(534, 166, 111, 29);
		formPanel.add(lblApt);
		
		checkinDateField = new JFormattedTextField(dateFormat.format(date));
		checkinDateField.setEditable(true);
		checkinDateField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		checkinDateField.setBounds(643, 166, 100, 29);
		formPanel.add(checkinDateField);
		
		
		label = new JLabel("/");
		label.setFont(new Font("Times New Roman", Font.BOLD, 34));
		label.setBounds(519, 24, 9, 51);
		formPanel.add(label);
		
		lowerBPField = new JFormattedTextField(LB);
		lowerBPField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lowerBPField.setColumns(10);
		lowerBPField.setBounds(528, 34, 41, 29);
		formPanel.add(lowerBPField);
		
		heartRateField = new JFormattedTextField(HR);
		heartRateField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		heartRateField.setColumns(10);
		heartRateField.setBounds(632, 34, 81, 29);
		formPanel.add(heartRateField);
		
		resetFormButton = new JButton("Reset Form");
		resetFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		resetFormButton.setForeground(Color.RED);
		resetFormButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		resetFormButton.setBounds(11, 291, 155, 29);
		formPanel.add(resetFormButton);

	}
	
	JButton getAddRecordButton() {
		return addRecordButton;
	}
	
	public boolean addToRecord() {

		doctor = assignDoctor.getSelectedItem().toString();
		room = assignRoom.getSelectedItem().toString();
		if (!upperBPField.getText().equals(""))
			upperBP = Integer.parseInt(upperBPField.getText());
		if (!lowerBPField.getText().equals(""))
			lowerBP = Integer.parseInt(lowerBPField.getText());
		if (!heartRateField.getText().equals(""))
			HeartRate = Integer.parseInt(heartRateField.getText());
		visitReason = reasonVisitField.getText();
		treatmentType = typeTreatment.getSelectedItem().toString();
		note = noteField.getText();
		checkInDate = checkinDateField.getText();
		checkOutDate = checkoutDateField.getText();


		if (active == 0) {
			invoice = generateInvoiceNumber();
			active = 1;
			PatientProfile.setActive(active);
		}
		if (!checkOutDate.equals("  /  /    ")) {
			active = 0;
		}

		if (dbc.addPatientHistory(invoice, doctor, room, upperBP, lowerBP, HeartRate, visitReason, treatmentType, note,
				checkInDate, checkOutDate, active)) {

			typeTreatment.setSelectedIndex(0);
			noteField.setText("");
			checkoutDateField.setText("");
			return true;
		} else {
			return false;
		}
	}

	public void loadAssignmentForm() {
		if (PatientProfile.getActive() == 0) {
			return;
		} else {
		ResultSet rs = dbc.loadPatientAssignment(PatientProfile.getPatientID());

		try {
			while (rs.next()) {
				invoice = rs.getNString("invoice");
				assignDoctor.setSelectedItem(rs.getNString("doctor"));
				assignRoom.setSelectedItem(rs.getNString("room"));
				checkinDateField.setText(rs.getNString("check_in_date"));
				upperBPField.setText(Integer.toString(rs.getInt("upper_bp")));
				lowerBPField.setText(Integer.toString(rs.getInt("lower_bp")));
				heartRateField.setText(Integer.toString(rs.getInt("heart_rate")));
				reasonVisitField.setText(rs.getNString("visit_reason"));
				typeTreatment.setSelectedItem(rs.getNString("treatment_type"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	private String generateInvoiceNumber() {
		String inv = "";
		long time = System.currentTimeMillis();
		inv = Long.toString(time);
		inv = PatientProfile.getSsnArea() + inv.substring(8, inv.length());
		
		while (dbc.checkExistingInvoice(inv)) {
			time = System.currentTimeMillis();
			inv = Long.toString((time));
			inv = PatientProfile.getSsnArea() + inv.substring(8, inv.length());
			
		}
		return inv;
	}

	private void resetForm() {
		if (!formEmpty() && PatientProfile.getActive() == 0) {

				assignDoctor.setSelectedIndex(-1);
				assignRoom.setSelectedIndex(-1);
				upperBPField.setText("");
				lowerBPField.setText("");
				heartRateField.setText("");
				reasonVisitField.setText("");
				typeTreatment.setSelectedIndex(-1);
				noteField.setText("");
				checkinDateField.setText(dateFormat.format(date));
				checkoutDateField.setText("");
		}
		else {
				typeTreatment.setSelectedIndex(-1);
				noteField.setText("");
				checkoutDateField.setText("");
			}

	}

	private boolean formEmpty() {
		if ((assignDoctor.getSelectedIndex() <= 0) && (assignRoom.getSelectedIndex() <= 0)
				&& upperBPField.getText().equals("") && lowerBPField.getText().equals("")
				&& heartRateField.getText().equals("") && reasonVisitField.getText().equals("")
				&& (typeTreatment.getSelectedIndex() <= 0) && noteField.getText().equals("")
			){
			
			return true;
		}
		else
			return false;
	}

}
