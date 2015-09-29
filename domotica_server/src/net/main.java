package net;

public class main {

	public static void main(String[] args) {
		System.out.println("Ola Mundo!");
		UdpComunicador comunicador = new UdpComunicador();
		comunicador.start();
	}

}
