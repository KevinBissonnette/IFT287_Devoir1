package tp1;

import org.w3c.dom.*;
import org.xml.sax.Attributes;

import javax.json.*;
import javax.json.stream.*;

public class Organ extends Identifiable implements Convertable {

	private String systemID;

	//Constructeur Organ pour l'attribution des attributs basé sur les diapositives 41 du ppw Représentation des données Framework
	public Organ(Attributes attributes) {
		super(attributes);
	 	this.id=attributes.getValue("id");
		this.name=attributes.getValue("name");
		this.systemID=attributes.getValue("systemID");

	}
	//Constructeur Organ pour le jsonObject basé sur les diapositives 41 du ppw Représentation des données Framework
	public Organ(JsonObject organ) {
		super(organ);
		name = organ.getString("name");
		id = organ.getString("id");
		systemID = organ.getString("systemID");
	}
	//Convertion en json selon la diapositive 37 du ppw Représentation des données Framework
	public void convertJson(JsonGenerator jsonGenerator) {
		jsonGenerator.writeStartObject()
				.write("name", name)
				.write("id", id)
				.write("systemID", systemID)
				.writeEnd();
	}
	//Convertion en xml selon la diapositive 29 du ppw Représentation des données Framework
	public void convertXML(Document document, Element parent) {
		Element Organ = document.createElement("Organ");

		Organ.setAttribute("name", name);
		Organ.setAttribute("id", id);
		Organ.setAttribute("systemID", systemID);

		parent.appendChild(Organ);
	}

}





