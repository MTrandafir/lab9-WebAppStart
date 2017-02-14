package my.apps.web;

import java.sql.Date;

/**
 * @author flo
 * @since 14/02/2017.
 */
public class Article {

    private String link;
    private Date date;
    private String summary;
    private String domain;

    public Article(String link, Date date, String summary, String domain) {
        this.link = link;
        this.date = date;
        this.summary = summary;
        this.domain = domain;
    }

    public String getLink() {
        return link;
    }

    public Date getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "Article{" +
                "link='" + link + '\'' +
                ", date='" + date + '\'' +
                ", summary='" + summary + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
