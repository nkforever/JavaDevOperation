import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MVCcontroller {

	private MainPanel mPanel;
	private NewPatientForm npForm; 
	private PatientAssignmentForm patientAssignmentForm;
	private EditPatientForm editPatient;
	private EditEmployeeForm editEmployee;

	MVCcontroller(MainPanel mp, NewPatientForm npf, PatientAssignmentForm paf, EditPatientForm epf,
			EditEmployeeForm eef) {

		editEmployee = eef;
		editPatient = epf;
		patientAssignmentForm = paf;
		mPanel  = mp;
		npForm = npf;
		mPanel.addSaveButtonListener(new addSaveButtonListener());
		mPanel.addRecordButtonListener(new addRecordButtonListener());
		mPanel.addUpdatePatientButtonListener(new addUpdatePatientButtonListener());
		mPanel.addUpdateEmployeeButtonListener(new addUpdateEmployeeButtonListener());
		
	}
	
	class addSaveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (npForm.generatePatientID())
				mPanel.loadPatient(); //
		}		
	}
	
	class addRecordButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				mPanel.loadLastPatientHistory();
		}		
	}
	
	class addUpdatePatientButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//				mPanel.do something();
				System.out.println("update patient info button is pressed");
			editPatient.updatePatientInfo();
			mPanel.loadPatient();
			mPanel.updatePatientInfoIsPressed();
		}
	}
	
	class addUpdateEmployeeButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			editEmployee.updateEmployeeInfo();
			mPanel.loadEmployee();
			mPanel.updateEmployeeInfoIsPressed();
		}
	}
	
}
