package com.chengjuiot.xmlVo;

import java.io.Serializable;

public class NodeValueVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4852655890901179134L;
	private String nodeName;
	private String nodeText;
	private String attribute;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeText() {
		return nodeText;
	}

	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public String toString() {
		return "VO [nodeName=" + nodeName + ", nodeText=" + nodeText + ", attribute=" + attribute + "]";
	}
	
	

}
