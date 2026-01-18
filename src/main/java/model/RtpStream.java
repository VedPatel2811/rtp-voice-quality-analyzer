package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class RtpStream {
    public String ssrc;
    public List<RtpPacket> packets = new ArrayList<>();

    public RtpStream(String ssrc) {
        this.ssrc = ssrc;
    }

    public void addPacket(RtpPacket packet) {
        packets.add(packet);
    }
}
