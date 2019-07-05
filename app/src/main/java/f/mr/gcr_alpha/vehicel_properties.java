package f.mr.gcr_alpha;


import java.io.Serializable;

public class vehicel_properties implements Serializable{

    public int _id;
    public int gewicht;
    public int kapazitaet;
    public int reichweite;
    public int preis;
    public int beschleunigung;
    public int max_tempo;
    public int widerstand;
    public int turbotempo;


    public vehicel_properties()
    {
        this._id = 0;
        this.gewicht = 0;
        this.kapazitaet = 0;
        this.reichweite = 0;
        this.preis = 0;
        this.beschleunigung = 0;
        this.max_tempo = 0;
        this.widerstand = 0;
        this.turbotempo = 0;
    }

    public vehicel_properties(int __id, int _gewicht, int _kapazitaet, int _reichweite, int _preis, int _beschleunigung, int _max_tempo, int _widerstand, int _turbotempo)
    {
        this._id = __id;
        this.gewicht = _gewicht;
        this.kapazitaet = _kapazitaet;
        this.reichweite = _reichweite;
        this.preis = _preis;
        this.beschleunigung = _beschleunigung;
        this.max_tempo = _max_tempo;
        this.widerstand = _widerstand;
        this.turbotempo = _turbotempo;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int __id)
    {
        this._id = __id;
    }

    public int getGewicht()
    {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public void setKapazitaet(int kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    public int getReichweite() {
        return reichweite;
    }

    public void setReichweite(int reichweite) {
        this.reichweite = reichweite;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getBeschleunigung() {
        return beschleunigung;
    }

    public void setBeschleunigung(int beschleunigung) {
        this.beschleunigung = beschleunigung;
    }

    public int getMax_tempo() {
        return max_tempo;
    }

    public void setMax_tempo(int max_tempo) {
        this.max_tempo = max_tempo;
    }

    public int getTurbotempo() {
        return turbotempo;
    }

    public void setTurbotempo(int turbotempo) {
        this.turbotempo = turbotempo;
    }

    public int getWiderstand() {
        return widerstand;
    }

    public void setWiderstand(int widerstand) {
        this.widerstand = widerstand;
    }
}
