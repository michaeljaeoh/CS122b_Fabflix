package website.web;

import java.util.ArrayList;
import java.util.List;

public class Entry {
	private int id;
	private String title;
	private String year;
	private String director;
	private String banner_url;
	private String trailer_url;

	private List<Star> starList;
	private List<Genre> genreList;

	public Entry() {
		id = 0;
		starList = new ArrayList<Star>();
		genreList = new ArrayList<Genre>();
	}

	public Entry(int id, String title, String year, String director, String banner_url, String trailer_url,
			List<Star> starList, List<Genre> genreList) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
		this.starList = starList;
		this.genreList = genreList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYear() {
		return this.year;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDirector() {
		return this.director;
	}

	public void setBanner(String banner) {
		this.banner_url = banner;
	}

	public String getBanner() {
		return this.banner_url;
	}

	public void setTrailer(String trailer) {
		this.trailer_url = trailer;
	}

	public String getTrailer() {
		return this.trailer_url;
	}

	public List<Star> getStarList() {
		return starList;
	}

	public void setStarList(List<Star> starList) {
		this.starList = starList;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

}
