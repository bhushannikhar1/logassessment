package logassignment;

import logassignment.service.LogDurationService;

public class LogReaderMainClass {

	public static void main(String[] args) {
		/**
		 * 
		 * 1. Read path and read file
		 * 
		 * 2. Make read file into list of JSON objects that are used to calculate
		 * duration
		 * 
		 * 3. Insert into DB with duration more than 4 ms as true.
		 * 
		 */

		String filepath = "C:/People/Bhushan/Projects/Credit-Suisse/ReadJavaLogs/logfile.txt";

		LogDurationService durationService = new LogDurationService();
		// Set path of logfile.
		durationService.setLogFilepath(filepath);
		// read file contents
		durationService.readLogFileContents();
		// calculate duration of each event
		durationService.calculateDuration();
		// print event with respect to duration and flag as true if taken more than 4ms
		//this print can be modified to persist in DB
		durationService.printEventDurations();

	}
}