import java.util.Arrays;

/** Insertion sort that prints the shelf after each book (from the second) is placed. */
public class LibraryBookShelf {

    public static void shelf(int[] books) {
        if (books == null) {
            throw new IllegalArgumentException("Books cannot be null.");
        }
        for (int i = 1; i < books.length; i++) {
            int newBook = books[i];
            int j = i - 1;
            while (j >= 0 && books[j] > newBook) {
                books[j + 1] = books[j];          // slide the bigger book one place right
                j--;
            }
            books[j + 1] = newBook;
            System.out.println("After placing " + newBook + ": " + Arrays.toString(books));
        }
    }

    public static void main(String[] args) {
        int[][] samples = {{30, 10, 40, 20}, {5, 4, 3, 2, 1}};
        for (int[] books : samples) {
            System.out.println("books = " + Arrays.toString(books));
            shelf(books);
        }
    }
}
