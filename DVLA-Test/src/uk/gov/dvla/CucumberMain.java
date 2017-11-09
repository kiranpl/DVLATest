package uk.gov.dvla;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import cucumber.api.cli.Main;

//Main class that runs the JUnit tests 
public class CucumberMain {

	static String mimeType = "text/csv";
	static String featurePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "uk" + File.separator + "gov" + File.separator + "dvla" + File.separator + "features" + File.separator;
	static String featureFile = featurePath + File.separator + "registration-test.feature";
	static String templateFile = featurePath + File.separator + "registration-test.feature.template";
	
   public static void main(String[] args) throws Throwable {
	   
	   setUp();
	   
       Main.main(new String[]{"-g", "uk.gov.dvla.stepDefinition", featureFile });

    }
   
   //Method that updates the feature file with data on the fly
   public static void setUp() {
       	try {
	    		String URL = "http://localhost:8080/FileService/GetFiles?type=" + mimeType;
	    		Gson gson = new Gson();
	    		HttpClient client = HttpClientBuilder.create().build();
	    		HttpGet get = new HttpGet(URL);
	    		
	    		HttpResponse response = client.execute(get);
	    		
	    		if(response!=null){
	                InputStream source = response.getEntity().getContent(); //Get the data in the entity
	                Reader reader = new InputStreamReader(source);
	                FileInfo[] result = gson.fromJson(reader, FileInfo[].class);
	                
	                for (FileInfo fileInfo : result) {
	                	switch (fileInfo.extension.toLowerCase()) {
	    	            	case ".csv":
	    	            		Helper.resetFeatureFile(templateFile, featureFile);
	    	            		for (VehicleData vehicleData : CSVReader.readFrom(fileInfo)) {
	    	            			appendVehicleData(vehicleData);
	    	            		}
	    	            		break;
	    	            	case ".xls":
	    	            		Helper.resetFeatureFile(templateFile, featureFile);
	    	            		for (VehicleData vehicleData : ExcelReader.readFromXLS(fileInfo)) {
	    	            			appendVehicleData(vehicleData);
	    	            		}
	    	            		break;
	    	            	case ".xlsx":
	    	            		Helper.resetFeatureFile(templateFile, featureFile);
	    	            		for (VehicleData vehicleData : ExcelReader.readFromXLSX(fileInfo)) {
	    	            			appendVehicleData(vehicleData);
	    	            		}
	    	            		break;        		
	                	}
	                }
	            }
       	} catch (Exception ex) {
       		System.out.println(ex.getMessage());
       		ex.printStackTrace();
       		System.exit(1);
       	}
   }	
   
	private static void appendVehicleData(VehicleData vehicleData) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(featureFile, true)) ) {
			bw.write("|" + vehicleData.registration + "|"+ vehicleData.make + "|" + vehicleData.color + "|");
			bw.newLine();
		}
	}   
}
