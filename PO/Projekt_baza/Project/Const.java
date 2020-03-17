/**
 * Klasa stalych
 */
public class Const
{
    public static double grade = 73;  //???
    public static double Pi = 3.141592;
    public static double thesamecity = 25; //???
    public static String empty = "--";
    public static String empty_number = "0";
    public static int width = 800;
    public static int heigth = 400;
    public static double sin(double x)
    {
        return x - x * x * x / 6 + x * x * x * x * x / 120;
    }
    public static double cos(double x)
    {
        return 1 - x * x / 2 + x * x * x * x / 24;
    }

    /**
     * Metoda poronujaca stringi
     * @link https://www.geeksforgeeks.org/compare-two-strings-in-java/
     * @param str1
     * @param str2
     * @return liczba calkowita
     */
    public static int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        }

        else {
            return 0;
        }
    }

    public Const()
    {

    }
}
