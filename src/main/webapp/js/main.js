window.addEventListener("DOMContentLoaded", function() {

class MapLayer {
    constructor() {
        this.key = "FBDFD0B2-17E5-3FE2-8FBF-B2E1A198CE74";
        this.layer = "Base";
        this.tileType = "png";
        
        this.setPointStyle();
        this.smokingAreaAjax();
    }

    setPointStyle() {
        this.styleP015 = new ol.style.Style({
            image: new ol.style.RegularShape({
                fill: new ol.style.Fill({color: "purple"}),
                stroke: new ol.style.Stroke({color: "red", width: 2}),
                points: 5,
                radius: 10, 
                radius2: 4,
                angle: 0
            })
        });
        this.styleP100 = new ol.style.Style({
            image: new ol.style.RegularShape({
                fill: new ol.style.Fill({color: "green"}),
                stroke: new ol.style.Stroke({color: "blue", width: 2}),
                points: 5,
                radius: 10, 
                radius2: 4,
                angle: 0
            })
        });
    }

    smokingAreaAjax() {
        var oReq = new XMLHttpRequest();
        oReq.addEventListener("load", function() {
            var data = JSON.parse(oReq.responseText);
            var vectorLayers = this.composeVetorLayer(data);
            var tile = this.composeTile();
            vectorLayers.unshift(tile);
            this.composeMap(vectorLayers);
        }.bind(this));
        oReq.open("GET","api/smokingArea");
        oReq.send();
    }

    composeVetorLayer(data) {
        var p015 = data["P015"];
        var p100 = data["P100"];

        // epsg 5186 projection inject
        proj4.defs("EPSG:5186","+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs");
        ol.proj.proj4.register(proj4);
        ol.proj.get('EPSG:5186').setExtent([-219825.99, -435028.96,
            819486.07, 877525.22]);
        var epsg5186 = ol.proj.get('EPSG:5186');
        var epsg3857 = ol.proj.get('EPSG:3857');

        var vectorLayerP015 = new ol.layer.Vector({
            source: new ol.source.Vector({
                features: new ol.format.GeoJSON()
                                .readFeatures(p015, {
                                    dataProjection: epsg5186,
                                    featureProjection: epsg3857})
            }),
            style: this.styleP015
        });
        var vectorLayerP100 = new ol.layer.Vector({
            source: new ol.source.Vector({
                features: new ol.format.GeoJSON()
                                .readFeatures(p100, {
                                    dataProjection: epsg5186,
                                    featureProjection: epsg3857})
            }),
            style: this.styleP100
        });
        debugger
        return [vectorLayerP015, vectorLayerP100];
    }

    composeTile() {
        var wmts = this.setUrl('http://api.vworld.kr/req/wmts/1.0.0/{key}/{layer}/{z}/{y}/{x}.{tileType}');
        var tms = this.setUrl('http://api.vworld.kr/req/tms/1.0.0/{key}/{layer}/{z}/{y}/{x}.{tileType}');
        
        var tile = new ol.layer.Tile({
            source: new ol.source.XYZ({
                url: wmts
            })
        });

        return tile;
    }
    
    composeMap(lyrs) {
        var centerPnt = ol.proj.fromLonLat([127.085851, 37.537097]);

        var map = new ol.Map({
            target: document.getElementById("map"),
            layers: lyrs,
            view: new ol.View({
                center: centerPnt,
                zoom: 15,
                minZoom: 6
            })
        });
    }
    
    setUrl(url) {
        var newUrl = url
                    .replace("{key}", this.key)
                    .replace("{layer}", this.layer)
                    .replace("{tileType}", this.tileType);
        
        return newUrl;
    }
}

const mapLayer = new MapLayer();

});