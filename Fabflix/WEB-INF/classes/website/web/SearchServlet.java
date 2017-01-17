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
public class SearchServlet extends HttpServlet {

	private List<Entry> movieList;

	@Override
	public void init(ServletConfig config) throws ServletException {
		movieList = null;

		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/search.jsp");

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ServletException {
		String title = req.getParameter("title");
		String year = req.getParameter("year");
		String director = req.getParameter("director");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");

		if (req.getParameter("search") == null) {
			req.getRequestDispatcher("/jsp/search.jsp").forward(req, resp);
		} else {
			try {
				movieList = new EntryDAO().findMovieBySearch(title, year, director, firstName, lastName);
				req.getSession().setAttribute("movies", movieList);
				req.getRequestDispatcher("/jsp/movielist.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}