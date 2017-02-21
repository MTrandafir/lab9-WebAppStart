package my.apps.web;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.List;
import my.apps.db.ArticleRepository;
@WebServlet("/articlesList")
public class ArticlesList extends HttpServlet {

    private int counter;

    private ArticleRepository articleRepository = new ArticleRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String Product = request.getParameter("Product");
        Integer Quantity = Integer.valueOf(request.getParameter("Quantity"));



        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        addStyle(out);

        try{

            Article article = new Article(Product, Quantity);

            out.println("<h3>Adaugare in curs...</h3>");
            articleRepository.insert(article);
            out.println("<b> + article.toString() + '</b><br />");
        }catch (ClassNotFoundException e){
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
                   }
        catch (IllegalArgumentException e) {
            out.println("<dif class='error'><b>Unable to parse date! Expected format is yyyy-MM-dd but was " + Quantity);
        }

        addGoBack(out);

        // finished writing, send to browser
        out.close();

    }

    private void addGoBack(PrintWriter out){
        out.println("<a href='/'>Go Back</a>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Articles </title>");
        addStyle(out);
        out.println("</head>");
     try {
         out.println("<h3>Articles...</h3>");
                     out.println("<table>");
                     out.println("<tr>");
                     out.println("<th>Id</th>");
                     out.println("<th>Product</th>");
                     out.println("<th>Quantity</th>");

                     out.println("</tr>");
         List<Article> articles = articleRepository.read();
                     for (Article article : articles) {
                         out.println("<tr>");
                                        out.println("<td>"+article.getId()+"</td>");
                                         out.println("<td>"+article.getProduct()+"</td>");
                                         out.println("<td>"+article.getQuantity()+"</td>");

                                         out.println("<td><a href='/delete?id="+article.getId()+"'><input type=\"submit\" value=\"Delete\"/></a></td>");
                                         out.println("</tr>");
                         }
         out.println("</table>");

    } catch (ClassNotFoundException e) {
         out.println("<div class='error'><b>Unable initialize database connection<b></div>");
                 } catch (SQLException e) {
                    out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
                 }
         addGoBack(out);
        out.close();
    }
    private void addStyle(PrintWriter out) {
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            }


    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called. Counter is: " + counter);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
    }
}