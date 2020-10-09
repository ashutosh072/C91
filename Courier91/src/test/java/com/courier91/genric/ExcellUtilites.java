package com.courier91.genric;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Ashutosh sharma
 * @created on 12/07/2018
 * reviewed by Ashutosh sharma
 *
 */

public class ExcellUtilites {
	static String filePath ="./TestData/testdata - Copy.xlsx";



	/**
	 * @description read data from testdata.xlsx
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 */

	public static String readData(String sheet, int row, int cell) {
		String value= null;


		try {
			Workbook 	wb = WorkbookFactory.create(new FileInputStream(new File(filePath)));
			value = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		} catch (  InvalidFormatException e ) {

			e.printStackTrace();
		}

		catch(EncryptedDocumentException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return value;

	}
}
