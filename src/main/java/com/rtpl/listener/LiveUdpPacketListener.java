package com.rtpl.listener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiveUdpPacketListener {
	
    private static Logger log = LoggerFactory.getLogger(LiveUdpPacketListener.class);
    
	public void captureUDPPackets() {
		System.out.println("LiveUdpPacketListener.captureUDPPackets()");
		String hostIp = "192.168.10.143";
		try {
			PcapNetworkInterface device = Pcaps.getDevByAddress(InetAddress.getByName(hostIp));
			PcapHandle handle = device.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);
			while (true) {
				try {
					Packet packet = handle.getNextPacket();
					if (Objects.nonNull(packet)) {
						UdpPacket udpPacket = packet.get(UdpPacket.class);
						if (Objects.nonNull(udpPacket)) {
							log.info("Receiving UDP packet {} ", udpPacket);
							// TODO: Read this UDP packet and do the remaining work
						}
					}
				} catch (Exception e) {
					log.error("Capture handle is not open:{} ", e.getMessage());
					break;
				}
			}
			handle.close();
		} catch (UnknownHostException | PcapNativeException e) {
			log.error("Something went wrong:{} ", e.getMessage());
		}
	}

}
