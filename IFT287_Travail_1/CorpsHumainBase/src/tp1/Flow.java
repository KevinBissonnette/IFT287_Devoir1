package tp1;
import org.xml.sax.Attributes;

public class Flow {

    public Connectible con;

    public String name;

    public String id;

    public Flow(Attributes attributes){
       // this.con=attributes.getValue("con");
        this.name=attributes.getValue("name");
        this.id=attributes.getValue("name");
    }
    public String toString() {
        return String.format("{\n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s,\n" +
                "  \"connectible\": %s\n" +
                "}",name,id,con.toString());

    }
}
