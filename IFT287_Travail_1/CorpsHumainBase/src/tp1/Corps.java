package tp1;

import java.util.ArrayList;
import org.xml.sax.Attributes;

public class Corps {


    //Notre corps possède nom et un id
    private String bodyName;
    private String bodyId;

    //Notre corps possède un HumanSystem et des Organs
    public ArrayList<HumanSystem> systems;
    private ArrayList<Organ> organs;

    //On initialise les attributs du corps et la liste d'organes et la liste de systèmes
    public Corps(Attributes attributes){

        systems=new ArrayList<HumanSystem>();
        organs=new ArrayList<Organ>();
        this.bodyName=attributes.getValue("bodyName");
        this.bodyId=attributes.getValue("bodyID");
    }

    public void AjouterSystems(Attributes attrributes){
        systems.add(new HumanSystem(attrributes));
    }

}
