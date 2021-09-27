package tp1;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.json.*;
import javax.json.stream.*;

public class Link extends Identifiable implements Convertable {


	public Link(Attributes attributes) {
		super(attributes);
	}

	public Link(JsonObject link) {
		super(link);
	}

	//Convertion en json selon la diapositive 37 du ppw Représentation des données Framework
	@Override
	public void convertJson(JsonGenerator jsonGenerator) {

		jsonGenerator.writeStartObject()
				.write("id", id);
		jsonGenerator.writeEnd();
	}

	@Override
	public void convertXML(Document document, Element element) {
		//Code provient de https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
		Element root = document.createElement("To");
		root.setAttribute("id", id);

		element.appendChild(root);

	}
}
