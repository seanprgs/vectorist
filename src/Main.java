import boostcli.BoostCLI;
import models.Vector;

public class Main {
	public static void main(String[] args) {
		BoostCLI _ = new BoostCLI();

		Vector v1 = new Vector(4, 1);
		Vector v2 = new Vector(6, 7);
		Vector v3 = new Vector(12, 21);

		_.p(v1.toString("1"), 2);
		_.p(v2.toString("2"), 2);
		_.p(v3.toString("3"), 2);
	}
}
