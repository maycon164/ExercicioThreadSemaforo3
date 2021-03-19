package application;

import java.util.concurrent.Semaphore;

import controller.Cliente;

public class Programa {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int x = 1; x <= 300; x++) {
			Thread cliente = new Cliente(semaforo, x);
			cliente.start();
		}
		
	}
}
