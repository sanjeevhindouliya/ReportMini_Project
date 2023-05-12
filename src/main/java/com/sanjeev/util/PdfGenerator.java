package com.sanjeev.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sanjeev.entity.CitizenPlan;

@Component
public class PdfGenerator {

	
	public void generate(HttpServletResponse response, List<CitizenPlan> plans , File f) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		
		
		
		
		com.lowagie.text.Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		  
		  Paragraph paragraph = new Paragraph("Citizen Plan", fontTiltle);
		  
		  paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		  
			document.add(paragraph);
			
			
		
		   PdfPTable  table = new PdfPTable(6);
		   table.setSpacingBefore(5);
		   
		   table.addCell("Id");
		   table.addCell("Citizen Name");
		   table.addCell("Plan Name");
		   table.addCell("Plan Status");
		   table.addCell("Start Date");
		   table.addCell("End Date");
		   
		  
		   for(CitizenPlan plan : plans) {
			   table.addCell(String.valueOf(plan.getCitizenId()));
			   table.addCell(plan.getCitizenName());
			   table.addCell(plan.getPlanName());
			   table.addCell(plan.getPlanStatus());
			   table.addCell(plan.getPlanStartDate()+"");
			   table.addCell(plan.getPlanEndDate()+"");
		   }
		   
		document.add(table);
		document.close();
	}

	
}
