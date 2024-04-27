package lt.eif.viko.mjakovcenko.banksoap.banksoap.transformation;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 * This class provides a method to convert XML documents into PDF format using XSLT.
 */
public class ToPdf {
    /**
     * Main method to execute the transformation.
     * @param args command line arguments
     */
    public static void main(String[] args){
        try {
            convertToPdf();
        } catch (IOException  | SAXException  | TransformerException e) {
            e.printStackTrace();
        }

    }

    /**
     * Converts an XML file to an PDF file using an XSLT stylesheet.
     *  The method reads an XML file and applies an XSLT transformation defined in XSLT file.
     *  The result of the transformation is written to an PDF file.
     * @throws IOException if there is an error reading the XML file or writing the HTML file.
     * @throws TransformerException if an error occurs during the XSLT transformation process.
     * @throws  SAXException If any parse errors occur during the processing of the XML file.
     */
    public static void convertToPdf() throws IOException, SAXException, TransformerException {
        File xsltFile = new File("Client.xsl");
        StreamSource xmlSource = new StreamSource(new File("Client.xml"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        OutputStream out = new FileOutputStream("Client_1.pdf");
        try{
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent,out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(xmlSource,res);
        }
        finally {
            out.close();
        }
    }
}