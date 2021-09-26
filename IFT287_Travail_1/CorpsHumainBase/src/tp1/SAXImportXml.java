package tp1;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;

public class SAXImportXml extends DefaultHandler {

	//private StringBuilder currentValue = new StringBuilder();


	private List<Corps> corps;


	@Override
	public void startDocument() {
		// System.out.println("Start Document");
		corps = new ArrayList<Corps>();

	}

	@Override
	public void endDocument() {
		//  System.out.println("End Document");
	}

	public void startElement(String uri, String localName,
							 String qName, Attributes attributes) {

		switch (qName) {
			case "MainBody":
				corps.add(new Corps(attributes));
				break;
			case "Systems":
				//rien
				break;
			case "System":
				corps.get(corps.size() - 1).addSystem(attributes);
				break;
			case "Flow":
				corps.get(corps.size() - 1).getLastSystem().addFlow(attributes);
				break;
			//case "Connectible" :
			case "Connections":
				//rien
				break;
			case "Connection":
				corps.get(corps.size() - 1).getLastSystem().last().addConnection(attributes);
				break;
			case "to":
				corps.get(corps.size() - 1).getLastSystem().last().getConnection().addLink(attributes);
				break;
			case "Organs":
				//rien
				break;
			case "Organ":
				corps.get(corps.size() - 1).addOrgan(attributes);
				break;
			default:
				corps.get(corps.size() - 1).getLastSystem().last().addConnectible(attributes, qName);
				break;
		}
	}


	public void endElement(String uri, String localName, String qName) {


	}


	public Corps getBody() {
		return corps.get(corps.size() - 1);
	}

}
