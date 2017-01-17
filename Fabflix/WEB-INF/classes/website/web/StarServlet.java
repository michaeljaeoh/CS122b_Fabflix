package website.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StarServlet extends HttpServlet {

	String query;
	Star star;

	@Override
	public void init(ServletConfig config) throws ServletException {
		query = null;
		star = null;
		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/star.jsp");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		query = req.getParameter("query");

		try {
			star = new StarDAO().findStar(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		req.getSession().setAttribute("chosen", star);
		req.getRequestDispatcher("/jsp/starpage.jsp").forward(req, resp);

	}

}
