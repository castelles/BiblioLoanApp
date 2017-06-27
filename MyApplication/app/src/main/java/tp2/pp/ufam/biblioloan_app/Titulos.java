package tp2.pp.ufam.biblioloan_app;

/*
 * Created by caiotelles on 26/06/17.
 */

public class Titulos
{
    private String title, author;
    private int edition, available;   //0 - available     1  - not available

    public Titulos(String title, String author, int edition, int available)
    {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
