package movie_xml_parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parse_mains243XML {

	static ArrayList<String> movies = new ArrayList<>(2 << 17);
	
	static ArrayList<String> stars = new ArrayList<>(2 << 14);

	static HashSet<String> genres = new HashSet<String>();
	
	static ArrayList<String> stars_in_movies = new ArrayList<>(2 << 17);
	static ArrayList<String> genres_in_movies = new ArrayList<>(2 << 17);

	
	private static void parse_main243() {
		
		try {
			File inputFile = new File("mains243.xml");
			if(!inputFile.exists())
				inputFile = new File("source_XML_parsing/mains243.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList directorfilms = doc.getElementsByTagName("directorfilms");

			for (int index = 0; index < directorfilms.getLength(); ++index) {

				Node directorfilm = directorfilms.item(index);

				if (directorfilm.getNodeType() == Node.ELEMENT_NODE) {
					
					Element directorfilmElement = (Element) directorfilm;

					String dirname = get_dirname(directorfilmElement);

					NodeList filmsList = directorfilmElement.getElementsByTagName("film");

					for (int i = 0; i < filmsList.getLength(); ++i) {

						Node films = filmsList.item(i);
						if (films.getNodeType() == Node.ELEMENT_NODE) {

							Element filmsElem = (Element) films;
							
							String title = get_tagName(filmsElem, "t");
							String year = get_tagName(filmsElem, "year");
							year.replace('x', '0');
							
							//Insert genres_in_movies
							NodeList cat = filmsElem.getElementsByTagName("cat");
							for (int j = 0; j < cat.getLength(); ++j) {
								String genre = cat.item(j).getTextContent().trim();
								if(!genre.isEmpty()){
									genres.add(genre);
									genres_in_movies.add(genre + "|" + title);
								}
							}
							
							//Insert movie info to movie map
							if (title.isEmpty() && year.isEmpty() && dirname.isEmpty()) {
								// Do nothing
							} else {	
								movies.add(title + "|" + year + "|" + dirname);
							}
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void parse_actors63() {
		
		try {
			File inputFile = new File("actors63.xml");
			if(!inputFile.exists())
				inputFile = new File("source_XML_parsing/actors63.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList actors = doc.getElementsByTagName("actor");

			for (int index = 0; index < actors.getLength(); ++index) {

				Node actor = actors.item(index);

				if (actor.getNodeType() == Node.ELEMENT_NODE) {
					Element actorElement = (Element) actor;
					String firstname = "";
					String lastname = "";
					String dob = "";
					
					String stagename = get_tagName(actorElement, "stagename");

					String name[] = stagename.split(" ", 2);
					
					if(name.length == 2){
						firstname = name[0];
						lastname = name[1];
					}
					else
						lastname = name[0];
					dob = get_tagName(actorElement, "dob");
					dob = dob + "-00-00";
					
					stars.add(firstname + "|" + lastname + "|" + dob);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void parse_cast124() {
		try {
			File inputFile = new File("casts124.xml");
			if(!inputFile.exists())
				inputFile = new File("source_XML_parsing/casts124.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList filmcs = doc.getElementsByTagName("filmc");

			for (int index = 0; index < filmcs.getLength(); ++index) {
				Node filmc = filmcs.item(index);
				if (filmc.getNodeType() == Node.ELEMENT_NODE) {
					Element mtemp = (Element) filmc;
					NodeList m = mtemp.getElementsByTagName("m");
					
					for(int i = 0; i < m.getLength(); ++i) {
						Node movie = m.item(i);
						if(movie.getNodeType() == Node.ELEMENT_NODE){
							Element movieElem = (Element) movie;
							String title = get_tagName(movieElem, "t");
							String stagename = get_tagName(movieElem, "a");
							
							if (stagename.equals("s a") || stagename.equals("sa")){
								//Do Nothing
							}
							else{
								String name[] = stagename.split(" ", 2);
								String firstname = "";
								String lastname = "";
								if(name.length == 2){
									firstname = name[0];
									lastname = name[1];
								}
								else
									lastname = name[0];
								stars_in_movies.add(firstname + "|" + lastname + "|" + title);
							}
						}
					}
									
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	private static String get_tagName(Element filmsElement, String tagName) {
		NodeList tagNameList = filmsElement.getElementsByTagName(tagName);
		String toReturn = "";
		if (tagNameList.getLength() != 0) {
			if (tagNameList.item(0).getFirstChild() != null)
				toReturn = tagNameList.item(0).getFirstChild().getTextContent();
			else
				toReturn = tagNameList.item(0).getTextContent();
		}
		return toReturn.trim();
	}


	private static String get_dirname(Element directorfilmElement) {
		NodeList dirnameList = directorfilmElement.getElementsByTagName("dirname");
		String dirname = "";
		if (dirnameList.getLength() != 0) {
			dirname = dirnameList.item(0).getTextContent();
			if (dirname.contains("Unknow") || dirname.contains("UnYear"))
				dirname = "";
		}
		return dirname.trim();
	}
	
	private static void print_ArrayList2File(ArrayList<?> arrayList, String fileName) {
		try {
			PrintWriter writer = new PrintWriter(fileName);
			for (int i = 0; i < arrayList.size(); ++i) {
				writer.println(arrayList.get(i));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void print_Set2File(HashSet<String> set, String fileName) {
		try {
			PrintWriter writer = new PrintWriter(fileName);
			for(String str : set) {
				writer.println(str);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		System.out.println("Star...");
		parse_main243();
		
		parse_actors63();
		
		parse_cast124();
	
		
		print_ArrayList2File(movies, "movies.txt");
		
		print_Set2File(genres, "genres.txt");

		print_ArrayList2File(genres_in_movies, "gen_mov.txt");
		
		print_ArrayList2File(stars, "stars.txt");
		
		print_ArrayList2File(stars_in_movies, "star_mov.txt");
		System.out.println("...Done");
	}

	


	


	



}
