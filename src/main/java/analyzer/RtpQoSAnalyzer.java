package main.java.analyzer;

import java.util.List;

import main.java.model.RtpPacket;
import main.java.model.RtpStream;

public class RtpQoSAnalyzer {
    public static void analyze(RtpStream stream) {
        List<RtpPacket> packets = stream.packets;

        int lostPackets = 0;
        double totalJitter = 0;
        int jitterSamples = 0;

        for (int i = 1; i < packets.size(); i++) {
            long expectedSeq = packets.get(i - 1).sequenceNumber + 1;
            if (packets.get(i).sequenceNumber != expectedSeq) {
                lostPackets += packets.get(i).sequenceNumber - expectedSeq;
            }

            double arrivalDiff = packets.get(i).arrivalTime - packets.get(i - 1).arrivalTime;
            double timestampDiff = (packets.get(i).timestamp - packets.get(i - 1).timestamp) / 8000.0;
            double jitter = Math.abs(arrivalDiff - timestampDiff);

            totalJitter += jitter;
            jitterSamples++;
        }

        double avgJitter = (jitterSamples > 0) ? totalJitter / jitterSamples : 0;

        System.out.println("SSRC: " + stream.ssrc);
        System.out.println("Total Packets: " + packets.size());
        System.out.println("Estimated Packet Loss: " + lostPackets);
        System.out.printf("Average Jitter: %.3f ms%n", avgJitter * 1000);
    }
}
