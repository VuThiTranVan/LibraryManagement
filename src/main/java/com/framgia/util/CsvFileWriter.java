package com.framgia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.framgia.users.bean.BorrowedDetailInfo;
import com.framgia.users.bean.BorrowedInfo;
import com.framgia.users.bean.UserInfo;

public class CsvFileWriter {

	// log
	private static final Logger logger = Logger.getLogger(CsvFileWriter.class);

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	public final static String HEADER_CSV_BORROWED = "Borrowed id, Borrowed code, Username, Fullname, Email, Phone number, Gender, Date intend borrowed, Date intend payment, Date borrowed, "
			+ "Date payment, Status of borrowed, Status of book rent, Book code, Book name, Price, Page number, Category, Publisher";

	// header of file report of screen management users
	public final static String HEADER_CSV_USER = "User id, Username, Permission, Full name, Email, Birthday, Address, Gender, Phone number, User create, Date create, User update, Date update";

	@SuppressWarnings("resource")
	public static void writeBorrowedCsv(String fileName, List<BorrowedInfo> borrowedInfo) throws FileNotFoundException {
		Writer fileWriter = null;
		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormatHeard = CSVFormat.newFormat(',').withHeader(HEADER_CSV_BORROWED)
				.withRecordSeparator(NEW_LINE_SEPARATOR);

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			// initialize FileWriter object
			fileWriter = new OutputStreamWriter(new FileOutputStream(fileName));

			// Create CSV file header
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormatHeard);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			if (!Helpers.isEmpty(borrowedInfo)) {
				// Write a new borrowed object list to the CSV file
				for (BorrowedInfo item : borrowedInfo) {
					for (BorrowedDetailInfo borrowedDetail : item.getBorrowedDetail()) {
						List<String> borrowedDataRecord = new ArrayList<String>();

						// Add data
						borrowedDataRecord.add(String.valueOf(item.getBorrowedId()));
						borrowedDataRecord.add(item.getBorrowedCode());

						borrowedDataRecord.add(item.getUserInfo().getUserName());
						borrowedDataRecord.add(item.getUserInfo().getName());
						borrowedDataRecord.add(item.getUserInfo().getEmail());
						borrowedDataRecord.add(item.getUserInfo().getPhone());
						borrowedDataRecord.add(item.getUserInfo().getSex());

						borrowedDataRecord.add(item.getdIntendBorrowed());
						borrowedDataRecord.add(item.getdIntendArrived());
						borrowedDataRecord.add(item.getDateBorrrowed());
						borrowedDataRecord.add(item.getDateArrived());
						borrowedDataRecord.add(item.getStatus());

						borrowedDataRecord.add(borrowedDetail.getStatus());
						borrowedDataRecord.add(borrowedDetail.getBookInfo().getBookCode());
						borrowedDataRecord.add(borrowedDetail.getBookInfo().getName());
						borrowedDataRecord.add(Helpers.formatCurrency(borrowedDetail.getBookInfo().getPrice()));
						borrowedDataRecord.add(String.valueOf(borrowedDetail.getBookInfo().getNumberPage()));
						borrowedDataRecord.add(borrowedDetail.getBookInfo().getCategoriesName());
						borrowedDataRecord.add(borrowedDetail.getBookInfo().getPublishersName());

						csvFilePrinter.printRecord(borrowedDataRecord);
					}
				}
			}

		} catch (Exception e) {
			logger.error("Error in CsvFileWriter!\n Message: " + e.getMessage());
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();

			} catch (IOException e) {
				logger.error("Error while flushing/closing fileWriter/csvPrinter !\n Message: " + e.getMessage());
			}
		}
	}

	@SuppressWarnings("resource")
	public static void writeUsersCsv(String fileName, List<UserInfo> userInfo) throws FileNotFoundException {
		Writer fileWriter = null;
		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormatHeard = CSVFormat.newFormat(',').withHeader(HEADER_CSV_USER)
				.withRecordSeparator(NEW_LINE_SEPARATOR);

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			// initialize FileWriter object
			fileWriter = new OutputStreamWriter(new FileOutputStream(fileName));

			// Create CSV file header
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormatHeard);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			if (!Helpers.isEmpty(userInfo)) {
				// Write a new user object list to the CSV file
				for (UserInfo item : userInfo) {
					List<String> userDataRecord = new ArrayList<String>();
					// Add data
					userDataRecord.add(String.valueOf(item.getUserId()));
					userDataRecord.add(item.getUserName());
					userDataRecord.add(item.getPermissions().getPermissionName());
					userDataRecord.add(item.getName());
					userDataRecord.add(item.getEmail());
					userDataRecord.add(item.getBirthDate());
					userDataRecord.add(item.getAddress());
					userDataRecord.add(item.getSex());
					userDataRecord.add(item.getPhone());
					userDataRecord.add(item.getUserCreate());
					userDataRecord.add(item.getDateCreate());
					userDataRecord.add(item.getUserUpdate());
					userDataRecord.add(item.getDateUpdate());

					csvFilePrinter.printRecord(userDataRecord);
				}
			}

		} catch (Exception e) {
			logger.error("Error in CsvFileWriter!\n Message: " + e.getMessage());
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();

			} catch (IOException e) {
				logger.error("Error while flushing/closing fileWriter/csvPrinter !\n Message: " + e.getMessage());
			}
		}
	}

	public static void deleteFileInServer(File downloadFile, OutputStream outStream) {
		FileInputStream inputStream = null;

		try {
			// File input stream
			inputStream = new FileInputStream(downloadFile);
			IOUtils.copy(inputStream, outStream);
		} catch (IOException e) {

			logger.error("Error download csv: " + e.getMessage());
		} finally {

			try {
				if (null != inputStream) {
					inputStream.close();
				}

				if (null != outStream) {
					outStream.close();
				}
			} catch (IOException e) {
				logger.error("Error download csv: " + e.getMessage());
			}

			// Delete file on server
			downloadFile.delete();
		}
	}

}