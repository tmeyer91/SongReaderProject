//*********************************************************
//Edward "Ted "Meyer
//September 9, 2017
//Project 3 - SongReader
//CMSC 256-901
//Description:
//This is the MySong subclass. It inherits the methods from the
//Song class and overrides the compareTo and equals methods
//to include playcount and compare it.
//*********************************************************
public class MySong extends Song{
	private int playcount;
	
	public MySong()
	{
		super();
		playcount = 0;
	}
	public MySong(String title, String artist, String album, int playcount)
	{
		super(title, artist, album);
		setPlaycount(playcount);
	}
	public void setPlaycount(int playcount)
	{
		this.playcount = playcount;
	}
	public int getPlaycount()
	{
		return this.playcount;
	}
	public String toString()
	{
		String display = super.toString();
		display = display + "\n" + "Playcount: " + playcount + "\n";
		return display;
	}
	public boolean eqauls (MySong other)
	{
		super.equals(other);
		if(playcount != other.getPlaycount())
		{
			return false;
		}
		return true;
	}
	public int compareTo(MySong other)
	{
		if(playcount - other.getPlaycount() > 0)
		{
			return 1;
		}
		if(playcount - other.getPlaycount() < 0)
		{
			return -1;
		}
		else
		{
			return super.compareTo(other);
		}
	}
}
