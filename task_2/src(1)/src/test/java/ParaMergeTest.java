

import cz.cuni.mff.turcana.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class ParaMergeTest {


    @Test
    public void testAlreadySortedArray() {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};


        Arrays.paraMergeSort(sortedArray);


        int[] expectedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(expectedArray, sortedArray);
    }

    // Test 2: Test paraMergeSort on an unsorted array
    @Test
    public void testUnsortedArray() {
        int[] unsortedArray = {38, 27, 43, 3, 9, 82, 10};

        Arrays.paraMergeSort(unsortedArray);


        int[] expectedArray = {3, 9, 10, 27, 38, 43, 82};
        assertArrayEquals(expectedArray, unsortedArray);
    }



}
