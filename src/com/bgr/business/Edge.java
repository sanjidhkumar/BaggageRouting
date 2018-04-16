package com.bgr.business;

/**
 * @author KSKumar
 *
 */
public class Edge {
	public final Vertex target;
	public final double weight;

	/**
	 * @param argTarget
	 * @param argWeight
	 */
	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}

	/**
	 * 
	 */
	public void reset() {
		if (!target.isReset()) {
			target.reset();
		}
	}
}