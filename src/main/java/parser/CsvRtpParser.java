package main.java.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.model.RtpPacket;
import main.java.model.RtpStream;

public class CsvRtpParser {
    // Regex patterns to extract RTP fields from Info column
    private static final Pattern SSRC_PATTERN = Pattern.compile("SSRC=0x([0-9A-Fa-f]+)");
    private static final Pattern SEQ_PATTERN  = Pattern.compile("Seq=(\\d+)");
    private static final Pattern TIME_PATTERN = Pattern.compile("Time=(\\d+)");

    public static Map<String, RtpStream> parse(String filePath) throws Exception {

        Map<String, RtpStream> streams = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        br.readLine(); // skip CSV header

        while ((line = br.readLine()) != null) {
            System.out.println(line);
            // Split CSV safely (handles quoted commas)
            String[] fields = line.split("\",\"");

            if (fields.length < 7) {
                continue;
            }

            // Clean quotes
            String timeStr = fields[1].replace("\"", "");
            String protocol = fields[4].replace("\"", "");
            String info = fields[6].replace("\"", "");

            // Only process RTP packets
            if (!protocol.equalsIgnoreCase("RTP")) {
                continue;
            }

            double arrivalTime = Double.parseDouble(timeStr);

            String ssrc = extract(SSRC_PATTERN, info);
            String seqStr = extract(SEQ_PATTERN, info);
            String rtpTimeStr = extract(TIME_PATTERN, info);

            if (ssrc == null || seqStr == null || rtpTimeStr == null) {
                continue;
            }

            long sequenceNumber = Long.parseLong(seqStr);
            long rtpTimestamp = Long.parseLong(rtpTimeStr);

            RtpPacket packet = new RtpPacket(
                    arrivalTime,
                    sequenceNumber,
                    rtpTimestamp,
                    "0x" + ssrc
            );

            streams.putIfAbsent(packet.ssrc, new RtpStream(packet.ssrc));
            streams.get(packet.ssrc).addPacket(packet);
        }

        br.close();
        return streams;
    }

    private static String extract(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : null;
    }
}
