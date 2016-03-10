
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//EDIT TEST FOR ECLIPSE AND GITHUB SYNC!!

public class iCal {
	public static void main(String[] args) {
		
        Scanner in = new Scanner(System.in);	
		System.out.println("Welcome! Please add a new event to the calendar!");

//EVENT NAME INPUT
		System.out.print("Event Name:");
		String EventName = in.nextLine();
		
//DATE START INPUT		
		String EventStartDate ="";
		boolean dateFlag = true;
		do{
		try {
			System.out.print("Start Date:");
			EventStartDate = in.nextLine();
			//Format of the date expected in EventStartDate string
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			//Converting the EventStartDate String to Date
			Date date = format.parse(EventStartDate);
			//Desired format: yyyyMMdd
			format = new SimpleDateFormat("yyyyMMdd");
			//Changing the format of date and storing it in String
			EventStartDate = format.format(date);
		       dateFlag = false; //ends loop if correct format used
		} catch (ParseException e) {
			System.out.println("! ! valid dates in MM/DD/YYYY format ! !" );
		}
		}while (dateFlag == true); 
		
//DATE END INPUT		
				String EventEndDate ="";
				dateFlag = true;
				do{
				try {
					System.out.print("End Date:");
					EventEndDate = in.nextLine();
					//Format of the date expected in EventEndDate string
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					//Converting the EventEndDate String to Date
					Date date = format.parse(EventEndDate);
					//Desired format: yyyyMMdd
					format = new SimpleDateFormat("yyyyMMdd");
					//Changing the format of date and storing it in String
					EventEndDate = format.format(date);
				       dateFlag = false; //ends loop if correct format used
				} catch (ParseException e) {
					System.out.println("! ! valid dates in MM/DD/YYYY format ! !" );
				}
				}while (dateFlag == true); 		
	
		
		
//TIME START INPUT
		String EventStartTime ="";
		boolean timeFlag = true;
		
		do{
		try {
			System.out.print("Start Time:");
			EventStartTime = in.nextLine();
			//Format of the date expected in EventStartTime string
			DateFormat tFormat = new SimpleDateFormat("hh:mm aa");
			//Desired format: 24 hour format
			DateFormat outputformat = new SimpleDateFormat("HHmmss");
			Date asDate = null;
			
	         //Converting the input String to Date
	    	 asDate= tFormat.parse(EventStartTime);
	         //Changing the format of date and storing it in String
	    	 EventStartTime = outputformat.format(asDate);
	    	 timeFlag = false; //ends loop if correct format used
	      }catch(ParseException pe){
	    	  System.out.println("! ! valid time in hh:mm am/pm ! ! " );
	       }	
		   }while (timeFlag == true); 	
		
		
//TIME END INPUT
		String EventEndTime ="";
		timeFlag = true;
		
		do{
		try {
			System.out.print("End Time:");
			EventEndTime = in.nextLine();
			//Format of the date expected in EventEndTime string
			DateFormat tFormat = new SimpleDateFormat("hh:mm aa");
			//Desired format: 24 hour format
			DateFormat outputformat = new SimpleDateFormat("HHmmss");
			Date asDate = null;
			
	         //Converting the EventEndTime String to Date
	    	 asDate= tFormat.parse(EventEndTime);
	         //Changing the format of date and storing it in String
	    	 EventEndTime = outputformat.format(asDate);
	    	 timeFlag = false; //ends loop if correct format used
	      }catch(ParseException pe){
	    	  System.out.println("! ! valid time in hh:mm am/pm ! ! " );
	       }	
		   }while (timeFlag == true); 
		
//PLACE INPUT		
		System.out.print("Place:");
		String EventPlace = in.nextLine();

//GEO LOCATION INPUT
		System.out.print("Add Geo Location? (y/n):");
		String Geo = in.nextLine();
		String EventGeo ="";
		String EventGeoLat ="";
		String EventGeoLon ="";
		float GeoLat = 0;
		float GeoLon = 0;
		
		if(Geo.equalsIgnoreCase("y"))
		{	
			boolean latFlag = true;
			boolean lonFlag = true;
			System.out.println("Geo coordinates...");
			do{
			try
		    { 
			System.out.print("Lattitude:");
		    GeoLat = in.nextFloat();	
		    in.nextLine();
		       latFlag = false;
		    }catch ( InputMismatchException ex )
		    { 
		      System.out.println("! ! You Must Enter a valid coordinate ! !" );
		      in.next(); //clears wrong input
		    }
			}while (latFlag == true);
			
			do{
			try
		    { 
			System.out.print("Longitute:");
		    GeoLon = in.nextFloat();	
		    in.nextLine();
		       lonFlag = false;
		    }catch ( InputMismatchException ex )
		    { 
		      System.out.println("! ! You Must Enter a valid coordinate ! !" );
		      in.next(); //clears wrong input
		    }
			}while (lonFlag == true);
		    
			EventGeoLat = String.valueOf(GeoLat);
			EventGeoLon = String.valueOf(GeoLon);
		}
		
		System.out.print("Additional notes:");
		String EventDesc = in.nextLine();
		
		System.out.print("Public/Private?:");
		String EventClass = in.nextLine();
			if(EventClass.equalsIgnoreCase("public"))
			{
				EventClass = "PUBLIC";
			}
			if(EventClass.equalsIgnoreCase("private"))
			{
				EventClass = "PRIVATE";
			}
			else
			{
				System.out.println("Invalid Entry, set as Default: PUBLIC");
				EventClass = "PUBLIC";
			}
		
		System.out.println("\nDone.");
		    // }
		
		try {

			
			String startCal = "BEGIN:VCALENDAR\n" 
			+ "VERSION:2.0\n"
			+ "PRODID:-//ZContent.net//Zap Calendar 1.0//EN\n"
            + "CALSCALE:GREGORIAN\n"
			+ "METHOD:PUBLISH\n";
            
			String Event = "BEGIN:VEVENT"
            + "\nSUMMARY:" + EventName 
            //+ "\nUID:"
            //+ "\nSEQUENCE:0"
            + "\nSTATUS:CONFIRMED"
            + "\nCLASS:" + EventClass
            + "\nTRANSP:TRANSPARENT"
            + "\nDTSTART:" + EventStartDate + "T" + EventStartTime
            + "\nDTEND:" + EventEndDate + "T" + EventEndTime
            + "\nCATEGORIES: uncategorized"
            + "\nLOCATION:" + EventPlace
            + "\nGEO:" + EventGeoLat + ";" + EventGeoLon
            + "\nDESCRIPTION:" + EventDesc
            + "\nEND:VEVENT\n";
			
 String endCal = "END:VCALENDAR\n";

			File file = new File("ical_catorze.ics");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(startCal + Event + endCal);
			bw.close();

			System.out.println("Calendar file has been created!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}