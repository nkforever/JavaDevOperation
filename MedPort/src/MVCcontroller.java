import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MVCcontroller {

	private MainPanel mPanel;
	private NewPatientForm npForm; 
	MVCcontroller(MainPanel mp, NewPatientForm npf,  patientAssignmentForm paf){
		mPanel  = mp;
		npForm = npf;
		mPanel.addSaveButtonListener(new addSaveButtonListener());
		mPanel.addRecordButtonListener(new addRecordButtonListener());
		
	}
	
	class addSaveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(true) {
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
	
	
	
}
