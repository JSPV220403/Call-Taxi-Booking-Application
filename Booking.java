package TaxiBookingApplication;

import java.util.*;
public class Booking {
	
	public static ArrayList<taxi> CreateTaxi(int n){
		ArrayList<taxi> ctaxi= new ArrayList<>();
		for(int i=0; i<n; i++) {
			ctaxi.add(new taxi());
		}
		return ctaxi;
	}
	public static ArrayList<taxi> CheckTaxi(ArrayList<taxi> Taxis, int time, char pick){
		ArrayList<taxi> ftaxis= new ArrayList<>();
		taxi o;
		for(taxi taxi1: Taxis) {
			o= taxi1;
			if(o.freetime<= time&&(Math.abs(o.standing-pick)<=time-o.freetime)) {
				ftaxis.add(taxi1);
			}
		}
		
		return ftaxis;
	}
	public static void BookTaxi(int customerId,ArrayList<taxi> freetaxis, char pick,char drop, int time) {
		taxi o= freetaxis.get(0);
		o.freetime= o.freetime+ ((o.standing-pick)*15)/15+Math.abs(pick-drop)+time;
		o.standing=pick;
		o.totalearnings+=Math.abs(((pick-drop)*15)-5)*10+100;
		String str="Customer Id: "+customerId+" Pick up: "+pick+" Drop: "+drop+" total earnings: "+o.totalearnings+" free time: "+ o.freetime;
		o.SetDetails(customerId, o.freetime, o.totalearnings,str);
		System.out.print("Tax-"+o.id+"Allocated");
	}
	public static void viewtripdetails(ArrayList<taxi> Taxis) {
		for(taxi taxiList: Taxis) {
			if(!taxiList.tripdetails.equals(null))
			 System.out.println(taxiList.tripdetails.toString());
		}
	}
	
	public static void main(String args[]) {
		Scanner sc= new Scanner(System.in);
		ArrayList<taxi> Taxis= CreateTaxi(4);
		int option;
		int customerId=0;
		char pickup;
		char drop;
		int picktime;
		while(true) {
			System.out.println("1. Book Taxi");
			System.out.println("2. Show Taxi Trip details");
			option= sc.nextInt();
			if(option==1) {
				customerId= customerId+1;
				System.out.print("Enter Pick up point: ");
				pickup= sc.next().charAt(0);
				System.out.print("Enter Drop point: ");
				drop= sc.next().charAt(0);
				System.out.print("Enter Pick up Time: ");
				picktime= sc.nextInt();
				if(pickup<'A'||pickup>'F'||drop<'A'||drop>'F') {
					System.out.println("Wrong Stations");
					return;
				}
				
				ArrayList<taxi> freetaxi= CheckTaxi(Taxis, picktime, pickup);
				
				if(freetaxi.size()==0) {
					System.out.println("No taxi Available!!!!");
					return;
				}
				
				Collections.sort(freetaxi,(a,b)->a.totalearnings-b.totalearnings);
				BookTaxi(customerId,freetaxi, pickup, drop,picktime);
				
			}
			else if(option==2) {
				viewtripdetails(Taxis);
			}
			else {
				return;
			}
			
		}
	}
}
