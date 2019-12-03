import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame {

	private JFrame mainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.mainFrame.setVisible(true);
					window.mainFrame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JPanel TopPanel, BottomPanel, LeftPanel, RightPanel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel ErrorMessageLabel;
	private JButton loginButton;


	private JLabel lblNewLabel, serverStatus;

	private DBcontrol dbc = new DBcontrol();

	private JScrollPane scrPane;


	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				connection();
			}
		});
		mainFrame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				connection();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		mainFrame.setResizable(false);
		mainFrame.setTitle("Welcome to MedPort");
		mainFrame.setBounds(100, 100, 694, 441);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TopPanel = new JPanel();
		TopPanel.setPreferredSize(new Dimension(100, 100));
		TopPanel.setMinimumSize(new Dimension(100, 100));
		mainFrame.getContentPane().add(TopPanel, BorderLayout.NORTH);

		LeftPanel = new JPanel();
		LeftPanel.setPreferredSize(new Dimension(100, 100));
		mainFrame.getContentPane().add(LeftPanel, BorderLayout.WEST);

		JPanel CenterPanel = new JPanel();
		mainFrame.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(null);

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblUserName.setBounds(85, 43, 90, 22);
		CenterPanel.add(lblUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassword.setBounds(85, 85, 90, 22);
		CenterPanel.add(lblPassword);

		loginButton = new JButton("Login");
		loginButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					verifyUser();
				}
			}
		});
		loginButton.setHorizontalTextPosition(SwingConstants.CENTER);
		loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		loginButton.setBounds(305, 119, 90, 29);
		CenterPanel.add(loginButton);

		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					verifyUser();
				}
			}

			public void keyTyped(KeyEvent e) {
				if (!isAlphaDigit(e.getKeyChar()))
					e.consume();
			}
		});
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		usernameField.setBounds(190, 35, 205, 31);
		CenterPanel.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					verifyUser();
				}
			}
		});
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(190, 77, 205, 31);
		CenterPanel.add(passwordField);
		passwordField.setColumns(10);

		ErrorMessageLabel = new JLabel("");
		ErrorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorMessageLabel.setForeground(new Color(255, 0, 0));
		ErrorMessageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ErrorMessageLabel.setBounds(-101, 155, 687, 29);
		CenterPanel.add(ErrorMessageLabel);


		loginButton.addActionListener(actions);

		RightPanel = new JPanel();
		RightPanel.setPreferredSize(new Dimension(100, 100));
		mainFrame.getContentPane().add(RightPanel, BorderLayout.EAST);

		BottomPanel = new JPanel();
		BottomPanel.setPreferredSize(new Dimension(100, 100));
		mainFrame.getContentPane().add(BottomPanel, BorderLayout.SOUTH);
		FlowLayout fl_BottomPanel = new FlowLayout(FlowLayout.LEFT, 10, 35);
		BottomPanel.setLayout(fl_BottomPanel);

		lblNewLabel = new JLabel("Server Status: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		BottomPanel.add(lblNewLabel);

		serverStatus = new JLabel("");
		serverStatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		BottomPanel.add(serverStatus);

		connection();
	}

	private boolean connection(){
		if(dbc.checkConnection()) {
			serverStatus.setText("OK");
			serverStatus.setForeground(Color.GREEN);
			serverStatus.setFont(new Font("Dialog", Font.BOLD, 12));
			serverStatus.setBounds(70, 277, 120, 36);
			return true;
		}
		else {
			serverStatus.setText("No Connection");
			serverStatus.setForeground(Color.RED);
			serverStatus.setFont(new Font("Dialog", Font.BOLD, 12));
			serverStatus.setBounds(70, 277, 120, 36);
			return false;
		}
	}
	private ActionListener actions = new ActionListener(){

		public void actionPerformed(ActionEvent e)	 {
			if(e.getSource() == loginButton){
				verifyUser();
			}
		}
	};//end action listener

	@SuppressWarnings("deprecation")
	void verifyUser() {

		if (!(passwordField.getText().isEmpty()) && !(usernameField.getText().isEmpty())) {
			String user = usernameField.getText();
			String pass = passwordField.getText();
			boolean valid = false;

			ErrorMessageLabel.setText("checking credential....");

			valid = dbc.validate(user, pass);
			if (valid) {
				dbc.LoadOwnProfile(user);
				OwnProfile.setUser(user);

				if (OwnProfile.getActive() == 1) {
					OwnProfile.setUser(user);
					ErrorMessageLabel.setText("SUCCESS!");
					mainFrame.getContentPane().removeAll();
					mainFrame.setExtendedState( mainFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
					mainFrame.setResizable(true);

					MainPanel mp = new MainPanel();
					{
						if (OwnProfile.getRole().equalsIgnoreCase("admin"))
							mp.setAdminOptionVisible();
					}
					mp.getNameSearchField().requestFocus();
					scrPane = new JScrollPane(mp);
					mainFrame.getContentPane().add(scrPane);
					mainFrame.setMinimumSize(new Dimension(950, 750));
					mainFrame.validate();

				} else {
					ErrorMessageLabel.setText("Your account is deactived.\nPlease contact your account administrator.");
				}

			} else {
				ErrorMessageLabel.setText("Invalid credential, try again!");
			}
		}

		else {
			ErrorMessageLabel.setText("Enter all credential.");
			usernameField.getSelectionStart();
		}
	}// end verify user

	boolean isAlphaDigit(char input) {
		if ((input >= 48 && input <= 57) || (input >= 65 && input <= 90) || (input >= 97 && input <= 122))
			return true;

		return false;
	}
}
