package kr.co.miroirs.smoking.dto;

import javax.persistence.Id;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.MultiPolygon;

public class BuildingCluster {

    @Id
    private int id;
    
    @JsonSerialize(using = GeometrySerializer.class)
    private MultiPolygon geometry;
    
    private int cluster;
    private String createDate;
    private String sigCode;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public MultiPolygon getGeometry() {
        return geometry;
    }
    public void setGeometry(MultiPolygon geom) {
        this.geometry = geom;
    }
    public int getCluster() {
        return cluster;
    }
    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getSigCode() {
        return sigCode;
    }
    public void setSigCode(String sigCode) {
        this.sigCode = sigCode;
    }
    @Override
    public String toString() {
        return "BuildingCluster [id=" + id + ", geom=" + geometry + ", cluster=" + cluster + ", createDate=" + createDate
                + ", sigCode=" + sigCode + "]";
    }
    
    
}
