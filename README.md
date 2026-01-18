# RTP Voice Quality Analyzer (Java)

## Overview
This project is a Java-based tool designed to analyze real-time voice communication quality using RTP (Real-time Transport Protocol) packet data captured with Wireshark.

The goal of this project is to demonstrate practical experience with:
- Voice-over-IP (VoIP) communication
- Real-time network traffic analysis
- Quality of Service (QoS) metrics used in mission-critical systems

The tool processes real RTP traffic captured from live voice calls and evaluates whether the audio quality would be considered clear or degraded.

---

## Why This Project Matters
In mission-critical environments such as air traffic control, emergency services, and public safety systems, voice clarity and reliability are essential.

This project focuses on answering a practical question:
> **“Is the voice communication reliable, and if not, why?”**

It simulates the type of analysis performed when investigating call quality issues in operational control centers.

---

## What the Tool Does (Non-Technical Summary)
- Takes a standard Wireshark CSV export of RTP packets
- Groups voice packets belonging to the same audio stream
- Analyzes network behavior affecting call quality
- Reports indicators such as:
  - Missing voice packets
  - Variations in packet arrival timing
  - Overall call stability

In simple terms, it helps determine whether a voice call would sound smooth or choppy due to network conditions.

---

## Technical Capabilities
- Parses real Wireshark RTP packet exports (no manual editing required)
- Identifies RTP streams using SSRC
- Calculates key QoS metrics:
  - Packet loss
  - Average jitter (timing variation)
- Designed using object-oriented principles and clean separation of concerns

---

## Technologies Used
- **Language:** Java
- **Protocols:** RTP over UDP
- **Tools:** Wireshark
- **Concepts:** Real-time systems, QoS analysis, network troubleshooting

---

## Project Structure

```
src/main/java
├── model → RTP packet and stream data models
├── parser → Wireshark CSV parsing logic
├── analyzer → QoS metric calculations
├── report → Human-readable reporting
└── Main.java → Application entry point
```

---

## How to Run
1. Capture RTP traffic using Wireshark
2. Apply display filter: `rtp`
3. Export packets as CSV (Displayed packets only)
4. Place the CSV file in the project directory
5. Run the application: `java Main`
6. The console will display a summarized RTP quality report.

---

## Example Output
```
===== RTP QoS Report =====
SSRC: 0x12ab34cd
Total Packets: 4
Estimated Packet Loss: 1
Average Jitter: 18.2 ms
```

---

## Relevance to Mission-Critical Systems
This project reflects real-world challenges found in safety-critical voice communication systems, including:
- Real-time constraints
- UDP-based transport behavior
- Diagnosing voice degradation
- Supporting incident analysis and troubleshooting

---

## Author
Ved Patel
Software Developer