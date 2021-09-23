package tp1;

public class Vein_Artery_data extends HasTag{



    public String name;

    public String id;

    public String startRadius;

    public String endRadius;

    public String length;


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
