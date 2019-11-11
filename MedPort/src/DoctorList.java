import java.util.ArrayList;

public class DoctorList {
	
	static ArrayList<String> doctors = new ArrayList<>();

	DoctorList(){
		
	}
	void addToList(String doctorName) {
		doctors.add(doctorName);
	}
}
