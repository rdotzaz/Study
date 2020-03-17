import java.util.zip.DeflaterOutputStream;

public abstract class Natural extends GeoObject
{
    protected int area;

    public Natural()
    {

    }

    protected void create_natural(String name,int area, double latitude, double longitude, double above_sea_level)
    {
        this.area = area;
        super.create_geoObject(name, latitude, longitude, above_sea_level);
    }

    @Override public int return_count(GeoObject g)
    {
        if(g instanceof Lake || g instanceof River || g instanceof Mountain || g instanceof Park || g instanceof Sea)
        {
            return this.area;
        }
        else return 0;
    }

    /**
     * Metoda wczytujaca parametry obiektu
     * @param data tablica parametrow
     */
    protected void load_data(String[] data)
    {
        try
        {
            String name = data[0];
            int area = Integer.parseInt(data[1]);
            double latitude = Double.parseDouble(data[2]);
            double longitude = Double.parseDouble(data[3]);
            double above_sea_level = Double.parseDouble(data[4]);

            this.create_natural(name, area, latitude, longitude, above_sea_level);
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
    }

    @Override protected Object[] to_table()
    {
        Object[] o = {this.name,this.area,this.latitude,this.longitude,this.above_sea_level,empty,empty};
        return o;
    }
}


class Mountain extends Natural
{

    /**
     * Konstruktor
     * @param data tablica danych
     */
    public Mountain(String[] data)//String name, int area, double latitude, double longitude, double above_sea_level)
    {
        super.load_data(data);
        super.create_id("m");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public Mountain(Object[] data)
    {
        super.create_natural((String)data[0],(int)data[1],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("m");
    }
}

class Park extends Natural
{

    /**
     * Konstruktor
     * @param data tablica danych
     */
    public Park(String[] data)//String name, int area, String spec, double latitude, double longitude, double above_sea_level)
    {
        super.load_data(data);
        super.create_id("p");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public Park(Object[] data)
    {
        super.create_natural((String)data[0],(int)data[1],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("p");
    }
}

abstract class WaterObject extends Natural
{
    public WaterObject()
    {

    }
}

class River extends WaterObject
{

    /**
     * Konstruktor
     * @param data tablica danych
     */
    public River(String[] data)//String name, int area, String spec, double latitude, double longitude, double above_sea_level)
    {
        super.load_data(data);
        super.create_id("r");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public River(Object[] data)
    {
        super.create_natural((String)data[0],(int)data[1],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("r");
    }
}

class Sea extends WaterObject
{

    /**
     * Konstruktor
     * @param data tablica danych
     */
    public Sea(String[] data)//String name, int area, String spec, double latitude, double longitude, double above_sea_level)
    {
        super.load_data(data);
        super.create_id("s");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public Sea(Object[] data)
    {
        super.create_natural((String)data[0],(int)data[1],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("s");
    }
}

class Lake extends WaterObject
{

    /**
     * Konstruktor
     * @param data tablica danych
     */
    public Lake(String[] data)//String name, int area, String spec, double latitude, double longitude, double above_sea_level)
    {
        super.load_data(data);
        super.create_id("l");
    }
    /**
     * Konstruktor dla obiektow tworzonych w programie w oknie AddFrame
     * @param data tablica danych
     */
    public Lake(Object[] data)
    {
        super.create_natural((String)data[0],(int)data[1],(double)data[2],(double)data[3],(double)data[4]);
        super.create_id("l");
    }
}

