package tp1;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.json.*;
import javax.json.stream.*;
import java.util.*;


public class HumanSystem extends Identifiable implements Convertable {

	private List<Flow> flows;

	//Constructeur qui donne les attributs au système humain
	public HumanSystem(Attributes attributes) {

		super(attributes);
		flows= new ArrayList<>();
		this.type = attributes.getValue("type");
	}

	//Constructeur avec un jsonObject boboff qui donne les attributs au système humain
	public HumanSystem(JsonObject jsonObject) {
		super(jsonObject);
		type = jsonObject.getString("type");
		flows= new ArrayList<>();
		//TODO
		// changer pour for si marche pas
		var array = ((JsonArray) jsonObject.get("Flow")).iterator();
		while (array.hasNext()) {
			this.flows.add(new Flow((JsonObject) array.next()));
		}
	}
    //Ajout d'un flow a un system
	public void addFlow(Attributes attrs) {
		this.flows.add(new Flow(attrs));
	}

	//Obtient la valeur du dernier index du flow
	public Flow last() {
		return this.flows.get(this.flows.size() - 1);
	}

	//Override la fonction de convertable pour la convertir avec le json generator
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

	//Override la fonction de convertable pour la convertir en xml
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
