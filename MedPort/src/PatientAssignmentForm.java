import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import javax.swing.text.NumberFormatter;

public class PatientAssignmentForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> doctorAssign, roomAssign;
	private JTextPane noteField;
	JComboBox<?> typeTreatment;
	private JFormattedTextField topBP, bottomPB, heartRate;
	JTextField reasonVisitField;
	
	private JLabel lblNewLabel, lblMidName, lblLastName, lblDob;
	private JLabel lblSsn, lblStreetAddr, lblZipcode;
	
	private JScrollPane scrollPane;
	DefaultListModel<String> dm;
	private ArrayList<String> al, rl, tl;
	
	private JLabel label;
	
	private JFormattedTextField dateAdmitField, dateDismissField;
	private JButton addRecordButton;
	

	private DBcontrol dbc = new DBcontrol();
	
	private DateFormat  dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
	private NumberFormat num = new DecimalFormat("###"); 
	private NumberFormatter UB  = new NumberFormatter(num); 
	private NumberFormatter LB  = new NumberFormatter(num);
	private NumberFormatter HR  = new NumberFormatter(num); 
	
	private long date = System.currentTimeMillis();
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PatientAssignmentForm() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		al = new ArrayList<>();
		al = dbc.getDoctorList(); //calling server to get arraylist
		doctorAssign = new JComboBox<Object>(al.toArray());
		doctorAssign.setModel(new DefaultComboBoxModel(new String[] {"", "Dr. V. Vinny", "Dr. J. Lynn", "Dr. N. Nyi", "Dr. D. Dale"}));
		doctorAssign.setEditable(true);
		doctorAssign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		doctorAssign.setBounds(11, 34, 171, 29);
		formPanel.add(doctorAssign);
		
		rl = new ArrayList<>();
		rl = dbc.getRoomList();
		roomAssign = new JComboBox<Object>(rl.toArray());
		roomAssign.setModel(new DefaultComboBoxModel(new String[] {"", "ICU 101", "ICU 102", "ICU 103", "ER 201", "ER 202", "ER 203", "HS 301", "HS 302", "HS 303"}));
		roomAssign.setEditable(true);
		roomAssign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		roomAssign.setBounds(226, 34, 166, 29);
		formPanel.add(roomAssign);
		
		topBP = new JFormattedTextField(UB);
		topBP.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		topBP.setBounds(478, 34, 41, 29);
		formPanel.add(topBP);
		topBP.setColumns(10);
		
		reasonVisitField = new JTextField();
		reasonVisitField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		reasonVisitField.setBounds(11, 99, 319, 29);
		formPanel.add(reasonVisitField);
		reasonVisitField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(11, 166, 501, 110);
		formPanel.add(scrollPane);
		
		noteField = new JTextPane();
		scrollPane.setViewportView(noteField);
		noteField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		dateDismissField = new JFormattedTextField(dateFormat);
		dateDismissField.setText(" ");
		dateDismissField.setEditable(true);
		dateDismissField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		dateDismissField.setBounds(643, 206, 100, 29);
		formPanel.add(dateDismissField);
		
		tl = new ArrayList<>();
		tl = dbc.getTreatmentList();
		typeTreatment = new JComboBox<Object>(tl.toArray());
		typeTreatment.setModel(new DefaultComboBoxModel(new String[] {"", "Surgrey: Brain", "Surgery: Spine", "Surgery: Knee", "Lab: Blood Basic Test", "Lab: Urine Test", "Lab: Stool Test", "Lab: Xray", "Lab: Ultra Sound", "Lab: CT Scan", "General: IV", "General: Pain Relieve med", "General: First Aid", "General: Anti-bliotic med", "General: Asprin", "General: Wound Dressing", "General: Wax Removal", "General: Consultation", "General: Diagnosis", "Lab: Allergry Test", "Lab: Blood Micro Test", "Dummy Testing"}));
		typeTreatment.setEditable(true);
		typeTreatment.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		typeTreatment.setBounds(414, 99, 329, 29);
		formPanel.add(typeTreatment);
		
		addRecordButton = new JButton("Add Record");
		addRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addRecordButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addRecordButton.setBounds(594, 273, 149, 37);
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
		
		dateAdmitField = new JFormattedTextField(dateFormat.format(date));
		dateAdmitField.setEditable(true);
		dateAdmitField.setFont(new Font("Times New Roman", Font.PLAIN, 18));		
		dateAdmitField.setBounds(643, 166, 100, 29);
		formPanel.add(dateAdmitField);
		
		
		label = new JLabel("/");
		label.setFont(new Font("Times New Roman", Font.BOLD, 34));
		label.setBounds(519, 24, 9, 51);
		formPanel.add(label);
		
		bottomPB = new JFormattedTextField(LB);
		bottomPB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		bottomPB.setColumns(10);
		bottomPB.setBounds(528, 34, 41, 29);
		formPanel.add(bottomPB);
		
		heartRate = new JFormattedTextField(HR);
		heartRate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		heartRate.setColumns(10);
		heartRate.setBounds(632, 34, 81, 29);
		formPanel.add(heartRate);
		

	}
	
	JButton getAddRecordButton() {
		return addRecordButton;
	}
	
	void addRecord() {
		
	}
	
	
	
}
