package assortment.types;

import assortment.Assortment;

public class Journal extends Assortment  {

    Genre genre;

    public Journal(String name, int publicationYear) {
        super(name, publicationYear);
    }
    public Journal(String name, int publicationYear,Genre genre) {
        super(name, publicationYear);
        this.genre = genre;
    }
    public Journal() {
        super();
        this.genre = genre;
    }
    public Genre getGenre(){return genre;}

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "\n\t* Журнал " + name +
                ":\n\t\tГод публикации: " + publicationYear +
                ":\n\t\tЖанр: " + genre;
    }
}
