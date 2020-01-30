package Pipeline;

import stockage.Nuplet;

public class RestrictionEgaliteIterator implements Requete {
	
	private Requete reqIn = null;
	private int att;
	private Object v;
	
	public RestrictionEgaliteIterator(Requete reqIn, int att, Object v) {
		this.reqIn = reqIn;
		this.att = att;
		this.v = v;
	}
	
	@Override
	public void open() {
		this.reqIn.open();
	}

	@Override
	public Nuplet next() {
		Nuplet n = this.reqIn.next();
		while(n != null) {
			if ((byte) n.getAtt(att) == (byte) this.v) {
				return n;
			}
			n = this.reqIn.next();
		}
		return null;
	}

}
