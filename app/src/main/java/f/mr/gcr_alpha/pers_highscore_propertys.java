package f.mr.gcr_alpha;

import java.io.Serializable;


public class pers_highscore_propertys implements Serializable {

    private static int COUNTER = 10;

    private String[] player_name;
    private int[] money_score;
    private int[] miles_score;
    private int[] place;

    private static final long serialVersionUID = 46543449;

    public pers_highscore_propertys(){
        this.player_name = new String[COUNTER];
        this.miles_score = new int[COUNTER];
        this.money_score = new int[COUNTER];
        this.place = new int[COUNTER];
    }

    public pers_highscore_propertys(String[] _player_name, int[] _money_score, int[] _miles_score, int[] _place)
    {
        this.player_name = _player_name;
        this.money_score = _money_score;
        this.miles_score = _miles_score;
        this.place = _place;
    }

    public int[] getMiles_score() {
        return miles_score;
    }

    public int[] getMoney_score() {
        return money_score;
    }

    public int[] getPlace() {
        return place;
    }

    public String[] getPlayer_name() {
        return player_name;
    }

    public void setMiles_score(int[] miles_score) {
        this.miles_score = miles_score;
    }

    public void setMoney_score(int[] money_score) {
        this.money_score = money_score;
    }

    public void setPlace(int[] place) {
        this.place = place;
    }

    public void setPlayer_name(String[] player_name) {
        this.player_name = player_name;
    }

}
