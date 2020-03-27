package searchandsort;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
     * unsorted integers that matches a given integer. The method should return
     * -1 if no match is found. It uses a sequential search algorithm. Although
     * its time complexity performance is quite poor, and it can be used on an
     * unsorted list of integers (unlike binary search).
     *
     * @param valuesList a list of integers to be searched.
     * @param value an integer to search for.
     * @return an index; the index in the list of the desired value.
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
     * This method determines the index of a desired integer in a list of sorted
     * integers that matches a given integer, using binary search. The method
     * should return -1 if no match is found. It has a better time complexity
     * performance than getIndex (sequential or linear search), but it requires
     * that the list that is being searched is sorted in ascending order.
     *
     * @param valuesList a sorted list of integers (ascending) to be searched.
     * @param value an integer to search for.
     * @return an index; the index in the list of the desired value.
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
     * This method performs selection sort on an ArrayList of integers. In the
     * first pass-through, the smallest number will be sorted to the first
     * position in the array list. After the second pass, the first two
     * positions will be correct; after the third, the first three positions
     * will be correct; and so on and so forth. It has a worst-case time
     * complexity of O(n^2).
     *
     * @param unsortedList an unsorted ArrayList.
     * @return a sorted ArrayList, in ascending order.
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
     * This method searches for the smallest value in part (from index min to
     * index max) of an ArrayList. It is a helper method for selectionSort().
     *
     * @param someList an ArrayList of integers to search.
     * @param min a minimum index to search from.
     * @param max a maximum index to search to.
     * @return an index; the index of the smallest number in the ArrayList
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

    /**
     * This method performs insertion sort on an ArrayList of integers. In the
     * first pass-through, the first two positions will be sorted correctly
     * relatively to themselves. After the second pass, the first three
     * positions will be correct relative to themselves; after the third, the
     * first four positions will be correct relative to themselves; and so on and
     * so forth. For example, when the first seven positions are correctly sorted,
     * and on the next pass the eighth value is smaller than any of them, it will
     * be swapped with each value in turn until it is in its correct position in
     * the first position in the ArrayList. Insertion sort has a worst-case time
     * complexity of O(n^2).
     *
     * @param unsortedList an unsorted ArrayList.
     * @return a sorted ArrayList, in ascending order.
     */
    public static ArrayList<Integer> insertionSort(
            ArrayList<Integer> unsortedList) {
        ArrayList<Integer> sortedList = unsortedList;
        for (int i = 0; i < sortedList.size() - 1; i++) {
            boolean continueChecking = true;
            int currentIndex = i;
            while (continueChecking) {
                continueChecking = Swap(sortedList, currentIndex);
                if (currentIndex > 0) {
                    currentIndex--;
                }//if
                else {
                    continueChecking = false;
                }//else
            }//while
        }//if
        return unsortedList;
    }
 
    /**
     * This is a helper method for insertion sort that assesses whether two values
     * in an ArrayList ought to be swapped; if so, it swaps them. If it performs
     * the swap, it returns a Boolean true, if it does not, it returns a Boolean
     * false. This helps the insertionSort method determine whether to ask the
     * Swap method to do a subsequent swap; if Swap() returns a false value to
     * insertionSort, it knows that the value is now its correct position within
     * the ArrayList.
     * 
     * @param someList an ArrayList, in which two values must be swapped
     * @param firstIndex the index of the first value of a pair to be (assessed
     * and then depending on the result) swapped.
     * @return an ArrayList, in which the two values specified will be swapped
     * if the first of the two values was greater than the second.
     */
    public static boolean Swap(ArrayList<Integer> someList, int firstIndex) {
        if (someList.get(firstIndex) > someList.get(firstIndex + 1)) {
            int helper = someList.get(firstIndex);
            someList.set(firstIndex, firstIndex + 1);
            someList.set(firstIndex + 1, helper);
            return true;
        }//If
        else {
            return false;
        }//Else

    }//Swap

    /**
     * This is a helper method that handles the merging of two sorted sublists
     * in mergeSort. It loops from i to max, each time choosing the value at
     * index i from either the first or second sorted sublist. The value chosen
     * will be whichever value is lowest. Once it has finished selecting all
     * values from either the first or second sublist, the rest of the merge is
     * completed by filling in all of the remaining values from the other
     * sublist.
     *
     * @param unmergedList the unmerged ArrayList. It should have two unmerged,
     * sorted parts to be merged together.
     * @param min the index of the beginning of the first sorted part.
     * @param mid the index of the beginning of the second sorted part.
     * @param max the index ( +1 ) of the end of the second sorted part.
     * @return an ArrayList that is sorted in ascending order (for indices min
     * to max).
     */
    public static ArrayList<Integer> merge(ArrayList<Integer> unmergedList,
            int min, int mid, int max) {
        ArrayList<Integer> mergedList = (ArrayList<Integer>) unmergedList.clone();
        int j = min;
        int k = mid;
        int i = min;
        while (j < mid && k < max) {
            if (unmergedList.get(j) < unmergedList.get(k)) {
                mergedList.set(i, unmergedList.get(j));
                j++;
                i++;
            } else {
                mergedList.set(i, unmergedList.get(k));
                k++;
                i++;
            } //if-else
        } //while

        while (j < mid) {
            mergedList.set(i, unmergedList.get(j));
            j++;
            i++;
        } //while

        while (k < max) {
            mergedList.set(i, unmergedList.get(k));
            k++;
            i++;
        } //while
        return mergedList;
    } //merge

    /**
     * This method performs a merge sort on an ArrayList of integers, using
     * recursion.
     *
     * @param unsortedList the ArrayList of integers to be sorted.
     * @param min the minimum index to sort from. At the top level, this will
     * usually be index 0. For recursion within the method, it could take any
     * index in the unsorted list.
     * @param max the maximum index to sort to. At the top level, this will
     * usually the size of the ArrayList. For recursion within the method, it
     * could take any index in the unsorted list.
     * @return a sorted ArrayList (for indices min to max).
     */
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> unsortedList,
            int min, int max) {
        if (min == max - 1) {
            return unsortedList;
        }
        int mid = (max + min) / 2;
        ArrayList<Integer> sortedList = mergeSort(unsortedList, min, mid);
        sortedList = mergeSort(sortedList, mid, max);
        sortedList = merge(sortedList, min, mid, max);
        return sortedList;
    }

    /**
     * The main method allows the user to test the different searching and
     * sorting algorithms. It generates a random ArrayList of 15 elements, each
     * a random integer from 1-19. It then asks the user what kind of method
     * they would like to test. If the input is not recognised, the user is
     * recommended to input 'h' to receive help about what inputs are valid. The
     * valid inputs allow the user to test insertion sort, selection sort, merge
     * sort, linear search, and binary search.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Searching and sorting algorithms");
        System.out.println("A random ArrayList has been generated:");
        ArrayList<Integer> someList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            someList.add(RNG.nextInt(20));
            System.out.print(someList.get(i) + " ");
        }

        Scanner input = new Scanner(System.in); //create the scanner object called input

        boolean keepLooping = true; //keep looping so the user doesn't need 
        //to restart the program to try again
        while (keepLooping) {
            System.out.println("");
            System.out.println("What method would you like to test?");
            //ask for input
            String action = input.nextLine(); //wait for user to input an string
            switch (action.toLowerCase()) {
                case "h":
                case "help":
                    System.out.println(
                            "Type 'I' or 'Insertion' to perform an insertion sort \n"
                            + "Type 'S' or 'Selection' to perform a selection sort \n"
                            + "Type 'M' or 'Merge' to perform a merge sort \n"
                            + "Type 'F', 'Find', or 'Search' to perform a linear search \n"
                            + "Type 'B' or 'Binary' to perform a binary search "
                            + "(the ArrayList will be sorted for you)\n"
                            + "+Type 'Bye' to stop the program");
                    break;
                case "f":
                case "find":
                case "search":
                case "linear search":
                    System.out.print("Choose a number: "); //ask for input
                    int num = input.nextInt(); //wait for user to input an double
                    System.out.println("The index of " + num + " in the ArrayList is "
                            + getIndex(someList, num));
                    break;
                case "insertion":
                case "i":
                case "insertion sort":
                    ArrayList<Integer> sortedList = insertionSort(someList);
                    System.out.println("Insertion:");
                    for (int i = 0; i < sortedList.size(); i++) {
                        System.out.print(sortedList.get(i) + " ");
                    }//for
                    System.out.println();
                    break;
                case "s":
                case "selection":
                case "selection sort":
                    sortedList = selectionSort(someList);
                    System.out.println("Selection:");
                    for (int i = 0; i < sortedList.size(); i++) {
                        System.out.print(sortedList.get(i) + " ");
                    }//for
                    break;
                case "b":
                case "binary":
                case "binary search":
                case "binary find":
                    sortedList = selectionSort(someList);
                    System.out.println("Sorted list:");
                    for (int i = 0; i < sortedList.size(); i++) {
                        System.out.print(sortedList.get(i) + " ");
                    }//for
                    System.out.println();
                    System.out.println("Choose a number: "); //ask for input
                    num = input.nextInt(); //wait for user to input an integer
                    System.out.println("The index of " + num + " in the sorted ArrayList is "
                            + getIndexBinary(someList, num, 0, someList.size()));
                    break;
                case "m":
                case "merge":
                case "merge sort":
                    sortedList = mergeSort(someList, 0, someList.size());
                    System.out.println("Sorted list:");
                    for (int i = 0; i < sortedList.size(); i++) {
                        System.out.print(sortedList.get(i) + " ");

                    }//for
                    break;
                case "no":
                case "bye":
                case "goodbye":
                case "good-bye":
                    keepLooping = false;
                    break;
                default:
                    System.out.println("Invalid input! Type 'h' for help.");
                    break;
            }//switch
        } // while
    } // main( String [] )

} // SearchAndSort
