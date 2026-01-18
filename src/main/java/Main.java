package main.java;

import java.util.Map;

import main.java.analyzer.RtpQoSAnalyzer;
import main.java.model.RtpStream;
import main.java.parser.CsvRtpParser;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, RtpStream> streams = CsvRtpParser.parse("src/main/resources/data/rtp_packets.csv");
            for (RtpStream stream : streams.values()) {
                RtpQoSAnalyzer.analyze(stream);
            }
        } catch (Exception e) {
            System.err.println("Error parsing RTP data: " + e.getMessage());
        }
    }
}
