package tp1;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;

//La classe SAXImportXml est basé sur le site https://mkyong.com/java/how-to-read-xml-file-in-java-sax-parser/ pour avoir notre structure du SAXimport xml
public class SAXImportXml extends DefaultHandler {

	//private StringBuilder currentValue = new StringBuilder();


	private List<Corps> listCorps;


	@Override
	public void startDocument() {
		// System.out.println("Start Document");
		listCorps = new ArrayList<Corps>();

	}

	@Override
	public void endDocument() {
		//  System.out.println("End Document");
	}

	public void startElement(String uri, String localName,
							 String qName, Attributes attributes) {
	//on vérifie tous les qName du fichier XML et on l'ajoute à notre modèle orienté object au besoin
		switch (qName) {
			case "MainBody":
				listCorps.add(new Corps(attributes));
				break;
			case "Systems":
				break;
			case "System":
				listCorps.get(listCorps.size() - 1).addSystem(attributes);
				break;
			case "Flow":
				listCorps.get(listCorps.size() - 1).getLastSystem().addFlow(attributes);
				break;
			case "Connections": //aucune modification à faire
				break;
			case "Connection":
				listCorps.get(listCorps.size() - 1).getLastSystem().last().addConnection(attributes);
				break;
			case "to":
				listCorps.get(listCorps.size() - 1).getLastSystem().last().getConnection().addLink(attributes);
				break;
			case "Organs": //aucune modification à faire
				break;
			case "Organ":
				listCorps.get(listCorps.size() - 1).addOrgan(attributes);
				break;
			default:
				listCorps.get(listCorps.size() - 1).getLastSystem().last().addConnectible(attributes, qName);
				break;
		}
	}

	public void endElement(String uri, String localName, String qName) {

	}


	public Corps getBody() {
		return listCorps.get(listCorps.size() - 1);
	}

}
