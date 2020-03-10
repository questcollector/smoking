/* eslint-disable func-names */
/* eslint-disable class-methods-use-this */
/* eslint-disable no-unused-vars */
/* eslint-disable no-undef */
window.addEventListener('DOMContentLoaded', () => {
  class MapLayer {
    constructor() {
      this.key = 'FBDFD0B2-17E5-3FE2-8FBF-B2E1A198CE74';
      this.layer = 'Base';
      this.tileType = 'png';
      this.centerPnt = ol.proj.fromLonLat([127.085851, 37.537097]);
      this.layers = [];

      this.setPointStyle();
      this.smokingAreaAjax();
    }

    setPointStyle() {
      this.styleP015 = new ol.style.Style({
        image: new ol.style.RegularShape({
          fill: new ol.style.Fill({ color: 'purple' }),
          stroke: new ol.style.Stroke({ color: 'red', width: 2 }),
          points: 5,
          radius: 10,
          radius2: 4,
          angle: 0,
        }),
      });

      this.styleP100 = new ol.style.Style({
        image: new ol.style.RegularShape({
          fill: new ol.style.Fill({ color: 'green' }),
          stroke: new ol.style.Stroke({ color: 'blue', width: 2 }),
          points: 5,
          radius: 10,
          radius2: 4,
          angle: 0,
        }),
      });
    }

    smokingAreaAjax() {
      const oReq = new XMLHttpRequest();
      oReq.addEventListener('load', () => {
        const data = JSON.parse(oReq.responseText);

        this.composeTile();
        this.composeVetorLayer(data);

        this.composePanorama();
        this.composePopup();

        const map = this.composeMap();
        this.setMapClickEvent(map);
      });
      oReq.open('GET', 'api/smokingArea');
      oReq.send();
    }

    composeTile() {
      const wmts = this.setVWorldUrl('http://api.vworld.kr/req/wmts/1.0.0/{key}/{layer}/{z}/{y}/{x}.{tileType}');
      const tms = this.setVWorldUrl('http://api.vworld.kr/req/tms/1.0.0/{key}/{layer}/{z}/{y}/{x}.{tileType}');

      const tile = new ol.layer.Tile({
        source: new ol.source.XYZ({
          url: wmts,
        }),
      });

      this.layers.push(tile);
    }

    composeVetorLayer(data) {
      const { p015 } = data;
      const { p100 } = data;

      // epsg 5186 projection inject
      proj4.defs('EPSG:5186', '+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs');
      ol.proj.proj4.register(proj4);
      ol.proj.get('EPSG:5186').setExtent([-219825.99, -435028.96,
        819486.07, 877525.22]);
      const epsg5186 = ol.proj.get('EPSG:5186');
      const epsg3857 = ol.proj.get('EPSG:3857');

      const vectorLayerP015 = new ol.layer.Vector({
        source: new ol.source.Vector({
          features: new ol.format.GeoJSON()
            .readFeatures(p015, {
              dataProjection: epsg5186,
              featureProjection: epsg3857,
            }),
        }),
        style: this.styleP015,
      });
      const vectorLayerP100 = new ol.layer.Vector({
        source: new ol.source.Vector({
          features: new ol.format.GeoJSON()
            .readFeatures(p100, {
              dataProjection: epsg5186,
              featureProjection: epsg3857,
            }),
        }),
        style: this.styleP100,
      });

      this.layers.push(vectorLayerP015, vectorLayerP100);
    }

    composePopup() {
      const popup = new ol.Overlay({
        element: document.getElementById('popup'),
        autoPan: true,
        autoPanAnimation: {
          duration: 250,
        },
      });

      this.overlays = [popup];
    }

    composePanorama() {
      this.pano = new naver.maps.Panorama('pano', {
        position: new naver.maps.LatLng(37.537097, 127.085851),
      });
    }

    composeMap() {
      const map = new ol.Map({
        target: document.getElementById('map'),
        layers: this.layers,
        overlays: this.overlays,
        view: new ol.View({
          center: this.centerPnt,
          zoom: 15,
          minZoom: 6,
        }),
      });

      return map;
    }

    setVWorldUrl(url) {
      const newUrl = url
        .replace('{key}', this.key)
        .replace('{layer}', this.layer)
        .replace('{tileType}', this.tileType);

      return newUrl;
    }

    setMapClickEvent(map) {
      map.on('singleclick', (evt) => {
        const { coordinate } = evt;
        const x = coordinate[0];
        const y = coordinate[1];

        if (map.hasFeatureAtPixel(evt.pixel)) {
          this.setPanoramaImage(x, y);
          this.overlays[0].setPosition([x, y]);
        } else {
          this.overlays[0].setPosition(undefined);
        }
      });
    }

    setPanoramaImage(x, y) {
      const [lon, lat] = ol.proj.transform([x, y], 'EPSG:3857', 'EPSG:4326');
      const position = new naver.maps.LatLng(lat, lon);

      this.pano.setPosition(position);
    }
  }

  const mapLayer = new MapLayer();
});
