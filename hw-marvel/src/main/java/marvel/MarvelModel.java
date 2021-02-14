package marvel;

import com.opencsv.bean.CsvBindByName;

public class MarvelModel {
    @CsvBindByName
    private String hero;

    @CsvBindByName
    private String book;

    public String getHero() {
        return this.hero;
    }

    public String getBook() {
        return this.book;
    }

    public void setHero(String v) {
        this.hero = v;
    }

    public void setBook(String v) {
        this.book = v;
    }
}
