import java.util. Comparator;

/**
 * Comparator for Student objects. 
 * Orders students by: 
 * 1. Ascending program (alphabetically)
 * 2. Descending OWL (highest to lowest)
 * 3. Ascending ID (lowest to highest)
 */
public class CompStudent implements Comparator<Student> {
    
    @Override
    public int compare(Student s1, Student s2) {
        // First compare by program (ascending)
        int programComparison = s1.getProgram().compareTo(s2.getProgram());
        
        if (programComparison != 0) {
            return programComparison;
        }
        
        // If programs are equal, compare by OWL (descending)
        // Note: We reverse the comparison for descending order
        int owlComparison = Integer.compare(s2.getOWL(), s1.getOWL());
        
        if (owlComparison != 0) {
            return owlComparison;
        }
        
        // If OWLs are equal, compare by ID (ascending)
        return Integer.compare(s1.getID(), s2.getID());
    }
}