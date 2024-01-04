package rip.lunarydess.lilith.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("ArrayKit.java")
class TestArrayKit {
    @Test
    @DisplayName("adding in arrays")
    void add() {
        String[] array = {"test1", "test2"};

        array = ArrayKit.add(String[]::new, array, "test3");
        assertEquals(3, array.length, "length is bad");
        assertEquals("test3", array[2], "simple adding does not work");

        array = ArrayKit.addAt(String[]::new, array, "test0", -1);
        assertEquals(4, array.length, "length is bad");
        assertEquals("test0", array[0], "index-based adding does not work");
    }

    @Test
    @DisplayName("removing in arrays")
    void remove() {
        String[] array = {"test1", "test2", "test3"};

        array = ArrayKit.remove(String[]::new, array, "test2");
        assertEquals(2, array.length, "length is bad");
        assertEquals("test1", array[0], "simple removing does not work");

        array = ArrayKit.removeAt(String[]::new, array, 0);
        assertEquals(1, array.length, "length is bad");
        assertEquals("test3", array[0], "index-based removing does not work");

        array = ArrayKit.removeAt(String[]::new, array, 0);
        assertEquals(0, array.length, "length is bad");
    }

    @Test
    @DisplayName("merging in arrays")
    void merge() {
        final String[]
                array1 = {"test1", "test2", "test3"},
                array2 = {"test3", "test2", "test1"};

        final String[] array = ArrayKit.merge(String[]::new, array1, array2);
        assertEquals(6, array.length, "length is bad");
        for (int i = 0; i < 6; i++) {
            final String test = array[i];
            assertNotNull(test, String.format("entry %d seems wrong", i));
            assertTrue(
                    test.equals("test1") ||
                            test.equals("test2") ||
                            test.equals("test3"),
                    String.format("entry %d seems wrong", i)
            );
        }
    }

    @Test
    @DisplayName("slicing in arrays")
    void slice() {
        final String[] array = ArrayKit.slice(String[]::new, new String[]{"test1", "test2", "test3", "test4"}, 1, 2);
        assertEquals(2, array.length, "length is bad");
        assertEquals("test2", array[0], "first entry is wrong");
        assertEquals("test3", array[1], "second entry is wrong");
    }

    @Test
    @DisplayName("reversing in arrays")
    void reverse() {
        final String[]
                array = {"test4", "test3", "test2", "test1", "test0"},
                arrayReversed = ArrayKit.reverse(String[]::new, array);

        assertEquals(array.length, arrayReversed.length, "length is bad");

        int reversedIndex = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], arrayReversed[reversedIndex--], String.format("the object in the normal array at idx %d is not the same as in the reversed array at idx %d", i, reversedIndex));
        }
    }

    @Test
    @DisplayName("index finding in arrays")
    void indexOf() {
        final String[] array = {"test1", "test2", "test3"};
        assertEquals(2, ArrayKit.indexOf(array, "test3"), "index finding seems wrong");

        final int[] indices = ArrayKit.indicesOf(array, "test1", "test2");
        assertEquals(2, indices.length, "length is bad");
        assertEquals(0, indices[0], "first entry is wrong");
        assertEquals(1, indices[1], "second entry is wrong");
    }

    @Test
    @DisplayName("list creation from array")
    void asList() {
        final String[]
                array1 = {"test1", "test2", "test3"},
                array2 = {"test1", "test2", "test3"},
                array3 = {"test1", "test2", "test3"},
                array4 = {"test1", "test2", "test3"};
        final int expectedSize = array1.length + array2.length + array3.length + array4.length;
        
        final ArrayList<String> list1 = ArrayKit.asList(ArrayList::new, array1, array2, array3, array4);
        assertEquals(expectedSize, list1.size(), "length is bad");
        assertTrue(list1.stream().noneMatch(Objects::isNull), "entry couldn't be added properly");

        final CopyOnWriteArrayList<String> list2 = ArrayKit.asList(CopyOnWriteArrayList::new, array1, array2, array3, array4);
        assertEquals(expectedSize, list2.size(), "length is bad");
        assertTrue(list2.stream().noneMatch(Objects::isNull), "entry couldn't be added properly");

        final LinkedList<String> list3 = ArrayKit.asList(LinkedList::new, array1, array2, array3, array4);
        assertEquals(expectedSize, list3.size(), "length is bad");
        assertTrue(list3.stream().noneMatch(Objects::isNull), "entry couldn't be added properly");
    }
}
