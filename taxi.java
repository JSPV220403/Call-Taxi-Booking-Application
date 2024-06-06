package TaxiBookingApplication;
import java.util.ArrayList;

public class taxi {
	static int number=1;
	public int id;
	public int customerId;
	public char standing;
	public int freetime;
	
	public ArrayList<String> tripdetails;
	public int totalearnings;
	
	
	taxi(){
		id= number++;
		standing= 'A';
		freetime= 6; // this 6 says like this car will start working from 6AM..
		totalearnings=0;
		tripdetails= new ArrayList<>();
	}
	
	public void SetDetails(int id, int newfreetime, int totalearnings, String detail ) {
		this.customerId= id;
		this.freetime= newfreetime;
		this.totalearnings= totalearnings;
		this.tripdetails.add(detail);
	}
}
