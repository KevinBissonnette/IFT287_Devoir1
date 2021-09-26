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


	@Override
	public void convertJson(JsonGenerator jsonGenerator) {

		jsonGenerator.writeStartObject()
				.write("id", id);
		jsonGenerator.writeEnd();
	}

	@Override
	public void convertXML(Document document, Element element) {
		Element root = document.createElement("To");
		root.setAttribute("id", id);

		element.appendChild(root);

	}
}
