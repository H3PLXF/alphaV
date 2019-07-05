package f.mr.gcr_alpha;

import java.io.Serializable;


public class static_einstellungen implements Serializable {

    private int GESCHWINDIGKEIT = 10; //kmh
    private int ANZAHL_MONEY = 10;
    private int ANZAHL_OBSTACES = 35;
    private int ANZAHL_Y_POINTS = 40;
    private int ANZAHL_LEBEN = 3;
    private int MAXIMALE_ANZAHL_COL_OBSTACLE = 10;
    private int MAXIMALE_ANZAHL_ROW_OBSTACLE = 7; //Startend von 0
    //GROESSE + (ABSTAND * 2) müssen immer 1 ergeben
    private double GROESSE_USER_IMG = 0.8;
    private double ABSTAND_SPURLINIE_USER = 0.1;
    private double GROESSE_MONEY = 0.3;
    private double ABSTAND_SPURLINIE_MONEY = 0.35;
    private double GROESSE_OBSTACLE = 0.75;
    private double ABSTAND_SPURLINIE_OBSTACLE = 0.35;
    private double OBSTACLE_PERCENT_FROM_USER = 3;//0.75;
    private double MONEY_PERCENT_FROM_USER = 0.5;
    private int FLASHTIME_USR = 100;
    private int FLASHCNT_USR = 20;

    private double less_hit_size = 0.9;

    private boolean isVisible;

    private static final long serialVersionUID = 46543446;

    public static_einstellungen(){
        this.GESCHWINDIGKEIT = 10;
        this.ANZAHL_MONEY = 10;
        this.ANZAHL_OBSTACES = 35;
        this.ANZAHL_Y_POINTS = 40;
        this.ANZAHL_LEBEN = 3;
        this.MAXIMALE_ANZAHL_COL_OBSTACLE = 10;
        this.MAXIMALE_ANZAHL_ROW_OBSTACLE = 7;
        this.GROESSE_USER_IMG = 0.8;
        this.ABSTAND_SPURLINIE_USER = 0.1;
        this.GROESSE_MONEY = 0.3;
        this.ABSTAND_SPURLINIE_MONEY = 0.35;
        this.GROESSE_OBSTACLE = 0.75;
        this.ABSTAND_SPURLINIE_OBSTACLE = 0.35;
        this.OBSTACLE_PERCENT_FROM_USER = 3;
        this.MONEY_PERCENT_FROM_USER = 0.35;
        this.FLASHTIME_USR = 100;
       this.FLASHCNT_USR = 20;
       this.less_hit_size = 1;
        this.isVisible = true;
    }

    public static_einstellungen(int speed, int anzahl_money, int anzahl_obst, int anzahl_Y, int anzahl_leben, int max_anz_col_obst, int max_anz_row_obst,
                                double groesse_USRIMG, double abstand_USRIMG, double groesseMoney, double abstandMoney, double groesseObst, double abstandObst,
                                double obst_percentUSRIMG, double money_percentUSRIMG, int flashtime, int flashcnt, double less_hit, boolean isVisible)
    {
        this.GESCHWINDIGKEIT = speed;
        this.ANZAHL_MONEY = anzahl_money;
        this.ANZAHL_OBSTACES = anzahl_obst;
        this.ANZAHL_Y_POINTS = anzahl_Y;
        this.ANZAHL_LEBEN = anzahl_leben;
        this.MAXIMALE_ANZAHL_COL_OBSTACLE = max_anz_col_obst;
        this.MAXIMALE_ANZAHL_ROW_OBSTACLE = max_anz_row_obst;
        this.GROESSE_USER_IMG = groesse_USRIMG;
        this.ABSTAND_SPURLINIE_USER = abstand_USRIMG;
        this.GROESSE_MONEY = groesseMoney;
        this.ABSTAND_SPURLINIE_MONEY = abstandMoney;
        this.GROESSE_OBSTACLE = groesseObst;
        this.ABSTAND_SPURLINIE_OBSTACLE = abstandObst;
        this.OBSTACLE_PERCENT_FROM_USER = obst_percentUSRIMG;
        this.MONEY_PERCENT_FROM_USER = money_percentUSRIMG;
        this.FLASHTIME_USR = flashtime;
        this.FLASHCNT_USR = flashcnt;
        this.less_hit_size = less_hit;
        this.isVisible = isVisible;

    }

    public int getGESCHWINDIGKEIT()
    {
        return GESCHWINDIGKEIT;
    }

    public void setGESCHWINDIGKEIT(int s)
    {
        this.GESCHWINDIGKEIT = s;
    }

    public double getABSTAND_SPURLINIE_MONEY() {
        return ABSTAND_SPURLINIE_MONEY;
    }

    public double getABSTAND_SPURLINIE_OBSTACLE() {
        return ABSTAND_SPURLINIE_OBSTACLE;
    }

    public double getABSTAND_SPURLINIE_USER() {
        return ABSTAND_SPURLINIE_USER;
    }

    public double getGROESSE_USER_IMG() {
        return GROESSE_USER_IMG;
    }

    public double getGROESSE_MONEY() {
        return GROESSE_MONEY;
    }

    public double getGROESSE_OBSTACLE() {
        return GROESSE_OBSTACLE;
    }

    public int getANZAHL_LEBEN() {
        return ANZAHL_LEBEN;
    }

    public int getANZAHL_MONEY() {
        return ANZAHL_MONEY;
    }

    public int getANZAHL_OBSTACES() {
        return ANZAHL_OBSTACES;
    }

    public int getANZAHL_Y_POINTS() {
        return ANZAHL_Y_POINTS;
    }

    public int getMAXIMALE_ANZAHL_COL_OBSTACLE() {
        return MAXIMALE_ANZAHL_COL_OBSTACLE;
    }

    public int getMAXIMALE_ANZAHL_ROW_OBSTACLE() {
        return MAXIMALE_ANZAHL_ROW_OBSTACLE;
    }

    public void setABSTAND_SPURLINIE_USER(double ABSTAND_SPURLINIE_USER) {
        this.ABSTAND_SPURLINIE_USER = ABSTAND_SPURLINIE_USER;
    }

    public void setABSTAND_SPURLINIE_MONEY(double ABSTAND_SPURLINIE_MONEY) {
        this.ABSTAND_SPURLINIE_MONEY = ABSTAND_SPURLINIE_MONEY;
    }

    public double getMONEY_PERCENT_FROM_USER() {
        return MONEY_PERCENT_FROM_USER;
    }

    public void setABSTAND_SPURLINIE_OBSTACLE(double ABSTAND_SPURLINIE_OBSTACLE) {
        this.ABSTAND_SPURLINIE_OBSTACLE = ABSTAND_SPURLINIE_OBSTACLE;
    }

    public double getOBSTACLE_PERCENT_FROM_USER() {
        return OBSTACLE_PERCENT_FROM_USER;
    }

    public void setANZAHL_LEBEN(int ANZAHL_LEBEN) {
        this.ANZAHL_LEBEN = ANZAHL_LEBEN;
    }

    public void setANZAHL_MONEY(int ANZAHL_MONEY) {
        this.ANZAHL_MONEY = ANZAHL_MONEY;
    }

    public void setANZAHL_OBSTACES(int ANZAHL_OBSTACES) {
        this.ANZAHL_OBSTACES = ANZAHL_OBSTACES;
    }

    public void setANZAHL_Y_POINTS(int ANZAHL_Y_POINTS) {
        this.ANZAHL_Y_POINTS = ANZAHL_Y_POINTS;
    }

    public int getFLASHCNT_USR() {
        return FLASHCNT_USR;
    }

    public void setGROESSE_MONEY(double GROESSE_MONEY) {
        this.GROESSE_MONEY = GROESSE_MONEY;
    }

    public void setGROESSE_OBSTACLE(double GROESSE_OBSTACLE) {
        this.GROESSE_OBSTACLE = GROESSE_OBSTACLE;
    }

    public void setGROESSE_USER_IMG(double GROESSE_USER_IMG) {
        this.GROESSE_USER_IMG = GROESSE_USER_IMG;
    }

    public int getFLASHTIME_USR() {
        return FLASHTIME_USR;
    }

    public void setMAXIMALE_ANZAHL_COL_OBSTACLE(int MAXIMALE_ANZAHL_COL_OBSTACLE) {
        this.MAXIMALE_ANZAHL_COL_OBSTACLE = MAXIMALE_ANZAHL_COL_OBSTACLE;
    }

    public void setMAXIMALE_ANZAHL_ROW_OBSTACLE(int MAXIMALE_ANZAHL_ROW_OBSTACLE) {
        this.MAXIMALE_ANZAHL_ROW_OBSTACLE = MAXIMALE_ANZAHL_ROW_OBSTACLE;
    }

    public void setFLASHCNT_USR(int FLASHCNT_USR) {
        this.FLASHCNT_USR = FLASHCNT_USR;
    }

    public void setFLASHTIME_USR(int FLASHTIME_USR) {
        this.FLASHTIME_USR = FLASHTIME_USR;
    }

    public void setMONEY_PERCENT_FROM_USER(double MONEY_PERCENT_FROM_USER) {
        this.MONEY_PERCENT_FROM_USER = MONEY_PERCENT_FROM_USER;
    }

    public void setOBSTACLE_PERCENT_FROM_USER(double OBSTACLE_PERCENT_FROM_USER) {
        this.OBSTACLE_PERCENT_FROM_USER = OBSTACLE_PERCENT_FROM_USER;
    }

    public void setLess_hit_size(double less_hit_size) {
        this.less_hit_size = less_hit_size;
    }

    public double getLess_hit_size()
    {
        return less_hit_size;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
