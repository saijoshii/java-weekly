// **************************************************
//   StringPlay.java
//
//   Play with String objects
// **************************************************
public class StringPlay {
    public static void main (String[] args) {
        String college = new String("Leeds Beckett University"); // Given statement

        // (a) Declare the variable town as a reference to a String object and initialize it to "Anytown, UK"
        String town = new String("Anytown, UK"); 

        int stringLength;
        String change1, change2, change3; 

        // (b) Write an assignment statement that invokes the length method of the string class 
        // to find the length of the college String object and assigns the result to stringLength
        stringLength = college.length(); 

        System.out.println(college + " contains " + stringLength + " characters.");

        // (c) Complete the assignment statement so that change1 contains the same characters as college 
        // but all in upper case
        change1 = college.toUpperCase(); 

        // (d) Complete the assignment statement so that change2 is the same as change1 except all 
        // lowercase "e's" are replaced with the asterisk (*) character
        change2 = change1.replace('E', '*'); 

        // (e) Complete the assignment statement so that change3 is the concatenation of college and town 
        // (use the concat method of the String class rather than the + operator)
        change3 = college.concat(" ").concat(town); 

        System.out.println("The final string is " + change3);
       
    }
}
