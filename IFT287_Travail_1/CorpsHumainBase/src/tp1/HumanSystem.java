package tp1;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.json.*;
import javax.json.stream.*;
import java.util.*;


public class HumanSystem extends Identifiable implements Convertable {

	private List<Flow> flows = new ArrayList<>();

	public HumanSystem(Attributes attributes) {
// TODO
// Changer pour if else if
		super(attributes);
		this.type = attributes.getValue("type");
	}


	public HumanSystem(JsonObject jsonObject) {
		super(jsonObject);
		type = jsonObject.getString("type");

		//TODO
		// changer pour for si marche pas
		var array = ((JsonArray) jsonObject.get("Flow")).iterator();
		while (array.hasNext()) {
			this.flows.add(new Flow((JsonObject) array.next()));
		}
	}

	public void addFlow(Attributes attrs) {
		this.flows.add(new Flow(attrs));
	}

	public Flow last() {
		return this.flows.get(this.flows.size() - 1);
	}

	@Override
	public void convertJson(JsonGenerator jsonGenerator) {
		jsonGenerator.writeStartObject()
				.write("name", name)
				.write("id", id)
				.write("type", type);

		jsonGenerator.writeStartArray("Flow");
		for (var i = 0; i < flows.size(); i++)
			flows.get(i).convertJson(jsonGenerator);
		jsonGenerator.writeEnd();

		jsonGenerator.writeEnd();
	}

	@Override
	public void convertXML(Document document, Element element) {

		Element root = document.createElement("System");

		root.setAttribute("name", name);
		root.setAttribute("id", id);
		root.setAttribute("type", type);

		for (var i = 0; i < flows.size(); i++)
			flows.get(i).convertXML(document, root);
		element.appendChild(root);
	}
}
