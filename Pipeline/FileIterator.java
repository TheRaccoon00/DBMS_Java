package Pipeline;

import java.io.RandomAccessFile;

import impl.NupletInt;
import stockage.Nuplet;

public class FileIterator implements Requete {
	
	private String fp;
	private int nupletSize;
	private RandomAccessFile f;
	private int pos;

	public FileIterator(String filePath, int nupletSize){
		this.fp = filePath;
		this.nupletSize = nupletSize;
	}

	@Override
	public void open() {
		try {
			this.f = new RandomAccessFile(this.fp, "r");
			this.pos = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Nuplet next() {
		try {
			byte[] b = new byte[nupletSize];
			this.f.seek(pos*this.nupletSize);
			if (this.f.read(b) != -1) {
				this.pos++;
				return new NupletInt(b);
			}else {
				this.f.close();
				return null;
			}
		}catch (Exception e) {
			return null;
		}
	}

}
