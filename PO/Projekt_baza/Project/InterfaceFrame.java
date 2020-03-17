import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa abstrakcyjna. W konstruktorze ustawiamy najogolniejsze parametry okien
 */
public abstract class InterfaceFrame extends Interface
{

    protected JFrame frame;
    protected JPanel panel;
    protected Base b;


    protected int refresh;

    public InterfaceFrame()
    {

    }

    /**
     * Ustawianie ogolnych parametrow okien
     * @param frame_name nazwa okna
     * @param width szerokosc okna
     * @param height wysokosc okna
     * @param b baza obiektow
     */
    public InterfaceFrame(String frame_name, int width, int height, Base b)
    {

        frame = new JFrame(frame_name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-width/2,dim.height/2- height/2);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);


        this.b=b;
    }

}


/**
 * Okno glowne calego programu.
 */
class MainFrame extends InterfaceFrame
{

    protected TitleLabel title, spec;
    protected ShowButton showButton;
    protected AddButton addButton;
    protected ExitButton exitButton;
    protected GeoComboBox geoComboBox;
    protected OptionComboBox optionComboBox;
    protected InterfaceTable geoTable;
    protected SortButton sortButton;
    protected DeleteButton deleteButton;


    public MainFrame(String frame_name,Base b)
    {
        super(frame_name,Const.width+100,Const.heigth,b);
        this.refresh=0;
        this.set_buttons();
        this.set_gif();
        this.b=b;

        frame.pack();
        frame.setVisible(true);
    }

    protected void set_gif()
    {
        Icon icon = new ImageIcon("obraz.gif");
        JLabel l = new JLabel(icon);
        l.setBounds(750,10,100,100);
        panel.add(l);
    }

    /**
     * Ustawanie parametrow dla wszytskich pol w klasie.
     * Dodanie ActionListener do kazdego przycisku w oknie.
     */
    protected void set_buttons()
    {

        title = new TitleLabel("Base of geographic objects",0,20,Const.width+100,50,25, SwingConstants.CENTER);
        spec = new TitleLabel("You can add objects [Add Object], display [Show results] and delete [Delete Object].", 100,340,Const.width,50,13, SwingConstants.CENTER);


        showButton = new ShowButton();
        addButton = new AddButton();
        exitButton = new ExitButton("Exit",50,290,150,50);
        geoComboBox = new GeoComboBox(50,170,150,50);//50,170,150,50
        optionComboBox = new OptionComboBox(Const.width/2,Const.heigth/2-95,150,35);
        String[] columns = {"Name","Count/Area","Latitude","Longitude","a.s.l.","City A", "City B"};
        geoTable = new InterfaceTable(new DefaultTableModel(columns,0));
        sortButton = new SortButton();
        deleteButton = new DeleteButton("Delete Object");

        deleteButton.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteFrame = new DeleteFrame(b);
            }
        });

        showButton.button.addActionListener(new ActionListener() {
            /**
             * Przed pokazaniem obiektow w tabeli czyscimy ja (z wyjatkiem pierwszego uzycia showButton - licznik refresh).
             * Sprawdzamy aktualna wartosc GeoComboBox. Nastepnie sprawdzamy kazdy element w liscie czy jest tego rodzaju (np. tylko miasta).
             * Metoda to_table() zwraca Object[] z danymi do wyswietlenia w tabeli.
             */

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(refresh>0)
                {
                    int index = geoTable.dtm.getRowCount();
                    for(int i = index-1; i>=0;i--)
                    {
                        geoTable.dtm.removeRow(i);
                    }
                }
                String s = geoComboBox.get_currnet_item();
                if(s=="Select") {
                   List l = b.list_of_objects;
                   Elem e = b.list_of_objects.first;
                   while (e!=null)
                   {
                       Object[] o = e.value.to_table();
                       geoTable.dtm.addRow(o);
                       e=e.next;
                   }
                }
                else
                {
                    s = geoComboBox.get_id(s);
                    List l = b.filter(s);
                    Elem e = l.first;
                    while (e!=null)
                    {
                        Object[] o = e.value.to_table();
                        geoTable.dtm.addRow(o);
                        e = e.next;
                    }
                }
                refresh++;
            }
        });

        exitButton.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                exitFrame = new ExitFrame("Exit",b);
            }
        });

        addButton.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                addFrame = new AddFrame("Add Object", columns,b);
            }
        });

        sortButton.button.addActionListener(new ActionListener() {

            /**
             * Sprawdzamy aktualna wartosc OptionComboBox i wg danego parametru (np. wg nazwy) sortujemy obiekty na liscie.
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String s = optionComboBox.get_currnet_item();
                if(s!="Select")
                {
                    b.list_of_objects.sort(s);
                    showButton.button.doClick();
                }
            }
        });

        /**
         * Dodawanie wszystich pol do JPanel
         */
        panel.add(title.label);
        panel.add(spec.label);
        panel.add(showButton.button);
        panel.add(addButton.button);
        panel.add(exitButton.button);
        panel.add(geoComboBox.box);
        panel.add(optionComboBox.box);
        panel.add(geoTable.sp);
        panel.add(sortButton.button);
        panel.add(deleteButton.button);
        frame.add(panel);

    }
}


/**
 * Okno w ktorym mozemy dodac nowy obiekt
 */
class AddFrame extends InterfaceFrame
{

    GeoTextField[] textFields;
    TitleLabel info;
    TitleLabel[] labels;
    AddButton add;
    ExitButton close;
    String[] s_text;
    String[] s_label;
    GeoComboBox box;

    /**
     * Konstruktor
     * @param frame_name nazwa okna
     * @param s tablica String'ow z parametrami obiektow (czyli "name", "count" itp)
     * @param b baza do ktorej dodajemy obiekty
     */
    public AddFrame(String frame_name, String[] s, Base b)
    {
        super(frame_name,300,500,b);
        this.s_label = new String[7];
        this.s_label=s;
        this.s_text = new String[7];
        this.set_buttons();
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Ustawanie parametrow wszytskich pol w oknie.
     */
    protected void set_buttons()
    {

        info = new TitleLabel("Enter the object's data",0,0,300,50,12,SwingConstants.CENTER);  //Label("Wpisz dane obiektu", SwingConstants.CENTER);

        box = new GeoComboBox(0,50,300,50);
        textFields = new GeoTextField[7];
        labels = new TitleLabel[7];

        for(int i=0;i<7;i++)
        {
            textFields[i] = new GeoTextField(i);
            labels[i] = new TitleLabel(this.s_label[i],0,50*(i+2),150,50,12,SwingConstants.CENTER);
        }
        textFields[0].textField.setText(empty);
        textFields[1].textField.setText(empty_number);
        textFields[2].textField.setText(empty_number);
        textFields[3].textField.setText(empty_number);
        textFields[4].textField.setText(empty_number);
        textFields[5].textField.setText(empty);
        textFields[6].textField.setText(empty);

        add = new AddButton("Add",0,450,150,50);
        close = new ExitButton("Exit",150,450,150,50);

        box.box.addActionListener(new ActionListener() {

            /**
             * Dezaktywoawanie odpowidnich pol w zaleznosci od rodzaju obiektu, ktory chcemy dodac.
             * Jest to konieczne, aby uzytkownik nie wpisywal niepotrzebnch danych.
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String source = box.get_currnet_item();
                for(int i=0;i<7;i++) textFields[i].textField.setEnabled(true);
                if(source!="Roads")
                {
                    textFields[5].textField.setEnabled(false);
                    textFields[6].textField.setEnabled(false);
                }

                if(source=="Mountain")
                {

                    textFields[1].textField.setEnabled(false);
                }
                else if(source=="Seas")
                {
                    textFields[4].textField.setEnabled(false);
                }
                else if(source=="Roads")
                {
                    textFields[1].textField.setEnabled(false);
                    textFields[2].textField.setEnabled(false);
                    textFields[3].textField.setEnabled(false);
                    textFields[4].textField.setEnabled(false);
                }
                else if(source=="Airports")
                {
                    textFields[1].textField.setEnabled(false);
                }
            }
        });

        add.button.addActionListener(new ActionListener() {

            /**
             * Try-catchowanie danych wpisanych przez uzytkownika.
             * W przypadku wylapania wyjatku pokazujemy okno bledu.
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                for(int i=0;i<7;i++)
                {
                    s_text[i]=textFields[i].textField.getText();
                }

                String source = box.get_currnet_item();
                if(source=="Select")
                {
                    errorFrame = new ErrorFrame("Error","Incorrect data",b);
                }
                else
                {
                    try
                    {
                        Object[] tmp;
                        tmp = new Object[7];
                        tmp[0] = s_text[0];
                        tmp[1] = Integer.parseInt(s_text[1]);
                        tmp[2] = Double.parseDouble(s_text[2]);
                        tmp[3] = Double.parseDouble(s_text[3]);
                        tmp[4] = Double.parseDouble(s_text[4]);
                        tmp[5] = s_text[5];
                        tmp[6] = s_text[6];
                        b.create_new(tmp,source);
                        frame.dispose();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                        errorFrame = new ErrorFrame("Error","Incorret data",b);
                    }
                }

            }
        });

        close.button.addActionListener(new ActionListener() {
            /**
             * Zamkniecie okna
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                frame.dispose();
            }
        });

        panel.add(info.label);
        panel.add(box.box);
        for(int i=0;i<7;i++)
        {
            panel.add(textFields[i].textField);
            panel.add(labels[i].label);
        }
        panel.add(add.button);
        panel.add(close.button);
        frame.add(panel);
    }
}

/**
 * Okno w ktorym pytamy sie uzytkownika czy chce zakonczyc program.
 * exitButtony - "Yes"
 * exitButtonn - "No"
 */
class ExitFrame extends InterfaceFrame
{

    protected ExitButton exitButtony, exitButtonn;
    protected TitleLabel info;

    /**
     * Konstruktor
     * @param frame_name nazwa okna
     * @param b baza obiektow
     */
    public ExitFrame(String frame_name, Base b)
    {
        super(frame_name, Const.width/4, Const.heigth/4,b);
        this.set_buttons();
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Ustawanie parametrow pol tego okna.
     */
    private void set_buttons()
    {

        info = new TitleLabel("Are you sure?",0,0,200,50,15, SwingConstants.CENTER);
        exitButtony = new ExitButton("Yes",20,50,70,50);
        exitButtonn = new ExitButton("No",110,50,70,50);
        exitButtony.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        exitButtonn.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });

        panel.add(info.label);
        panel.add(exitButtony.button);
        panel.add(exitButtonn.button);
        frame.add(panel);
    }

}


/**
 * Okno bledu
 */
class ErrorFrame extends InterfaceFrame
{

    TitleLabel l;
    ErrorButton b;

    /**
     * Konstruktor
     * @param frame_name nazwa okna
     * @param b baza obiektow
     */
    public ErrorFrame(String frame_name,String info, Base b)
    {
        super(frame_name,200,100,b);
        this.set_buttons(info);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Ustawanie parametrow przyciskow
     */
    protected void set_buttons(String info)
    {
        l = new TitleLabel(info,0,0,200,50,12,SwingConstants.CENTER);
        b = new ErrorButton("Ok :)",50,50,100,50);
        b.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });

        panel.add(l.label);
        panel.add(b.button);
        frame.add(panel);
    }
}

/**
 * Okno w ktorym mozemy usunac wybrany obiekt z bazy
 */
class DeleteFrame extends InterfaceFrame
{

    TitleLabel label;
    DeleteButton button;
    ExitButton exitButton;
    JTextField textField;
    String name;
    Base b;

    /**
     * Konstruktor okna do zamkniecia programu
     * @param b baza obiektow
     */
    public DeleteFrame(Base b)
    {
        super("Delete Object",300,100,b);
        this.b=b;
        set_buttons();
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Ustawanie parametrow pol w oknie.
     */
    protected void set_buttons()
    {

        label = new TitleLabel("Name of object",0,0,150,50,12,SwingConstants.CENTER);
        button = new DeleteButton();
        exitButton = new ExitButton("Exit",150,50,150,50);
        textField = new JTextField(this.name,40);
        textField.setText(empty);
        textField.setBounds(150,0,150,50);

        button.button.addActionListener(new ActionListener() {

            /**
             * Usuwanie obiektu z bazy. Sprawadzamy wpisany napis przez uzytkownika.
             * Jesli usuniecie sie powiodlo, to zamkykamy okno.
             * W przeciwym przypadku wyswietlamy informacje o bledzie.
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String n =textField.getText();
                String info;
                if(b.list_of_objects.size_of_list()==0)
                {
                    errorFrame = new ErrorFrame("Error","Base is empty",b);
                }
                else if(b.list_of_objects.find_to_delete(n)==0)
                {
                    errorFrame = new ErrorFrame("Error","Incorrect data",b);
                }
                else frame.dispose();
            }
        });

        exitButton.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });

        panel.add(label.label);
        panel.add(button.button);
        panel.add(textField);
        panel.add(exitButton.button);
        frame.add(panel);
    }
}
