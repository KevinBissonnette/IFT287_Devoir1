package tp1;

import org.w3c.dom.*;

import javax.json.stream.*;

public interface Convertable {
	//Interface servant à implémenter les deux fonctions dans les classes enfants.
	public void convertJson(JsonGenerator jsonGenerator);

	public void convertXML(Document document, Element element);

}
