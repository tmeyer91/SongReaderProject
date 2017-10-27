//*********************************************************
//Edward "Ted "Meyer
//September 9, 2017
//Project 3 - SongReader
//CMSC 256-901
//Description:
//This is the main method for the program. It reads the command
//line arguments and prompts for anything missing such as a 
//file or a missing selection, or a missing artist name. It then
//preforms the functions either display the top 10 based on playcount
//or confirm if there is a artist present in the playlist or organize
//and sort by album then by title.
//*********************************************************
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicManager {

	public static void main(String[] args) 
	{	
		SongReader songRead = new SongReader();
		printHeading("Song Reader Part 2", 3);
		 try
		 {
	        // check and read command line argument for file name
	          String fileName = "";
	          String selection;
	          String artist;
	          boolean validSelection = false;
	          boolean validArtist = false;
	          Scanner keyIn = new Scanner(System.in);
	          if (args.length == 0) 
	          {
	        	  fileName = songRead.promptForFileName(keyIn);// prompts for file from the 
	          }
	          else
	          {
	              fileName = args[0];
	          }
	          Scanner fileReader = songRead.openFile(fileName, keyIn);
	          ArrayList<MySong> songList = songRead.readFile(fileReader);
	          if(args.length < 2)
	          {
	        	  selection = songRead.promptForSelection(keyIn);
	          }
	          else
	          {
	        	  selection = args[1];
	          }
	          while(!validSelection)
	          {
	        	  if (selection.equals("1"))
	        	  {
	        		  commandLineOne(songList);
	        		  validSelection = true;
	        	  }
	        	  else if (selection.equals("2"))
	        	  {
	        		  validSelection = true;
	        		  while(!validArtist)
	        		  {
	        			  if(args.length < 3)
	        			  {
	        				  artist = songRead.promptForArtist(keyIn);
	        				  commandLineTwo(songList, artist);
	        				  validArtist = true;
	        			  }
	        			  else
	        			  {
	        				  artist = args[2];
        					  commandLineTwo(songList, artist);
        					  validArtist = true;
        				  }
	        		  	}
	            	  }
	            	  else if (selection.equals("3"))
	            	  {
	            		  validSelection = true;
	            		  while(!validArtist)
		        		  {
		        			  if(args.length < 3)
		        			  {
		        				  artist = songRead.promptForArtist(keyIn);
		        				  commandLineThree(songList, artist);
		        				  validArtist = true;
		        			  }
		        			  else
		        			  {
		        				  artist = args[2];
	        					  commandLineThree(songList, artist);
	        					  validArtist = true;
	        				  }
		        		  }
	            	  	}
	            	  else
	            	  {
	            		  selection = songRead.promptForSelection(keyIn);
	            	  }
	        	  keyIn.close();
	              	}
		 } 
		 catch(FileNotFoundException noFile)
		 {
		 	System.out.println("There was an error opening or reading from the file.");
		 }
	}
	private static void printHeading(String projectName, int projectNumber)
	{
    System.out.println("Edward Meyer");
    System.out.println("CMSC 256-901");
    System.out.println("Project " + projectNumber + " - " + projectName);
    System.out.println("Fall 2017");
    System.out.println();
  //method to display heading for project
	}
	private static void commandLineOne(ArrayList<MySong> songList)
	{
		QuickSort.quickSort(songList, songList.size());
		if(songList.size() < 10 && songList.size() > 0)
		{
			for (int index = songList.size()-1; index > -1; index--)
			{
				System.out.println(songList.get(index));
			}
		}
		else if(songList.size() > 0)
		{
			for(int index = 10; index >= 0; index--)
			{
				System.out.println(songList.get(index));
			}
		}
	}
	private static void commandLineTwo(ArrayList<MySong> songList, String artistName)
	{
		artistName = artistName.trim();
		boolean found = false;
		int index = 0;
		while((!found) && index < songList.size())
		{
			if(songList.get(index).getArtist().equalsIgnoreCase(artistName))
			{
				System.out.println("The artist " + artistName + " exists in the playlist.");
				found = true;
			}
			else
			{
				index++;
			}
		}
			if(found == false)
			{
				System.out.println("The artist " + artistName + " does not exist in the playlist.");
			}
	}
	private static void commandLineThree(ArrayList<MySong> songList, String artistName)
	{
		ArrayList<Song> sortByAlbum = new ArrayList<Song>();
		for (int index = 0; index < songList.size(); index++)
		{
			if(songList.get(index).getArtist().equals(artistName))
			{
				Song temp = (Song) songList.get(index);
				sortByAlbum.add(temp);
			}
		}
		InsertionSort.insertionSort(sortByAlbum, sortByAlbum.size());
		for(int index = 0; index < sortByAlbum.size(); index++)
		{
			System.out.println(sortByAlbum.get(index));
		}
	}
private static class InsertionSort
{
	// INSERTION SORT
		public static void insertionSort(ArrayList<Song> a, int n) {
			insertionSort(a, 0, n - 1);
		} 
	   
		public static void insertionSort(ArrayList<Song> a, int first, int last)	{
			for (int unsorted = first + 1; unsorted <= last; unsorted++){   
			
				Song firstUnsorted = a.get(unsorted);
				
				insertInOrder(firstUnsorted, a, first, unsorted - 1);
			} 
		} 
		private static void insertInOrder(Song anEntry, ArrayList<Song> a, int begin, int end){
	      int index = end;
	      
			while ((index >= begin) && (anEntry.compareTo(a.get(index)) < 0)){
				a.set(index + 1, a.get(index)); // Make room
	         index--;
			} 
			
			// Assertion: a[index + 1] is available
			a.set(index + 1, anEntry);  // Insert
		} 
}
private static class QuickSort
{
	// QUICK SORT
	   /** Sorts an array into ascending order. Uses quick sort with
	       median-of-three pivot selection for arrays of at least
	       MIN_SIZE entries, and uses insertion sort for other arrays. */
	private static void quickSort(ArrayList<MySong> array, int n)
		{
			quickSort(array, 0, n - 1);
		} // end quickSort

	private static void quickSort(ArrayList<MySong> a, int first, int last)
	   {
		final int MIN_SIZE = 5;
	      if (last - first + 1 < MIN_SIZE)
	      {
	         insertionSort(a, first, last);
	      }
	      else
	      {
	         // Create the partition: Smaller | Pivot | Larger
	         int pivotIndex = partition(a, first, last);

	         // Sort subarrays Smaller and Larger
	         quickSort(a, first, pivotIndex - 1);
	         quickSort(a, pivotIndex + 1, last);
	      }  
	   } // end quickSort

	   //  Partitions an array as part of quick sort into two subarrays
	   //  called Smaller and Larger that are separated by a single
	   //  entry called the pivot.
	   //  Entries in Smaller are <= pivot and appear before the
	   //  pivot in the array.
	   //  Entries in Larger are >= pivot and appear after the
	   //  pivot in the array.
	   //  Parameters:
	   //     a      An array of Comparable objects.
	   //     first  The integer index of the first array entry;
	   //            first >= 0 and < a.length.
	   //     last   The integer index of the last array entry;
	   //            last - first >= 3; last < a.length.
	   //   Returns the index of the pivot.
	private static int partition(ArrayList<MySong> a, int first, int last)
	   {
	      int mid = first + (last - first) / 2;
	      sortFirstMiddleLast(a, first, mid, last);

	      // Assertion: The pivot is a[mid]; a[first] <= pivot and 
	      // a[last] >= pivot, so do not compare these two array entries
	      // with pivot.

	      // Move pivot to next-to-last position in array
	      swap(a, mid, last - 1);
	      int pivotIndex = last - 1;
	      MySong pivotValue = a.get(pivotIndex);

	      // Determine subarrays Smaller = a[first..endSmaller]
	      // and                 Larger  = a[endSmaller+1..last-1]
	      // such that entries in Smaller are <= pivotValue and
	      // entries in Larger are >= pivotValue; initially, these subarrays are empty

	      int indexFromLeft = first + 1; 
	      int indexFromRight = last - 2;

	      boolean done = false;
	      while (!done)
	      {
	         // Starting at beginning of array, leave entries that are < pivotValue;
	         // locate first entry that is >= pivotValue; you will find one,
	         // since last entry is >= pivot
	         while (a.get(indexFromLeft).compareTo(pivotValue) < 0)
	            indexFromLeft++;

	         // Starting at end of array, leave entries that are > pivot;
	         // locate first entry that is <= pivot; you will find one, 
	         // since first entry is <= pivot

	         while (a.get(indexFromRight).compareTo(pivotValue) > 0)
	            indexFromRight--;

	         assert a.get(indexFromLeft).compareTo(pivotValue) >= 0 &&
	                a.get(indexFromRight).compareTo(pivotValue) <= 0;
	              
	         if (indexFromLeft < indexFromRight)
	         {
	            swap(a, indexFromLeft, indexFromRight);
	            indexFromLeft++;
	            indexFromRight--;
	         }
	         else 
	            done = true;
	      }  

	      // Place pivotValue between the subarrays Smaller and Larger
	      swap(a, pivotIndex, indexFromLeft);
	      pivotIndex = indexFromLeft;

	      // Assertion:
	      //   Smaller = a[first..pivotIndex-1]
	      //   Pivot = a[pivotIndex]
	      //   Larger = a[pivotIndex+1..last]

	      return pivotIndex; 
	   } // end partition

	   //  Sorts the first, middle, and last entries of an array into ascending order.
	   //  Parameters:
	   //     a      An array of Comparable objects.
	   //     first  The integer index of the first array entry;
	   //            first >= 0 and < a.length.
	   //     mid    The integer index of the middle array entry.
	   //     last   The integer index of the last array entry;
	   //            last - first >= 2, last < a.length.
	   private static void sortFirstMiddleLast(ArrayList<MySong> a, int first, int mid, int last)
	   {
	      order(a, first, mid); // Make a[first] <= a[mid]
	      order(a, mid, last);  // Make a[mid] <= a[last]
	      order(a, first, mid); // Make a[first] <= a[mid]
	   } // end sortFirstMiddleLast

	   // Orders two given array elements into ascending order
	   // so that a[i] <= a[j].
	   private static void order(ArrayList<MySong> a, int i, int j)
	   {
	      if (a.get(i).compareTo(a.get(j)) > 0)
	         swap(a, i, j);
	   } // end order

	   // Swaps the array entries array[i] and array[j]. 
	   private static void swap(ArrayList<MySong> array, int i, int j)
	   {
	      MySong temp = array.get(i);
	      array.set(i, array.get(j));
	      array.set(j, temp);
	   } // end swap
	 // end ArraySorter
	   public static void insertionSort(ArrayList<MySong> a, int first, int last)
	   {
		   if (first < last)
		   {
			// Sort all but the last entry
			insertionSort(a, first, last - 1);

			// Insert the last entry in sorted order
			insertInOrder(a.get(last), a, first, last - 1); 
		   }  
	   } // end insertionSort
	   private static void insertInOrder(MySong element, ArrayList<MySong> a, int begin, int end)
	   {	
		   if (element.compareTo(a.get(end)) >= 0)
			   a.set(end + 1,element);
		   else if (begin < end)
		   {
			   a.set(end + 1, a.get(end));
			   insertInOrder(element, a, begin, end - 1);
		   } 
		   else
		   {
			   a.set(end + 1, a.get(end));
			   a.set(end,element);
		   }  
	   } // end insertInOrder

	}
}


