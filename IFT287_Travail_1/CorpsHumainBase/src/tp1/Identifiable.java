package tp1;

import org.xml.sax.*;

import javax.json.*;

public abstract class Identifiable {

	protected String type;
	protected String id;
	protected String name;

	protected Identifiable(Attributes attributes) {

		name = attributes.getValue("name");
		id = attributes.getValue("id");


	}

	protected Identifiable(JsonObject object) {

		name = object.getString("name");
		id = object.getString("id");
	}

}
