package tp1;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.json.*;
import javax.json.stream.*;
import java.util.*;

public class Corps {

	private String bodyName;
	private String bodyID;

	private List<HumanSystem> humanSystems = new ArrayList<>();
	private List<Organ> organs = new ArrayList<>();

	public Corps(Attributes attributes) {
		for (var i = 0; i < attributes.getLength(); i++) {
			if (attributes.getQName(i) == "bodyName")
				bodyName = attributes.getValue(i);
			else
				bodyID = attributes.getValue(i);
		}
	}

	public Corps(JsonObject corps) {
		JsonObject corpsJsonObject = corps.getJsonObject("MainBody");
		bodyName = corpsJsonObject.getString("bodyName");
		bodyID = corpsJsonObject.getString("bodyID");

		for (var system : (JsonArray) corpsJsonObject.get("Systems"))
			humanSystems.add(new HumanSystem((JsonObject) system));
		for (var organ : (JsonArray) corpsJsonObject.get("Organs"))
			organs.add(new Organ((JsonObject) organ));
	}

	public void addSystem(Attributes attributes) {
		humanSystems.add(new HumanSystem(attributes));
	}

	public void addOrgan(Attributes attributes) {
		organs.add(new Organ(attributes));
	}

	public HumanSystem getLastSystem() {
		return humanSystems.get(humanSystems.size() - 1);
	}

	public void convertJson(JsonGenerator jsonGenerator) {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStartObject("MainBody")
				.write("bodyName", bodyName)
				.write("bodyID", bodyID);

		jsonGenerator.writeStartArray("Systems");
		for (var i = 0; i < humanSystems.size(); i++)
			humanSystems.get(i).convertJson(jsonGenerator);

		jsonGenerator.writeEnd();

		jsonGenerator.writeStartArray("Organs");
		for (var i = 0; i < organs.size(); i++)
			organs.get(i).convertJson(jsonGenerator);

		jsonGenerator.writeEnd()
				.writeEnd()
				.writeEnd();
	}

	public void convertXML(Document document) {
		Element mainBody = document.createElement("MainBody");
		mainBody.setAttribute("bodyName", bodyName);
		mainBody.setAttribute("bodyID", bodyID);
		document.appendChild(mainBody);

		Element xmlSystems = document.createElement("Systems");
		for (var i = 0; i < humanSystems.size(); i++)
			humanSystems.get(i).convertXML(document, xmlSystems);
		mainBody.appendChild(xmlSystems);

		Element xmlOrgans = document.createElement("Organs");
		for (var i = 0; i < organs.size(); i++)
			organs.get(i).convertXML(document, xmlOrgans);
		mainBody.appendChild(xmlOrgans);
	}

}
