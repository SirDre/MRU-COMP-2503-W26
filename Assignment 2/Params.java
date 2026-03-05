
public class Params {

	public static void main( String[] args) {

		for ( int i = 0; i < args.length; i++) {
			System.out.println( i + ": " + args[i]); 
		}
		String line = args[0];
		System.out.println( line); 
		String[] columns = line.split(",");

		for ( int i = 0; i < columns.length; i++) {
			System.out.println( i + ": " + columns[i]); 
		}
	}
}

