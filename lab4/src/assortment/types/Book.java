package assortment.types;

import assortment.Assortment;

public class Book extends Assortment  {
    Genre genre;

    public Book(String name, int publicationYear) {
        super(name, publicationYear);
    }
    public Book(String name, int publicationYear,Genre genre) {
        super(name, publicationYear);
        this.genre = genre;
    }
    public Book() {
        super();
        this.genre = genre;
    }
    public Genre getGenre(){return genre;}

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "\n\t* Открытка " + name +
                ":\n\t\tГод публикации: " + publicationYear +
                ":\n\t\tЖанр: " + genre;
    }
}
