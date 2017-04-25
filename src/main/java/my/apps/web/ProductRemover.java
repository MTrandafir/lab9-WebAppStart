package my.apps.web;

import my.apps.db.ArticleRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/delete")
public class ProductRemover extends HttpServlet {

    private ArticleRepository articleRepository = new ArticleRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();




        try {
            articleRepository.delete(id);
            out.println("<b>Product Removed</b>");

        }catch (ClassNotFoundException e){
        out.println("<div class='error'><b>Unable initialize database connection<b></div>");
    } catch (SQLException e) {
        out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
    }
    out.println("<a href='/articlesList?'>Go Back</a>");


    }
}
