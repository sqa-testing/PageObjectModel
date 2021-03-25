package com.coreframework.testngdataprovider;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.coreframework.exceldtos.FacebookLogin;

public class ExcelReadDataProvider implements Iterator<Object[]> {
	Logger logger = Logger.getLogger(ExcelReadDataProvider.class);
	private int rowIndex = 2;
	private final ExcelReader excelRead;
	private final String sheetName;
	private final String classObject;

	public ExcelReadDataProvider(String fileName, String sheetName, String classObject) {
		excelRead = new ExcelReader(fileName);
		this.sheetName = sheetName;
		this.classObject = classObject;
	}

	@Override
	public boolean hasNext() {
		logger.debug("row index" + rowIndex);
		logger.debug("number of rows in" + excelRead.getRowCount(sheetName));
		return (rowIndex <= excelRead.getRowCount(sheetName));
	}

	@Override
	public Object[] next() {
		List<String> lst = excelRead.getRowData(sheetName, rowIndex);
		if (classObject.equalsIgnoreCase("login")) {
			FacebookLogin _facebookLogin = new FacebookLogin();
			if (lst.size() > 0) {
				_facebookLogin.setUserLogin(lst.get(0));
				_facebookLogin.setUserPassword(lst.get(1));
				//_facebookLogin.setIsValidUser(lst.get(2));
				
			}
			rowIndex++;
			return new Object[] { _facebookLogin };
		}
		return null;

	}
}