

public abstract class Communication extends GeoObject
{
    public void create_cummunication(String name, double latitude, double longitude, double above_sea_level)
    {
        super.create_geoObject(name, latitude, longitude, above_sea_level);
    }

    /**
     * Metoda wczytujaca parametry obiektu typu Road lub Airport
     * @param data tablica parametrow
     */
    protected void load_data(String[] data)
    {
        try
        {
            String name = data[0];
            double latitude = Double.parseDouble(data[2]);
            double longitude = Double.parseDouble(data[3]);
            double above_sea_level = Double.parseDouble(data[4]);

            this.create_cummunication(name, latitude, longitude, above_sea_level);
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
    }
}

class Road extends Communication
{
    protected String city_a, city_b;

    /**
     * Konstruktor
     * @param data tablica danych
     */
    public Road(String[] data)//String name, double latitude, double longitude, double above_sea_level, City a, City b)
    {
        this.city_a = "c-" + data[5];
        this.city_b = "c-" + data[6];
        super.load_data(data);
        super.create_id("rd");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public Road(Object[] data)
    {
        this.city_a=(String)data[5];
        this.city_b=(String)data[6];
        super.create_cummunication((String)data[0],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("rd");
    }

    @Override protected Object[] to_table()
    {
        Object[] o = {this.name,empty,empty,empty,empty,this.city_a,this.city_b};
        return o;
    }
}

class Airport extends Communication
{
    protected String city;


    /**
     * Konstruktor
     * @param data tablica danych
     */
    public Airport(String[] data)//String name, double latitude, double longitude, double above_sea_level, City c)
    {
        this.city = "c-" + data[5];
        super.load_data(data);
        super.create_id("a");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public Airport(Object[] data)
    {
        this.city=(String)data[5];
        super.create_cummunication((String)data[0],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("a");
    }



    @Override protected Object[] to_table()
    {
        Object[] o = {this.name,empty,this.latitude,this.longitude,this.above_sea_level,empty,empty};
        return o;
    }
}