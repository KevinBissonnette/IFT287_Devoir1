package tp1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Hashtable;

public class SAXImportXml extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();



    @Override
    public void startDocument() {
        System.out.println("Start Document");

    }

    @Override
    public void endDocument() {
        System.out.println("End Document");
    }

    public void startElement(String uri, String localName,
                             String Organ, Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        System.out.printf("Start Element : %s%n", Organ);

        if (Organ.equalsIgnoreCase("Heart")) {
            // get tag's attribute by name
            String id = attributes.getValue("Artery");
            System.out.printf("organ id : %s%n", id);
        }
/*
        if (qName.equalsIgnoreCase("salary")) {
            // get tag's attribute by index, 0 = first attribute
            String currency = attributes.getValue(0);
            System.out.printf("Currency :%s%n", currency);
        }
        */

    }

    public void endElement(String uri, String localName,String Organ) {

        System.out.printf("End Element : %s%n", Organ);

        if (Organ.equalsIgnoreCase("Hearth")) {
            System.out.printf("Organ : %s%n", currentValue.toString());
        }
/*
        if (qName.equalsIgnoreCase("role")) {
            System.out.printf("Role : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("salary")) {
            System.out.printf("Salary : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("bio")) {
            System.out.printf("Bio : %s%n", currentValue.toString());
        }
*/
    }

    @Override
    public void characters(char ch[], int start, int length) {

        // The characters() method can be called multiple times for a single text node.
        // Some values may missing if assign to a new string

        // avoid doing this
        // value = new String(ch, start, length);

        // better append it, works for single or multiple calls
        currentValue.append(ch, start, length);

    }



}
