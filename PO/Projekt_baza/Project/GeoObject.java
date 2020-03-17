/**
 * Klasa abstrakcyjna obiektu geograficznego
 */
public abstract class GeoObject extends Base {

    protected String name;
    protected String id_object;
    protected double latitude;
    protected double longitude;
    protected double above_sea_level;

    public GeoObject() {

    }


    /**
     * Tworzenie unikalnego id obiektu
     * @param ob pierwsza litera lub litery rodzaju obiektu (na przyklad: "c" - City, "rd" - Road)
     */
    protected void create_id(String ob) {
        this.id_object = ob + "-" + this.name;
    }

    /**
     * W przypadku wpisania przez uzytkownika nazwy z "małej litety" stosujemy metode toUpperCase() dla pierwszego znaku
     * @param name
     * @param latitude
     * @param longitude
     * @param above_sea_level
     */
    public void create_geoObject(String name, double latitude, double longitude, double above_sea_level) {

        this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        this.latitude = latitude;
        this.longitude = longitude;
        this.above_sea_level = above_sea_level;
    }

    /**
     * Metoda zwracajca pole count (dostepne tylko w Natural i Cities)
     * @param g obiekt
     * @return
     */
    public int return_count(GeoObject g)
    {
        return 0;
    }



    public String write() {
        return this.name + " - " + this.latitude + " - " + this.longitude + " - " + this.above_sea_level;
    }

    /**
     * Metoda zwracajaca tablice parametrow potrzebnych do dodania do InterfaceTable jako wiersz
     * @return tablica
     */
    protected Object[] to_table()
    {
        Object[] o = {this.name,empty,this.latitude,this.longitude,this.above_sea_level,empty,empty};
        return o;
    }
}
