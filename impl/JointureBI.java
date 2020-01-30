package impl;

import java.util.Vector;

import operateurs.Jointure;
import stockage.Nuplet;

public class JointureBI implements Jointure{
	
	@Override
	public Nuplet[] jointure(Nuplet[] t1, Nuplet[] t2, int att1, int att2) {
		Vector<Nuplet> v = new Vector<Nuplet>();
		for(Nuplet n: t1) {
			for(Nuplet m: t2) {
				if(n.getAtt(att1) == m.getAtt(att2)) {
					byte tab[] = new byte[n.size()+m.size()-1];
                	int k = 0;
					tab[0] = (byte) n.getAtt(att1);
					for (int i=0;i<n.size();i++) {
						if (i != att1) {
							tab[i+1-k] = (byte) n.getAtt(i);
						}else {
							k++;
						}
					}
					k = 0;
					for (int i=0;i<m.size();i++) {
						if (i != att2) {
							tab[i+n.size()-k] = (byte) m.getAtt(i);
						}else {
							k++;
						}
					}
					Nuplet temp = new NupletInt(tab);
					v.addElement(temp);
				}
			}
		}
		Nuplet[] ret = new Nuplet[v.size()];
		for(int i=0;i<v.size();i++)
			ret[i] = v.elementAt(i);
		return ret;
	}
}
