package com.bgr.business;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KSKumar
 *
 */
public class Vertex implements Comparable<Vertex> {
	private final String name;
	private final boolean isReset = true;
	private Map<String, Edge> adjacencies = new HashMap<String, Edge>();
	private double minDistance = Double.POSITIVE_INFINITY;
	private Vertex previous;
	private boolean isVisited = false;

	

	/**
	 * @param argName
	 */
	public Vertex(String argName) {
		name = argName;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public Map<String, Edge> getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(Map<String, Edge> adjacencies) {
		this.adjacencies = adjacencies;
	}

	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	public String getName() {
		return name;
	}
	

	public boolean isReset() {
		return isReset;
	}

	public String toString() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

	/**
	 * 
	 */
	public void reset() {
		this.isVisited = false;
		this.minDistance = Double.POSITIVE_INFINITY;
		this.previous = null;
		for (Edge e : adjacencies.values()) {
			e.reset();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Vertex) {
			Vertex tmpObj = (Vertex) obj;
			if (tmpObj.name != null
					&& tmpObj.name.trim().equalsIgnoreCase(name.trim())) {
				return true;
			}
		}
		return false;
	}
}
