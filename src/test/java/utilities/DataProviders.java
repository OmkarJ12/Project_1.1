package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data provider 1
	@DataProvider(name="LoginData")
	public String [][] getData()throws IOException{
		String path = ".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path); //creating obj for xlutility
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols]; //created for 2d array which can store
		
		for(int i=1;i<=totalRows;i++) {    //1   read the data from xl storing in 2d array
			for(int j=0;j<totalCols;j++) {          //0  i is row j is col     
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j); //1,0
			}
		}
		
		return loginData; //returning 2d array
	}
	
	
	//data provider 2
	
	//data provider 3
}
