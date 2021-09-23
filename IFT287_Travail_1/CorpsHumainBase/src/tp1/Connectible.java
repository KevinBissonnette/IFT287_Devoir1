package tp1;

import java.util.ArrayList;
import java.util.List;

public class Connectible {

    public List<Vein_Artery_data> circulatoire= new ArrayList<>();
    public List<Atrium_Ventricule_data> coeur = new ArrayList<>();
    public List<Tract> pipe = new ArrayList<>();
    public List<Duct> ventre = new ArrayList<>();

    public HumanSystem salivaryDuct;
    public Organ organ;

    public String toString() {

        List list = null;

        if( circulatoire.size()>0)
            list = circulatoire;
        else if(coeur.size()>0)
            list = coeur;
        else if(pipe.size()>0)
            list = pipe;
        else if(ventre.size()>0)
            list = ventre;
        if(list != null)
            return list.toString();

        return null;
    }

}
