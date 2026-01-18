package main.java.model;

public class RtpPacket {
    public double arrivalTime;
    public long sequenceNumber;
    public long timestamp;
    public String ssrc;

    public RtpPacket(double arrivalTime, long sequenceNumber, long timestamp, String ssrc) {
        this.arrivalTime = arrivalTime;
        this.sequenceNumber = sequenceNumber;
        this.timestamp = timestamp;
        this.ssrc = ssrc;
    }
}
