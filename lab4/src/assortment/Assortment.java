package assortment;

import org.apache.log4j.Logger;
import assortment.types.assort;

import java.io.Serializable;

public class Assortment extends assort implements Serializable {
    private static final Logger LOG = Logger.getLogger(Assortment.class);

    public Assortment(String name, int publicationYear) {
        this.name = name;
        this.publicationYear = publicationYear;
        LOG.info("\t\n* Added Assortment");
    }

    public Assortment() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "\n\t* Издание: " + name +
                ":\n\t\tГод издания: " + publicationYear;
    }
}
