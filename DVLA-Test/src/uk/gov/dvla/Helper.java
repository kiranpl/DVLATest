package uk.gov.dvla;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Helper {
	
	public static VehicleData createVehicleDataObj(String[] values) {
		
		if (values.length >= 3) {
			VehicleData vehicleData = new VehicleData();
			vehicleData.registration = values[0];
			vehicleData.make = values[1];
			vehicleData.color = values[2];
			
			return vehicleData;
		}
		
		return null;
	}
	
	public static void resetFeatureFile(String templateFile, String featureFile) throws IOException {
		FileUtils.copyFile(new File(templateFile), new File(featureFile));
	}
}
