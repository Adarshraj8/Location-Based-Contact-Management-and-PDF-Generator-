package bihar.max.view;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import bihar.max.reg.RegDAO;
import bihar.max.reg.RegDTO;
@Service
public class UserPDFExporter {

	// private List<User> listUsers;
     
	@Autowired
	RegDAO regDAO;
	@Autowired
	PDFGenerate pDFGenerate;
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(6);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("ID", font));
	         table.addCell(cell);
	         
	         cell.setPhrase(new Phrase("Regis ID", font));
	         table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("State Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("District Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Phone", font));
	        table.addCell(cell);       
	    }
	     
	    private void writeTableData(PdfPTable table) {
	    	List<RegDTO> listUsers = pDFGenerate.setDataWithLactionName();
	    	int count =0;
	        for (RegDTO p : listUsers) {
	        	
	            table.addCell(String.valueOf(++count));
	            table.addCell(String.valueOf(p.getRid()));
	            table.addCell(p.getName());
	            table.addCell(p.getStCode());
	            table.addCell(p.getDistCode());
	            table.addCell(p.getPhone());
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(20);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("Welcome User", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(6);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.0f, 2.0f, 3.0f, 3.0f, 4.0f, 3.0f});
	        table.setSpacingBefore(12);
	         
	        writeTableHeader(table);// set header in table
	        writeTableData(table);  // data set in table
	         
	        document.add(table);
	         
	        document.close();
	         
	    }
	
	
}
