package tp1;

import org.w3c.dom.*;
import org.xml.sax.Attributes;

import javax.json.*;
import javax.json.stream.*;

public class Organ extends Identifiable implements Convertable {

	private String systemID;

	public Organ(Attributes attributes) {
		super(attributes);
		for (var i = 0; i < attributes.getLength(); i++) {
			var att = attributes.getQName(i);
			var value = attributes.getValue(i);
			if (att.equalsIgnoreCase("name"))
				name = value;

			else if (att.equalsIgnoreCase("id"))
				id = value;

			else if (att.equalsIgnoreCase("systemID"))
				systemID = value;
		}
	}

	public Organ(JsonObject organ) {
		super(organ);
		name = organ.getString("name");
		id = organ.getString("id");
		systemID = organ.getString("systemID");
	}

	public void convertJson(JsonGenerator jsonGenerator) {
		jsonGenerator.writeStartObject()
				.write("name", name)
				.write("id", id)
				.write("systemID", systemID)
				.writeEnd();
	}

	public void convertXML(Document document, Element parent) {
		Element Organ = document.createElement("Organ");

		Organ.setAttribute("name", name);
		Organ.setAttribute("id", id);
		Organ.setAttribute("systemID", systemID);

		parent.appendChild(Organ);
	}

}





