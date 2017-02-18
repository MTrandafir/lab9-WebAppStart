package my.apps.web;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/myList")
public class MyList extends HttpServlet {

    private int counter;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String Product = request.getParameter("Product");
        String Quantity = request.getParameter("Quantity");

        System.out.println(Product + Quantity);
        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Asta e tot? </h2>");
        out.println("input1 - <b>" + Product + "</b><br/>");
        out.println("input2 - <b>" + Quantity + "</b><br/>");

        out.println("<a href='/'>Go Back</a>");

        // finished writing, send to browser
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Get count </title>");
        out.println("</head>");

        out.println("<h2>Get count</h2>");
        out.println(counter);
        out.close();
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