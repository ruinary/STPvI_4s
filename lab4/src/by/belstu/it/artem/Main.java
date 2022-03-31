package by.belstu.it.artem;

import assortment.Assortment;
import assortment.types.Book;
import assortment.types.Genre;
import assortment.types.Journal;
import assortment.types.Postcards;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import seller.Seller;
import shop.Shop;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.xml.sax.SAXException;

public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);

    //=================================================================================

    /**
     * @param pathXml - путь к XML
     * @param pathXsd - путь к XSD
     */
    public static boolean checkXMLforXSD(String pathXml, String pathXsd)
            throws Exception {

        try {
            File xml = new File(pathXml);
            File xsd = new File(pathXsd);

            if (!xml.exists()) {
                System.out.println("Не найден XML " + pathXml);
            }

            if (!xsd.exists()) {
                System.out.println("Не найден XSD " + pathXsd);
            }

            if (!xml.exists() || !xsd.exists()) {
                return false;
            }

            SchemaFactory factory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(pathXsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(pathXml));
            return true;
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static void main(String[] args) throws Exception {
        LOG.info("Starting program");
        try {
            Shop LitLovers = new Shop("LitLovers");
            System.out.println(LitLovers.toString());

            Seller sel = new Seller("Sano");

            Assortment assort = new Assortment("Tokyo Revelers", 2017);
            Book book1 = new Book("Re:Zero", 2014);
            Journal journal1 = new Journal("Forbes", 2019);
            Postcards card1 = new Postcards("8March", 2022);
            Book book2 = new Book("Fire Force", 2014, Genre.Drama);
            Journal journal2 = new Journal("When the Promised Flower Blooms", 2019, Genre.Drama);
            Postcards card2 = new Postcards("9May", 2022);
            card2.setGenre(Genre.ect);

            LitLovers.addAssortment(assort);
            LitLovers.addAssortment(book1);
            LitLovers.addAssortment(journal1);
            LitLovers.addAssortment(card1);
            LitLovers.addAssortment(book2);
            LitLovers.addAssortment(journal2);
            LitLovers.addAssortment(card2);
            System.out.println(LitLovers.toString());

            sel.sortByPublicationYear(LitLovers);
            System.out.println(LitLovers.toString());
            sel.searchByName(LitLovers, "Tokyo Revelers");
            System.out.println(LitLovers.toString());
            sel.AddAssortment(LitLovers, "Jujutsu Kaisen", 2018);
            sel.DelAssortment(LitLovers, "Tokyo Revelers");
            System.out.println(LitLovers.toString());

//=================================================================================

            System.out.println("\n\t\t* Валидация XML по схеме:");
            String pathXml = new String("D:/study/2 курс/JAVA/lab4/files/example.xml");
            String pathXsd = new String("D:/study/2 курс/JAVA/lab4/files/example.xsd");
            System.out.println("XML соответствует XSD: " + (Main.checkXMLforXSD(pathXml, pathXsd)));

            System.out.println("\n\t\t* Парс объектов из XML:");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SaxParser saxp = new SaxParser();
            parser.parse(new File(pathXml), saxp);

            Assortment assortXML = saxp.getResult();
            System.out.println(assortXML.toString());
            LitLovers.addAssortment(assortXML);

            System.out.println("\n\t\t* Сериализация в XML");
            FileOutputStream out = new FileOutputStream("files/Serialize.xml");
            XMLEncoder xmlEncoder = new XMLEncoder(out);
            xmlEncoder.writeObject(assortXML);
            xmlEncoder.close();

            System.out.println("\n\t\t* Десериализация из XML");
            FileInputStream in = new FileInputStream("files/Serialize.xml");
            XMLDecoder xmlDecoder = new XMLDecoder(in);
            Assortment assortXML2 = (Assortment) xmlDecoder.readObject();
            xmlDecoder.close();
            System.out.println(assortXML2);

            System.out.println("\n\t\t* Сериализация в JSON");
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(assortXML);
            System.out.println(json);
            FileOutputStream jsonOut = new FileOutputStream("files/Serialize.json");
            byte[] outText = json.getBytes(StandardCharsets.UTF_8);
            jsonOut.write(outText, 0, outText.length);

            System.out.println("\n\t\t* Десериализация из JSON");
            Scanner scanner = new Scanner(new File("/files/Serialize.json"));
            String result = "";
            while (scanner.hasNext())
                result += scanner.nextLine();
            scanner.close();
            Assortment assortXML3 = gson.fromJson(result, Assortment.class);
            System.out.println(assortXML3);

            System.out.println("\n\t\t* Stream API");

            System.out.println("\t* Ассортимент с годом публикации == 2022:");
            Stream<Assortment> resultStream1 = LitLovers.getAssortment().stream().filter(w -> w.getPublicationYear() == 2022);
            for (var elem : resultStream1.collect(Collectors.toList())) {
                System.out.println(elem.name + " " + elem.publicationYear);
            }

            Stream<Assortment> resultStream2 = LitLovers.getAssortment().stream();
            System.out.println("\t* Всего изданий в ассортименте: " + resultStream2.count());
        }
        catch (Exception e)
        {
            LOG.fatal("Fatal error! " + e.getMessage());
        }
    }
}