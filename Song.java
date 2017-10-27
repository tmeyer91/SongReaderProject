//*********************************************************
//Edward "Ted "Meyer
//September 9, 2017
//Project 3 - SongReader
//CMSC 256-901
//Description:
//This is the Song superclass which has an artist, title, and album
//string variable. It also has an overridden equals method and
//an overridden compareTo method to compare the two Song objects.
//*********************************************************
import java.lang.IllegalArgumentException;
public class Song implements Comparable<Song>{

private String title;
private String artist;
private String album;

public Song()
{
	title = "";
	artist = "";
	album = "";
}
public Song(String songTitle, String artist, String album)
{
	setSongTitle(songTitle);
	setArtist(artist);
	setAlbum(album);
}
public void setSongTitle(String songTitle) throws IllegalArgumentException
{
	if(songTitle.equals(null))
	{
		throw new IllegalArgumentException();
	}
	this.title = songTitle;
}
public void setArtist (String artist)
{
	if(artist.equals(null))
	{
		throw new IllegalArgumentException();
	}
	this.artist = artist;
}
public void setAlbum(String album)
{
	if(album.equals(null))
	{
		throw new IllegalArgumentException();
	}
	this.album = album;
}
public String getSongTitle()
{
	return title;
}
public String getArtist()
{
	return artist;
}
public String getAlbum()
{
	return album;
}
//Overridden equals method
public boolean equals (Song other)
{
	if(this == other)
	{
		return true;
	}
	if(other == null)
	{
		return false;
	}
	if(getClass() != other.getClass())
	{
		return false;
	}
	Song otherSong = (Song) other;
	if(!(getAlbum().equals(otherSong.getAlbum())) || !(getSongTitle().equals(otherSong.getSongTitle())) || !(getArtist().equals(otherSong.getArtist())))
	{
		return false;
	} 
	return true;
}
//Overridden compareTo method
public int compareTo(Song other)throws NullPointerException
{
	if (other.equals(null))
	{
		throw new NullPointerException();
	}
	if(getAlbum().compareTo(other.getAlbum()) > 0)
	{
		return 1;
	}
	if(getAlbum().compareTo(other.getAlbum()) < 0)
	{
		return -1;
	}
	else if(getAlbum().compareTo(other.getAlbum()) == 0)
	{
		if(getSongTitle().compareTo(other.getSongTitle()) > 0)
		{
			return 1;
		}
		if(getSongTitle().compareTo(other.getSongTitle()) == 0)
		{
			if(getArtist().compareTo(other.getArtist()) > 0)
			{
				return 1;
			}
			if(getArtist().compareTo(other.getArtist())== 0)
			{	
				return 0;
			}
			if(getArtist().compareTo(other.getArtist())< 0)
			{
				return -1;
			}
		}
		if(getSongTitle().compareTo(other.getSongTitle()) < 0)
		{
			return -1;
		}
	}
	return 0;
}
public String toString()
{
	String display = "Title: " + title + "\n" 
		+ "Artist: " + artist + "\n" + "Album: " + album;
	return display;
}
}
