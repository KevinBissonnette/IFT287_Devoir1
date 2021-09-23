package tp1;

public class Flow {

    public Connectible con;

    public String name;

    public String id;

    public String toString() {
        return String.format("{\n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s,\n" +
                "  \"connectible\": %s\n" +
                "}",name,id,con.toString());

    }
}
