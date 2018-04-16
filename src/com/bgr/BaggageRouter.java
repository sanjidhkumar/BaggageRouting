package com.bgr;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.bgr.business.Edge;
import com.bgr.business.Vertex;
import com.bgr.constants.Constants;
import com.bgr.model.FlightBagInfo;
import com.bgr.model.FlightInfo;
import com.bgr.model.OptimizedRoute;
import com.bgr.utils.Utility;

/**
 * @author KSKumar
 *
 */
public class BaggageRouter {
	/**
	 * 
	 */
	static Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File file = new File(ClassLoader.getSystemResource(Constants.FILENAME)
				.getFile());
		Map<String, FlightInfo> flightInfoMap = new LinkedHashMap<String, FlightInfo>();
		Map<String, FlightBagInfo> flightBagInfoMap = new LinkedHashMap<String, FlightBagInfo>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			boolean extractGateNodeDetails = false;
			boolean extractFlightDepartureDetails = false;
			boolean extractFlightAndBagDetails = false;
			boolean endGraphSection = false;

			while (scanner.hasNextLine() && !endGraphSection) {
				String line = scanner.nextLine();

				if (line.trim().equals("")) {
					continue;
				}

				if (line.startsWith(Constants.CS_SECTION_HEADER)) {
					extractGateNodeDetails = true;
					line = scanner.nextLine();

				}
				if (line.startsWith(Constants.DEP_SECTION_HEADER)) {
					extractGateNodeDetails = false;
					extractFlightDepartureDetails = true;
					line = scanner.nextLine();
				}
				if (line.startsWith(Constants.BAG_SECTION_HEADER)) {
					extractFlightDepartureDetails = false;
					extractFlightAndBagDetails = true;
					line = scanner.nextLine();
				}

				if (extractGateNodeDetails) {
					Utility.extractNodeDetails(line, vertexMap);
				}
				if (extractFlightDepartureDetails) {
					Utility.extractFlightDepartureDetails(line, flightInfoMap);
				}
				if (extractFlightAndBagDetails) {
					Utility.extractFlightBagDetails(line, flightBagInfoMap,
							flightInfoMap);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		List<OptimizedRoute> optimizedRouteList = new ArrayList<OptimizedRoute>();
		for (FlightBagInfo value : flightBagInfoMap.values()) {
			OptimizedRoute optimizedRoute = new OptimizedRoute(
					value.getBagNumber(), getShortestPathTo(
							vertexMap.get(value.getEntryPoint()),
							vertexMap.get(value.getDestination())),
					vertexMap.get(value.getDestination()).getMinDistance());
			for (Vertex vertex : vertexMap.values()) {
				vertex.reset();
			}
			System.out.println(optimizedRoute.toString());
			optimizedRouteList.add(optimizedRoute);
		}

	}

	/**
	 * @param source
	 * @param dest
	 */
	public static void computePaths(Vertex source, Vertex dest) {
		source.setMinDistance(0);
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			if (null != u.getAdjacencies()) {
				// Visit each edge exiting u
				for (Edge e : u.getAdjacencies().values()) {
					Vertex v = e.target;
					double weight = e.weight;
					double distanceThroughU = u.getMinDistance() + weight;
					if (!v.isVisited() && distanceThroughU < v.getMinDistance()) {
						vertexQueue.remove(v);

						v.setMinDistance(distanceThroughU); 
						v.setPrevious(u);
						v.setVisited(true);
						if (!v.getName().equalsIgnoreCase(dest.getName())) {
							vertexQueue.add(v);
						} else {
							return;
						}
						vertexMap.put(v.getName(), v);

					}

				}
			}
		}
	}

	/**
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<Vertex> getShortestPathTo(Vertex source, Vertex target) {
		computePaths(source, target);
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.getPrevious())
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}

}