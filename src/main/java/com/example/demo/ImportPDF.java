package com.example.demo;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.awt.*;


public class ImportPDF extends HelloController{

    public static void Import2PDF(String[] barcodeIds){
        try {
//, int[] barcodeID
            // path where the pdf is to be created.
            String path = "C:\\Users\\HHSRobot9\\Downloads\\pdf.pdf";//implement system to find path
            PdfWriter pdfwriter = new PdfWriter(path);
            Rectangle myPageSize = new Rectangle(612, 792);
            // Creating a PdfDocument object.
            // passing PdfWriter object constructor
            PdfDocument pdfdocument = new PdfDocument(pdfwriter);

            // Creating a Document and
            // passing pdfDocument object

            Document document = new Document(pdfdocument, new PageSize(myPageSize));
            int x = 14;
            int y = 756;
            //loop to add every image using the barcode id inside method
            for(int i = 0; i < barcodeIds.length; i++) {
                if(barcodeIds[i]!= null) {
                    createBarcode(barcodeIds[i]);//creates new barcode image to add to pdf document
                    ImageData data = ImageDataFactory.create("C:\\Users\\HHSRobot9\\Downloads\\demoRyanCurrent\\barcode.png"); // Create an ImageData object
                    Image image = new Image(data);// Creating an Image object
                    for (int j = 0; j < 1; j++) {
                        if (y == 36) {
                            y = 756;
                            x += 198;
                        }
                        y -= 72;

                    }
                    image.setFixedPosition(x, y);// Set the position of the image.
                    image.scaleToFit(189, 72);
                    document.add(image);// Adding image to the document
                }
            }
            // Closing the document
            document.close();

            System.out.println(
                    "Image  position set successfully in pdf");
        }
        catch (Exception e) {
            System.out.println("unable to set image position due to " + e);
        }
    }
}