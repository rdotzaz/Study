import java.util.Scanner;
import java.io.File;

/**
 * Baza obiektow geograficznych
 */
public class Base  extends Interface
{
    protected List list_of_objects;
    protected List current_list;
    public Base()
    {

    }

    /**
     * Konstruktor
     * @param something dowolna liczba calkowita (nieuzywana w programie)
     */
    public Base(int something)
    {
        list_of_objects = new List();
        current_list = list_of_objects;
        this.read();
    }

    /**
     * Metoda odpowidzealna za wczytywanie danych obiektow z plikow tekstowych
     */
    private void read()
    {

        this.read("cities.txt");
        this.read("mountains.txt");
        this.read("rivers.txt");
        this.read("seas.txt");
        this.read("roads.txt");
        this.read("airports.txt");
    }

    /**
     * Metoda wczytujaca z pliku paramtery obiektu (w 1 linii jeden parametr)
     * Dane sa zapamietywane w tablicy, ktora jest przekazywana do konstruktora nowego obiektu w bazie.
     *
     * @param source nazwa pliku
     */
    private void read(String source)
    {
        try
        {
            File f = new File(source);
            Scanner s = new Scanner(f);
                int counter = 0;
                String line;
                String[] data;
                data = new String[7];
                line = s.nextLine();
                while(s.hasNextLine())
                {
                    line = s.nextLine();
                    if(counter==0)
                    {
                        data[counter] = line;
                        counter++;
                    }
                    else if(counter%7 == 0)
                    {
                        GeoObject g;
                        if (source == "cities.txt")
                        {
                            g = new City(data);
                        }
                        else if (source == "villages.txt")
                        {
                            g = new Village(data);
                        }
                        else if (source == "lakes.txt")
                        {
                            g = new Lake(data);
                        }
                        else if(source == "mountains.txt")
                        {
                            g = new Mountain(data);
                        }
                        else if(source == "parks.txt")
                        {
                            g = new Park(data);
                        }
                        else if(source == "rivers.txt")
                        {
                            g = new River(data);
                        }
                        else if(source == "seas.txt")
                        {
                            g = new Sea(data);
                        }
                        else if(source == "roads.txt")
                        {
                            g = new Road(data);
                        }
                        else if(source == "airports.txt")
                        {
                            g = new Airport(data);
                        }
                        else
                        {
                            return;
                        }
                        this.add_to_list(g);
                        counter = 0;
                        data[counter] = line;
                        counter++;
                    }
                    else
                    {
                        data[counter] = line;
                        counter++;
                    }

                    //0 1 2 3 4 5 6 7 8 9 10 11 12 13
                    //c
                }
            //}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Dodanie newgo objektu do listy
     * @param g obiekt geograficzny
     */
    private void add_to_list(GeoObject g)
    {
        this.list_of_objects.add(g);
    }

    /**
     * Metoda do tworzenia obietkow w programie. Uzywana w oknie AddFrame
     * @param data tablica danych z parametrami obiektu
     * @param source rodzaj obiektu
     */
    protected void create_new(Object[] data, String source)
    {
        GeoObject g;
        if (source == "Cities")
        {
            g = new City(data);
            this.add_to_list(g);
        }
        else if (source == "Villages")
        {
            g = new Village(data);
            this.add_to_list(g);
        }
        else if (source == "Lakes")
        {
            g = new Lake(data);
            this.add_to_list(g);
        }
        else if(source == "Mountains")
        {
            g = new Mountain(data);
            this.add_to_list(g);
        }
        else if(source == "Parks")
        {
            g = new Park(data);
            this.add_to_list(g);
        }
        else if(source == "Rivers")
        {
            g = new River(data);
            this.add_to_list(g);
        }
        else if(source == "Seas")
        {
            g = new Sea(data);
            this.add_to_list(g);
        }
        else if(source == "Roads")
        {
            g = new Road(data);
            this.add_to_list(g);
        }
        else if(source == "Airports")
        {
            g = new Airport(data);
            this.add_to_list(g);
        }
        else
        {
            System.out.println("Error in create_new");
        }
    }



    public String write(GeoObject g)
    {
        return list_of_objects.write(g);
    }

    /**
     * Metoda filtrujaca obiekty na liscie
     * @param id identyfikator wg ktorego umiesczamy obiekty w liscie
     * @return nowa lista z obiektami spelniajacymi odpowiedni waruenk (na przyklad: id="c" zwracamy liste z obiektami typu City)
     */
    public List filter(String id)
    {
        List l = new List();
        Elem e = list_of_objects.first;
        while (e!=null)
        {
            String[] s = e.value.id_object.split("-");
            if(s[0].equals(id))
            {
                l.add(e.value);
            }
            e = e.next;
        }
        return l;
    }
}
