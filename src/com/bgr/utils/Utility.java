package com.bgr.utils;

import java.util.Map;

import com.bgr.business.Edge;
import com.bgr.business.Vertex;
import com.bgr.constants.Constants;
import com.bgr.model.FlightBagInfo;
import com.bgr.model.FlightInfo;

/**
 * @author KSKumar
 *
 */
public final class Utility {
	private Utility() {
		// do nothing
	}

	/**
	 * @param line
	 * @param flightInfoMap
	 */
	public static void extractFlightDepartureDetails(String line,
			 Map<String, FlightInfo> flightInfoMap) {
		// <flight_id> <flight_gate> <destination> <flight_time>
		String flightDepartureDetails[] = line.split(" ");
		FlightInfo flightInfo = new FlightInfo(flightDepartureDetails[0],
				flightDepartureDetails[1], flightDepartureDetails[2],
				flightDepartureDetails[3]);
		flightInfoMap.put(flightDepartureDetails[0], flightInfo);
	}

	/**
	 * @param line
	 * @param flightBagInfoMap
	 * @param flightInfoMap
	 */
	public static void extractFlightBagDetails(String line,
			Map<String,FlightBagInfo> flightBagInfoMap, Map<String, FlightInfo> flightInfoMap) {
		// <bag_number> <entry_point> <flight_id>
		String flightBagDetails[] = line.split(" ");
		FlightBagInfo flightBagInfo = new FlightBagInfo(flightBagDetails[0],
				flightBagDetails[1], flightBagDetails[2]);

		if (flightBagDetails[2].equalsIgnoreCase(Constants.ARRIVAL)) {
			flightBagInfo.setDestination(Constants.BAGGAGE_CLAIM);
		} else {
			flightBagInfo.setDestination(flightInfoMap.get(flightBagDetails[2])
					.getFlightGate());
		}
		flightBagInfoMap.put(flightBagDetails[0], flightBagInfo);
	}

	/**
	 * @param line
	 * @param vertexMap
	 */
	public static void extractNodeDetails(String line,
			Map<String, Vertex> vertexMap) {

		// <Node 1> <Node 2> <travel_time>
		String nodeDetails[] = line.split(" ");

		String startNode = nodeDetails[0];
		String endNode = nodeDetails[1];
		int gateTransitTime = Integer.parseInt(nodeDetails[2]);
		Vertex startVertex;
		Vertex endVertex;
		if (vertexMap.containsKey(startNode)) {
			startVertex = vertexMap.get(startNode);
		} else {
			startVertex = new Vertex(startNode);
		}
		if (vertexMap.containsKey(endNode)) {
			endVertex = vertexMap.get(endNode);
		} else {
			endVertex = new Vertex(endNode);
		}

		startVertex.getAdjacencies().put(endVertex.getName(), (new Edge(endVertex,
				gateTransitTime)));
		endVertex.getAdjacencies().put(startVertex.getName(), (new Edge(startVertex,
				gateTransitTime)));
		vertexMap.put(startVertex.getName(), startVertex);
		vertexMap.put(endVertex.getName(), endVertex);

	}
}
