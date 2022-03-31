package manager_dao.impl;

import entity.core.GroupDTO;
import manager_dao.inter.IExportStudentFileDAO;
import entity.core.StudentDTO;
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

    private static final int COLUMN_INDEX_GROUPID = 0;
    private static final int COLUMN_INDEX_MEMBERID = 1;
    private static final int COLUMN_INDEX_FULLNAME = 2;
    private static final int COLUMN_INDEX_TOPICID = 3;
    private static CellStyle cellStyleFormatNumber = null;

    public void write_file_student(List<GroupDTO> group_list, String excelFilePath) throws IOException {
        //create workbook
        Workbook workbook = getWorkbook(excelFilePath);

        //create sheet
        Sheet sheet = workbook.createSheet("Student");

        int rowIndex = 0;

        //write header
        write_Student_Header(sheet, rowIndex);
        //write data
        rowIndex++;
        for (GroupDTO group : group_list) {
            //create row
            Row row = sheet.createRow(rowIndex);
            //write data on row
            writeData(group, row);
            rowIndex++;
        }

        //auto size column
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

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
        Cell cell = row.createCell(COLUMN_INDEX_GROUPID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Group ID");

        cell = row.createCell(COLUMN_INDEX_MEMBERID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Member ID");

        cell = row.createCell(COLUMN_INDEX_FULLNAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Full name");

        cell = row.createCell(COLUMN_INDEX_TOPICID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Topic ID");
    }
    
    //Write Data
    @Override
    public void writeData(GroupDTO group, Row row) {
        if (cellStyleFormatNumber == null) {
            //format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_INDEX_GROUPID);
        cell.setCellValue(group.getGroupId());

        cell = row.createCell(COLUMN_INDEX_MEMBERID);
        cell.setCellValue(group.getMemberId());

        cell = row.createCell(COLUMN_INDEX_FULLNAME);
        cell.setCellValue(group.getFullname());

        cell = row.createCell(COLUMN_INDEX_TOPICID);
        cell.setCellValue(group.getTopicId());

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
    @Override
    public void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    //Create output file
    @Override
    public void createOutputFile(Workbook workbook, String excelFilePath) throws FileNotFoundException, IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
