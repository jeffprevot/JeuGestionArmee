/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import unitesDeCombat.armee.Propriete;
import unitesDeCombat.armee.Soldat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import utils.SoldatBuilderArgumentException;

/**
 *
 * @author jeff
 */
public class SoldatControler {

    private final Map<String, List<Propriete>> proprieteMap;
    private final Map<String, List<String>> nomsMap;
    private final Random random;

    public SoldatControler(Map<String, List<Propriete>> proprieteMap, Map<String, List<String>> nomsMap) {
        this.proprieteMap = proprieteMap;
        this.nomsMap = nomsMap;
        
        random = new Random();
    }

//    public Propriete createPropriete(String nom) throws CloneNotSupportedException {
//                
//        for (String type : proprieteMap.keySet()) {
//            for (Propriete ppt : proprieteMap.get(type)) {
//                if (nom.equalsIgnoreCase(ppt.getNom())) {
//                    return ppt.clone();
//                }
//            }
//        }
//        
//        return null;
//    }

    public Propriete createRandomProprieteFromType(Map<String, List<Propriete>> propByType, String type) {
        
        List<Propriete> values = propByType.get(type);
        Propriete prop = (Propriete) values.get(random.nextInt(values.size()));
        return prop;
    }

    public Soldat createRandomSoldat() throws SoldatBuilderArgumentException {
        
        List<Propriete> propList = new ArrayList<>();
        Propriete race = createRandomProprieteFromType(proprieteMap, "race");
        propList.add(race);
        propList.add(createRandomProprieteFromType(proprieteMap, "classe"));
        
        List<String> noms = nomsMap.get(race.getNom());
        
        int i = random.nextInt(noms.size());
        String nom = noms.get(i);
        
        return new Soldat(propList, nom);
    }

}
