package tp1;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.json.*;
import javax.json.stream.*;
import java.util.*;

public class Connection implements Convertable {

	private String id;

	private List<Link> link ;
	//Constructeur de Connection, on attribue le id et on initialise la liste de lien
	public Connection(Attributes attributes) {
		link = new ArrayList<>();
		id = attributes.getValue("id");
	}
	//Constructeur de Connection avec jsonObject, on attribue le id et on initialise la liste de lien
	public Connection(JsonObject connection) {
		link = new ArrayList<>();
		id = connection.getString("id");

		for (JsonValue x : (JsonArray) connection.get("to"))
			link.add(new Link((JsonObject) x));
	}

	public void addLink(Attributes attributes) {
		this.link.add(new Link(attributes));
	}

	//Convertion en json selon la diapositive 37 du ppw Représentation des données Framework
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

		//code inspiré des notes de cours Représentation des données Framework diapo 29 et le site web https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
		Element xmlConnection = document.createElement("Connection");
		xmlConnection.setAttribute("id", id);

		for (Link lien : link)
			lien.convertXML(document, xmlConnection);
		element.appendChild(xmlConnection);

	}
}
