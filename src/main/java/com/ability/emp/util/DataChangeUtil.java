package com.ability.emp.util;

import java.util.ArrayList;
import java.util.List;

public class DataChangeUtil {
	
	private String id;
	private String text;
	//private String[] nodes;
	private List<DataChangeUtil> nodes = new ArrayList<DataChangeUtil>();
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<DataChangeUtil> getNodes() {
		return nodes;
	}
	public void setNodes(List<DataChangeUtil> nodes) {
		this.nodes = nodes;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}