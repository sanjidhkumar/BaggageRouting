package com.bgr.model;

/**
 * @author KSKumar
 *
 */
public class FlightBagInfo {
	private String bagNumber;
	private String entryPoint;
	private String destination;
	private String flightId;

	public FlightBagInfo() {

	}

	/**
	 * @param bagNumber
	 * @param entryPoint
	 * @param flightId
	 */
	public FlightBagInfo(String bagNumber, String entryPoint, String flightId) {
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}

	public String getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "FlightBagInfo [bagNumber=" + bagNumber + ", entryPoint="
				+ entryPoint + ", destination=" + destination + ", flightId="
				+ flightId + "]";
	}

}
