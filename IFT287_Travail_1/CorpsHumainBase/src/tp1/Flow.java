package tp1;

import org.w3c.dom.*;
import org.xml.sax.Attributes;

import javax.json.*;
import javax.json.stream.*;
import java.util.*;

public class Flow extends Identifiable implements Convertable {

	private List<Connectible> connectibles = new ArrayList<>();
	private List<Connection> connections = new ArrayList<>();

	public Flow(Attributes attributes) {
		// this.con=attributes.getValue("con");
		super(attributes);
	}

	public Flow(JsonObject flows) {

		super(flows);
		for (JsonValue ctibles : (JsonArray) flows.get("Connectible"))
			connectibles.add(new Connectible((JsonObject) ctibles));

		for (JsonValue ction : (JsonArray) flows.get("Connections"))
			connections.add(new Connection((JsonObject) ction));
	}

	public void addConnectible(Attributes attributes, String type) {
		connectibles.add(new Connectible(attributes, type));
	}

	public void addConnection(Attributes attributes) {
		connections.add(new Connection(attributes));
	}

	public Connection getConnection() {
		return connections.get(connections.size() - 1);
	}

	@Override
	public void convertJson(JsonGenerator jsonGenerator) {

		jsonGenerator.writeStartObject()
				.write("id", id)
				.write("name", name);

		jsonGenerator.writeStartArray("Connectible");
		for (Convertable c : connectibles)
			c.convertJson(jsonGenerator);
		jsonGenerator.writeEnd();

		jsonGenerator.writeStartArray("Connections");
		for (Convertable c : connections)
			c.convertJson(jsonGenerator);
		jsonGenerator.writeEnd();

		jsonGenerator.writeEnd();
		//return gen.writeEnd();
	}

	@Override
	public void convertXML(Document document, Element element) {

		Element root = document.createElement("Flow");

		root.setAttribute("id", id);
		root.setAttribute("name", name);

		Element e_connectible = document.createElement("Connectibles");
		for (Convertable c : connectibles)
			c.convertXML(document, e_connectible);
		root.appendChild(e_connectible);

		Element e_connection = document.createElement("Connections");
		for (Convertable c : connections)
			c.convertXML(document, e_connection);
		root.appendChild(e_connection);

		element.appendChild(root);
	}
}
