public abstract class Cities extends GeoObject
{
    protected int count;

    public Cities()
    {

    }

    /**
     * Metoda pomocnicza do tworzenia obietku typu City lub Village
     * @param name
     * @param count
     * @param latitude
     * @param longitude
     * @param above_sea_level
     */
    protected void create_cities(String name, int count,  double latitude, double longitude, double above_sea_level)
    {
        this.count = count;
        super.create_geoObject(name, latitude, longitude, above_sea_level);
    }

    /**
     * Metoda zwracajca pole count, jesli GeoObject jest typu City lub Village. Jest to wymagane do przedstawienia obiektow w tabeli.
     * @param g obiekt
     * @return liczba calkowita - count lub 0 gdy obiekt jest innego typu
     */
    @Override public int return_count(GeoObject g)
    {
        if(g instanceof City || g instanceof Village) return this.count;
        else return 0;
    }

    /**
     * Metoda wczytujaca parametry obiektu.
     * @param data tablica danych obiektu
     */
    protected void load_data(String[] data)
    {
        try
        {
            String name = data[0];
            int count = Integer.parseInt(data[1]);
            double latitude = Double.parseDouble(data[2]);
            double longitude = Double.parseDouble(data[3]);
            double above_sea_level = Double.parseDouble(data[4]);

            this.create_cities(name, count, latitude, longitude, above_sea_level);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metoda zwracajaca tablice, ktora dodajemy w InterfaceTable jako wiersz
     * @return tablica danych
     */
    @Override protected Object[] to_table()
    {
        Object[] o = {this.name,this.count,this.latitude,this.longitude,this.above_sea_level,empty,empty}; //7
        return o;
    }

}

class City extends Cities
{

    /**
     * Konstruktor
     * @param data tablica danych
     */
    public City(String[] data)//String name, int count, double latitude, double longitude, double above_sea_level)
    {
        super.load_data(data);
        super.create_id("c");
    }

    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public City(Object[] data)
    {
        super.create_cities((String)data[0],(int)data[1],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("c");
    }

}

class Village extends Cities
{
    /**
     * Konstruktor
     * @param data tablica danych
     */
    public Village(String[] data)
    {
        super.load_data(data);
        super.create_id("v");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public Village(Object[] data)
    {
        super.create_cities((String)data[0],(int)data[1],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("v");
    }

}