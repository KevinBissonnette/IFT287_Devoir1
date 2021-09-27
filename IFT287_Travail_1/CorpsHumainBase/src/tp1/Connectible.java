package tp1;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.json.*;
import javax.json.stream.*;

public class Connectible extends Identifiable implements Convertable {


	private String volume;
	private String length;
	private String startRadius;
	private String endRadius;
	//Constructeur Connectible, on attributs les attributs aux types
	public Connectible(Attributes attributes, String type) {
		super(attributes);
		this.type = type;
		this.volume = attributes.getValue("volume");
		this.length = attributes.getValue("length");
		this.startRadius = attributes.getValue("startRadius");
		this.endRadius = attributes.getValue("endRadius");
	}
	//Constructeur connectible avec le jsonObject, on doit réattribué les attributs
	public Connectible(JsonObject connectible) {

		super(connectible);

		if (connectible.containsKey("type")) {
			type = connectible.getString("type");
		}
		else if (connectible.containsKey("volume")) {
			volume = connectible.getString("volume");
		}
		else if (connectible.containsKey("length")) {
			length = connectible.getString("length");
		}
		else if (connectible.containsKey("startRadius")) {
			startRadius = connectible.getString("startRadius");
		}
		else if (connectible.containsKey("endRadius")) {
			endRadius = connectible.getString("endRadius");
		}
	}


	@Override
	public void convertJson(JsonGenerator jsonGenerator) {

		//code inspiré des notes de cours Représentation des données Framework diapo 37
		jsonGenerator.writeStartObject()
				.write("type", type)
				.write("name", name)
				.write("id", id);
		if (volume != null) {
			jsonGenerator.write("volume", volume);
		}
		if (length != null) {
			jsonGenerator.write("length", length);
		}
		if (startRadius != null) {
			jsonGenerator.write("startRadius", startRadius);
		}
		if (endRadius != null) {
			jsonGenerator.write("endRadius", endRadius);
		}
		jsonGenerator.writeEnd();
	}

	@Override
	public void convertXML(Document document, Element element) {

		//code inspiré des notes de cours Représentation des données Framework diapo 29
		Element root = document.createElement(type);

		root.setAttribute("name", name);
		root.setAttribute("id", id);


		if (volume != null) {
			root.setAttribute("volume", volume);
		}
		if (length != null) {
			root.setAttribute("length", length);
		}
		if (startRadius != null) {
			root.setAttribute("startRadius", startRadius);
		}
		if (endRadius != null) {
			root.setAttribute("endRadius", endRadius);
		}
		element.appendChild(root);
	}
}
