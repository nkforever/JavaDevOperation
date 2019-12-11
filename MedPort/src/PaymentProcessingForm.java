import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentProcessingForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbl1, invoiceDateLabel, lbl2, lbl5, invoiceLabel;
	private JLabel lblAmount, lblCheckAmount, lblCashAmount, lblCheckNumber, lblRoutineNumber, lblAccountNumber;
	
	DBcontrol dbc = new DBcontrol();
	private JButton processPaymentButton;
	private JTextField ccNumberField, checkNumberField, totalAmountField, ccAmountField, checkAmountField,
			cashAmountField, checkRoutineField, checkAccountField;
	private JCheckBox checkRadioButton, ccRadioButton, cashRadioButton;

	private String invoiceNum = "", checkRoutine, checkAccount, checkNum, ccNum;
	private double ccAmount = 0.00, checkAmount = 0.00, cashAmount = 0.00, totalAmount = 0.00;
	private JLabel errorMessageLabel;

	public PaymentProcessingForm() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				errorMessageLabel.setVisible(false);
			}
		});
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
		
		lbl5 = new JLabel("Select Form of Payment");
		lbl5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl5.setBounds(11, 49, 171, 29);
		formPanel.add(lbl5);
		
		invoiceLabel = new JLabel("10002111");
		invoiceLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		invoiceLabel.setBounds(362, 8, 133, 29);
		formPanel.add(invoiceLabel);

		processPaymentButton = new JButton("Process Payment");
		processPaymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processPayment();
			}
		});
		processPaymentButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		processPaymentButton.setBounds(567, 332, 171, 37);
		formPanel.add(processPaymentButton);

		ccRadioButton = new JCheckBox("Credit / Debit Card");
		ccRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ccRadioButton.isSelected()) {
					ccNumberField.setVisible(true);
					ccAmountField.setVisible(true);
					ccNumberField.setText("");
					ccAmountField.setText("0.00");
				}
				{
					ccAmountField.setVisible(false);
					ccNumberField.setVisible(false);
					totalAmount -= ccAmount;
					sumTotal();
				}
			}
		});
		ccRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ccRadioButton.setBounds(11, 79, 223, 37);
		formPanel.add(ccRadioButton);

		checkRadioButton = new JCheckBox("Check");
		checkRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (checkRadioButton.isSelected()) {
					checkNumberField.setVisible(true);
					checkAmountField.setVisible(true);
					checkNumberField.setText("");
					checkAmountField.setText("0.00");
				}
				{
					checkAmountField.setVisible(false);
					checkNumberField.setVisible(false);
					totalAmount -= checkAmount;
					sumTotal();
				}
			}
		});
		checkRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkRadioButton.setBounds(11, 161, 223, 37);
		formPanel.add(checkRadioButton);

		cashRadioButton = new JCheckBox("Cash");
		cashRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cashRadioButton.isSelected()) {
					cashAmountField.setVisible(true);
					cashAmountField.setText("0.00");
				}
				{
					cashAmountField.setVisible(false);
					totalAmount -= cashAmount;
					sumTotal();
				}
			}
		});
		cashRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cashRadioButton.setBounds(11, 291, 223, 37);
		formPanel.add(cashRadioButton);

		ccNumberField = new JTextField();
		ccNumberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()))
					e.consume();
				if (ccNumberField.getText().length() >= 16) // limit textfield to 5 characters
					e.consume();
			}
		});
		ccNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ccNumberField.setBounds(258, 79, 236, 35);
		formPanel.add(ccNumberField);
		ccNumberField.setColumns(10);
		checkNumberField = new JTextField();
		checkNumberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()))
					e.consume();
				if (ccNumberField.getText().length() >= 10) // limit textfield to 5 characters
					e.consume();
			}
		});
		checkNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkNumberField.setColumns(10);
		checkNumberField.setBounds(262, 161, 101, 35);
		formPanel.add(checkNumberField);

		totalAmountField = new JFormattedTextField("###,###,###,###.00");
		totalAmountField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()) || !ccNumberField.getText().equals("."))
					e.consume();
			}
		});
		totalAmountField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		totalAmountField.setText("0.00");
		totalAmountField.setColumns(10);
		totalAmountField.setBounds(567, 293, 171, 35);
		formPanel.add(totalAmountField);

		JLabel lbl3 = new JLabel("Total Amount");
		lbl3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl3.setBounds(567, 261, 171, 29);
		formPanel.add(lbl3);

		ccAmountField = new JFormattedTextField("###,###,###,###.00");
		ccAmountField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()) || !ccNumberField.getText().equals("."))
					e.consume();
			}
		});
		ccAmountField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ccAmountField.setText("0.00");
		ccAmountField.setColumns(10);
		ccAmountField.setBounds(527, 81, 154, 35);
		formPanel.add(ccAmountField);

		checkAmountField = new JFormattedTextField("###,###,###,###.00");
		checkAmountField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()) || !ccNumberField.getText().equals("."))
					e.consume();
			}
		});
		checkAmountField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkAmountField.setText("0.00");
		checkAmountField.setColumns(10);
		checkAmountField.setBounds(527, 159, 154, 35);
		formPanel.add(checkAmountField);

		cashAmountField = new JFormattedTextField("###,###,###,###.00");
		cashAmountField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()) || !ccNumberField.getText().equals("."))
					e.consume();
			}
		});
		cashAmountField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cashAmountField.setText("0.00");
		cashAmountField.setColumns(10);
		cashAmountField.setBounds(254, 292, 154, 35);
		formPanel.add(cashAmountField);

		checkRoutineField = new JTextField();
		checkRoutineField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()))
					e.consume();
				if (ccNumberField.getText().length() >= 10) // limit textfield to 5 characters
					e.consume();
			}
		});
		checkRoutineField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkRoutineField.setColumns(10);
		checkRoutineField.setBounds(21, 220, 176, 35);
		formPanel.add(checkRoutineField);

		checkAccountField = new JTextField();
		checkAccountField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!isDigit(e.getKeyChar()))
					e.consume();
				if (ccNumberField.getText().length() >= 10) // limit textfield to 5 characters
					e.consume();
			}
		});
		checkAccountField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkAccountField.setColumns(10);
		checkAccountField.setBounds(219, 220, 176, 35);
		formPanel.add(checkAccountField);

		lblAmount = new JLabel("Cc or Db Amount:");
		lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAmount.setBounds(527, 59, 171, 29);
		formPanel.add(lblAmount);

		lblCheckAmount = new JLabel("Check Amount:");
		lblCheckAmount.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCheckAmount.setBounds(527, 129, 171, 29);
		formPanel.add(lblCheckAmount);

		lblCashAmount = new JLabel("Cash Amount:");
		lblCashAmount.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCashAmount.setBounds(244, 271, 171, 29);
		formPanel.add(lblCashAmount);

		lblCheckNumber = new JLabel("Check Number:");
		lblCheckNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCheckNumber.setBounds(252, 140, 171, 29);
		formPanel.add(lblCheckNumber);

		lblRoutineNumber = new JLabel("Routine Number:");
		lblRoutineNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRoutineNumber.setBounds(11, 194, 171, 29);
		formPanel.add(lblRoutineNumber);

		lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAccountNumber.setBounds(209, 194, 171, 29);
		formPanel.add(lblAccountNumber);

		errorMessageLabel = new JLabel("Fail to process payment!");
		errorMessageLabel.setForeground(Color.RED);
		errorMessageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		errorMessageLabel.setBounds(249, 353, 311, 29);
		formPanel.add(errorMessageLabel);

	}
	
	public JButton getProcessPaymentButton() {
		return processPaymentButton;
	}

	void loadLastHistory() {

	}

	private void sumTotal() {
		totalAmountField.setText(Double.toString(totalAmount));
	}

	public void processPayment() {
		getFieldValue();

		if (!(totalAmount > 0)) {

		} else {
			if (dbc.paymentPosting(PatientProfile.getPatientID(), invoiceNum, cashAmount)) {
				dbc.addPaymentHistory(invoiceNum, ccNum, ccAmount, checkNum, checkAmount, checkRoutine, checkAccount, cashAmount);
				resetForm();
			}
			else
				errorMessageLabel.setVisible(true);
		}
	}

	private void getFieldValue() {
		if(ccNumberField.getText() != null) ccNum = ccNumberField.getText();
		if(checkNumberField.getText() != null) checkNum = checkNumberField.getText();
		if(checkRoutineField.getText()!= null) checkRoutine =  checkRoutineField.getText();
		if(checkAccountField.getText() != null) checkAccount = checkAccountField.getText();
		
		if (!totalAmountField.getText().equals("0.00"))
			totalAmount = Double.parseDouble(totalAmountField.getText());
		if (ccAmountField.getText().equals("0.00"))
			ccAmount = Double.parseDouble(ccAmountField.getText());
		if (checkAmountField.getText().equals("0.00"))
			checkAmount = Double.parseDouble(checkAmountField.getText());
		if (cashAmountField.getText().equals("0.00"))
			cashAmount = Double.parseDouble(cashAmountField.getText());

	}

	private void resetForm() {
		ccNumberField.setText("");
		checkNumberField.setText("");
		totalAmountField.setText("0.00");
		ccAmountField.setText("0.00");
		checkAmountField.setText("0.00");
		cashAmountField.setText("0.00");
		checkRoutineField.setText("");
		checkAccountField.setText("");
		checkRadioButton.setSelected(false);
		ccRadioButton.setSelected(false);
		cashRadioButton.setSelected(false);

		invoiceNum = "";
		ccAmount = 0.00;
		checkAmount = 0.00;
		cashAmount = 0.00;
		totalAmount = 0.00;

		errorMessageLabel.setVisible(false);
	}

	boolean isDigit(char input) {
		if (input >= 48 && input <= 57)
			return true;

		return false;
	}
}
