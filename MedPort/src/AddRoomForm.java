import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddRoomForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNewLabel, lblDob;
	
	DBcontrol dbc = new DBcontrol();
	private JPanel formPanel;
	private JTextField roomNameField, roomNumberField;
	private JButton addNewRoomButton, removeRoomButton;
	private JComboBox<?> roomListComboBox;

	ArrayList<String> al = new ArrayList<>();
	private JLabel addMessageLabel, removeMessageLabel;

	public AddRoomForm() {
		setLayout(new BorderLayout(0, 0));
		
		formPanel = new JPanel();
		formPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				addMessageLabel.setText("");
				removeMessageLabel.setText("");
			}
		});
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Enter New Room Name and Number");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(37, 11, 382, 29);
		formPanel.add(lblNewLabel);
		
		lblDob = new JLabel("Current Room List");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(37, 172, 250, 29);
		formPanel.add(lblDob);
		
		al = dbc.getRoomList();
		roomListComboBox = new JComboBox<Object>(al.toArray());
		roomListComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		roomListComboBox.setBounds(37, 201, 398, 35);
		formPanel.add(roomListComboBox);
		
		roomNameField = new JTextField();
		roomNameField.setBounds(37, 76, 179, 35);
		formPanel.add(roomNameField);
		roomNameField.setColumns(10);
		
		addNewRoomButton = new JButton("Add New Room");
		addNewRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRoomToList(roomNameField.getText(), Integer.parseInt(roomNumberField.getText()));
			}
		});
		addNewRoomButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addNewRoomButton.setBounds(458, 82, 223, 29);
		formPanel.add(addNewRoomButton);
		
		removeRoomButton = new JButton("Remove Room");
		removeRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selection = roomListComboBox.getSelectedItem().toString();
				StringTokenizer st = new StringTokenizer(selection);
				String name = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				removeRoomFromList(name, num);
			}
		});
		removeRoomButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		removeRoomButton.setBounds(457, 206, 223, 32);
		formPanel.add(removeRoomButton);

		roomNumberField = new JTextField();
		roomNumberField.setColumns(10);
		roomNumberField.setBounds(238, 76, 181, 35);
		formPanel.add(roomNumberField);

		JLabel lblNewLabel_1 = new JLabel("Room Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(37, 46, 179, 29);
		formPanel.add(lblNewLabel_1);

		JLabel lblLastName = new JLabel("Room Number");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblLastName.setBounds(238, 42, 181, 29);
		formPanel.add(lblLastName);

		addMessageLabel = new JLabel("");
		addMessageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addMessageLabel.setBounds(37, 119, 382, 29);
		formPanel.add(addMessageLabel);

		removeMessageLabel = new JLabel("");
		removeMessageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		removeMessageLabel.setBounds(37, 247, 398, 29);
		formPanel.add(removeMessageLabel);

	}
	
	void loadRoomList() {
		al = dbc.getRoomList();
		roomListComboBox.setModel(new DefaultComboBoxModel(al.toArray()));

	}

	JButton getAddButton() {
		return addNewRoomButton;
	}

	JButton getRemoveButton() {
		return removeRoomButton;
	}

	void addRoomToList(String name, int number) {
		if (dbc.addRoomToList(name, number)) {
			loadRoomList();
			addMessageLabel.setText("Successfully add to list");
		} else {
			addMessageLabel.setText("Unable to add to list");
		}
	}

	void removeRoomFromList(String name, int num) {
		if (dbc.removeRoomFromList(name, num)) {
			loadRoomList();
			removeMessageLabel.setText("Successfully remove from list");
		} else {
			removeMessageLabel.setText("Unable to remove to list");
		}
	}
}
