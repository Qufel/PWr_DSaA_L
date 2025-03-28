package dsaa.lab03;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    @Test
    void testAddSingleElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        assertTrue(list.add(42));
        assertEquals(1, list.size());
        assertEquals(42, list.get(0));
    }

    @Test
    void testAddMultipleElements() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void testRemoveHead() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.remove(0));
        assertEquals(2, list.get(0));
        assertEquals(2, list.size());
    }

    @Test
    void testIterator() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void testInvalidIndex() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(5);

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
        assertThrows(NoSuchElementException.class, () -> list.get(1));
        assertThrows(NoSuchElementException.class, () -> list.remove(2));
    }

    @Test
    void testNewListIsEmpty() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testAddAtHead() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add("B");
        list.add(0, "A");

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testAddAtTail() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(10);
        list.add(20);
        list.add(2, 30);

        assertEquals(3, list.size());
        assertEquals(30, list.get(2));
    }

    @Test
    void testAddInMiddle() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add("A");
        list.add("C");
        list.add(1, "B");

        assertEquals(3, list.size());
        assertEquals("B", list.get(1));
    }

    @Test
    void testRemoveTail() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(30, list.remove(2));
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveMiddleElement() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.remove(1));
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testBackwardIterator() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(10);
        list.add(20);
        list.add(30);

        ListIterator<Integer> it = list.listIterator();

        assertTrue(it.hasPrevious());
        assertEquals(30, it.previous());
        assertEquals(20, it.previous());
        assertEquals(10, it.previous());
        assertFalse(it.hasPrevious());
    }

    @Test
    void testForwardIterator() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(10);
        list.add(20);
        list.add(30);

        ListIterator<Integer> it = list.listIterator();

        assertTrue(it.hasNext());
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertFalse(it.hasNext());
    }


    @Test
    void testIteratorOnEmptyList() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();

        Iterator<String> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    void testGetInvalidIndex() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(10);

        assertThrows(NoSuchElementException.class, () -> list.get(5));
        assertThrows(NoSuchElementException.class, () -> list.get(-1));
    }

    @Test
    void testRemoveInvalidIndex() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(10);

        assertThrows(NoSuchElementException.class, () -> list.remove(3));
        assertThrows(NoSuchElementException.class, () -> list.remove(-1));
    }

    @Test
    void testAddInvalidIndex() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add("A");

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, "Y"));
    }

    @Test
    void testLargeList() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        assertEquals(10000, list.size());
        assertEquals(0, list.get(0));
        assertEquals(9999, list.get(9999));
    }

    @Test
    void testSelfAddList() {
        TwoWayUnorderedListWithHeadAndTail<String> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add("A");
        list.add("B");

        list.add(list);

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

}
