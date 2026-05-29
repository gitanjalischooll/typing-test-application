import java.util.*;

public class ParaManager 
{
    private ArrayList<String> list = new ArrayList<>();

    public ParaManager()
    {
        list.add("Technology is an important part of our daily life. It helps us communicate, learn, and work more efficiently. However, we should use it wisely and avoid overdependence.");
        list.add("Exercise keeps our body healthy and active. It improves energy, reduces stress, and helps us stay fit. Even a short daily workout can make a big difference..");
        list.add("Reading books improves knowledge and imagination. It helps us learn new ideas and enhances our thinking skills. Developing this habit is very beneficial..");
    }

    public String getPara()
    {
        Random r = new Random();
        int i = r.nextInt(list.size());
        return list.get(i);
    }
}