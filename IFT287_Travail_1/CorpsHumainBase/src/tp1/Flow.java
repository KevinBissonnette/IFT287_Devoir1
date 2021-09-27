package tp1;

import org.w3c.dom.*;
import org.xml.sax.Attributes;

import javax.json.*;
import javax.json.stream.*;
import java.util.*;

public class Flow extends Identifiable implements Convertable {

	private List<Connectible> connectibles;
	private List<Connection> connections ;

	//Constructeur qui initialise nos variables
	public Flow(Attributes attributes) {

		super(attributes);
		connectibles = new ArrayList<>();
		connections = new ArrayList<>();
	}

	//Constructeur avec jsonObject qui initialise nos variables
	public Flow(JsonObject flows) {

		super(flows);
		connectibles = new ArrayList<>();
		connections = new ArrayList<>();
		for (JsonValue connectible : (JsonArray) flows.get("Connectible"))
			connectibles.add(new Connectible((JsonObject) connectible));

		for (JsonValue connection : (JsonArray) flows.get("Connections"))
			connections.add(new Connection((JsonObject) connection));
	}

	//Permet d'ajouter un connectible
	public void addConnectible(Attributes attributes, String type) { this.connectibles.add(new Connectible(attributes, type));
	}

	//Permet d'ajouter une connexion
	public void addConnection(Attributes attributes) {
		this.connections.add(new Connection(attributes));
	}

	//Permet d'obtenir l'index de la dernière connexion
	public Connection getConnection() {
		return connections.get(connections.size() - 1);
	}

	//Override la fonction de convertable pour la convertir avec le json generator
	@Override
	public void convertJson(JsonGenerator jsonGenerator) {

		//Convertion en json selon la diapositive 37 du ppw Représentation des données Framework
		jsonGenerator.writeStartObject()
				.write("name", name)
				.write("id", id);


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
	//Code provient de https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
		Element root = document.createElement("Flow");

		root.setAttribute("name", name);
		root.setAttribute("id", id);

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
