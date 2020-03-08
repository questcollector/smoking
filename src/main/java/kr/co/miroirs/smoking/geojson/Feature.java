package kr.co.miroirs.smoking.geojson;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

public class Feature {

    private String type;
    
    @JsonSerialize(using = GeometrySerializer.class)
    private Geometry geometry;
    private Object properties;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Geometry getGeometry() {
        return geometry;
    }
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
    public Object getProperties() {
        return properties;
    }
    public void setProperties(Object properties) {
        this.properties = properties;
    }
    
    @Override
    public String toString() {
        return "Feature [type=" + type + ", geometry=" + geometry + ", properties=" + properties + "]";
    }
}
