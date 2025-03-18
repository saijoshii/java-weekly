public class Punctuation {
    public static void main(String[] args) {
        // The string provided
        String text = "Mary had a little lamb, her fleece was as white as snow, and everywhere Mary went, the lamb was sure to go. -that was a nice poem- the end.";

        // Variables to count the punctuation marks
        int commaCount = 0;
        int periodCount = 0;
        int dashCount = 0;
        int apostropheCount = 0;
        int quoteCount = 0;

        // Loop through each character in the string
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i); // Get the current character

            // Check if the character is a specific punctuation mark and count it
            if (ch == ',') {
                commaCount++;
            } else if (ch == '.') {
                periodCount++;
            } else if (ch == '-') {
                dashCount++;
            } else if (ch == '\'') {
                apostropheCount++;
            } else if (ch == '"') {
                quoteCount++;
            }
        }

        // Print the table of punctuation counts
        System.out.println("Punctuation Count:");
        System.out.println("Symbol\tCount");
        System.out.println(",\t" + commaCount);
        System.out.println(".\t" + periodCount);
        System.out.println("-\t" + dashCount);
        System.out.println("'\t" + apostropheCount);
        System.out.println("\"\t" + quoteCount);
    }
}