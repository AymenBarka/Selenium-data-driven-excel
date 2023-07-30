package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

	
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;


    public void setExcelFile(String excelFilePath, String sheetName) throws IOException
    {
        // Create an object of File class to open xls file
        File file = new File(excelFilePath);

        // Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);

        // creating workbook instance that refers to .xls file
        workbook = new XSSFWorkbook(inputStream);

        // creating a Sheet object
        sheet = workbook.getSheet(sheetName);

    }

    public String getCellData(int rowNumber, int cellNumber)
    {
        // getting the cell value from rowNumber and cell Number
        cell = sheet.getRow(rowNumber).getCell(cellNumber);

        // Cr�er un formateur de donn�es pour obtenir la valeur de la cellule sous forme de cha�ne de caract�res
        DataFormatter dataFormatter = new DataFormatter();

        // V�rifier le type de la cellule
        switch (cell.getCellType()) {
            case STRING:
                // Si la cellule contient d�j� une cha�ne de caract�res, renvoyer directement la valeur
                return cell.getStringCellValue();
            case NUMERIC:
                // Si la cellule contient un nombre, utiliser le formateur de donn�es pour r�cup�rer la valeur sous forme de cha�ne de caract�res
                return dataFormatter.formatCellValue(cell);
            default:
                // G�rer les autres types de cellules ici si n�cessaire
                return "";
        }
    }

    public int getRowCountInSheet()
    {
        int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        return rowcount;
    }

    public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) throws IOException
    {
        // creating a new cell in row and setting value to it
        sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
    }
}
