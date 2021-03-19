package controller;

import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
	Semaphore semaforo;
	static int quantidadeIngressos = 100;
	int id;

	public Cliente(Semaphore semaforo, int id) {
		this.semaforo = semaforo;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			if(login() && comprar()) {
				semaforo.acquire();
				validar();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

	private boolean login() throws InterruptedException {
		int tempo = (int) (Math.random() * 1951) + 50;
		sleep(tempo);
		if (tempo > 1000) {
			//System.out.println("Cliente #" + id + " não conseguiu fazer login");
			return false;
		}
		//System.out.println("Cliente #" + id + " login realizado");
		return true;
	}

	public boolean comprar() throws InterruptedException {
		int tempo = (int) (Math.random() * 2001) + 1000;
		sleep(tempo);
		if (tempo > 2500) {
			//System.out.println("Cliente #" + id + " tempo de seção esgotado");
			return false;
		}
		//System.out.println("Cliente #" + id + " realizando compra");
		return true;
	}

	public synchronized void validar() {
		int quantidade = (int) (Math.random() * 4) + 1;

		if (quantidade <= quantidadeIngressos) {
			quantidadeIngressos -= quantidade;
			System.out.println("Cliente #" + id + " comprou " + quantidade + " ingressos");
			System.out.println("Quantidade de ingressos restantes: " + quantidadeIngressos);
		} else {
			System.out.println("Cliente #" + id + " ingressos esgotados");
		}
	}

}
