package tp1;

public class Tract extends HasTag{

    public String name;

    public String id;

    public String startRadius;

    public String endRadius;

    public String length;

    public String volume;

    public String toString() {
        return String.format("{\n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s, \n" +
                "  \"startRadius\": %s, \n" +
                "  \"endRadius\": %s, \n" +
                "  \"length\": %s,\n" +
                "  \"volume\": %s\n" +
                "}",name,id,startRadius,endRadius,length,volume);

    }
}
