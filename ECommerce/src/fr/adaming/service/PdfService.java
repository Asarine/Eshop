//Tutoriel pour faire ça
//https://www.ibm.com/developerworks/library/os-javapdf/index.html


package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;


public class PdfService {
	
	public static void Facture(Commande comm) throws FileNotFoundException, DocumentException {
		
		Document document = new Document(PageSize.A4, 75, 75,75, 75);
		
		PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\int0348\\Desktop\\Formation\\FactureECommerce.pdf"));
		document.open();
		
		Paragraph titre = new Paragraph("Facture", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE, new CMYKColor(74,255,178,0)));
		titre.setSpacingAfter(20);
		document.add(titre);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Paragraph indic1=new Paragraph("Votre commande du "+format.format(comm.getDate()), FontFactory.getFont(FontFactory.HELVETICA));
		indic1.setSpacingAfter(5);
		document.add(indic1);
		
		PdfPTable Fac= new PdfPTable(5);
		Fac.setSpacingAfter(10);
		Fac.addCell("Animal");
		Fac.addCell("Description");
		Fac.addCell("Prix à l'unité");
		Fac.addCell("Quantité réservée");
		Fac.addCell("Prix");
		List<LigneCommande> listeCommande = comm.getListeLCommande();
		for (LigneCommande ligne:listeCommande){
			Fac.addCell(ligne.getProd().getDesignation());
			Fac.addCell(ligne.getProd().getDescription());
			Fac.addCell(String.valueOf(ligne.getProd().getPrix()));
			Fac.addCell(String.valueOf(ligne.getQuantite()));
			Fac.addCell(String.valueOf(ligne.getPrix()));
		}
		Fac.addCell(" ");
		Fac.addCell(" ");
		Fac.addCell(" ");
		Fac.addCell("Prix total ");
		Fac.addCell(String.valueOf(comm.getPrix()));
		document.add(Fac);
		
		Calendar cd=Calendar.getInstance();
		cd.setTime(comm.getDate());
		cd.add(Calendar.DAY_OF_YEAR, 14);
		Date dateRecup =cd.getTime();
		
		Paragraph indic2=new Paragraph("Nous vous remercions pour votre commande. Vous pourrez venir chercher vos petits animaux à partir du "+format.format(dateRecup)+".", FontFactory.getFont(FontFactory.HELVETICA));
		indic2.setSpacingAfter(5);
		document.add(indic2);
		
		Paragraph indic3=new Paragraph("En vous remerciant.", FontFactory.getFont(FontFactory.HELVETICA));
		indic3.getExtraParagraphSpace();
		indic3.setSpacingAfter(5);
		document.add(indic3);
		

		Paragraph indic4=new Paragraph("Le service client", FontFactory.getFont(FontFactory.HELVETICA));
		indic4.setAlignment(indic4.ALIGN_CENTER);
		indic4.setSpacingAfter(5);
		document.add(indic4);
		document.close();
				
				
	}
	
}