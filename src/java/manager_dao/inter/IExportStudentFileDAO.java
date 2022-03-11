
package manager_dao.inter;

import enitiy.StudentDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public interface IExportStudentFileDAO {
    public void write_file_student(List<StudentDTO> student_list, String excelFilePath) throws IOException;
    public Workbook getWorkbook(String excelFilePath);
    public void write_Student_Header(Sheet sheet, int rowIndex);
    public void writeData(StudentDTO student, Row row);
    public CellStyle createStyleForHeader(Sheet sheet);
//    public void autosizeColumn(Sheet sheet, int lastColumn);
    public void createOutputFile(Workbook workbook, String excelFilePath) throws FileNotFoundException, IOException;
}
