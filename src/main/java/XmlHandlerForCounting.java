import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;

//    SAX event handler для подсчета уникальных значений атрибута
class XmlHandlerForCounting extends DefaultHandler {

    private String tagName;
    private String attrName;

    public XmlHandlerForCounting(String tag, String attr){
        this.tagName = tag;
        this.attrName = attr;
    }

        //    Хэшсет для хранения уникальных значений атрибута тэга
        private HashSet<String> uniqueAttr = new HashSet<>();

        @Override
        public void startDocument() throws SAXException {
            uniqueAttr.clear();
        }

        @Override
        public void endDocument() throws SAXException {
            int tagsCount = uniqueAttr.size();
            System.out.println("The number of unique attribute"+ attrName +" in "+ tagName +" tags is " + tagsCount);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            String modName = null;
            if (localName.equalsIgnoreCase(tagName)) {
                modName = attributes.getValue(attrName);
            }
            if (modName != null && !uniqueAttr.contains(modName)) uniqueAttr.add(modName);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }
}
