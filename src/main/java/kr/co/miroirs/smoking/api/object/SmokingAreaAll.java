package kr.co.miroirs.smoking.api.object;

import kr.co.miroirs.smoking.geojson.GeoJson;

public class SmokingAreaAll {

    private GeoJson p015;
    private GeoJson p100;
    
    public GeoJson getP015() {
        return p015;
    }
    public void setP015(GeoJson p015) {
        this.p015 = p015;
    }
    public GeoJson getP100() {
        return p100;
    }
    public void setP100(GeoJson p100) {
        this.p100 = p100;
    }
    @Override
    public String toString() {
        return "SmokingAreaAll [P015=" + p015 + ", P100=" + p100 + "]";
    }
}
