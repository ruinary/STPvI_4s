package seller;

import shop.Shop;
import assortment.Assortment;

public interface ISeller {
    void sortByPublicationYear(Shop shop);
    void searchByName(Shop shop, String name);
    void AddAssortment(Shop assort,String name,int publicationYear);
    void DelAssortment(Shop assort,String name);
}
