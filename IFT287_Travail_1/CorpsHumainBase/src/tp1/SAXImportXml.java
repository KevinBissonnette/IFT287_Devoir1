package tp1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SAXImportXml extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();

    private HumanSystem system;

    private List<HumanSystem> list = new ArrayList<>();

    private int FlowIndex = 0;

    @Override
    public void startDocument() {
       // System.out.println("Start Document");

    }

    @Override
    public void endDocument() {
      //  System.out.println("End Document");
    }

    public void startElement(String uri, String localName,
                             String Organ, Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

       // System.out.printf("Start Element : %s%n", Organ);
        if (Organ.equalsIgnoreCase("System")) {
            system = new HumanSystem();
            system.name = attributes.getValue("name");
            system.id = attributes.getValue("id");
            system.length = attributes.getValue("length");
            system.volume = attributes.getValue("volume");

        }

        if (Organ.equalsIgnoreCase("Flow")) {
            var temp = new Flow();

            temp.name = attributes.getValue("name");
            temp.id = attributes.getValue("id");
            temp.con = new Connectible();
            system.flow.add(temp);
            FlowIndex++;
        }
        if (Organ.equalsIgnoreCase("Connectible")) {
        }

        if ( is(Organ,"Ventricle") || is(Organ,"Atrium")) {
            var temp = new Atrium_Ventricule_data();
            temp.tagName = Organ;
            temp.id = attributes.getValue("id");
            temp.name = attributes.getValue("name");
            temp.volume = attributes.getValue("volume");
            system.flow.get(FlowIndex-1).con.coeur.add(temp);
        }

        if ( is(Organ,"Vein") || is(Organ,"Artery") || is(Organ,"Capillaries")) {
            var temp = new Vein_Artery_data();
            temp.tagName = Organ;
            temp.name = attributes.getValue("name");
            temp.id = attributes.getValue("id");
            temp.startRadius = attributes.getValue("startRadius");
            temp.endRadius = attributes.getValue("endRadius");
            temp.length = attributes.getValue("length");
            system.flow.get(FlowIndex-1).con.circulatoire.add(temp);
        }

        if ( is(Organ,"AirConnectible") || is(Organ,"Alveoli") || is(Organ,"Nose") || is(Organ,"Nose") ) {
            var temp = new Tract();
            temp.tagName = Organ;
            temp.name = attributes.getValue("name");
            temp.length = attributes.getValue("length");
            temp.volume = attributes.getValue("volume");
            system.flow.get(FlowIndex-1).con.pipe.add(temp);
        }







    }

    public void endElement(String uri, String localName,String Organ) {

       // System.out.printf("End Element : %s%n", Organ);

        if (Organ.equalsIgnoreCase("System")) {
            list.add(system);
            FlowIndex=0;

        }
    }

    public List<HumanSystem> getSystem() {

        return list;
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


    private boolean is(String a, String b) {

        return a.equalsIgnoreCase(b);
    }


}
