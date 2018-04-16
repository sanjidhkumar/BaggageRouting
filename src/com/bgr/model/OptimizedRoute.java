package com.bgr.model;

import java.util.List;
import java.util.stream.Collectors;

import com.bgr.business.Vertex;

/**
 * @author KSKumar
 *
 */
public class OptimizedRoute {
	private String bagNumber;
	private List<Vertex> path;
	private double distance;

	/**
	 * @param bagNumber
	 * @param path
	 * @param distance
	 */
	public OptimizedRoute(String bagNumber, List<Vertex> path, double distance) {
		this.bagNumber = bagNumber;
		this.path = path;
		this.distance = distance;
	}

	public String getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}

	public List<Vertex> getPath() {
		return path;
	}

	public void setPath(List<Vertex> path) {
		this.path = path;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return bagNumber
				+ "  "
				+ path.stream().map(e -> e.getName())
						.collect(Collectors.joining(" ")) + " : "
				+ (int) distance;
	}

}
