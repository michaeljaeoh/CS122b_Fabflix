package website.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class AutoSearchServlet extends HttpServlet {
	private List<Entry> movieList;
	private List<String> titleList;

	@Override
	public void init(ServletConfig config) throws ServletException {

		movieList = null;
		titleList = null;
		ServletContext context = config.getServletContext();
		context.getRequestDispatcher("/jsp/autosearch.jsp");

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ServletException {
		boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

		String title = req.getParameter("title");

		if (req.getParameter("title") == null) {
			req.getRequestDispatcher("/jsp/autosearch.jsp").forward(req, resp);
		} else {
			try {
				if (ajax) {
					title = title.trim().toLowerCase();
					movieList = new EntryDAO().findMovieByAutoTitle(title);
					titleList = new ArrayList<String>();
					req.getSession().setAttribute("movies", movieList);
					for (Entry entry : movieList) {
						titleList.add(entry.getTitle());
					}
					String searchList = new Gson().toJson(titleList);

					resp.getWriter().write(searchList);

				} else {
					movieList = new EntryDAO().findMovieByAutoTitle(title);
					req.getSession().setAttribute("movies", movieList);
					req.getRequestDispatcher("/jsp/movielist.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
