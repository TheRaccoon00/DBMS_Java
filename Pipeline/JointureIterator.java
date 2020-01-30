package Pipeline;

import impl.NupletInt;
import stockage.Nuplet;

public class JointureIterator implements Requete {

	private Requete reqIn1;
	private Requete reqIn2;
	private int att1;
	private int att2;
	private Nuplet n = null;
	
	public JointureIterator(Requete reqIn1, Requete reqIn2, int att1, int att2) {
		this.reqIn1 = reqIn1;
		this.reqIn2 = reqIn2;
		this.att1 = att1;
		this.att2 = att2;
	}
	
	
	@Override
	public void open() {
		this.reqIn1.open();
		this.reqIn2.open();
		this.n = this.reqIn1.next();
	}

	@Override
	public Nuplet next() {
		Nuplet m = this.reqIn2.next();
		while (m != null) {
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
				return new NupletInt(tab);
			}
			m = this.reqIn2.next();
		}
		this.n = this.reqIn1.next();
		if (n != null) {
			this.reqIn2.open();
			return this.next();
		}else {
			return null;
		}
	}

}
