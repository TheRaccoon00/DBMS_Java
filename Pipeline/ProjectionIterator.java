package Pipeline;

import impl.NupletInt;
import stockage.Nuplet;

public class ProjectionIterator implements Requete {
	
	private Requete reqIn = null;
	private int[] atts;
	
	public ProjectionIterator(Requete reqIn, int[] atts) {
		this.reqIn = reqIn;
		this.atts = atts;
	}
	
	@Override
	public void open() {
		this.reqIn.open();
	}

	@Override
	public Nuplet next() {
		Nuplet n = this.reqIn.next();
		if (n != null) {
			byte tab[] = new byte[this.atts.length];
			int k = 0;
			for(int j: this.atts) {
				tab[k] = (byte) n.getAtt(j);
				k++;
			}
			return new NupletInt(tab);
		}else {
			return null;
		}
		
	}

}
