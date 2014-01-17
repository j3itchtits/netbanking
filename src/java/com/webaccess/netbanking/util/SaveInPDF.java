/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.util;

/**
 *
 * @author Jay Prakash
 */
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.webaccess.netbanking.bean.TransactionDetailBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SaveInPDF {

    private static String FILE = "d:/TransactionDetail.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
//            addTitlePage(document);
            //addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //generating pdf
    public void savePDF(UserInfoBean userInfoBean, ArrayList arrayList) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document, userInfoBean, arrayList);
            // addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    //creating title for pdf
    private static void addTitlePage(Document document, UserInfoBean userInfoBean, ArrayList arrayList)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Transaction Detail", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph("Report generated by:   " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Account Number:  " + userInfoBean.getName(), smallBold));

        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Account Type:  " + userInfoBean.getAccountType(), smallBold));

        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Available Balance:  " + userInfoBean.getAvailBalance(), smallBold));

        addEmptyLine(preface, 3);

        createTable(preface, arrayList);
        document.add(preface);

        //  document.add(preface);  createTable(new Paragraph());
        // Start a new page
        //document.newPage();
    }

    private static void addContent(Document document) throws DocumentException {
        // Anchor anchor = new Anchor();
        //anchor.setName("First Chapter");
        // Second parameter is the number of the chapter
        //Chapter catPart = new Chapter(new Paragraph(anchor),0);
        //Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        //   Paragraph subPara = new Paragraph();
        //Section subCatPart = catPart.addSection(subPara);
        // subCatPart.add(new Paragraph("Hello"));
        //subPara = new Paragraph("Subcategory 2", subFont);
        //subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Paragraph 1"));
//        subCatPart.add(new Paragraph("Paragraph 2"));
//        subCatPart.add(new Paragraph("Paragraph 3"));
        // add a list
        //createList(subCatPart);
//        Paragraph paragraph = new Paragraph();
//        //addEmptyLine(paragraph, 5);
//        subPara.add(paragraph);
        // add a table
//        createTable(subPara);
        // now add all this to the document
        //     document.add(subPara);
        // Next section
//        anchor = new Anchor("Second Chapter", catFont);
//        anchor.setName("Second Chapter");
        // Second parameter is the number of the chapter
//        catPart = new Chapter(new Paragraph(anchor), 1);
//
//        subPara = new Paragraph("Subcategory", subFont);
//        subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("This is a very important message"));
//
//        // now add all this to the document
//        document.add(catPart);
    }

    //generating table in pdf formate
    private static void createTable(Paragraph subPara, ArrayList arrayList) throws BadElementException {
        PdfPTable table = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("S. NO."));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Particulars"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Withdrawal"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Deposit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (int i = 0; i < arrayList.size(); i++) {
            TransactionDetailBean transactionDetailBean = new TransactionDetailBean();
            transactionDetailBean = (TransactionDetailBean) arrayList.get(i);
            table.addCell(Integer.toString(i + 1));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(transactionDetailBean.getTransactionDate());
            table.addCell(date);
            table.addCell(transactionDetailBean.getParticulars());
            if (transactionDetailBean.getTransactionType() == "CR") {
                table.addCell("---");
                table.addCell(Double.toString(transactionDetailBean.getTransactionAmount()));
            } else {
                table.addCell(Double.toString(transactionDetailBean.getTransactionAmount()));
                table.addCell("---");
            }
        }
        subPara.add(table);

    }

//    private static void createList(Section subCatPart) {
//        List list = new List(true, false, 10);
//        list.add(new ListItem("First point"));
//        list.add(new ListItem("Second point"));
//        list.add(new ListItem("Third point"));
//        subCatPart.add(list);
//    }
    //adding empty line into pdf
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
