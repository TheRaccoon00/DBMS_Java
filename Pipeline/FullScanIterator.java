package Pipeline;
import stockage.Nuplet;

public class FullScanIterator implements Requete {
	
	Requete reqIn = null;

	public FullScanIterator(Requete reqIn) {
		this.reqIn = reqIn;
	}
	
	@Override
	public void open() {
		this.reqIn.open();
	}

	@Override
	public Nuplet next() {
		return this.reqIn.next();
	}
}
