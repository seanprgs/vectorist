package models;

public class Vector
{
	private Coordinates coordinates;
	private double length;

	public Vector(double x, double y) {
		coordinates = new Coordinates(x, y);
		cacheLength();
	}

	public void cacheLength() {
		length = Math.hypot(coordinates.getX(), coordinates.getY());
	}

	public double getLength() {
		return length;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
		cacheLength();
	}

	public void setX(double x) {
		coordinates.setX(x);
		cacheLength();
	}

	public void setY(double y) {
		coordinates.setY(y);
		cacheLength();
	}

	public double getX() {
		return coordinates.getX();
	}

	public double getY() {
		return coordinates.getY();
	}

	public String toString(String plotter) {
		String str = "";
		int size;

		if (coordinates.getX() >= coordinates.getY())
			size = (int) (Math.round(coordinates.getX()) + 2);
		else
			size = (int) (Math.round(coordinates.getY()) + 2);

		for (int i = size; i>=0; i--) {
			str += i + "\t";
			for (int j = 0; j<size; j++) {
				if (j == coordinates.getX() && i == coordinates.getY()) {
					str += plotter + "\t";
				} else {
					str += "-\t";
				}
			}
			str += "\n";

			if(i == 0) {
				for (int k = 0; k<size+1; k++) {
					if(k == 0) {
						str += " \t";
					} else {
						str += (k-1) + "\t";
					}
				}
			}
		}

		return str;
	}
}
