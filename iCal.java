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

//EDIT TEST FOR ECLIPSE AND GITHUB SYNC!!

public class iCal {
	public static void main(String[] args) {
		
		List<Float> x = new ArrayList<Float>();
		List<Float> y = new ArrayList<Float>();
		List<String> withGeo = new ArrayList<String>();
		int n = 0; //number of events with geo location

		Scanner in = new Scanner(System.in);
		System.out.println("Welcome! Please add a new event to the calendar!");
		
		boolean run = true;
		while(run){

		// EVENT NAME INPUT
		System.out.print("Event Name:");
		String EventName = in.nextLine();

		// EVENT NAME INPUT
		System.out.print("Category:");
		String EventCat = in.nextLine();

		// DATE START INPUT
		String EventStartDate = "";
		boolean dateFlag = true;
		do {
			try {
				System.out.print("Start Date:");
				EventStartDate = in.nextLine();
				// Format of the date expected in EventStartDate string
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				// Converting the EventStartDate String to Date
				Date date = format.parse(EventStartDate);
				// Desired format: yyyyMMdd
				format = new SimpleDateFormat("yyyyMMdd");
				// Changing the format of date and storing it in String
				EventStartDate = format.format(date);
				dateFlag = false; // ends loop if correct format used
			} catch (ParseException e) {
				System.out.println("! ! valid dates in MM/DD/YYYY format ! !");
			}
		} while (dateFlag == true);

		// DATE END INPUT
		String EventEndDate = "";
		dateFlag = true;
		do {
			try {
				System.out.print("End Date:");
				EventEndDate = in.nextLine();
				// Format of the date expected in EventEndDate string
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				// Converting the EventEndDate String to Date
				Date date = format.parse(EventEndDate);
				// Desired format: yyyyMMdd
				format = new SimpleDateFormat("yyyyMMdd");
				// Changing the format of date and storing it in String
				EventEndDate = format.format(date);
				dateFlag = false; // ends loop if correct format used
			} catch (ParseException e) {
				System.out.println("! ! valid dates in MM/DD/YYYY format ! !");
			}
		} while (dateFlag == true);

		// TIME START INPUT
		String EventStartTime = "";
		boolean timeFlag = true;

		do {
			try {
				System.out.print("Start Time:");
				EventStartTime = in.nextLine();
				// Format of the date expected in EventStartTime string
				DateFormat tFormat = new SimpleDateFormat("hh:mm aa");
				// Desired format: 24 hour format
				DateFormat outputformat = new SimpleDateFormat("HHmmss");
				Date asDate = null;

				// Converting the input String to Date
				asDate = tFormat.parse(EventStartTime);
				// Changing the format of date and storing it in String
				EventStartTime = outputformat.format(asDate);
				timeFlag = false; // ends loop if correct format used
			} catch (ParseException pe) {
				System.out.println("! ! valid time in hh:mm am/pm ! ! ");
			}
		} while (timeFlag == true);

		// TIME END INPUT
		String EventEndTime = "";
		timeFlag = true;

		do {
			try {
				System.out.print("End Time:");
				EventEndTime = in.nextLine();
				// Format of the date expected in EventEndTime string
				DateFormat tFormat = new SimpleDateFormat("hh:mm aa");
				// Desired format: 24 hour format
				DateFormat outputformat = new SimpleDateFormat("HHmmss");
				Date asDate = null;

				// Converting the EventEndTime String to Date
				asDate = tFormat.parse(EventEndTime);
				// Changing the format of date and storing it in String
				EventEndTime = outputformat.format(asDate);
				timeFlag = false; // ends loop if correct format used
			} catch (ParseException pe) {
				System.out.println("! ! valid time in hh:mm am/pm ! ! ");
			}
		} while (timeFlag == true);

		// PLACE INPUT
		System.out.print("Place:");
		String EventPlace = in.nextLine();

		// GEO LOCATION INPUT
		System.out.print("Add Geo Location? (y/n):");
		String Geo = in.nextLine();
		String EventGeo = "";
		String EventGeoLat = "";
		String EventGeoLon = "";
		float GeoLat = 0;
		float GeoLon = 0;

		if (Geo.equalsIgnoreCase("y")) {
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
		    withGeo.add(EventName);
		    n++;
		}

		System.out.print("Additional notes:");
		String EventDesc = in.nextLine();

		System.out.print("Public/Private?:");
		String EventClass = in.nextLine();
		if (EventClass.equalsIgnoreCase("public")) {
			EventClass = "PUBLIC";
		} else if (EventClass.equalsIgnoreCase("private")) {
			EventClass = "PRIVATE";
		} else {
			System.out.println("Invalid Entry, set as Default: PUBLIC");
			EventClass = "PUBLIC";
		}		
		
		try {
			File file = new File("ical_catorze.ics");
			String Event = "BEGIN:VEVENT" + "\nSUMMARY:" + EventName
					+ "\nSTATUS:CONFIRMED" + "\nCLASS:" + EventClass
					+ "\nTRANSP:TRANSPARENT" + "\nDTSTART:" + EventStartDate
					+ "T" + EventStartTime + "\nDTEND:" + EventEndDate + "T"
					+ EventEndTime + "\nCATEGORIES:" + EventCat + "\nLOCATION:"
					+ EventPlace + "\nGEO:" + EventGeoLat + ";" + EventGeoLon
					+ "\nDESCRIPTION:" + EventDesc + "\nEND:VEVENT\n";
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
				for (int i = 0; i < list.size() - 1; i++) {
					bw.write(list.get(i) + "\n");
					c = i;
				}
				c++;
				bw.write(list.get(c));
				bw.close();
			}
			
			System.out.println("\nDone, event has been added!");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
					double distance = 69.1105 * angle;

					System.out.println("Great Circle Distance between" + withGeo.get(i) + " and " + withGeo.get(i+1) + " is " + distance + " miles");
				}
				else{
					System.out.print("Not enough events added with Geo Location to calculate Great Circle Distance");
				}
			}
		}
		catch(IndexOutOfBoundsException aoe){
			System.out.println("\nThank you for using iCal!");
		}
}
}
