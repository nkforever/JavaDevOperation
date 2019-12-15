import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class LastPatientHistory extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNewLabel, checkinDateLabel, lblRoom, lblDob;
	JLabel reasonLabel;

	private JLabel staffNoteLabel, roomLabel;
	private JTextPane txtpnThisIsWhere;
	
	DBcontrol dbc = new DBcontrol();
	private JLabel lblCheckoutDate;
	private JLabel checkoutDateLabel;

	public LastPatientHistory() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Check-in Date:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(11, 8, 121, 29);
		formPanel.add(lblNewLabel);
		
		checkinDateLabel = new JLabel("01/01/2019");
		checkinDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkinDateLabel.setBounds(119, 8, 94, 29);
		formPanel.add(checkinDateLabel);
		
		lblRoom = new JLabel("Room: ");
		lblRoom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRoom.setBounds(288, 8, 56, 29);
		formPanel.add(lblRoom);
		
		lblDob = new JLabel("Reason for visit:");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(11, 61, 121, 29);
		formPanel.add(lblDob);
		
		reasonLabel = new JLabel("Patient has severe headache");
		reasonLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		reasonLabel.setBounds(67, 81, 323, 29);
		formPanel.add(reasonLabel);
		
		staffNoteLabel = new JLabel("Staff Note: ");
		staffNoteLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		staffNoteLabel.setBounds(11, 124, 171, 29);
		formPanel.add(staffNoteLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(11, 150, 700, 98);
		formPanel.add(scrollPane);
		
		txtpnThisIsWhere = new JTextPane();
		txtpnThisIsWhere.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtpnThisIsWhere.setText(
				"Patient complain about having headached everyday and after admitting to get Lab: CT Scan complete, the neuron specialist Dr. Newman concluded that appeared to be tuama in the brain and that require brain surgery. \r\n\r\n---------------------------------------------------------");
		scrollPane.setViewportView(txtpnThisIsWhere);
		
		JLabel lblApt = new JLabel("Blood Pressure");
		lblApt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblApt.setBounds(490, 8, 121, 29);
		formPanel.add(lblApt);
		
		roomLabel = new JLabel("ICU 112");
		roomLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		roomLabel.setBounds(349, 8, 133, 29);
		formPanel.add(roomLabel);
		
		JLabel BPLabel = new JLabel("88 / 120");
		BPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BPLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		BPLabel.setBounds(490, 39, 108, 29);
		formPanel.add(BPLabel);
		
		JLabel lblHeartRate = new JLabel("Heart Rate");
		lblHeartRate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblHeartRate.setBounds(633, 12, 94, 29);
		formPanel.add(lblHeartRate);
		
		JLabel HRLabel = new JLabel("77");
		HRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HRLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		HRLabel.setBounds(633, 39, 75, 29);
		formPanel.add(HRLabel);
		
		JLabel lblTypeOfTreatment = new JLabel("Type of Treatment: ");
		lblTypeOfTreatment.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTypeOfTreatment.setBounds(389, 61, 278, 29);
		formPanel.add(lblTypeOfTreatment);
		
		JLabel treatmentLabel = new JLabel("Lab: CT Scan");
		treatmentLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		treatmentLabel.setBounds(431, 81, 280, 29);
		formPanel.add(treatmentLabel);
		
		lblCheckoutDate = new JLabel("Checkout Date:");
		lblCheckoutDate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCheckoutDate.setBounds(490, 260, 118, 29);
		formPanel.add(lblCheckoutDate);

		checkoutDateLabel = new JLabel("01/01/2019");
		checkoutDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkoutDateLabel.setBounds(617, 260, 94, 29);
		formPanel.add(checkoutDateLabel);

	}
	
	void loadLastHistory() {

	}

}
