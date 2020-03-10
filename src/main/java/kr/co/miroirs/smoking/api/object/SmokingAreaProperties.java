package kr.co.miroirs.smoking.api.object;

public class SmokingAreaProperties {

    private int id;
    private int dayMorning;
    private int dayNoon;
    private int dayAfternoon;
    private int dayEvening;
    private int dayNight;
    private int endMorning;
    private int endNoon;
    private int endAfternoon;
    private int endEvening;
    private int endNight;
    private int count;
    private double value;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDayMorning() {
        return dayMorning;
    }
    public void setDayMorning(int dayMorning) {
        this.dayMorning = dayMorning;
    }
    public int getDayNoon() {
        return dayNoon;
    }
    public void setDayNoon(int dayNoon) {
        this.dayNoon = dayNoon;
    }
    public int getDayAfternoon() {
        return dayAfternoon;
    }
    public void setDayAfternoon(int dayAfternoon) {
        this.dayAfternoon = dayAfternoon;
    }
    public int getDayEvening() {
        return dayEvening;
    }
    public void setDayEvening(int dayEvening) {
        this.dayEvening = dayEvening;
    }
    public int getDayNight() {
        return dayNight;
    }
    public void setDayNight(int dayNight) {
        this.dayNight = dayNight;
    }
    public int getEndMorning() {
        return endMorning;
    }
    public void setEndMorning(int endMorning) {
        this.endMorning = endMorning;
    }
    public int getEndNoon() {
        return endNoon;
    }
    public void setEndNoon(int endNoon) {
        this.endNoon = endNoon;
    }
    public int getEndAfternoon() {
        return endAfternoon;
    }
    public void setEndAfternoon(int endAfternoon) {
        this.endAfternoon = endAfternoon;
    }
    public int getEndEvening() {
        return endEvening;
    }
    public void setEndEvening(int endEvening) {
        this.endEvening = endEvening;
    }
    public int getEndNight() {
        return endNight;
    }
    public void setEndNight(int endNight) {
        this.endNight = endNight;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "SmokingAreaProperties [id=" + id + ", dayMorning=" + dayMorning + ", dayNoon=" + dayNoon
                + ", dayAfternoon=" + dayAfternoon + ", dayEvening=" + dayEvening + ", dayNight=" + dayNight
                + ", endMorning=" + endMorning + ", endNoon=" + endNoon + ", endAfternoon=" + endAfternoon
                + ", endEvening=" + endEvening + ", endNight=" + endNight + ", count=" + count + ", value=" + value
                + "]";
    }
    
    
}
