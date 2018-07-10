package models;

public class Vector
{
	private String name;
	private Coordinates coordinates;
	private double magnitude;

	// TODO: to be implemented
	// private double direction;
	public Vector(double x, double y) {
		this.name = "#";
		coordinates = new Coordinates(x, y);
		cacheMagnitude();
	}

	public Vector(String name, double x, double y) {
		this.name = name;
		coordinates = new Coordinates(x, y);
		cacheMagnitude();
	}

	public String plot() {
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
					str += name + "\t";
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

	/*
	* OPERATIONS
	* */
	public static Vector add(Vector... args) {
		double sumX=0, sumY=0;

		for (Vector v : args) {
			sumX = sumX + v.getX();
			sumY = sumY + v.getY();
		}

		return new Vector(sumX, sumY);
	}

	public static Vector scale(Vector vector, double multiplier) {
		scaleX(vector, multiplier);
		scaleY(vector, multiplier);
		return vector;
	}

	public static Vector scaleX(Vector vector, double multiplier) {
		vector.setX(vector.getX() * multiplier);
		return vector;
	}

	public static Vector scaleY(Vector vector, double multiplier) {
		vector.setY(vector.getY() * multiplier);
		return vector;
	}


	/*
	* HELPER METHODS
	* */
	public void cacheMagnitude() {
		magnitude = Math.hypot(coordinates.getX(), coordinates.getY());
	}


	/*
	* GETTERS AND SETTERS
	* */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
		cacheMagnitude();
	}

	public void setX(double x) {
		coordinates.setX(x);
		cacheMagnitude();
	}

	public void setY(double y) {
		coordinates.setY(y);
		cacheMagnitude();
	}

	public double getX() {
		return coordinates.getX();
	}

	public double getY() {
		return coordinates.getY();
	}
}
