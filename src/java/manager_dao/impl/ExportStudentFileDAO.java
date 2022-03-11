package manager_dao.impl;

import manager_dao.inter.IExportStudentFileDAO;
import enitiy.StudentDTO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportStudentFileDAO implements IExportStudentFileDAO{

    private static final int COLUMN_INDEX_STUDENTID = 0;
    private static final int COLUMN_INDEX_ACCOUNTID = 1;
    private static final int COLUMN_INDEX_FULLNAME = 2;
    private static final int COLUMN_INDEX_DOB = 3;
    private static final int COLUMN_INDEX_EMAIL = 4;
    private static final int COLUMN_INDEX_PHONENUMBER = 5;
    private static final int COLUMN_INDEX_MAJORID = 6;
    private static final int COLUMN_INDEX_GROUPID = 7;
    private static CellStyle cellStyleFormatNumber = null;

    public void write_file_student(List<StudentDTO> student_list, String excelFilePath) throws IOException {
        //create workbook
        Workbook workbook = getWorkbook(excelFilePath);

        //create sheet
        Sheet sheet = workbook.createSheet("Student");

        int rowIndex = 0;

        //write header
        write_Student_Header(sheet, rowIndex);
        //write data
        rowIndex++;
        for (StudentDTO student : student_list) {
            //create row
            Row row = sheet.createRow(rowIndex);
            //write data on row
            writeData(student, row);
            rowIndex++;
        }

        //auto size column
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
//        autosizeColumn(sheet, numberOfColumn);

        //create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("write student file complete!!");
    }

    //Create WorkBook
    @Override
    public Workbook getWorkbook(String excelFilePath) {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("Not correct excel file type!!");
        }
        return workbook;
    }

    //Write Header!!
    @Override
    public void write_Student_Header(Sheet sheet, int rowIndex) {
        //create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
        //Create row
        Row row = sheet.createRow(rowIndex);

        //Create cells
        Cell cell = row.createCell(COLUMN_INDEX_STUDENTID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Student ID");

        cell = row.createCell(COLUMN_INDEX_ACCOUNTID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Account ID");

        cell = row.createCell(COLUMN_INDEX_FULLNAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Full name");

        cell = row.createCell(COLUMN_INDEX_DOB);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("DOB");

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(COLUMN_INDEX_PHONENUMBER);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Phone number");

        cell = row.createCell(COLUMN_INDEX_MAJORID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Major ID");

        cell = row.createCell(COLUMN_INDEX_GROUPID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Group ID");
    }
    
    //Write Data
    @Override
    public void writeData(StudentDTO student, Row row) {
        if (cellStyleFormatNumber == null) {
            //format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_INDEX_STUDENTID);
        cell.setCellValue(student.getStudentID());

        cell = row.createCell(COLUMN_INDEX_ACCOUNTID);
        cell.setCellValue(student.getAccountID());

        cell = row.createCell(COLUMN_INDEX_FULLNAME);
        cell.setCellValue(student.getFullName());

        cell = row.createCell(COLUMN_INDEX_DOB);
        cell.setCellValue(student.getDOB());

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellValue(student.getEmail());

        cell = row.createCell(COLUMN_INDEX_PHONENUMBER);
        cell.setCellValue(student.getPhoneNumber());

        cell = row.createCell(COLUMN_INDEX_MAJORID);
        cell.setCellValue(student.getMajorID());

        cell = row.createCell(COLUMN_INDEX_GROUPID);
        cell.setCellValue(student.getGroupID());
    }

    //Create Cellstyle for header
    @Override
    public CellStyle createStyleForHeader(Sheet sheet) {
        //create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);//font size

        //create cellstyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    //Auto resize column width
//    @Override
//    public void autosizeColumn(Sheet sheet, int lastColumn) {
//        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
//            sheet.autoSizeColumn(columnIndex);
//        }
//    }

    //Create output file
    @Override
    public void createOutputFile(Workbook workbook, String excelFilePath) throws FileNotFoundException, IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
