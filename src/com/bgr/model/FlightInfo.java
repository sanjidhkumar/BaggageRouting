package com.bgr.model;


/**
 * @author KSKumar
 *
 */
public class FlightInfo {
    private String flightId;
    private String flightGate;
    private String destination;
    private String flightTime;

    public FlightInfo(){
    }

    /**
     * @param flightId
     * @param flightGate
     * @param destination
     * @param flightTime
     */
    public FlightInfo(String flightId, String flightGate, String destination, String flightTime){
        this.flightId = flightId;
        this.flightGate = flightGate;
        this.destination = destination;
        this.flightTime = flightTime;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFlightGate() {
		return flightGate;
	}

	public void setFlightGate(String flightGate) {
		this.flightGate = flightGate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	@Override
	public String toString() {
		return "FlightDeparture [flightId=" + flightId + ", flightGate="
				+ flightGate + ", destination=" + destination + ", flightTime="
				+ flightTime + "]";
	}
}
