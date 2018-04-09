package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import fr.adaming.model.Produit;

public class PdfAdmin {
	
	public static void Enregistrement (Produit pr) throws FileNotFoundException, DocumentException {
		
		// Création du document pdf
		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		
		PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\inti0426\\Desktop\\Formation Java\\Produit.pdf"));
		
		doc.open();
		
		doc.add(new Paragraph("LISTE DES PRODUITS",FontFactory.getFont(FontFactory.TIMES, 50, Font.BOLD, new CMYKColor(45, 0, 85, 49))));
		
		// Création d'un tableau dans le pdf		
		PdfPTable table = new PdfPTable(5);
		
		table.setSpacingBefore(25);
		
		
		// Contenu du tableau
		table.addCell("ID");
		table.addCell("Designation");
		table.addCell("Description");
		table.addCell("Prix");
		table.addCell("Quantité");
		
	   List<Produit> listeProd = pr.getListeProduits();
	   
	   for (Produit prd : listeProd){
		   
		   table.addCell(String.valueOf(prd.getIdProduit()));
		   table.addCell(prd.getDesignation());
		   table.addCell(prd.getDescription());
		   table.addCell(String.valueOf(prd.getQuantite()));
		   table.addCell(String.valueOf(prd.getPrix()));
	   }
	   
	   doc.add(table);
	   
	   doc.close();
		
	}

}
