
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class iCal {
	public static void main(String[] args) {
		try {

			String content = "BEGIN:VCALENDAR\n" + "METHOD:PUBLISH\n" + "VERSION:2.0\n" + "X-WR-CALNAME:ics314\n" + "PRODID:-//Apple Inc.//Mac OS X 10.11.2//EN\n" + "X-APPLE-CALENDAR-COLOR:#FF9500\n" + "X-WR-TIMEZONE:Pacific/Honolulu\n" + "CALSCALE:GREGORIAN\n" + "BEGIN:VTIMEZONE\n" + "TZID:Pacific/Honolulu\n" + "BEGIN:DAYLIGHT\n" + "TZOFFSETFROM:-1030\n" + "DTSTART:19330430T020000\n" + "TZNAME:HDT\n" + "TZOFFSETTO:-0930\n" + "RDATE:19330430T020000\n" + "RDATE:19420209T020000\n" + "END:DAYLIGHT\n" + "BEGIN:STANDARD\n" + "TZOFFSETFROM:-1030\n" + "DTSTART:19470608T020000\n" + "TZNAME:HST\n" + "TZOFFSETTO:-1000\n" + "RDATE:19470608T020000\n" + "END:STANDARD\n" + "END:VTIMEZONE\n" + "BEGIN:VEVENT\n" + "CREATED:20160223T231946Z\n" + "UID:E552196C-4BBD-4E4F-96B3-8D93D3FD1C26\n" + "DTEND;TZID=Pacific/Honolulu:20160225T160000\n" + "TRANSP:OPAQUE\n" + "X-APPLE-TRAVEL-ADVISORY-BEHAVIOR:AUTOMATIC\n" + "SUMMARY:Meet with TA Amy\n" + "DTSTART;TZID=Pacific/Honolulu:20160225T150000\n" + "DTSTAMP:20160223T232045Z\n" + "SEQUENCE:0\n" + "END:VEVENT\n" + "END:VCALENDAR\n";

			File file = new File("ical_catorze.ics");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}