package tp1;


public class Atrium_Ventricule_data extends HasTag{

    public String name;

    public String id;

    public String volume;

    public String toString() {
        return String.format("{\n" +
                "  \"name\": %s, \n" +
                "  \"id\": %s,\n" +
                "  \"volume\": %s\n" +
                "}",name,id,volume.toString());

    }


}
