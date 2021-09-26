package tp1;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.json.*;
import javax.json.stream.*;
import java.util.*;

public class Connection implements Convertable {

	private String id;

	private List<Link> link ;

	public Connection(Attributes attributes) {
		link = new ArrayList<>();
		id = attributes.getValue("id");
	}

	public Connection(JsonObject connection) {
		link = new ArrayList<>();
		id = connection.getString("id");

		for (JsonValue x : (JsonArray) connection.get("to"))
			link.add(new Link((JsonObject) x));
	}

	public void addLink(Attributes attributes) {
		this.link.add(new Link(attributes));
	}

	@Override
	public void convertJson(JsonGenerator jsonGenerator) {

		jsonGenerator.writeStartObject()
				.write("id", id)
				.writeStartArray("to");
		for (Link l : link)
			l.convertJson(jsonGenerator);

		jsonGenerator.writeEnd()
				.writeEnd();
	}

	@Override
	public void convertXML(Document document, Element element) {

		Element xmlConnection = document.createElement("Connection");
		xmlConnection.setAttribute("id", id);

		for (Link l : link)
			l.convertXML(document, xmlConnection);
		element.appendChild(xmlConnection);

	}
}
