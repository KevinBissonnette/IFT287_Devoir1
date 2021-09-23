package tp1;

public class Duct extends HasTag{

    public String name;

    public String id;



    public String toString() {
        return String.format("{\n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s,\n" +
                "}",name,id);

    }
}
