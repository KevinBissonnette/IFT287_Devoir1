package tp1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

public class HumanSystem {

    public String name;

    public String id;

    public String type;

    public List<Flow> flow;

    public HumanSystem(Attributes attributes){
        flow = new ArrayList<Flow>();
        this.name = attributes.getValue("name");
        this.id = attributes.getValue("id");
        this.type = attributes.getValue("type");

    }

    public String toString() {

        return String.format("{\n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s,\n" +
                " \"volume\": %s, \n" +
                " \"length\": %s, \n" +
                "  \"flow\": %s\n" +
                "}",name,id,flow.toString());
    }
}
