/*
 * Copyright 2009 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.examples.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;

public class MapBean implements Serializable {

	private MapModel emptyModel;

	private MapModel simpleModel;
	
	private MapModel advancedModel;
	
	private MapModel polylineModel;
	
	private MapModel polygonModel;
	
	private MapModel draggableModel;

	private Marker marker;
	
	private String title;
	
	private double lat;
	
	private double lng;

	public MapBean() {
		emptyModel = new DefaultMapModel();
		simpleModel = new DefaultMapModel();
		advancedModel = new DefaultMapModel();
		polylineModel = new DefaultMapModel();
		polygonModel = new DefaultMapModel();
		draggableModel = new DefaultMapModel();
		
		//Shared coordinates
		LatLng coord1 = new LatLng(36.879466, 30.667648);
		LatLng coord2 = new LatLng(36.883707, 30.689216);
		LatLng coord3 = new LatLng(36.879703, 30.706707);
		LatLng coord4 = new LatLng(36.885233, 30.702323);
		
		//Basic marker
		simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
		simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
		simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
		simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
		
		//Icons and Data
		advancedModel.addOverlay(new Marker(coord1, "Konyaalti", "konyaalti.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
		advancedModel.addOverlay(new Marker(coord2, "Ataturk Parki", "ataturkparki.png"));
		advancedModel.addOverlay(new Marker(coord4, "Kaleici", "kaleici.png", "http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
		advancedModel.addOverlay(new Marker(coord3, "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));
		
		//Draggable
		draggableModel.addOverlay(new Marker(coord1, "Konyaalti"));
		draggableModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
		draggableModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
		draggableModel.addOverlay(new Marker(coord4, "Kaleici"));
		
		for(Marker marker : draggableModel.getMarkers()) {
			marker.setDraggable(true);
		}
		
		//Polyline
		Polyline polyline = new Polyline();
		polyline.getPaths().add(coord1);
		polyline.getPaths().add(coord2);
		polyline.getPaths().add(coord3);
		polyline.getPaths().add(coord4);
		
		polyline.setStrokeWeight(10);
		polyline.setStrokeColor("#FF9900");
		polyline.setStrokeOpacity(0.7);
		
		polylineModel.addOverlay(polyline);
		
		//Polygon
		Polygon polygon = new Polygon();
		polygon.getPaths().add(coord1);
		polygon.getPaths().add(coord2);
		polygon.getPaths().add(coord3);

		polygon.setStrokeColor("#FF9900");
		polygon.setFillColor("#FF9900");
		polygon.setStrokeOpacity(0.7);
		polygon.setFillOpacity(0.7);
		
		polygonModel.addOverlay(polygon);
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}
	
	public MapModel getSimpleModel() {
		return simpleModel;
	}
	
	public MapModel getPolylineModel() {
		return polylineModel;
	}
	
	public MapModel getPolygonModel() {
		return polygonModel;
	}
	
	public MapModel getEmptyModel() {
		return emptyModel;
	}
	
	public MapModel getDraggableModel() {
		return draggableModel;
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
	}
	
	public void onPolylineSelect(OverlaySelectEvent event) {
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Polyline Selected", null));
	}
	
	public void onPolygonSelect(OverlaySelectEvent event) {
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Polygon Selected", null));
	}
	
	public void onMarkerDrag(MarkerDragEvent event) {
		marker = event.getMarker();
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
	}
	
	public void onStateChange(StateChangeEvent event) {
		LatLngBounds bounds = event.getBounds();
		int zoomLevel = event.getZoomLevel();
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", bounds.getCenter().toString()));
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));
	}
	
	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public void addMarker(ActionEvent actionEvent) {
		Marker marker = new Marker(new LatLng(lat, lng), title);
		emptyModel.addOverlay(marker);
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
	}

}