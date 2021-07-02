import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class AutoCatalog {

    public static void main(String[] args) {
        String xmlName;

//        Тэг и атрибут этого тега, который мы будем считать, можно вынести в файл настроек
        final String TAG = "modification";
        final String ATTR = "name";

        if (args.length == 1) {
            xmlName = args[0];
        }else {
            System.out.println("Specify XML file name in this directory");
            return;
        }

        try {
            SAXParserFactory spFactory = SAXParserFactory.newInstance();
            spFactory.setNamespaceAware(true);
            SAXParser saxParser = spFactory.newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            reader.setContentHandler(new XmlHandlerForCounting(TAG, ATTR));
            reader.parse(convertToFileURL(xmlName));
        } catch (ParserConfigurationException e) {
            System.out.println("Error in parser configuration");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Error while parsing xml");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while reading file");
            e.printStackTrace();
        }

    }

    private static String convertToFileURL(String xmlName) {
        String path = new File(xmlName).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

}
