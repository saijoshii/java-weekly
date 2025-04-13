public class SimpleEncryption {
    public static void main(String[] args) {
        
        Encryptable mySecret = new Secret("Hello", 3);
        System.out.println("Secret Test:");
        System.out.println("Original: " + mySecret);
        mySecret.encrypt();
        System.out.println("Encrypted: " + mySecret);
        mySecret.decrypt();
        System.out.println("Decrypted: " + mySecret);
        
        System.out.println();
        
        Encryptable myPassword = new Password("abc123");
        System.out.println("Password Test:");
        System.out.println("Original: " + myPassword);
        myPassword.encrypt();
        System.out.println("Encrypted: " + myPassword);
        myPassword.decrypt();
        System.out.println("Decrypted: " + myPassword);
    }
}

interface Encryptable {
    void encrypt();
    void decrypt();
    String toString();
}

class Secret implements Encryptable {
    String text;
    int shift;
    boolean isEncrypted;
    
    public Secret(String t, int s) {
        text = t;
        shift = s;
        isEncrypted = false;
    }
    
    public void encrypt() {
        if (!isEncrypted) {
            String result = "";
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (Character.isLetter(c)) {
                    char base = Character.isLowerCase(c) ? 'a' : 'A';
                    c = (char)(((c - base + shift) % 26) + base);
                }
                result += c;
            }
            text = result;
            isEncrypted = true;
        }
    }
    
    public void decrypt() {
        if (isEncrypted) {
            String result = "";
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (Character.isLetter(c)) {
                    char base = Character.isLowerCase(c) ? 'a' : 'A';
                    c = (char)(((c - base - shift + 26) % 26) + base);
                }
                result += c;
            }
            text = result;
            isEncrypted = false;
        }
    }
    
    public String toString() {
        return text;
    }
}

class Password implements Encryptable {
    String text;
    boolean isEncrypted;
    
    public Password(String t) {
        text = t;
        isEncrypted = false;
    }
    
    public void encrypt() {
        if (!isEncrypted) {
            String result = "";
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // Just add 1 to each character's ASCII value
                result += (char)(c + 1);
            }
            text = result;
            isEncrypted = true;
        }
    }
    
    public void decrypt() {
        if (isEncrypted) {
            String result = "";
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // Subtract 1 to get back original
                result += (char)(c - 1);
            }
            text = result;
            isEncrypted = false;
        }
    }
    
    public String toString() {
        return text;
    }
}