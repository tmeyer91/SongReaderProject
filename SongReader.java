//*********************************************************
//Edward "Ted "Meyer
//September 9, 2017
//Project 3 - SongReader
//CMSC 256-901
//Description:
//This program reads the file from the MySong class and reads 
//through a text document and if the song has valid tags it 
//produces the MySong and adds it to the array list and then 
//returns it. It also contains the methods for reading the file 
//*********************************************************
import java.util.Scanner;
import java.io.*;
import java.util.EmptyStackException;
import java.util.ArrayList;
public class SongReader 
{
		public ArrayList<MySong> readFile(Scanner fileReader)
		{
			//stacks for separating tags and contents
			SongStack songContents = new SongStack();
			SongStack songTags = new SongStack();
			//default strings for title, artist, and album
			String title = "";
			String artist = "";
			String album = "";
			String playcountWord = "";
			int playcount = 0;
			//counters to ensure only valid contents are used
			int contentsCounter = 0;
			int contentsStackCounter = 0;
			boolean validTag = false;
			boolean titleExists = false;
			boolean artistExists = false;
			boolean albumExists = false;
			boolean playcountExists = false;
			boolean validPlaycount = false;
			//Array list for valid songs
			ArrayList<MySong> songList = new ArrayList<MySong>();
			while(fileReader.hasNextLine())//while loop to traverse the document
			{
				String temp = fileReader.nextLine().trim();
//				System.out.println(temp);
//				System.out.println(temp);
				if(temp.equals(""))//sets empty temp strings to null to prevent them from being added to the songContents
				{
					temp = "null";
				}
				if(temp.equalsIgnoreCase("<song>"))
				{
					songTags.push(temp);
				}
				else if(temp.equalsIgnoreCase("<title>") || temp.equalsIgnoreCase("<artist>") || temp.equalsIgnoreCase("<album>") || (temp.equalsIgnoreCase("<playcount>")))
				{
					if( temp.equalsIgnoreCase("<playcount>"))
					{
						playcountExists = true;
					}
					songTags.push(temp);
					validTag = true;//sets valid tag to true to ensure contents are only removed when a valid tag is present
				}
				else if(temp.equalsIgnoreCase("</title>"))
				{
					if(songTags.peek().equalsIgnoreCase("<title>"))
					{
						//removes the opening title tag
						songTags.pop();
						validTag = false;
						contentsStackCounter = 0;//stack contents counter reset to allow another entry
						//pops from songContents stack and assigns the title it's value.
						if(songTags.peek().equalsIgnoreCase("<song>") && songContents.isEmpty() == false)
						{
							if(titleExists == false)
							{
								title = songContents.pop();
								contentsCounter++;
								titleExists = true;
							}
							else
							{
								songContents.pop();
							}
						}
						
					}
				}
				else if(temp.equalsIgnoreCase("</artist>"))
				{
					if(songTags.peek().equalsIgnoreCase("<artist>"))
					{
						//removes the opening artist tag
						songTags.pop();
						validTag = false;
						contentsStackCounter = 0;//stack contents counter reset to allow another entry
						//pops from songContents stack and assigns the artist it's value.
						if(songTags.peek().equalsIgnoreCase("<song>") && songContents.isEmpty() == false)
						{
							if(artistExists == false)
							{
								artist = songContents.pop();
								contentsCounter++;
							}
							else
							{
								songContents.pop();
							}	
						}
					}
				}
				else if(temp.equalsIgnoreCase("</album>"))
				{
					if(songTags.peek().equalsIgnoreCase("<album>"))
					{
						//removes the opening album tag
						songTags.pop();
						validTag = false;
						contentsStackCounter = 0;//stack contents counter reset to allow another entry
						//pops from songContents stack and assigns the album it's value.
						if(songTags.peek().equalsIgnoreCase("<song>") && songContents.isEmpty() == false)
						{
							if(albumExists == false)
							{
								album = songContents.pop();
								contentsCounter++;
								albumExists = true;
							}
							else
							{
								songContents.pop();
							}
						}	
					}
				}
				else if(temp.equalsIgnoreCase("</playcount>"))
				{
					if(songTags.peek().equalsIgnoreCase("<playcount>"))
					{
						//removes the opening playcount tag
						songTags.pop();
						validTag = false;
						contentsStackCounter = 0;//stack contents counter reset to allow another entry
						//pops from songContents stack and assigns the playcount it's value.
						if(songTags.peek().equalsIgnoreCase("<song>"))
						{	
							if(playcountExists == true)
							{
								if(!(songContents.isEmpty()))
								{
									playcountWord = songContents.pop();
									try
									{
										playcount = Integer.parseInt(playcountWord);
									}
									catch (NumberFormatException exception)
									{
										validPlaycount = false;
									}
								}
								else
								{
									playcount = 0;
								}
								if(playcount > -1)
								{
									validPlaycount = true;
								}
								else
								{
									validPlaycount = false;
								}
								
							}
							else
							{
								songContents.pop();
							}
						}	
					}
				}
				else if(temp.equalsIgnoreCase("</song>"))
				{
					
					//creates a song class if everything is valid and clears the stack
					if(songTags.peek().equalsIgnoreCase("<song>") && contentsCounter == 3 && (validPlaycount == true || playcountExists == false))
					{
						MySong tempSong = new MySong(title, artist, album, playcount);
						songList.add(tempSong);
						songTags.clear();
						songContents.clear();
					}
					contentsCounter=0;
					songTags.clear();
					songContents.clear();
					titleExists = false;
					artistExists = false;
					albumExists = false;
					validPlaycount = false;
					playcountExists = false;
					playcount = 0;
				}
				//adds a value to the contents stack only if the stack counter is 0 and a valid tag exists in the tag stack and entry is not null
				else if(contentsStackCounter == 0 && validTag == true && !(temp.equals("null")))
				{
					songContents.push(temp);
					contentsStackCounter++;
				}
			}
			return songList;
		}

	    public String promptForFileName(Scanner keyIn) 
	    {
	         System.out.println("Enter the file name: ");
	         String entry = keyIn.next();
	         return entry;
	     }
	    public String promptForSelection(Scanner keyIn)
	    {
	    	boolean validSelection = false;
	    	String entry = "";
	    	while (!validSelection)
	    	{
	    		System.out.println("Enter your selection 1,2, or 3: ");
	    		entry = keyIn.next();
	    		if(entry.equals("1") || entry.equals("2") || entry.equals("3"))
	    		{
	    			validSelection = true;
	    		}
	    		else
	    		{
	    			System.out.println("Invalid Selection");
	    		}
	    	}
	    		return entry;
	    }
	    public String promptForArtist(Scanner keyIn)
	    {
	    	System.out.println("Enter an artist name: ");
	         String entry = keyIn.next();
	         return entry;
	    }
	  public  Scanner openFile(String fileName,Scanner keyIn) throws FileNotFoundException 
	  {
	      File file = new File(fileName);
	      while (!file.exists()) 
	      {
	          file = new File(promptForFileName(keyIn));
	      }
	      return new Scanner(file);
	  }
	private static class SongStack implements StackInterface<String>
	{
		private Node topNode;
		public SongStack()
		{
			topNode = null;
		}
		public void push(String newEntry) 
		{
			topNode = new Node(newEntry, topNode);
		}
		public String pop() 
		{
			String top = peek();
			assert (topNode != null);
			topNode = topNode.getNextNode();
			return top;
		}
		public String peek() 
		{
			if(isEmpty() == true)
			{
				throw new EmptyStackException();
			}
			return topNode.getData();
		}
		public boolean isEmpty() 
		{
			return topNode == null;
		}
		public void clear() 
		{
			topNode = null;
		}
		private class Node
		{
	      private String data; // Entry in stack
	      private Node next; // Link to next node

	      private Node(String dataPortion)
	      {
	         this(dataPortion, null);
	      } // end constructor

	      private Node(String dataPortion, Node linkPortion)
	      {
	         data = dataPortion;
	         next = linkPortion;	
	      } // end constructor

	      private String getData()
	      {
	         return data;
	      } // end getData

	      @SuppressWarnings("unused")
		private void setData(String newData)
	      {
	         data = newData;
	      } // end setData

	      private Node getNextNode()
	      {
	         return next;
	      } // end getNextNode

	      @SuppressWarnings("unused")
		private void setNextNode(Node nextNode)
	      {
	         next = nextNode;
	      } // end setNextNode
		} // end Node
	}
}

