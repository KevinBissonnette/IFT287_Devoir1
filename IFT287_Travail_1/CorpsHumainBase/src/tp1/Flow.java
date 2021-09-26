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
		for (JsonValue connectible : (JsonArray) flows.get("Connectible"))
			connectibles.add(new Connectible((JsonObject) connectible));

		for (JsonValue connection : (JsonArray) flows.get("Connections"))
			connections.add(new Connection((JsonObject) connection));
	}

	//Permet d'ajouter un connectible
	public void addConnectible(Attributes attributes, String type) {
		connectibles.add(new Connectible(attributes, type));
	}

	//Permet d'ajouter une connexion
	public void addConnection(Attributes attributes) {
		connections.add(new Connection(attributes));
	}

	//Permet d'obtenir l'index de la dernière connexion
	public Connection getConnection() {
		return connections.get(connections.size() - 1);
	}

	//Override la fonction de convertable pour la convertir avec le json generator
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
		for (Connection c : connections)
			c.convertJson(jsonGenerator);
		jsonGenerator.writeEnd();

		jsonGenerator.writeEnd();

	}
	//Override la fonction de convertable pour la convertion en xml
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
