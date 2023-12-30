package pro.sky.javacoursepart2.intList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.javacoursepart2.exceptions.IllegalCapacityException;
import pro.sky.javacoursepart2.exceptions.IllegalIndexException;
import pro.sky.javacoursepart2.exceptions.InsufficientCapacityException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntListImplTest {

    private IntList list = new IntListImpl(8);

    @BeforeEach
    void setList() {
        list.add(15);
        list.add(4);
        list.add(37);
        list.add(7);
        list.add(99);
    }

    @Test
    void checkConstructorWithParameter() {
        IntList list2 = new IntListImpl(3);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        String expected = "[0, 1, 2]";
        assertEquals(expected, Arrays.toString(list2.toArray()));
        assertThrows(InsufficientCapacityException.class, () -> list2.add(3));
        assertThrows(IllegalCapacityException.class, () -> new IntListImpl(-15));
        list2.clear();
    }

    @Test
    void addMethodShouldAddIntArgumentToIntList() {
        int expected = -1;
        assertEquals(expected, list.add(-1));
        String newExpected = "[15, 4, 37, 7, 99, -1]";
        assertEquals(newExpected, Arrays.toString(list.toArray()));
    }

    @Test
    void addMethodWithIntIndexAndIntegerItemArgumentShouldAddNewItemAtSpecifiedPlaceInIntList() {
        int expected = 101;
        assertEquals(expected, list.add(10, 101));
        String newExpected = "[15, 4, 37, 7, 99, 101]";
        assertEquals(newExpected, Arrays.toString(list.toArray()));
        newExpected = "[20, 15, 4, 37, 7, 99, 101]";
        list.add(0, 20);
        assertEquals(newExpected, Arrays.toString(list.toArray()));
    }

    @Test
    void methodsWithItemParameterShouldThrowExceptionWhenReceiveNullNullItemArg() {
        Integer number = null;
        assertThrows(IllegalArgumentException.class, () -> list.add(number));
        assertThrows(IllegalArgumentException.class, () -> list.add(6, number));
        assertThrows(IllegalArgumentException.class, () -> list.contains(number));
        assertThrows(IllegalArgumentException.class, () -> list.indexOf(number));
        assertThrows(IllegalArgumentException.class, () -> list.lastIndexOf(number));
    }

    @Test
    void methodsWithIndexParameterShouldThrowExceptionWhenReceiveNegativeIndex() {
        assertThrows(IllegalIndexException.class, () -> list.add(-2, -2));
        assertThrows(IllegalIndexException.class, () -> list.set(-2, -2));
        assertThrows(IllegalIndexException.class, () -> list.remove(-2));
        assertThrows(IllegalIndexException.class, () -> list.get(-2));
    }


    @Test
    void setMethodShouldSetNewValueToExistingItemInIntList() {
        int expected = 77;
        assertEquals(expected, list.set(3, 77));
        String newExpected = "[15, 4, 37, 77, 99]";
        assertEquals(newExpected, Arrays.toString(list.toArray()));
    }

    @Test
    void removeMethodRemovesItemBasedOnIndex() {
        int expected = 37;
        assertEquals(expected, list.remove(2));
        String newExpected = "[15, 4, 7, 99]";
        assertEquals(newExpected, Arrays.toString(list.toArray()));
    }

    @Test
    void containsMethodChecksIfIntListContainsGivenInt() {
        assertTrue(list.contains(15));
        assertFalse(list.contains(404));
    }

    // [15, 4, 37, 7, 99]
    @Test
    void indexOfMethodReturnsFirstIndexOfGivenItemIfItIsFound() {
        assertEquals(0, list.indexOf(15));
        assertEquals(4, list.indexOf(99));
        assertEquals(-1, list.indexOf(404));
        list.set(1, 7);
        assertEquals(1, list.indexOf(7));
    }

    @Test
    void lastIndexOfMethodReturnsLastIndexOfGivenItemIfItIsFound() {
        assertEquals(0, list.lastIndexOf(15));
        assertEquals(4, list.lastIndexOf(99));
        assertEquals(-1, list.lastIndexOf(404));
        list.set(1, 7);
        assertEquals(3, list.lastIndexOf(7));
    }

    @Test
    void getMethodReturnsItemByIndex() {
        assertEquals(15, list.get(0));
        assertEquals(37, list.get(2));
        assertEquals(99, list.get(4));

    }

    @Test
    void equalsMethodChecksTwoIntListsForEqualityCapacitiesMayVary() {
        IntList list2 = new IntListImpl(5);

        list2.add(15);
        list2.add(4);
        list2.add(37);
        list2.add(7);
        list2.add(99);

        assertTrue(list.equals(list2));
        list2.set(1, 99);
        assertFalse(list.equals(list2));
    }

    @Test
    void sizeReturnsSizeOfTheIntList() {
        assertEquals(5, list.size());
    }

    @Test
    void isEmptyReturnsTrueOrFalseDependingOnIntListIsEmptyOrNot() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    @AfterEach
    void clearClearsTheIntList() {
        String expected = "[]";
        list.clear();
        assertEquals(expected, Arrays.toString(list.toArray()));
    }

    @Test
    void toArrayReturnsCopyOfIntListItemsArray() {
        String expected = "[15, 4, 37, 7, 99]";
        assertEquals(expected, Arrays.toString(list.toArray()));
    }
}