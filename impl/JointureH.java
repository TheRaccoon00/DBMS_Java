package impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import operateurs.Jointure;
import stockage.Nuplet;

public class JointureH implements Jointure{
	
	@Override 
    public Nuplet[] jointure(Nuplet[] t1, Nuplet[] t2, int att1,  int att2) {
        Vector<Nuplet> result = new Vector<Nuplet>();
        Map<Byte, List<Nuplet>> map = new HashMap<>();

        for (Nuplet t : t1) {
            Vector<Nuplet> v = (Vector<Nuplet>) map.getOrDefault(t.getAtt(att1), new Vector<Nuplet>());
            v.add(t);
            map.put((Byte)t.getAtt(att1), v);
        }
 
        for (Nuplet t : t2) {
            List<Nuplet> lst = map.get(t.getAtt(att2));
            if (lst != null) {
                lst.stream().forEach(r -> {
                	byte tab[] = new byte[r.size()+t.size()-1];
                	int k = 0;
					tab[0] = (byte) r.getAtt(att2);
					for (int i=0;i<t.size();i++) {
						if (i != att1) {
							tab[i+1-k] = (byte) t.getAtt(i);
						}else {
							k++;
						}
					}
					k = 0;
					for (int i=0;i<r.size();i++) {
						if (i != att2) {
							tab[i+t.size()-k] = (byte) r.getAtt(i);
						}else {
							k++;
						}
					}
					Nuplet temp = new NupletInt(tab);
					result.addElement(temp);
                });
            }
        }
        
        Nuplet[] ret = new Nuplet[result.size()];
		for(int i=0;i<result.size();i++)
			ret[i] = result.elementAt(i);
		
        return ret;
    }
    
}
