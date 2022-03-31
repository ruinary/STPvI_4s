package by.belstu.it.artem;

import assortment.Assortment;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler {

    Assortment assort = new Assortment();
    String thisElement = "";

    public Assortment getResult(){ return assort; };
    @Override
    public void startDocument() throws SAXException {
        System.out.println("----> Start parse XML...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("----> Stop parse XML...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(thisElement.equals("name")){
            assort.name=new String(ch,start,length);
        }else if(thisElement.equals("publicationYear")){
            assort.publicationYear=new Integer(new String(ch, start, length));
        }
    }
}
