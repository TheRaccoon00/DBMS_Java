package impl;

import java.util.Vector;

import stockage.Nuplet;

public class ProjectionImpl {
	public Nuplet[] project(Nuplet[] t, int[] atts) {
		Vector<Nuplet> v = new Vector<Nuplet>();
		for(int i=0;i<t.length;i++){
			byte tab[] = new byte[atts.length];
			int k = 0;
			for(int j: atts) {
				tab[k] = (byte) t[i].getAtt(j);
				k++;
			}
			Nuplet temp = new NupletInt(tab);
			v.addElement(temp);
		}
		Nuplet[] ret = new Nuplet[v.size()];
		for(int i=0;i<v.size();i++)
			ret[i] = v.elementAt(i);
		return ret;
	}
}
