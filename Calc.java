public class Calc 
{
    public int getWPM(String t, int s)
    {
        if(t == null || t.equals("") || s <= 0)
            return 0;

        String arr[] = t.split(" ");
        int w = arr.length;

        return (w * 60) / s;
    }

    public double getAcc(String o, String ty)
    {
        int correct = 0;

        int len;
        if(o.length() < ty.length())
            len = o.length();
        else
            len = ty.length();

        for(int i = 0; i < len; i++)
        {
            if(o.charAt(i) == ty.charAt(i))
                correct++;
        }

        double acc = (correct * 100.0) / o.length();

        if(acc > 100)
            acc = 100;

        return acc;
    }
}