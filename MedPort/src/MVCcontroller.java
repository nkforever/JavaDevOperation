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
		mPanel.addUpdateButtonListener(new addUpdatePatientButtonListener());
		mPanel.addUpdateButtonListener(new addUpdateEmployeeButtonListener());
		
	}
	
	class addSaveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (true) {
				if(npForm.generatePatientID()) 	mPanel.loadPatient();

			}
		}		
	}
	
	class addRecordButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(true) {
				mPanel.loadLastPatientHistory();
			}
		}		
	}
	
	class addUpdatePatientButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(true) {
				mPanel.do something();
			}
		}
	}
	
	class addUpdateEmployeeButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(true) {
				mPanel.do something();
			}
		}
	}
	
}
