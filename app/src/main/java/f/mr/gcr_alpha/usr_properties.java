package f.mr.gcr_alpha;

import java.io.Serializable;

public class usr_properties implements Serializable {

    public int _id;
    public int fahr_exp;
    public int gewicht;
    public int gehalt_pro_minute;
    public int preis;
    public String name_usr;
    public int img_resourcre;

    public usr_properties()
    {
        this._id = 0;
        this.fahr_exp = 0;
        this.gewicht = 0;
        this.gehalt_pro_minute = 0;
        this.preis = 0;
        this.name_usr = "Bonez";
        this.img_resourcre = 0;
    }

    public usr_properties(int __id, int _fahr_exp, int _gewicht, int _gehalt_pro_minute, int _preis, String _name_usr, int _img_resource)
    {
        this._id = __id;
        this.fahr_exp = _fahr_exp;
        this.gewicht = _gewicht;
        this.gehalt_pro_minute = _gehalt_pro_minute;
        this.preis = _preis;
        this.name_usr = _name_usr;
        this.img_resourcre = _img_resource;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int __id)
    {
        this._id = __id;
    }

    public int getFahr_exp()
    {
        return fahr_exp;
    }

    public void setFahr_exp(int _fahr_exp)
    {
        this.fahr_exp = _fahr_exp;
    }

    public int getGewicht()
    {
        return gewicht;
    }

    public void setGewicht(int _gewicht)
    {
        this.gewicht = _gewicht;
    }

    public int getGehalt_pro_minute()
    {
        return gehalt_pro_minute;
    }

    public void setGehalt_pro_minute(int _gehalt_pro_minute){
        this.gehalt_pro_minute = _gehalt_pro_minute;
    }

    public int getPreis()
    {
        return  preis;
    }

    public void setPreis(int _preis)
    {
        this.preis = _preis;
    }

    public String getName_usr()
    {
        return  name_usr;
    }

    public void setName_usr(String _name_usr)
    {
        this.name_usr = _name_usr;
    }

    public int getImg_resourcre()
    {
        return img_resourcre;
    }

    public void setImg_resourcre(int _img_resource)
    {
        this.img_resourcre = _img_resource;
    }
}
