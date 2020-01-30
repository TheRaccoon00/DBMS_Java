package impl;

import java.util.Vector;

import operateurs.Jointure;
import stockage.Nuplet;

public class JointureS implements Jointure{
	
	public Nuplet[] jointure(Nuplet[] t1,Nuplet[] t2, int att1, int att2) {        
        sort(t1, 0, t1.length - 1, att1);
        sort(t2, 0, t2.length - 1, att2);    
        return join(t1,att1,t2,att2);
	}
	
	void merge(Nuplet arr[], int gauche, int milieu, int droite, int att) 
    { 
        int n1 = milieu - gauche + 1; 
        int n2 = droite - milieu; 
        Nuplet L[] = new Nuplet [n1]; 
        Nuplet R[] = new Nuplet [n2]; 
        
        for (int i=0; i<n1; ++i) 
            L[i] = arr[gauche + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[milieu + 1+ j]; 
        
        int i = 0, j = 0, k = gauche; 
        
        while (i < n1 && j < n2){ 
            if ((byte) L[i].getAtt(att) <= (byte) R[j].getAtt(att)){ 
                arr[k] = L[i]; 
                i++; 
            }else{ 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
        while (i < n1){ 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while (j < n2){ 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    void sort(Nuplet arr[], int gauche, int droite, int att) 
    { 
        if (gauche < droite) 
        { 
            int milieu = (gauche+droite)/2; 
            sort(arr, gauche, milieu, att); 
            sort(arr , milieu+1, droite, att); 
            merge(arr, gauche, milieu, droite, att); 
        } 
    }
    
    Nuplet[] join(Nuplet t1[], int att1, Nuplet t2[], int att2) {
    	int size_max = Math.max(t1.length,t2.length);
    	int element1 = 0;
    	int element2 = 0;
    	int old_pos = -1;
    	
    	Vector<Nuplet> result = new Vector<Nuplet>();
    	while (element1 < size_max && element2 < size_max) {
    		if (old_pos == -1) {
    			old_pos = element2;
    		}
    		if ((byte)t1[element1].getAtt(att1) == (byte)t2[element2].getAtt(att2)) {
    			byte tab[] = new byte[t1[element1].size()+t2[element2].size()-1];
    			tab[0] = (byte) t1[element1].getAtt(att1);
    			int k = 0;
    			for (int j = 0 ; j < t1[element1].size() ; j++) {
					if (j != att1) {
						tab[j+1-k] = (byte) t1[element1].getAtt(j);
					}else {
						k++;
					}
				}
    			k = 0;
    			for (int j = 0 ; j < t2[element2].size() ; j++) {
					if (j != att2) {
						tab[j+t1[element1].size()-k] = (byte) t2[element2].getAtt(j);
					}else {
						k++;
					}
				}
    			Nuplet temp = new NupletInt(tab);
				result.addElement(temp);
				element2++;
				
    		}else {
    			if ((byte)t1[element1].getAtt(att1) > (byte)t2[element2].getAtt(att2)) {
    				element2++;
    			}else {
    				element1++;
    				element2 = old_pos;
    				old_pos = -1;
    			}
    		}
    		
    	}
    	
    	Nuplet[] ret = new Nuplet[result.size()];
		for(int i=0;i<result.size();i++)
			ret[i] = result.elementAt(i);
		return ret;
    }	

}
