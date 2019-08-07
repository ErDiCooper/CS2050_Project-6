/*
   CS2050 - Computer Science 2
   Dejnag Vang & Eric DiCarlo
   Project 6 - Shell & Quick Sorting
   Windows 10, Desktop, jGRASP
   importune - verb - to press or urge with troublesome persistence.
   "Home is what you take with you, not what you leave behind." - N.K. Jemisin (1972 - )
*/

import java.io.*;
import java.util.Scanner;

public class Dejnag_Eric_Project06 
{

   private static final int MAX_ARRAY = 100; // The max array size.
   private static int[] shellSortArray = new int[MAX_ARRAY]; // The array to be Shell sorted.
   private static int[] quickSortArray = new int[MAX_ARRAY]; // The array to be Quick sorted.
   private static int[] tempArray;


   public static void main (String[] args) throws IOException 
   {
      int counter = 0; // A counter to be used while reading the input file.
   
      final String INPUT_FILE = "2050_Project06_Input.txt"; // The name of the input file.
      final String OUTPUT_1 = "Dejnag_Eric_Output1.txt"; // The name of the first output file.
      final String OUTPUT_2 = "Dejnag_Eric_Output2.txt"; // The name of the second input file.
      
      File inputFile = new File(INPUT_FILE); // The input file.
      File outputFile1 = new File(OUTPUT_1); // The first output file.
      File outputFile2 = new File(OUTPUT_2); // The second output file.
      
      Scanner scnr = new Scanner(inputFile); // Reads the input file.
      
      // Read the input file to shellSortArray.
      while (scnr.hasNext()) 
      {
         shellSortArray[counter] = scnr.nextInt();
         counter++;
      }
      
      // Copy the contents of shellSortArray to quickSortArray.
      quickSortArray = shellSortArray;
      
      // Run shellSort method.
      shellSortArray = shellSort(shellSortArray);     
      
      // Print shellSortArray.
      PrintWriter output1 = new PrintWriter(new FileWriter(OUTPUT_1)); // Writes to the first output file.
      for (int i = 0; i < shellSortArray.length; i++) 
      {
         output1.println(shellSortArray[i]);
      }
      output1.close();
      

      // Run quickSort method.
      tempArray = quickSortArray;
      Dejnag_Eric_Project06 ob = new Dejnag_Eric_Project06();
	   int n = quickSortArray.length; 
	   ob.quickSort(0, n-1); 
      
      // Print quickSortArray.
      PrintWriter output2 = new PrintWriter(new FileWriter(OUTPUT_2)); // Writes to the second output file.
      for (int a = 0; a < n; a++) 
      {
         output2.println(quickSortArray[a]);
      }
      output2.close();
       
   } // End of main.
   
   //*****************************************************************************************

   /**
     * shellSort - Sorts an array using the Shell sorting algorithm.
     *
     * @param array - The array that you want to sort.
     * @return array - The sorted array!
   */
   public static int[] shellSort (int[] array) 
   {
      // Begin Shell sort from default state.
      // Determine the size of the gap for each pass through.
      for (int gap = array.length/2; gap > 0; gap /= 2) 
      { 
         // Get a chunk of the array for sorting.
         for (int i = gap; i < array.length; i += 1) 
         { 
             int temp = array[i]; 
  
             // Shift the elements of the chunk to be sorted.
             int j;
             for (j = i; j >= gap && array[j - gap] > temp; j -= gap)
             { 
                 array[j] = array[j - gap]; 
             }
             
             array[j] = temp; 
         } 
      }
      return array;
   } // End of shellSort.
   
   //*****************************************************************************************   
   
   /**
    * This method sorts an array
    */
   public static void sort(){
       int first = 0;
       int last = tempArray.length-1;
            
       quickSort(first, last);
   }
   
   //*****************************************************************************************
   
   /**
    * This method is used to sort the array using quicksort algorithm.
    * @param int first value of first position
    * @param int last value of last position
    */
   private static void quickSort(int first,int last)
   {
        
       // If array is completely scanned, exit.
       if(first >= last)
       {
           return;
       }
        
       // Pivot using median of 3 
       int pivot = getMedian(first, last);
       int partition = partition(first, last, pivot);
        
       // Recursively, call the quick sort until the array is completely sorted
       quickSort(0, partition-1);
       quickSort(partition+1, last);
   }
   
   //*****************************************************************************************
    
   /**
    * This method is used to partition the given array 
    * @param int first value of first position
    * @param int last value of last position
    * @param int pivot value which is the middle position
    * @return the integer that points to the sorted pivot index
    */
   private static int partition(int first,int last,int pivot)
   {
       int firstCursor = first-1;
       int lastCursor = last;
       while(firstCursor < lastCursor){
       while(tempArray[++firstCursor] < pivot);
       while(lastCursor > 0 && tempArray[--lastCursor] > pivot);
           if(firstCursor >= lastCursor)
           {
               break;
           }else{
               swap(firstCursor, lastCursor);
           }
       }
       swap(firstCursor, last);
       return firstCursor;
   }
   
   //*****************************************************************************************
    
   /**
    * The purpose of this program is to pick the first, middle
    * and last values of the array positions and sort them according 
    * to their values.
    * @param int first value of first position
    * @param int last value of last position
    * @return the temporary array with the pointed first, middle and last
    */
   public static int getMedian(int first,int last)
   {
       int center = (first+last)/2;
        
       if(tempArray[first] > tempArray[center])
           swap(first,center);
        
       if(tempArray[first] > tempArray[last])
           swap(first, last);
        
       if(tempArray[center] > tempArray[last])
           swap(center, last);
        
       swap(center, last);
       return tempArray[last];
   }
   
   //*****************************************************************************************
    
   /**
    * This method is used to swap the values between the two given index
    * @param int first value of first position
    * @param int last value of last position
    */
   public static void swap(int first,int last)
   {
       int temp = tempArray[first];
       tempArray[first] = tempArray[last];
       tempArray[last] = temp;
   }
} 
