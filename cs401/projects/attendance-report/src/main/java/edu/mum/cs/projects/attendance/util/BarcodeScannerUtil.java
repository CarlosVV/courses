package edu.mum.cs.projects.attendance.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import edu.mum.cs.projects.attendance.domain.BarcodeRecord;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Utility that reads the results of barcode scanner from {@value #BARCODE_FILE}.</p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class BarcodeScannerUtil {
	private static final String BARCODE_FILE = "src/main/resources/rc_data.txt";
	private static volatile Collection<BarcodeRecord> barcodeRecords;
	
	public static List<BarcodeRecord> getBarcodeRecordsList() {
		// Default is all dates
		return getBarcodeRecordsList(LocalDate.ofEpochDay(0), LocalDate.now());
	}
	
	public static List<BarcodeRecord> getBarcodeRecordsList(LocalDate startDate, LocalDate endDate) {
		Comparator<BarcodeRecord> byDate = (b1, b2) -> b1.getDate().compareTo(b2.getDate());
		Comparator<BarcodeRecord> byTimeslot = (b1, b2) -> b1.getTimeslot().compareTo(b2.getTimeslot());
		Comparator<BarcodeRecord> byBarcode = (b1, b2) -> b1.getBarcode().compareTo(b2.getBarcode());
		
		// The dates are decremented/incremented to make the date range inclusive
		return getBarcodeRecords()
					.stream()
					.filter(b -> b.getDate().isAfter(startDate.minusDays(1)))
					.filter(b -> b.getDate().isBefore(endDate.plusDays(1)))
					.sorted(byDate.thenComparing(byTimeslot).thenComparing(byBarcode))
					.collect(Collectors.toList());
	}
	
	private static Collection<BarcodeRecord> getBarcodeRecords() {
		if(null == barcodeRecords) {
			barcodeRecords = loadBarcodeRecords();
		}
		
		return barcodeRecords;
	}
	
	private synchronized static Collection<BarcodeRecord> loadBarcodeRecords() {
		if(null != barcodeRecords) {
			return barcodeRecords;
		}
		
		System.out.println("Loading scanned barcode records...");
		
		File file = new File(BARCODE_FILE);
		long fileSize = file.length();

		Map<String, BarcodeRecord> dataMap = new HashMap<String, BarcodeRecord>((int)(fileSize/20));
		
		try {
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				
				if(line.isEmpty()) {
					continue;
				}
						
				// Removes duplicates
				if(dataMap.containsKey(line)) {
					continue;
				}
				else {
					dataMap.put(line,  new BarcodeRecord(line));
				}
			}			
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return dataMap.values();
	}

	public static void main(String[] args) {
		List<BarcodeRecord> list = getBarcodeRecordsList();
		list.stream().forEach(System.out::println);
		System.out.println("Number of records processed: " + list.size());
	}

}
