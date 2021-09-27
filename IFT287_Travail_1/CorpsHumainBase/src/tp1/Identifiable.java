package tp1;

import org.xml.sax.*;

import javax.json.*;
import javax.xml.namespace.QName;

public abstract class Identifiable {

	protected String type;
	protected String id;
	protected String name;

	protected Identifiable(Attributes attributes) {

		type=attributes.getValue("type");
		name = attributes.getValue("name");
		id = attributes.getValue("id");


		if(name==null){
			 name="";
		}
		if(id==null) {
			id= "";
		}

	}

	protected Identifiable(JsonObject object) {


		if (object.containsKey("name"))
			name = object.getString("name");
		if (object.containsKey("id"))
			id = object.getString("id");

		if (name == null)
			name = "";
		if (id == null)
			id = "";
	}

}
