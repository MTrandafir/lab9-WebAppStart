package my.apps.db;

         import my.apps.web.Article;

         import java.sql.*;
         import java.util.ArrayList;
         import java.util.List;



         /**
   * @author flo
  * @since 17/01/2017.
   */
         public class ArticleRepository {

             // 1. define connection params to db
             final static String URL = "jdbc:postgresql://54.93.65.5:5432/QA6_Mariana";
     final static String USERNAME = "fasttrackit_dev";
     final static String PASSWORD = "fasttrackit_dev";

             public void insert(Article article) throws ClassNotFoundException, SQLException {
                // 1. load the driver
                        Class.forName("org.postgresql.Driver");

                        // 2. obtain a connection
                                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                       // 3. create a query statement
                                PreparedStatement pSt = conn.prepareStatement("INSERT INTO ware( Product,Quantity) VALUES (?,?)");
                pSt.setString(1, article.getProduct());
               pSt.setInt(2, article.getQuantity());



                        // 4. execute a prepared statement
                                int rowsInserted = pSt.executeUpdate();
                System.out.println("Inserted " + rowsInserted + " rows.");

                       // 5. close the objects
                                pSt.close();
                conn.close();
            }
 public List<Article> read() throws ClassNotFoundException, SQLException {
                     // 1. load the driver
                            Class.forName("org.postgresql.Driver");

                            // 2. obtain a connection
                                     Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                             // 3. create a query statement
                                     Statement st = conn.createStatement();

                             // 4. execute a query
                                     ResultSet rs = st.executeQuery("SELECT id, Product, Quantity FROM ware");

                             // 5. iterate the result set and print the values
                                    List<Article> articles = new ArrayList<>();
                     while (rs.next()) {
                             Article article = new Article(
                                             rs.getString("Product"),
                                             rs.getInt("Quantity")
                                     );
                             article.setId(rs.getLong("id"));
                             articles.add(article);
                         }

                             // 6. close the objects
                                    rs.close();
                    st.close();
                     conn.close();
                     return articles;
 }}

