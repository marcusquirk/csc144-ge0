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

    // TO-DO: Define a method that sorts a list
    // of integers using the merge sort algorithm.
    
    
    /**
     * The main method allows the user to test the different searching and
     * sorting algorithms. It generates a random ArrayList of 15 elements, each
     * a random integer from 1-19. It then asks the user what kind of method
     * they would like to test. If the input is not recognised, the user is
     * recommended to input 'h' to receive help about what inputs are valid.
     * The valid inputs allow the user to test insertion sort, selection sort,
     * merge sort, linear search, and binary search.
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
