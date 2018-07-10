import boostcli.BoostCLI;
import models.Vector;

public class Main {
	public static void main(String[] args) {
		BoostCLI io = new BoostCLI();

		Vector a = new Vector(4, 1);
		Vector b = new Vector(6, 7);
		Vector c = new Vector(12, 15);

		io.p(a.plot(), 2);
		io.p(b.plot(), 2);
		io.p(c.plot(), 2);
	}
}
