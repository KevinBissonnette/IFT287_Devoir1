package tp1;

import org.xml.sax.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SAXImportXml extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();

    private HumanSystem system;

    private List<Corps> list;

    private int FlowIndex = 0;

    @Override
    public void startDocument() {
       // System.out.println("Start Document");
       list = new ArrayList<Corps>();
    }

    @Override
    public void endDocument() {
      //  System.out.println("End Document");
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

       // System.out.printf("Start Element : %s%n", Organ);
        if (qName==("MainBody")){
            list.add(new Corps(attributes));
            //intiialiser les valeurs dans la calsse System ensuite on va l'ajouter a un Corps

        }
        if (qName==("System")) {

            //intiialiser les valeurs dans la classe System ensuite on va l'ajouter a un Corps
            int valeur=0;
            list.get(valeur).AjouterSystems(attributes);
            valeur++;
        }


           /* var temp = new Flow();

            temp.name = attributes.getValue("name");
            temp.id = attributes.getValue("id");
            temp.con = new Connectible();
            system.flow.add(temp);
            FlowIndex++;
            */


        if (qName.equalsIgnoreCase("Connectible")) {
        }

        if ( is(qName,"Ventricle") || is(qName,"Atrium")) {
            var temp = new Atrium_Ventricule_data();
            temp.tagName = qName;
            temp.id = attributes.getValue("id");
            temp.name = attributes.getValue("name");
            temp.volume = attributes.getValue("volume");
            system.flow.get(FlowIndex-1).con.coeur.add(temp);
        }

        if ( is(qName,"Vein") || is(qName,"Artery") || is(qName,"Capillaries")) {

        }

        if ( is(qName,"AirConnectible") || is(qName,"Alveoli") || is(qName,"Nose")) {
            var temp = new Tract();
            temp.tagName = qName;
            temp.name = attributes.getValue("name");
            temp.length = attributes.getValue("length");
            temp.volume = attributes.getValue("volume");
            system.flow.get(FlowIndex-1).con.pipe.add(temp);
        }







    }

    public void endElement(String uri, String localName,String qName) {

       // System.out.printf("End Element : %s%n", Organ);

      /*  if (qName.equalsIgnoreCase("System")) {
            //list.add(system);
            FlowIndex=0;

        }*/
    }

    public List<HumanSystem> getSystem() {

        return null;
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
