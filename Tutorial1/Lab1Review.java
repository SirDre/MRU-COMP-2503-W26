import java.util.Scanner;

/*
 * Lab1Review - A program that reads from the input stream System.in, word
 * by word, and writes the words, one line at a time, to the output stream
 * System.out.
*/

public class Lab1Review {

    // Run method
    public void run () {
        Scanner i = new Scanner(System.in);

        // input stream
        while (i.hasNext()) {
            String w = i.next().toLowerCase().trim().replaceAll("[^a-z0-9]", "");
          
			if ( w.length() > 0) {
				System.out.println(w);
			}
        }
        
        //close
        i.close();
    } 

    // Main method
    public static void main(String[] args) {
        Lab1Review lab1 = new Lab1Review();
        lab1.run();
    } 


}