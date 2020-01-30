package impl;

import java.util.Vector;

import stockage.Nuplet;
import impl.NupletInt;

public class RestrictionInt {

	public Nuplet[] egalite(Nuplet[] t, int att, Object v2) {
		Vector<NupletInt> v = new Vector<NupletInt>();
		for(int i=0;i<t.length;i++){
			NupletInt temp = (NupletInt) t[i];
			if((byte)(temp.getAtt(att)) == (byte)v2){
				v.addElement(temp);
			}
		}
		NupletInt[] ret = new NupletInt[v.size()];
		for(int i=0;i<v.size();i++)
			ret[i] = v.elementAt(i);
		return ret;
	}
	
	public Nuplet[] superieur(Nuplet[] t, int att, Object v2) {
		Vector<NupletInt> v = new Vector<NupletInt>();
		for(int i=0;i<t.length;i++){
			NupletInt temp = (NupletInt) t[i];
			if((byte)(temp.getAtt(att)) > (byte)v2){
				v.addElement(temp);
			}
		}
		NupletInt[] ret = new NupletInt[v.size()];
		for(int i=0;i<v.size();i++)
			ret[i] = v.elementAt(i);
		return ret;	
	}
	
	public Nuplet[] inferieur(Nuplet[] t, int att, Object v2) {
		Vector<NupletInt> v = new Vector<NupletInt>();
		for(int i=0;i<t.length;i++){
			NupletInt temp = (NupletInt) t[i];
			if((byte)(temp.getAtt(att)) < (byte)v2){
				v.addElement(temp);
			}
		}
		NupletInt[] ret = new NupletInt[v.size()];
		for(int i=0;i<v.size();i++)
			ret[i] = v.elementAt(i);
		return ret;
	}
}

/*
	
*/
