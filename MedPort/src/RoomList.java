import java.util.ArrayList;

public class RoomList {
	
	static ArrayList<String> generalPractices = new ArrayList<>();
	static ArrayList<String> surgicals = new ArrayList<>();
	static ArrayList<String> labs = new ArrayList<>();


	RoomList(){
		
	}
	void addToGeneralPractices(String doctorName) {
		generalPractices.add(doctorName);
	}
	void addToSurgicals(String doctorName) {
		surgicals.add(doctorName);
	}
	void addToGeneralLabs(String doctorName) {
		labs.add(doctorName);
	}
}
