public class Bookshelf {
    public static void main(String[] args) {
        Book book1 = new Book("The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company", 1951);
        Book book2 = new Book("1984", "George Orwell", "Secker & Warburg", 1949);

        System.out.println("Original Book 1:");
        System.out.println(book1);
        System.out.println("\nOriginal Book 2:");
        System.out.println(book2);

        book1.setCopyrightDate(1952);
        book1.setPublisher("Little, Brown");
        book2.setTitle("Nineteen Eighty-Four");

        System.out.println("\nUpdated Book 1:");
        System.out.println(book1);
        System.out.println("\nUpdated Book 2:");
        System.out.println(book2);
    }
}