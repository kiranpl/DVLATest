package uk.gov.dvla;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	static List<VehicleData> _vehiclesData = new LinkedList<VehicleData>();
	
	public static List<VehicleData> readFromXLS(FileInfo fileInfo) throws IOException {
		FileInputStream excelFile = new FileInputStream(new File(fileInfo.name));
		Workbook workbook = new HSSFWorkbook(excelFile);
		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		
		while (iterator.hasNext()) {
			String[] values = new String[3];
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			int counter = 0;
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				values[counter] = currentCell.getStringCellValue();
				counter++;
			}
			_vehiclesData.add(Helper.createVehicleDataObj(values));
		}
		
		return _vehiclesData;
	}
	
	public static List<VehicleData> readFromXLSX(FileInfo fileInfo) throws IOException {
		FileInputStream excelFile = new FileInputStream(new File(fileInfo.name));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		
		while (iterator.hasNext()) {
			String[] values = new String[3];
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			int counter = 0;
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				values[counter] = currentCell.getStringCellValue();
				counter++;
			}
			_vehiclesData.add(Helper.createVehicleDataObj(values));
		}
		
		return _vehiclesData;
	}	
}
