public class Result 
{
    private int wpm;
    private double acc;
    private boolean flag = false;

    public void save(int w, double a)
    {
        wpm = w;
        acc = a;
        flag = true;
    }

    public String show()
    {
        if(flag == false)
            return "No test done";

        return "Speed = " + wpm + " WPM , Accuracy = " + String.format("%.2f", acc) + "%";
    }
}