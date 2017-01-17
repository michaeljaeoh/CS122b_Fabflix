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
public class MovieListServlet extends HttpServlet {
	List<Entry> movieItems;

	String query;
	Entry entry;

	@Override
	public void init(ServletConfig config) throws ServletException {
		query = null;
		entry = null;

		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/movielist.jsp");

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			movieItems = (List<Entry>) req.getAttribute("movies");
			req.getSession().setAttribute("movies", movieItems);
			req.getRequestDispatcher("/jsp/movielist.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
