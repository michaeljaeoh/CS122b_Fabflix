package website.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SingleMovieServlet extends HttpServlet {
	String query;
	Entry entry;

	@Override
	public void init(ServletConfig config) throws ServletException {
		query = null;
		entry = null;
		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/singlemovie.jsp");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		query = req.getParameter("query");

		try {
			entry = new EntryDAO().findSingleMovie(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		req.getSession().setAttribute("choice", entry);
		req.getRequestDispatcher("/jsp/singlemovie.jsp").forward(req, resp);

	}
}
