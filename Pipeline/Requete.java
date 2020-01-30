package Pipeline;

import stockage.Nuplet;

public interface Requete {
	public void open();
	public Nuplet next();
}
