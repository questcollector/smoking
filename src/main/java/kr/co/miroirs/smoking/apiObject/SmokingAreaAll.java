package kr.co.miroirs.smoking.apiObject;

import java.util.List;

import kr.co.miroirs.smoking.dto.SmokingArea;

public class SmokingAreaAll {

    private List<SmokingArea> P015;
    private List<SmokingArea> P100;
    
    public List<SmokingArea> getP015() {
        return P015;
    }
    public void setP015(List<SmokingArea> p015) {
        P015 = p015;
    }
    public List<SmokingArea> getP100() {
        return P100;
    }
    public void setP100(List<SmokingArea> p100) {
        P100 = p100;
    }
    @Override
    public String toString() {
        return "SmokingAreaAll [P015=" + P015 + ", P100=" + P100 + "]";
    }
}
