package tp1;

import java.util.jar.Attributes;

public class Vein_Artery_data extends HasTag{



    public String name;

    public String id;

    public String startRadius;

    public String endRadius;

    public String length;


    public Vein_Artery_data(Attributes attributes){
       // p.tagName = qName;
        this.name = attributes.getValue("name");
        this.id = attributes.getValue("id");
        this.startRadius = attributes.getValue("startRadius");
        this.endRadius = attributes.getValue("endRadius");
        this.length = attributes.getValue("length");
       // system.flow.get(FlowIndex-1).con.circulatoire.add(temp);
    }
    public String toString() {
        return String.format("{\n" +
                "  \"tagName\": %s, \n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s,\n" +
                "  \"startRadius\": %s\n" +
                "  \"endRadius\": %s\n" +
                "  \"length\": %s\n" +
                "}",tagName,name,id,startRadius,endRadius,length);

    }


}
