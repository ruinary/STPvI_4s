package seller;

import shop.Shop;
import assortment.Assortment;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Seller implements ISeller{
    private static final Logger LOG = Logger.getLogger(Seller.class);
    public String name;

    public Seller(String name) {
        this.name = name;
        System.out.println("\t* Добавлен продавец: " + this.name + "\n");
    }

    public Seller() {
        System.out.println("\t* Добавлен Продавец");
    }

//провести сортировку книг на основе года издания
    @Override
    public void sortByPublicationYear(Shop assort) {
        ArrayList<Assortment> arr = assort.getAssortment();
        if(arr == null)
            System.out.println("В ассортименте ничего нет!");
        else {
            arr.sort(new Comparator<Assortment>() {
                @Override
                public int compare(Assortment o1, Assortment o2) {
                    return o1.getPublicationYear() > o2.getPublicationYear() ? -1 : o1.getPublicationYear() < o1.getPublicationYear() ? 1 : 0;
                }
            });
        }
        System.out.println("\t\t Ассортимент отсортирован по году издания.");
        LOG.info("\n====== Sorted by Publication Year ======");
    }
    //найти печатное издание по названию
    @Override
    public void searchByName(Shop shop, String name) {
        System.out.println("Поиск издания с названием " + name);
        if(shop == null)
            throw new NullPointerException("Null pointer");
        ArrayList<Assortment> arr = shop.getAssortment();
        for (Assortment assort: arr) {
            if(assort.getName() == name)
                System.out.println(assort.toString());
        }
    }
    //добавить
    public void AddAssortment(Shop assort,String name,int publicationYear) {
        ArrayList<Assortment> arr = assort.getAssortment();
         Assortment addedAssort = new Assortment(name,publicationYear);
            arr.add(addedAssort);
        System.out.println("\n\t= = Добавлено новое издание = ="+addedAssort.toString());
        LOG.info("\n====== Add New Assortment! ======");
    }
    //удалить
    public void DelAssortment(Shop assort,String name) {
        ArrayList<Assortment> arr = assort.getAssortment();
        Iterator<Assortment> iA=arr.iterator();
        while(iA.hasNext()) {
            Assortment nA = iA.next();
            if (nA.name.equals(name)) {
                Assortment deletedAssort = new Assortment(nA.name,nA.publicationYear);
                iA.remove();
                System.out.println("\n\t= = Продано издание = ="+deletedAssort.toString());
                LOG.info("\n====== Delete Assortment! ======");
            }
        }
    }

}

