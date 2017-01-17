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
public class GenreServlet extends HttpServlet {
	String query;
	List<Genre> genreList;
	List<Entry> genreSelect;
	List<Star> starList;

	@Override
	public void init(ServletConfig config) throws ServletException {
		query = null;

		genreList = null;
		genreSelect = null;

		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/genrelist.jsp");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		query = req.getParameter("query");

		if (query == null) {
			try {
				genreList = new GenreDAO().genreList();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.getSession().setAttribute("genres", genreList);
			req.getRequestDispatcher("/jsp/genrelist.jsp").forward(req, resp);
		} else {

			try {
				genreSelect = new EntryDAO().findMoviesFromGenre(query);

			} catch (Exception e) {
				e.printStackTrace();
			}

			req.getSession().setAttribute("movies", genreSelect);
			req.getRequestDispatcher("/jsp/movielist.jsp").forward(req, resp);

		}

	}

}
