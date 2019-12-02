import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ViewBillForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbl1, invoiceDateLabel, lbl2, lbl4;
	JLabel totalAmountLabel;

	private JLabel lbl5, invoiceLabel;
	private JTextPane detailChargeField;
	
	DBcontrol dbc = new DBcontrol();
	private JLabel lbl3;
	private JLabel previousBalanceLabel;
	private JLabel lblTreatmeantDescription;
	private JLabel lblTotalCharge;
	private JLabel lblPaymentCharge;
	private JLabel lblBalance;
	private JLabel lblResponsibleOf;

	public ViewBillForm() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		lbl1 = new JLabel("Invoice Date:");
		lbl1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl1.setBounds(11, 8, 101, 29);
		formPanel.add(lbl1);
		
		invoiceDateLabel = new JLabel("12/01/2019");
		invoiceDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		invoiceDateLabel.setBounds(109, 8, 86, 29);
		formPanel.add(invoiceDateLabel);
		
		lbl2 = new JLabel("Invoice# ");
		lbl2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl2.setBounds(288, 8, 69, 29);
		formPanel.add(lbl2);
		
		lbl4 = new JLabel("Total charge: $ ");
		lbl4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl4.setBounds(421, 292, 105, 29);
		formPanel.add(lbl4);
		
		totalAmountLabel = new JLabel("1,000,000.00");
		totalAmountLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		totalAmountLabel.setBounds(525, 292, 197, 29);
		formPanel.add(totalAmountLabel);
		
		lbl5 = new JLabel("Detail of charge:");
		lbl5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl5.setBounds(11, 61, 171, 29);
		formPanel.add(lbl5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(11, 107, 711, 141);
		formPanel.add(scrollPane);
		
		detailChargeField = new JTextPane();
		detailChargeField.setText(
				"1002003 \tSurgery: Brain \t$ 1,000,000.00 \t$ 0.00\t\t$ 1,000,000.00 \tPatient\r\n9882210\tGeneral: consultation\t$ 80.00\t\t$ 80.00\t\t$ 0.00\t\tPatient");
		detailChargeField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(detailChargeField);
		
		invoiceLabel = new JLabel("10002111");
		invoiceLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		invoiceLabel.setBounds(362, 8, 133, 29);
		formPanel.add(invoiceLabel);
		
		lbl3 = new JLabel("Previous Balance: $ ");
		lbl3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl3.setBounds(11, 292, 148, 29);
		formPanel.add(lbl3);
		
		previousBalanceLabel = new JLabel("1,000,000.00");
		previousBalanceLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		previousBalanceLabel.setBounds(146, 292, 197, 29);
		formPanel.add(previousBalanceLabel);
		
		lblTreatmeantDescription = new JLabel("Treatmeant Description");
		lblTreatmeantDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblTreatmeantDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTreatmeantDescription.setBounds(11, 83, 208, 29);
		formPanel.add(lblTreatmeantDescription);

		lblTotalCharge = new JLabel("Total Charge");
		lblTotalCharge.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCharge.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTotalCharge.setBounds(222, 83, 115, 29);
		formPanel.add(lblTotalCharge);

		lblPaymentCharge = new JLabel("Payment");
		lblPaymentCharge.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentCharge.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPaymentCharge.setBounds(339, 83, 115, 29);
		formPanel.add(lblPaymentCharge);

		lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBalance.setBounds(514, 77, 77, 29);
		formPanel.add(lblBalance);

		lblResponsibleOf = new JLabel("Responsible of");
		lblResponsibleOf.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblResponsibleOf.setBounds(645, 77, 77, 29);
		formPanel.add(lblResponsibleOf);

	}
	
	void loadLastHistory() {

	}

}
