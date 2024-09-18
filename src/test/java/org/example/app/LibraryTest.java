package org.example.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private static Library library;

    @BeforeAll
    public static void setUp() {
        library = new Library();
    }

    @AfterEach
    public void init() {
        library.getBooks().clear();
    }

    @Test
    @DisplayName("Test if book is really added.")
    void whenAddBook_ThenBookIsAdded() {
        //given
        Book book = new Book("The Kobzar of the Ukraine",
                "Taras Shevchenko");
        int expectedBookCount = 1;
        //then
        library.addBook(book);
        //assertions & verification
        assertEquals(expectedBookCount, library.getBookCount(),
                "Library should have at least 1 book!");
    }

    @Test
    @DisplayName("When null book is adding then exception can be thrown.")
    public void whenAddNullBook_ThenThrowException() {
        // given
        Book invalidBook = null;
        String expectedExceptionMessage =
                "Parameter [book] must not be null!";
        // then
        NullPointerException exception =
                assertThrows(NullPointerException.class, () ->
                        library.addBook(invalidBook));
        // assertions & verification
        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }

    @Test
    @DisplayName("Test if book is really removed.")
    void whenRemoveExistingBook_ThenBookIsRemoved() {
        // given
        Book book = new Book("The Kobzar of the Ukraine",
                "Taras Shevchenko");
        int expectedBookCount = 0;
        // when
        library.addBook(book);
        // then
        boolean isBookRemoved = library.removeBook(book);
        // assertions & verification
        assertTrue(isBookRemoved, "Result should be 'true'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Library shouldn't have any books!");
    }

    @Test
    @DisplayName("Test removing non-existing book.")
    public void whenRemoveNonExistingBook_ThenBookIsNotRemoved() {
        // given
        Book book = new Book("The Kobzar of the Ukraine",
                "Taras Shevchenko");
        Book toRemove = new Book("Fundation",
                "Isaac Asimov");
        int expectedBookCount = 1;
        // when
        library.addBook(book);
        // then
        boolean isBookRemoved = library.removeBook(toRemove);
        // assertions & verification
        assertFalse(isBookRemoved, "Result should be 'false'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Library should have at least 1 book!");
    }

    @Test
    @DisplayName("When null book is removing then exception can be thrown.")
    public void whenRemoveNullBook_ThenThrowException() {
        // given
        Book toRemove = null;
        String expectedExceptionMessage =
                "Parameter [book] must not be null!";
        // then
        NullPointerException exception =
                assertThrows(NullPointerException.class,
                        () -> library.removeBook(toRemove));
        // assertions & verification
        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }

}