
package searchandsort;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * This program has some selection and sort algorithms.
 *
 * @author marcus
 * @version 27 March 2020
 */
public class SearchAndSort {

    private static final Random RNG = new Random();

    /**
     * This method determines the index of the first integer in a list of
     * integers that matches a given integer. The method should return -1 if no
     * match is found. Use the sequential search algorithm.
     *
     * @param valuesList the list of integers to be searched
     * @param value the integer to search for
     * @return the index of the value in the list
     */
    public static int getIndex(List<Integer> valuesList, int value) {

        int index = -1;
        for (int i = 0; i < valuesList.size(); i++) {
            if (valuesList.get(i) == value) {
                index = i;
                break;

            } // If
        } // For loop
        return index;
    } //getIndex

    /**
     * This method determines the index of the first integer in a list of
     * integers that matches a given integer, using binary search. The method
     * should return -1 if no match is found
     *
     * @param valuesList the sorted list of integers (ASC) to be searched
     * @param value the integer to search for
     * @return the index of the value in the list
     */
    public static int getIndexBinary(List<Integer> valuesList, int value,
            int min, int max) {
        int index = -1;
        int mid = (max + min) / 2;
        if (valuesList.get(mid) == value) {
            index = mid;
        } else if (mid + 1 == max) {
            return -1;
        } else if (valuesList.get(mid) < value) {
            index = getIndexBinary(valuesList, value, mid, max);
        } else {
            index = getIndexBinary(valuesList, value, min, mid);
        }
        return index;
    }

    /**
     * This method performs selection sort on an ArrayList of integers.
     *
     * @param unsortedList an unsorted ArrayList
     * @return the sorted ArrayList, in ascending order
     */
    public static ArrayList<Integer> selectionSort(
            ArrayList<Integer> unsortedList) {
        ArrayList<Integer> sortedList = unsortedList;
        for (int i = 0; i < sortedList.size(); i++) {
            int currentIndex = selectionHelper(sortedList, i, sortedList.size());
            if (sortedList.get(currentIndex) != sortedList.get(i)) {
                int swapNum = sortedList.get(i);
                sortedList.set(i, sortedList.get(currentIndex));
                sortedList.set(currentIndex, swapNum);
            } //if
        }//for
        return unsortedList;
    }//selectionSort

    /**
     * This method searches for the smallest value in part of an ArrayList. It
     * is a helper function for selectionSort().
     *
     * @param someList the ArrayList of integers to search
     * @param min the minimum index to search from
     * @param max the maximum index to search to
     * @return the index of the smallest number in the ArrayList
     */
    public static int selectionHelper(ArrayList<Integer> someList, int min,
            int max) {
        int index = min;
        int currentmin = someList.get(min);
        for (int i = min + 1; i < max; i++) {
            if (someList.get(i) < currentmin) {
                index = i;
                currentmin = someList.get(i);
            } //if
        } //for
        return index;
    }

    // TO-DO: Define a method that sorts a list
    // of integers using the insertion sort algorithm.
    // TO-DO: Define a method that sorts a list
    // of integers using the merge sort algorithm.
    public static void main(String[] args) {
        System.out.println("Searching and sorting algorithms");
        ArrayList<Integer> someList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            someList.add(RNG.nextInt(20));
            System.out.print(someList.get(i) + " ");
        }
        ArrayList<Integer> sortedList = selectionSort(someList);
        System.out.println();
        for (int i=0; i < sortedList.size(); i++){
            System.out.print(sortedList.get(i)+" ");
        }//for
        
        // TO-DO: Add code that tests the searching and sorting
        // methods.
    } // main( String [] )
} // SearchAndSort
