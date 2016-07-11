import is.juan.LinkInfo;
import is.juan.Category;
import is.juan.SiteMap;
import is.juan.WebCrawler;
import org.rendersnake.DocType;
import org.rendersnake.HtmlCanvas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.href;

@WebServlet("/siteMap")
public class FormHandler extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlString = request.getParameter("url");

		URL url = new URL (urlString);

		final int numberOfWorkers = 5;
		final int maxDepth = 5;

		WebCrawler crawler = new WebCrawler(numberOfWorkers, maxDepth);

		SiteMap siteMap = crawler.crawl(url);

		Collection<Category> internalLinks = siteMap.getInternalLinks();
		Collection<Category> externalLinks = siteMap.getExternalLinks();

		response.setContentType("text/html");

		HtmlCanvas html = new HtmlCanvas(response.getWriter());
		html = html.render(DocType.HTML5)
			.html()
				.head()
					.title().content("Sitemap for: " + siteMap.getBaseURL())
					.link(href("//cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.1/semantic.min.css").rel("stylesheet"))
				._head()
				.body()
					.h1().content("Sitemap for: " + siteMap.getBaseURL().toString())
					.h2().content("Internal Links");

		for (Category category : internalLinks) {
			html = html
					.h3(class_("ui header"))
						.i(class_("large folder icon"))._i()
						.div(class_("content"))
							.write(category.getName())
							.div(class_("ui teal label")).content(category.getItems().size() + " pages")
						._div()
					._h3()
					.div(class_("ui relaxed selection divided list"));
			for (LinkInfo link : category.getItems()) {
				html = html.div(class_("item"))
					.i(class_("large linkify middle aligned icon"))._i()
					.div(class_("content"))
						.a(class_("header").href(link.getURL())).content(link.getURL())
						.div(class_("description"))._div()
					._div()
				._div();
			}
			html = html._div();
		}

		html = html
				.h2().content("External Links")
				.div(class_("ui middle aligned selection divided list"));
				for (Category category : externalLinks) {
					for (LinkInfo link : category.getItems()) {
						html = html
								.div(class_("item"))
									.i(class_("large external share middle aligned icon"))._i()
									.div(class_("content"))
										.a(class_("header").href(link.getURL())).content(link.getURL())
									._div()
								._div();
					}
				}

		html
				._div()
			._body()
		._html();
	}

}
