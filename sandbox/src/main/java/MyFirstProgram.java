import maria.belyaeva.qa.Point;

public class MyFirstProgram {

	public static void main(String[] args) {

        Point p1 = new Point(3.0,-4.0);
        Point p2 = new Point(-6.0,5.0);

		System.out.println("The distance between the points p1 and p2 is: " + p1.distance(p2));

	}
}	
