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

	public Connectible(Attributes attributes, String type) {
		super(attributes);
		this.type = type;
		this.volume = attributes.getValue("volume");
		this.length = attributes.getValue("length");
		this.startRadius = attributes.getValue("startRadius");
		this.endRadius = attributes.getValue("endRadius");
	}

	public Connectible(JsonObject connectible) {

		super(connectible);

		if (connectible.containsKey("type")) {
			type = connectible.getString("type");
		}

		if (connectible.containsKey("volume")) {
			volume = connectible.getString("volume");
		}
		if (connectible.containsKey("length")) {
			length = connectible.getString("length");
		}
		if (connectible.containsKey("startRadius")) {
			startRadius = connectible.getString("startRadius");
		}
		if (connectible.containsKey("endRadius")) {
			endRadius = connectible.getString("endRadius");

		}
	}


	@Override
	public void convertJson(JsonGenerator jsonGenerator) {
		jsonGenerator.writeStartObject()
				.write("type", type)
				.write("name", name)
				.write("id", id);
		if (volume != null) {
			jsonGenerator.write(name, volume);
		}
		if (length != null) {
			jsonGenerator.write(name, length);
		}
		if (startRadius != null) {
			jsonGenerator.write(name, startRadius);
		}
		if (endRadius != null) {
			jsonGenerator.write(name, endRadius);
		}
		jsonGenerator.writeEnd();
	}

	@Override
	public void convertXML(Document document, Element element) {

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
