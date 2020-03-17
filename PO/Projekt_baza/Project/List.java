/**
 * Klasa Elem ktora jako wartosc przechowuje GeoObject
 */
class Elem extends Const
{
    protected Elem next;
    protected GeoObject value;
    public Elem()
    {
        this.next = null;
    }
    public Elem(Elem next_e)
    {
        this.next = next_e;
    }

}

/**
 * Lista jednokierunkowa podobna do list implementowanej na pracowniach
 */
public class List extends Elem
{
    protected Elem first;
    private int size;

    public List()
    {
        this.first = null;
        this.size = 0;
    }

    /**
     * Metoda sprawdzajaca rownosc obiektow poprzez ich id
     * @param a
     * @param b
     * @return true jesli te same
     */
    private boolean IsTheSame(GeoObject a, GeoObject b)
    {
        return a.id_object==b.id_object;
    }

    /**
     * Metoda sortujaca wg parametru what (sortowanie babelkowe :D)
     * @param what rodzaj parametru
     */
    public void sort(String what)
    {
        Elem i = this.first;
        Elem j = this.first;
        while (i!=null)
        {
            while (j.next!=null)
            {
                if(what=="Name")
                {
                    if(stringCompare(j.value.name,j.next.value.name)>0)
                    {
                        GeoObject tmp = j.value;
                        j.value=j.next.value;
                        j.next.value=tmp;
                    }
                }
                else if(what=="Above sea level")
                {
                    if(j.value.above_sea_level>j.next.value.above_sea_level)
                    {
                        GeoObject tmp = j.value;
                        j.value=j.next.value;
                        j.next.value=tmp;
                    }
                }
                else if(what=="Count/Area")
                {
                    int count_j = j.value.return_count(j.value);
                    int count_j_next = j.next.value.return_count(j.next.value);
                    if(count_j>count_j_next)
                    {
                        GeoObject tmp = j.value;
                        j.value=j.next.value;
                        j.next.value=tmp;
                    }
                }
                else if(what=="Latitude")
                {
                    if(j.value.latitude>j.next.value.latitude)
                    {
                        GeoObject tmp = j.value;
                        j.value=j.next.value;
                        j.next.value=tmp;
                    }
                }
                else if(what=="Longitude")
                {
                    if(j.value.longitude>j.next.value.longitude)
                    {
                        GeoObject tmp = j.value;
                        j.value=j.next.value;
                        j.next.value=tmp;
                    }
                }
                j=j.next;


            }
            i=i.next;
            j=this.first;
        }
    }

    /**
     * Dodawanie obeiktu do listy
     * @param val
     */
    public void add(GeoObject val)
    {
        if (size == 0)
        {
            this.first = new Elem();
            this.first.value = val;
            this.first.next = null;
        }
        else
        {
            Elem tmp = this.first;
            while (tmp.next != null)
            {
                tmp = tmp.next;
            }
            tmp.next = new Elem();
            tmp.next.value = val;
            tmp.next.next = null;
        }
        this.size++;
    }

    /**
     * Usuwanie obietku z listy
     * @param val
     */
    public void delete(GeoObject val)
    {
        if (size == 0)
        {
           System.out.println("**Error**");
        }
        else if (size == 1)
        {
            this.first = this.first.next = null;
            this.size--;
        }
        else
        {
            Elem tmp = this.first;
            if(this.IsTheSame(tmp.value,val))
            {
                this.first = tmp.next;
                this.size--;
                return;
            }
            else
            {
                while (tmp.next != null)
                {
                    if (this.IsTheSame(tmp.next.value, val))
                    {
                        tmp.next = tmp.next.next;
                        this.size--;
                        return;
                    }
                    tmp = tmp.next;

                }
            }
            System.out.println("**Wrong key or object don't exist");
            // a b c d
            //   t f
        }
    }

    public GeoObject find(GeoObject g)
    {
        if(size==0)
        {
            System.out.println("**Empty list**");
            return this.first.value;
        }
        else
        {
            Elem e = this.first;
            while(e!=null)
            {
                if(this.IsTheSame(e.value,g))
                {
                    return e.value;
                }
                e = e.next;
            }
            System.out.println("**Don't find**");
            return this.first.value;
        }
    }

    public double[] find_as_name(String id)
    {
        Elem e = this.first;
        double[] tmp;
        tmp = new double[2];
        while (e!=null)
        {
            if(e.value.name==id)
            {
                tmp[0] = e.value.latitude;
                tmp[1] = e.value.longitude;
                return tmp;
            }
            e = e.next;
        }
        return tmp;
    }

    /**
     * Metoda znajdujaca GeoObject na liscie do usuniecia
     * @param name nazwa obiektu do usuniecia
     * @return 1 gdy usuniecie sie powidlo, 0 wpp
     */
    public int find_to_delete(String name)
    {
        Elem e = this.first;
        while (e!=null)
        {
            if(e.value.name.equals(name))
            {
                this.delete(e.value);
                return 1;
            }
            e = e.next;
        }
        return 0;
    }

    public int size_of_list()
    {
        return this.size;
    }


    public String write(GeoObject g)
    {
        Elem tmp = this.first;
        while(tmp!=null)
        {
            if(this.IsTheSame(tmp.value,g))//object.ReferenceEquals(tmp,e))
            {
                return tmp.value.write();
            }
            tmp = tmp.next;
        }
        return "error";
    }

    public String[] write(int something)
    {
        String[] s = new String[this.size];
        Elem tmp = this.first;
        int i =0;
        while (tmp!=null)
        {
            s[i] = tmp.value.write();
            tmp = tmp.next;
            i++;
        }
        return s;
    }

    public void write()
    {
        Elem tmp = this.first;
        while(tmp!=null)
        {
            System.out.println(tmp.value.write());
            tmp = tmp.next;
        }
    }
}