package com.rtpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rtpl.listener.LiveUdpPacketListener;

@SpringBootApplication
public class RadarPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadarPocApplication.class, args);
		LiveUdpPacketListener listner = new LiveUdpPacketListener();
		listner.captureUDPPackets();
	}

}
