package main.java.report;

import main.java.analyzer.RtpQoSAnalyzer;
import main.java.model.RtpStream;

public class ConsoleReport {
    public static void generate(RtpStream stream) {
        System.out.println("===== RTP QoS Report =====");
        RtpQoSAnalyzer.analyze(stream);
        System.out.println("==========================");
    }
}
