import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class lastPatientHistory extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNewLabel, admitDateLabel, lblRoom, lblDob;
	JLabel reasonLabel;

	private JLabel staffNoteLabel;
	private JTextPane txtpnThisIsWhere;
	private JLabel roomLabel;
	
	public lastPatientHistory() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Admitted: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(11, 8, 70, 29);
		formPanel.add(lblNewLabel);
		
		admitDateLabel = new JLabel("01/01/2019");
		admitDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		admitDateLabel.setBounds(90, 8, 184, 29);
		formPanel.add(admitDateLabel);
		
		lblRoom = new JLabel("Room: ");
		lblRoom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRoom.setBounds(288, 8, 56, 29);
		formPanel.add(lblRoom);
		
		lblDob = new JLabel("Reason for visit:");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(11, 92, 121, 29);
		formPanel.add(lblDob);
		
		reasonLabel = new JLabel("Stroke");
		reasonLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		reasonLabel.setBounds(137, 92, 179, 29);
		formPanel.add(reasonLabel);
		
		staffNoteLabel = new JLabel("Staff Note: ");
		staffNoteLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		staffNoteLabel.setBounds(11, 124, 171, 29);
		formPanel.add(staffNoteLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(11, 150, 744, 98);
		formPanel.add(scrollPane);
		
		txtpnThisIsWhere = new JTextPane();
		txtpnThisIsWhere.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtpnThisIsWhere.setText("this is where doctor note goes, he can write anything he want.\r\nalso nurse can add note to this field as needed.\r\n\r\n---------------------------------------------------------");
		scrollPane.setViewportView(txtpnThisIsWhere);
		
		JLabel lblApt = new JLabel("Blood Pressure");
		lblApt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblApt.setBounds(518, 8, 121, 29);
		formPanel.add(lblApt);
		
		roomLabel = new JLabel("ICU 112");
		roomLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		roomLabel.setBounds(349, 8, 133, 29);
		formPanel.add(roomLabel);
		
		JLabel BPLabel = new JLabel("88 / 120");
		BPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BPLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		BPLabel.setBounds(518, 39, 108, 29);
		formPanel.add(BPLabel);
		
		JLabel lblHeartRate = new JLabel("Heart Rate");
		lblHeartRate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblHeartRate.setBounds(661, 8, 94, 29);
		formPanel.add(lblHeartRate);
		
		JLabel HRLabel = new JLabel("77");
		HRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HRLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		HRLabel.setBounds(661, 35, 75, 29);
		formPanel.add(HRLabel);
		
		JLabel lblTypeOfTreatment = new JLabel("Type of Treatment: ");
		lblTypeOfTreatment.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTypeOfTreatment.setBounds(428, 92, 141, 29);
		formPanel.add(lblTypeOfTreatment);
		
		JLabel treatmentLabel = new JLabel("Surgery");
		treatmentLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		treatmentLabel.setBounds(576, 92, 179, 29);
		formPanel.add(treatmentLabel);
		

	}
	
	

}
