package f.mr.gcr_alpha;

import android.util.Log;

import java.io.Serializable;


public class main_properties implements Serializable {

    private int points;
    private int diamonds;
    private int level;
    private int exp;
    private int map_resource;
    private usr_properties user;
    private vehicel_properties vehicel;

    private static final long serialVersionUID = 46543445;

    public main_properties(){
        this.points = 0;
        this.diamonds = 0;
        this.level = 1;
        this.exp = 0;
        this.map_resource = 0;
        this.user = new usr_properties();
        this.vehicel = new vehicel_properties();
    }

    public main_properties(int _points, int _diamonds, int _level, int _exp, int _map_resource, usr_properties _user, vehicel_properties _vehicel)
    {
        this.points = _points;
        this.diamonds = _diamonds;
        this.level = _level;
        this.exp = _exp;
        this.map_resource = _map_resource;
        this.user = _user;
        this.vehicel = _vehicel;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int _points)
    {
        this.points = _points;
    }

    public int getDiamonds()
    {
        return diamonds;
    }

    public void setDiamonds(int _diamonds)
    {
        this.diamonds = _diamonds;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int _level)
    {
        this.level = _level;
    }

    public int getExp()
    {
        return exp;
    }

    public void setExp(int _exp)
    {
        this.exp = _exp;
    }

    public int getMap_resource()
    {
        return map_resource;
    }

    public void setMap_resource(int _map_resource)
    {
        this.map_resource = _map_resource;
    }

    public usr_properties getUser()
    {
        return user;
    }

    public void setUser(usr_properties _user)
    {
        this.user = _user;
    }

    public vehicel_properties getVehicel()
    {
        return vehicel;
    }

    public void setVehicel(vehicel_properties _vehicel)
    {
        this.vehicel = _vehicel;
    }

}
