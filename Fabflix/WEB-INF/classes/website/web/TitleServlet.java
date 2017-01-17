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
public class TitleServlet extends HttpServlet {

	String title;
	String[] movieTitles;
	List<Entry> titleSelect;

	@Override
	public void init(ServletConfig config) throws ServletException {
		title = null;

		movieTitles = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f",
				"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		titleSelect = null;

		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/titlelist.jsp");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		title = req.getParameter("query");

		if (title == null) {

			req.getSession().setAttribute("titles", movieTitles);
			req.getRequestDispatcher("/jsp/titlelist.jsp").forward(req, resp);

		} else {

			try {
				titleSelect = new EntryDAO().findMoviesByTitle(title);
			} catch (Exception e) {
				e.printStackTrace();
			}

			req.getSession().setAttribute("movies", titleSelect);
			req.getRequestDispatcher("/jsp/movielist.jsp").forward(req, resp);

		}

	}

}
