package tp1;

import java.util.ArrayList;
import java.util.List;

public class HumanSystem {

    public String name;

    public String id;

    public String length;

    public String volume;

    public List<Flow> flow = new ArrayList<>();



    public String toString() {

        return String.format("{\n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s,\n" +
                " \"volume\": %s, \n" +
                " \"length\": %s, \n" +
                "  \"flow\": %s\n" +
                "}",name,id,volume,length,flow.toString());
    }
}
