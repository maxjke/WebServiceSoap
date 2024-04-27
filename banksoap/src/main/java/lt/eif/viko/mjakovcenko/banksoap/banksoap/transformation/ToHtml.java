package lt.eif.viko.mjakovcenko.banksoap.banksoap.transformation;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 * This class provides a method to convert XML documents into HTML format using XSLT.
 */
public class ToHtml {
    /**
     * Main method to execute the transformation.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            convertToHtml();
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
    /**
     * Converts an XML file to an HTML file using an XSLT stylesheet.
     *
     * The method reads an XML file and applies an XSLT transformation defined in XSLT file.
     * The result of the transformation is written to an HTML file.
     *
     * @throws IOException if there is an error reading the XML file or writing the HTML file.
     * @throws TransformerException if an error occurs during the XSLT transformation process.
     */
    public static void convertToHtml() throws IOException, TransformerException {
        File xsltFile = new File("clientToHTML.xsl");
        StreamSource xmlSource = new StreamSource(new File("clienttoHtml.xml"));

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

        OutputStream out = new FileOutputStream("Client.html");
        try {
            Result result = new StreamResult(out);
            transformer.transform(xmlSource, result);
        } finally {
            out.close();
        }
    }
}
