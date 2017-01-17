package website.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public class InfoBoxServlet extends HttpServlet {

	private List<Entry> singleMovie;
	private List<String> movieInfo;

	@Override
	public void init(ServletConfig config) throws ServletException {
		singleMovie = null;

		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/movielist.jsp");

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ServletException {
			
		boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));	
		String movieID = req.getParameter("movieID");
			try {
			if(ajax){
				singleMovie = new EntryDAO().findSingleMovieList(movieID);
				req.getSession().setAttribute("movieEntry", singleMovie);
				req.getRequestDispatcher("/jsp/infobox.jsp").forward(req,resp);
				String genres = "";
				for(Entry entry: singleMovie){
					movieInfo.add(entry.getTitle());
					movieInfo.add(entry.getBanner());
					movieInfo.add(entry.getYear());
					movieInfo.add(entry.getDirector());
					for (Genre genre: entry.getGenreList()){
						genres = genres + genre.getName() + " ";
					}
				}
				String displayInfo = "<table>"+
							"<tr><p>Title: "+movieInfo.indexOf(0)+"</p></tr>"+
							"<tr><img src='"+movieInfo.indexOf(1)+"'></tr>"+
							"<tr><p>Release Date: "+movieInfo.indexOf(2)+"<p></tr>"+
							"<tr><p>Director: "+movieInfo.indexOf(3)+"</p></tr>"+
							"<tr><td>Genre: </td>"+
							"<td>"+genres+"</td></tr></table></div>";
							
				resp.getWriter().write(displayInfo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}