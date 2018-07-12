import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class Milestone1
{
	public static class Driver
	{
		public static void main(String[] args) {
			BoostCLI io = new BoostCLI();
		}
	}

	public static class Vector
	{
		public static final int X = 0;
		public static final int Y = 1;
		public static final int Z = 2;

		private String name;
		private double[] coordinates;
		private int dimensionality;

		public Vector(double... args) {
			this.name = "#";
			coordinates = new double[args.length];
			setCoordinates(args);
			dimensionality = coordinates.length;
		}

		public Vector(String name, double... args) {
			this.name = name;
			coordinates = new double[args.length];
			setCoordinates(args);
			dimensionality = coordinates.length;
		}

		public void setCoordinates(double... args) {
			int i = 0;
			for (double c : args) {
				coordinates[i] = c;
				i++;
			}
		}

		/* OPERATIONS */
		public static Vector add(Vector... args) {
			int totalCoors = args[0].getDimensionality();
			double[] sum = new double[args[0].getDimensionality()];

			for (int k=0; k<totalCoors; k++)
				sum[k] = 0;

			for (int i = 0; i < args.length; i++)
				for (int j = 0; j < totalCoors; j++)
					sum[j] = sum[j] + args[i].getCoordinates()[j];

			return new Vector(sum);
		}

		public static Vector scaleVector(Vector vector, double multiplier) {
			for (int i = 0; i < vector.getDimensionality(); i++)
				vector.scaleCoordinate(i, multiplier);
			return vector;
		}

		public Vector scaleVector(double multiplier) {
			for (int i = 0; i < dimensionality; i++)
				scaleCoordinate(i, multiplier);
			return this;
		}

		public static Vector scaleCoordinate(Vector vector, double multiplier, int coordinate) {
			vector.setCoordinate(coordinate, vector.getCoordinates()[coordinate]*multiplier);
			return vector;
		}

		public Vector scaleCoordinate(int coordinate, double multiplier) {
			setCoordinate(coordinate, getCoordinates()[coordinate]*multiplier);
			return this;
		}

		/* GETTERS AND SETTERS */
		public void setCoordinate(int coordinate, double coordinateVal) {
			coordinates[coordinate] = coordinateVal;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getDimensionality() {
			return dimensionality;
		}

		public double[] getCoordinates() {
			return coordinates;
		}

		@Override
		public String toString() {
			String str = "";

			str += name + " : [";
			for (int i = 0; i < coordinates.length; i++) {
				if (i > 0)
					str += ", ";
				str += coordinates[i];
			}
			str += "]";

			return str;
		}

		@Override
		public Vector clone() {
			return new Vector(name, coordinates);
		}
	}

	public static class BoostCLI
	{
		private Scanner sc;
		/**
		 * Prints a single line break.
		 * @return itself for method chaining
		 * */
		public BoostCLI p() {
			System.out.println();
			return this;
		}

		/**
		 * Prints a string on the console and goes to next line.
		 *  @param print the string to be printed
		 *  @return itself for method chaining
		 * */
		public BoostCLI p(String print) {
			System.out.println(print);
			return this;
		}

		/**
		 * Prints a string and prints next line for a set number of times.
		 * A positive ln arg will print the line breaks after the string.
		 * A negative ln arg will print the line breaks before the string.
		 *  @param print the string to be printed
		 * @param lines number of line breaks to be printed
		 *
		 * @return itself for method chaining
		 * */
		public BoostCLI p(String print, int lines) {
			if (lines < 0) {
				for (; lines < 0; lines++) p("");
				System.out.print(print);
			} else {
				System.out.print(print);
				for (; lines > 0; lines--) p("");
			}
			return this;
		}

		/**
		 * Prints a responsive decoration around text. Uses the style's
		 * default character theme.
		 *  @param print    the string to be printed
		 * @param style    the decoration style
		 *
		 * @return itself for method chaining
		 * */
		public BoostCLI p(String print, String style) {
			if (style.equals("box")) {
				styleBox(print);
			}
			else if(style.equals("box-append")) {
				styleBoxAppend(print);
			}
			else if(style.equals("pipe")) {
				stylePipe(print);
			}
			else if(style.equals("pillar-tab")) {
				stylePillarTab(print);
			}
			else if(style.equals("pillar-space")) {
				stylePillarSpace(print);
			}
			else if(style.equals("pillar")) {
				stylePillar(print);
			}
			else {
				styleQuickWrap(print, style);
			}
			return this;
		}

		/**
		 * Prints a responsive decoration around text. Uses the param
		 * character theme to replace main character used in theme.
		 *  @param print        the string to be printed
		 * @param style        the decoration style
		 * @param charTheme the character to be replaced
		 *
		 * @return itself for method chaining
		 * */
		public BoostCLI p(String print, String style, String charTheme) {
			if(style.equals("pipe")) {
				stylePipe(print, charTheme);
			}
			else if(style.equals("pillar-tab")) {
				stylePillarTab(print, charTheme);
			}
			else if(style.equals("pillar-space")) {
				stylePillarSpace(print, charTheme);
			}
			else if(style.equals("pillar")) {
				stylePillar(print, charTheme);
			}
			return this;
		}

		/**
		 * Prints a responsive decoration around text. Uses the param
		 * character theme to replace main character used in theme.
		 *  @param print            the string to be printed
		 * @param style            the decoration style
		 * @param styleLength    gives the style a fixed metric instead of being responsive
		 *
		 * @return itself for method chaining
		 * */
		public BoostCLI p(String print, String style, int styleLength) {
			if (style.equals("box")) {
				styleBox(print, styleLength);
			}
			else if(style.equals("box-append")) {
				styleBoxAppend(print, styleLength);
			}
			else if(style.equals("pipe")) {
				stylePipe(print, styleLength);
			}
			else if(style.equals("pillar-tab")) {
				stylePillarTab(print, styleLength);
			}
			else if(style.equals("pillar-space")) {
				stylePillarSpace(print, styleLength);
			}
			else if(style.equals("pillar")) {
				stylePillar(print, styleLength);
			}
			else {
				styleQuickWrap(print, style, styleLength);
			}
			return this;
		}

		/**
		 * Prints a responsive decoration around text. Uses the param
		 * character theme to replace main character used in theme.
		 *  @param print            the string to be printed
		 * @param style            the decoration style
		 * @param charTheme the character to be replaced
		 * @param styleLength    gives the style a fixed metric instead of being responsive
		 *
		 * @return itself for method chaining
		 * */
		public BoostCLI p(String print, String style, String charTheme, int styleLength) {
			if(style.equals("pipe")) {
				stylePipe(print, charTheme, styleLength);
			}
			else if(style.equals("pillar-tab")) {
				stylePillarTab(print, charTheme, styleLength);
			}
			else if(style.equals("pillar-space")) {
				stylePillarSpace(print, charTheme, styleLength);
			}
			else if(style.equals("pillar")) {
				stylePillar(print, charTheme, styleLength);
			}
			return this;
		}


		/**
		 * Prints a string an indicated number of times.
		 *  @param print        the string to be printed
		 * @param amount    amount of times the string is to be printed
		 *
		 * @return itself for method chaining
		 * */
		public BoostCLI pp(String print, int amount) {
			String end = "";
			if( amount < 0 ) end = "\n";
			amount = Math.abs(amount);
			for(; amount > 0; amount--) p(print, 0);
			p(end, 0);
			return this;
		}

		/**
		 * Iterates through a list and prints the String associated with the index.
		 *  @param list    the string to be printed
		 *
		 *  @return itself for method chaining
		 * */
		public BoostCLI pl(String[] list) {
			for(String item : list) p(item);
			return this;
		}

		/**
		 * Iterates through a list and prints the String associated with the index.
		 *  @param list    the string to be printed
		 *
		 *  @return itself for method chaining
		 * */
		public BoostCLI pl(int[] list) {
			for(int item : list) p(String.valueOf(item));
			return this;
		}

		/**
		 * Iterates through a list and prints the String associated with the index.
		 *  @param list    the string to be printed
		 *
		 *  @return itself for method chaining
		 * */
		public BoostCLI pl(float[] list) {
			for(float item : list) p(String.valueOf(item));
			return this;
		}

		/**
		 * Iterates through a list and prints the String associated with the index.
		 *  @param list    the string to be printed
		 *
		 *  @return itself for method chaining
		 * */
		public BoostCLI pl(double[] list) {
			for(double item : list) p(String.valueOf(item));
			return this;
		}

		/**
		 * Iterates through a list and prints the String associated with the index.
		 *  @param list    the string to be printed
		 * @param style    the style of which the list is to be printed
		 *
		 * @return itself for method chaining
		 * */
		public BoostCLI pl(Object[] list, String style) {
			for(Object item : list) p(String.valueOf(item));
			return this;
		}

		/**
		 * Iterates through a list and prints the String associated with the index.
		 *  @param list    the string to be printed
		 *
		 *  @return itself for method chaining
		 * */
		public BoostCLI pl(List<String> list) {
			for(String item : list) p(item);
			return this;
		}

		/**
		 * Prints a message then asks for a string. String input
		 * breaks upon next line.
		 *
		 * @param print the message to be printed
		 * @return user input
		 * */
		public String s(String print) {
			p(print, 0);
			sc = new Scanner(System.in);
			String input = sc.nextLine();
			return input;
		}

		/**
		 * Prints a message then asks for a string. String input
		 * breaks upon a [space].
		 *
		 * @param print the message to be printed
	 *     @return user input
		 * */
		public String sw(String print) {
			p(print, 0);
			sc = new Scanner(System.in);
			String input = sc.next();
			return input;
		}

		/**
		 * Prints a message then asks for an character. (can input a string
		 * but only utilizes first char)
		 *
		 * @param print the message to be printed
		 * @return user input
		 * */
		public String sc(String print) {
			p(print, 0);
			sc = new Scanner(System.in);
			String input = sc.next();
			return "" + input.charAt(0);
		}

		/**
		 * Prints a message then asks for an integer.
		 *
		 * @param print the message to be printed
		 * @return user input
		 * */
		public int si(String print) {
			p(print, 0);
			sc = new Scanner(System.in);
			int input = sc.nextInt();
			return input;
		}

		/**
		 * Prints a message then asks for a float value.
		 *
		 * @param print the message to be printed
		 * @return user input
		 * */
		public float sf(String print) {
			p(print, 0);
			sc = new Scanner(System.in);
			float input = sc.nextFloat();
			return input;
		}

		/**
		 * Prints a message then asks for a double value.
		 *
		 * @param print the message to be printed
		 * @return user input
		 * */
		public double sd(String print) {
			p(print, 0);
			sc = new Scanner(System.in);
			double input = sc.nextDouble();
			return input;
		}

		@Override
		public String toString() {
			String info = "";
			info += "BoostCLI\n";
			info += "Version 0.6.3\n";
			info += "by Patrick Latorre";
			return info;
		}

		public BoostCLI version() {
			p(toString(), "pipe", 20);
			return this;
		}

		/* PRIVATE METHODS */
		private String replaceInnerSpaces(String print, int slots) {
			String modifiedStr = "";
			int padding = (slots-print.length()) / 2;

			for(int i = 0; i < padding; i++)
				modifiedStr += " ";
			modifiedStr += print;
			for(int i = 0; i < padding; i++)
				modifiedStr += " ";

			if((slots - print.length()) % 2 != 0)
				modifiedStr += " ";

			return modifiedStr;
		}

		private void styleBox(String s) {
			int width = s.length();
			p("+-", 0).pp("-", width).p("-+");
			stylePillar(s, width+2);
			p("+-", 0).pp("-", width).p("-+");
		}

		private void styleBox(String s, int styleLength) {
			String paddedString = replaceInnerSpaces(s, styleLength);
			int fullWidth = paddedString.length();
			p("+", 0).pp("-", fullWidth).p("+");
			stylePillar(paddedString, styleLength);
			p("+", 0).pp("-", fullWidth).p("+");
		}

		private void styleBoxAppend(String s) {
			int width = s.length();
			stylePillar(s, width+2);
			p("+-", 0).pp("-", width).p("-+");
		}

		private void styleBoxAppend(String s, int styleLength) {
			String paddedString = replaceInnerSpaces(s, styleLength);
			int fullWidth = paddedString.length();
			stylePillar(paddedString, styleLength);
			p("+", 0).pp("-", fullWidth).p("+");
		}

		private void stylePipe(String print) {
			pp("=", print.length()).p();
			p(print);
			pp("=", print.length()).p();
		}

		private void stylePipe(String print, int styleLength) {
			pp("=", styleLength).p();
			p(print);
			pp("=", styleLength).p();
		}

		private void stylePipe(String print, String charTheme) {
			pp(charTheme, print.length()).p();
			p(print);
			pp(charTheme, print.length()).p();
		}

		private void stylePipe(String print, String charTheme, int styleLength) {
			pp(charTheme, styleLength).p();
			p(print);
			pp(charTheme, styleLength).p();
		}

		private void stylePillarTab(String print) {
			p("|\t" +print+ "\t|");
		}

		private void stylePillarTab(String print, String charTheme) {
			p(charTheme + "\t" +print+ "\t" + charTheme);
		}

		private void stylePillarTab(String print, int styleLength) {
			p("|", 0);
			for(int i = 0; i < styleLength; i++) p("\t", 0);
			p(print, 0);
			for(int i = 0; i < styleLength; i++) p("\t", 0);
			p("|", 1);
		}

		private void stylePillarTab(String print, String charTheme, int styleLength) {
			p(charTheme, 0);
			for(int i = 0; i < styleLength; i++) p("\t", 0);
			p(print, 0);
			for(int i = 0; i < styleLength; i++) p("\t", 0);
			p(charTheme, 1);
		}

		private void stylePillarSpace(String print) {
			p("| " +print+ " |");
		}

		private void stylePillarSpace(String print, String charTheme) {
			p(charTheme + " " +print+ " " + charTheme);
		}

		private void stylePillarSpace(String print, int styleLength) {
			p("|", 0);
			for(int i = 0; i < styleLength; i++) p(" ", 0);
			p(print, 0);
			for(int i = 0; i < styleLength; i++) p(" ", 0);
			p("|", 1);
		}

		private void stylePillarSpace(String print, String charTheme, int styleLength) {
			p(charTheme, 0);
			for(int i = 0; i < styleLength; i++) p(" ", 0);
			p(print, 0);
			for(int i = 0; i < styleLength; i++) p(" ", 0);
			p(charTheme, 1);
		}

		private void stylePillar(String print) {
			p("|" +print+ "|");
		}

		private void stylePillar(String print, String charTheme) {
			p(charTheme +print+ charTheme);
		}

		private void stylePillar(String print, int styleLength) {
			p("|" +replaceInnerSpaces(print, styleLength)+ "|");
		}

		private void stylePillar(String print, String charTheme, int styleLength) {
			p(charTheme +replaceInnerSpaces(print, styleLength)+ charTheme);
		}

		private void styleQuickWrap(String print, String wrapper) {
			String[] splitWrap = wrapper.split("[|]");
			p(splitWrap[0] +print+ splitWrap[1], 0);
		}

		private void styleQuickWrap(String print, String wrapper, int styleLength) {
			String[] splitWrap = wrapper.split("|");

			p(splitWrap[0], 0);
			for(int i = 0; i < styleLength; i++)
				p(" ", 0);
			p(print, 0);
			for(int i = 0; i < styleLength; i++)
				p(" ", 0);
			p(splitWrap[1], 0);
		}
	}

	@Deprecated
	public static class OldVector
	{
		private String name;
		private Coordinates coordinates;
		private double magnitude;

		public OldVector(double x, double y) {
			this.name = "#";
			coordinates = new Coordinates(x, y);
			cacheMagnitude();
		}

		public OldVector(String name, double x, double y) {
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

		/* OPERATIONS */
		public static OldVector add(OldVector... args) {
			double sumX=0, sumY=0;

			for (OldVector v : args) {
				sumX = sumX + v.getX();
				sumY = sumY + v.getY();
			}

			return new OldVector(sumX, sumY);
		}

		public static OldVector scale(OldVector vector, double multiplier) {
			scaleX(vector, multiplier);
			scaleY(vector, multiplier);
			return vector;
		}

		public OldVector scale(double multiplier) {
			scaleX(this, multiplier);
			scaleY(this, multiplier);
			return this;
		}

		public static OldVector scaleX(OldVector vector, double multiplier) {
			vector.setX(vector.getX() * multiplier);
			vector.cacheMagnitude();
			return vector;
		}

		public OldVector scaleX(double multiplier) {
			setX(getX() * multiplier);
			cacheMagnitude();
			return this;
		}

		public static OldVector scaleY(OldVector vector, double multiplier) {
			vector.setY(vector.getY() * multiplier);
			vector.cacheMagnitude();
			return vector;
		}

		public OldVector scaleY(double multiplier) {
			setY(getY() * multiplier);
			cacheMagnitude();
			return this;
		}


		/* HELPER METHODS */
		public void cacheMagnitude() {
			magnitude = Math.hypot(coordinates.getX(), coordinates.getY());
		}


		/* GETTERS AND SETTERS */
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

	@Deprecated
	public static class Coordinates
	{
		private double x;
		private double y;

		public Coordinates(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}
	}
}
