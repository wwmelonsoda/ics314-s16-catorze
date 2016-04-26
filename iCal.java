package iCalendar;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class iCal {
	public static void main(String[] args) {
		
		List<Float> x = new ArrayList<Float>(); //stores x coordinates when geolocation given
		List<Float> y = new ArrayList<Float>(); //stores y coordinates when geolocation given
		List<String> withGeo = new ArrayList<String>(); //stores event name when geolocation given
		
		List<String> EventName = new ArrayList<String>();
		List<String> EventCat = new ArrayList<String>();
		List<String> EventStartDate = new ArrayList<String>();
		List<String> EventEndDate = new ArrayList<String>();
		List<String> EventStartTime = new ArrayList<String>();
		List<String> EventEndTime = new ArrayList<String>();
		List<String> EventPlace = new ArrayList<String>();
		List<String> EventDesc = new ArrayList<String>();
		List<String> EventClass = new ArrayList<String>();
		List<String> gCircDis = new ArrayList<String>();
		int n = 0; //number of events with geo location

		Scanner in = new Scanner(System.in);
		System.out.println("Welcome! What day would you like to add events to?");
		
		
		// DATE START INPUT
		String getEventDate = "";
		String theEventDate = "";
		boolean dateFlag = true;
		do {
			try {
				System.out.print("Start Date:");
				getEventDate = in.nextLine();
				theEventDate = getEventDate;
				// Format of the date expected in EventStartDate string
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				// Converting the EventStartDate String to Date
				Date date = format.parse(getEventDate);
				// Desired format: yyyyMMdd
				format = new SimpleDateFormat("yyyyMMdd");
				// Changing the format of date and storing it in String
				getEventDate = format.format(date);
				dateFlag = false; // ends loop if correct format used

			} catch (ParseException e) {
				System.out.println("! ! valid dates in MM/DD/YYYY format ! !");
			}
		} while (dateFlag == true);

		// DATE END INPUT
		
		System.out.println("Now you may add Events to Calendar on " + theEventDate);
		
		boolean run = true;
		while(run){
			
			EventStartDate.add(getEventDate);
			EventEndDate.add(getEventDate);

		// EVENT NAME INPUT
		System.out.print("Event Name:");
		String getEventName = in.nextLine();
		EventName.add(getEventName);

		// EVENT NAME INPUT
		System.out.print("Category:");
		String getCat = in.nextLine();
		EventCat.add(getCat);


		// TIME START INPUT
		String getEventStartTime = "";
		boolean timeFlag = true;

		do {
			try {
				System.out.print("Start Time:");
				getEventStartTime = in.nextLine();
				// Format of the date expected in EventStartTime string
				DateFormat tFormat = new SimpleDateFormat("hh:mm aa");
				// Desired format: 24 hour format
				DateFormat outputformat = new SimpleDateFormat("HHmmss");
				Date asDate = null;

				// Converting the input String to Date
				asDate = tFormat.parse(getEventStartTime);
				// Changing the format of date and storing it in String
				getEventStartTime = outputformat.format(asDate);
				timeFlag = false; // ends loop if correct format used
				EventStartTime.add(getEventStartTime);
			} catch (ParseException pe) {
				System.out.println("! ! valid time in hh:mm am/pm ! ! ");
			}
		} while (timeFlag == true);

		// TIME END INPUT
		String getEventEndTime = "";
		timeFlag = true;

		do {
			try {
				System.out.print("End Time:");
				getEventEndTime = in.nextLine();
				// Format of the date expected in EventEndTime string
				DateFormat tFormat = new SimpleDateFormat("hh:mm aa");
				// Desired format: 24 hour format
				DateFormat outputformat = new SimpleDateFormat("HHmmss");
				Date asDate = null;

				// Converting the EventEndTime String to Date
				asDate = tFormat.parse(getEventEndTime);
				// Changing the format of date and storing it in String
				getEventEndTime = outputformat.format(asDate);
				timeFlag = false; // ends loop if correct format used
				EventEndTime.add(getEventEndTime);
			} catch (ParseException pe) {
				System.out.println("! ! valid time in hh:mm am/pm ! ! ");
			}
		} while (timeFlag == true);

		// PLACE INPUT
		System.out.print("Place:");
		String getEventPlace = in.nextLine();
		EventPlace.add(getEventPlace);

		// GEO LOCATION INPUT
		System.out.print("Add Geo Location? (y/n):");
		String Geo = in.nextLine();
		String EventGeo = "";
		String EventGeoLat = "";
		String EventGeoLon = "";
		float GeoLat = 0;
		float GeoLon = 0;
		boolean GeoFlag = true;

		if (Geo.equalsIgnoreCase("y") || Geo.equalsIgnoreCase("yes")) {
			boolean latFlag = true;
			boolean lonFlag = true;
			System.out.println("Geo coordinates...");
			do {
				try {
					System.out.print("Lattitude:");
					GeoLat = in.nextFloat();
					in.nextLine();
					latFlag = false;
				} catch (InputMismatchException ex) {
					System.out
							.println("! ! You Must Enter a valid coordinate ! !");
					in.next(); // clears wrong input
				}
			} while (latFlag == true);

			do {
				try {
					System.out.print("Longitute:");
					GeoLon = in.nextFloat();
					in.nextLine();
					lonFlag = false;
				} catch (InputMismatchException ex) {
					System.out
							.println("! ! You Must Enter a valid coordinate ! !");
					in.next(); // clears wrong input
				}
			} while (lonFlag == true);

			EventGeoLat = String.valueOf(GeoLat);
			EventGeoLon = String.valueOf(GeoLon);
			x.add(GeoLat);
		    y.add(GeoLon);
		    withGeo.add(getEventName);
		    gCircDis.add(null);
		    n++;
		}
		else if(Geo.equalsIgnoreCase("n") || Geo.equalsIgnoreCase("no")){
			GeoFlag = false; 
			x.add(null);
			y.add(null);
			withGeo.add(null);
			gCircDis.add(null);
			
			
		}

		System.out.print("Additional notes:");
		String getEventDesc = in.nextLine();
		EventDesc.add(getEventDesc);

		System.out.print("Public/Private?:");
		String getEventClass = in.nextLine();
		if (getEventClass.equalsIgnoreCase("public")) {
			getEventClass = "PUBLIC";
			EventClass.add(getEventClass);
		} else if (getEventClass.equalsIgnoreCase("private")) {
			getEventClass = "PRIVATE";
			EventClass.add(getEventClass);
		} else {
			System.out.println("Invalid Entry, set as Default: PUBLIC");
			getEventClass = "PUBLIC";
			EventClass.add(getEventClass);
		}		
		
		
		
//ASKS USER IF THEY WOULD LIKE TO ADD A NEW EVENT
		boolean RunAgainFlag = true;
		do{
		System.out.println("Would you like to add another event?");
		String RunAgain = in.nextLine();
		if (RunAgain.equalsIgnoreCase("yes") || RunAgain.equalsIgnoreCase("y")) {
			run = true;
			RunAgainFlag = false;

		} else if (RunAgain.equalsIgnoreCase("no") || RunAgain.equalsIgnoreCase("n")) {
			run = false;
			RunAgainFlag = false;
		} else {
			System.out.println("Enter y/n");
			RunAgainFlag = true;
		}
		} while (RunAgainFlag == true);
		
	}//while run = true
		
		try{
			for(int i = 0; i < withGeo.size(); i++){
				if(n >= 2){ //only runs if more than 2 events with geo location available
					double L1 = x.get(i);
					double G1 = y.get(i);
					double L2 = x.get(i+1);
					double G2 = y.get(i+1);

					// convert to radians
					L1 = Math.toRadians(L1);
					L2 = Math.toRadians(L2);
					G1 = Math.toRadians(G1);
					G2 = Math.toRadians(G2);

					// do the spherical trig calculation
					double angle = Math.acos(Math.sin(L1) * Math.sin(L2)  +
		                             Math.cos(L1) * Math.cos(L2) * Math.cos(G1 - G2));

					// convert back to degrees
					angle = Math.toDegrees(angle);

					// each degree on a great circle of Earth is 69.1105 miles
					double miDistance = 69.1105 * angle;
					double kiloDistance = miDistance*1.609347218694;
					
					String GreatCircleDistance = ("Great Circle Distance between " + withGeo.get(i) + " and " + withGeo.get(i+1) + " is " + miDistance + " miles and " + kiloDistance + "in Kilometers.");

					gCircDis.set( i, GreatCircleDistance); 
				}
				else{
					System.out.print("Not enough events added with Geo Location to calculate Great Circle Distance");
				}
			}
			System.out.println("\nThank you for using iCal!");
		}
		catch(IndexOutOfBoundsException aoe){
			System.out.println("\nThank you for using iCal!");
		}
		
		//CREATE .ICS FILE AND PRINT EVENT DETAILS 	
				try {
					for(int i = 0; i < EventName.size(); i++){
					File file = new File("ical_catorze.ics");
					String Event = "BEGIN:VEVENT" + "\nSUMMARY:" + EventName.get(i)
							+ "\nSTATUS:CONFIRMED" + "\nCLASS:" + EventClass.get(i)
							+ "\nTRANSP:TRANSPARENT" + "\nDTSTART:" + EventStartDate.get(i)
							+ "T" + EventStartTime.get(i) + "\nDTEND:" + EventEndDate.get(i) + "T"
							+ EventEndTime.get(i) + "\nCATEGORIES:" + EventCat.get(i) + "\nLOCATION:"
							+ EventPlace.get(i) + "\nGEO:" + x.get(i) + ";" + y.get(i)
							+ "\nDESCRIPTION:" + EventDesc.get(i) + ", " + gCircDis.get(i) + "\nEND:VEVENT\n";
					String endCal = "END:VCALENDAR\n";
					// if file doesn't exist, then create it
					if (!file.exists()) {
						file.createNewFile();
						String startCal = "BEGIN:VCALENDAR\n" + "VERSION:2.0\n"
								+ "PRODID:-//ZContent.net//Zap Calendar 1.0//EN\n"
								+ "CALSCALE:GREGORIAN\n" + "METHOD:PUBLISH\n";
						FileWriter fw = new FileWriter(file.getName());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(startCal + Event + endCal);
						bw.close();
					} else {
						ArrayList<String> list = new ArrayList<String>();
						FileReader fr = new FileReader(file.getName());
						BufferedReader br = new BufferedReader(fr);
						String input;
						while ((input = br.readLine()) != null) {
							list.add(input);

						}
						//System.out.println(list);
						// list.remove(list.size() - 1);
						list.set(list.size() - 1, Event + endCal);
						FileWriter fw = new FileWriter(file.getName());
						BufferedWriter bw = new BufferedWriter(fw);
						int c = 0;
						for (int q = 0; q < list.size() - 1; q++) {
							bw.write(list.get(q) + "\n");
							c = q;
						}
						c++;
						bw.write(list.get(c));
						bw.close();
					}
					System.out.println("\nDone," + EventName.get(i) + " has been added!");
					}
					} catch (IOException e) {
					e.printStackTrace();
				}
		//END WRITING EVENTS INTO .ICS FILE
		
		
		
}
}