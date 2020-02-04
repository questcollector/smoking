package kr.co.miroirs.smoking.objectMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import kr.co.miroirs.smoking.apiObject.SmokingAreaAll;
import kr.co.miroirs.smoking.dto.SmokingArea;

public class GeoJsonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GeoJsonObjectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(SmokingAreaAll.class, new JsonSerializer<SmokingAreaAll>() {

            @Override
            public void serialize(SmokingAreaAll smoking, JsonGenerator generator, SerializerProvider serializers)
                    throws IOException {
                generator.writeStartObject();
                generator.writeFieldName("P015");
                
                generator.writeStartObject();  
                generator.writeFieldName("type");
                generator.writeString("FeatureCollection");
                
                generator.writeFieldName("features");
                
                List<SmokingArea> p015 = smoking.getP015();
                
                generator.writeStartArray(p015.size());
                
                for (SmokingArea point: p015) {
                    writeFeature(generator, point);
                }
                
                generator.writeEndArray();        
                generator.writeEndObject();
                
                generator.writeFieldName("P100");
                generator.writeStartObject();
                generator.writeFieldName("type");
                generator.writeString("FeatureCollection");
                
                generator.writeFieldName("features");
                
                List<SmokingArea> p100 = smoking.getP100();
                
                generator.writeStartArray(p100.size());
                
                for (SmokingArea point: p100) {
                    writeFeature(generator, point); 
                }
                
                generator.writeEndArray();
                generator.writeEndObject();
                
                generator.writeEndObject();
                
            }
            
            private void writeFeature(JsonGenerator generator, SmokingArea point) throws IOException {
                try {
                    generator.writeStartObject();
                    
                    generator.writeFieldName("type");
                    generator.writeString("Feature");
                    
                    generator.writeFieldName("geometry");
                    
                    PGobject pgo = point.getGeom();
                    new PGgeometry();
                    Geometry g = PGgeometry.geomFromString(pgo.getValue());
                    Point p = g.getPoint(0);
                    
                    generator.writeStartObject();
                    generator.writeFieldName("type");
                    generator.writeString(setGeomType(g.getType()));
                    generator.writeFieldName("coordinates");
                    generator.writeStartArray(2);
                    generator.writeNumber(p.getX());
                    generator.writeNumber(p.getY());
                    generator.writeEndArray();
                    generator.writeEndObject();
                    
                    generator.writeFieldName("properties");
                    generator.writeStartObject();
                    generator.writeFieldName("id");
                    generator.writeNumber(point.getId());
                    generator.writeFieldName("dayMorning");
                    generator.writeNumber(point.getDayMorning());
                    generator.writeFieldName("dayNoon");
                    generator.writeNumber(point.getDayNoon());
                    generator.writeFieldName("dayAfternoon");
                    generator.writeNumber(point.getDayAfternoon());
                    generator.writeFieldName("dayEvening");
                    generator.writeNumber(point.getDayEvening());
                    generator.writeFieldName("dayNight");
                    generator.writeNumber(point.getDayNight());
                    generator.writeFieldName("endMorning");
                    generator.writeNumber(point.getEndMorning());
                    generator.writeFieldName("endNoon");
                    generator.writeNumber(point.getEndNoon());
                    generator.writeFieldName("endAfternoon");
                    generator.writeNumber(point.getEndAfternoon());
                    generator.writeFieldName("endEvening");
                    generator.writeNumber(point.getEndEvening());
                    generator.writeFieldName("endNight");
                    generator.writeNumber(point.getEndNight());
                    generator.writeFieldName("count");
                    generator.writeNumber(point.getCount());
                    generator.writeFieldName("value");
                    generator.writeNumber(point.getValue());
                    generator.writeFieldName("clusterId");
                    generator.writeNumber(point.getClusterId());
                    generator.writeEndObject();
                    
                    generator.writeEndObject();
                    
                } catch(SQLException e) {
                    logger.debug("{}: {}", e.getCause(), e.getMessage());
                }
            }      
        });
        
        registerModule(simpleModule);
    }
    
    private String setGeomType(int type) {
        if (type == 1) return "Point";
        else if (type == 2) return "LingString";
        else if (type == 3) return "Polygon";
        else if (type == 4) return "MultiPoint";
        else if (type == 5) return "MultiLineString";
        else if (type == 6) return "MultiPolygon";
        else return "GeometryCollection";
    }
}
