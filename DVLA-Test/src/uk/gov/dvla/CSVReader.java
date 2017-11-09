package uk.gov.dvla;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {
	static List<VehicleData> _vehiclesData = new LinkedList<VehicleData>();
	
	public static List<VehicleData> readFrom(FileInfo fileInfo) throws FileNotFoundException, IOException {

		boolean isHeader = true;
		// create an instance of BufferedReader // using try with resource, Java 7 feature to close resources 
		try (BufferedReader br = new BufferedReader(new FileReader(fileInfo.name)))
		{ 
			String line = null; 
			// loop until all lines are read 
			while ((line=br.readLine()) != null) 
			{ 
				if (isHeader) {
					isHeader = false;
					continue;
				}
				String[] values = line.split(","); 

				// adding vehicledata  into List 
				_vehiclesData.add(Helper.createVehicleDataObj(values));
			}
		}

		return _vehiclesData;
	}

}
