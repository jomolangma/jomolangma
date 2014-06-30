package com.jomolangma.app.performance.ch4.gs.future;

import java.util.LinkedList;

public class RequestQueue {
	private LinkedList<Request> queue = new LinkedList<Request>();

	public synchronized Request getRequest() {
		while (queue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return (Request) queue.remove();
	}

	public synchronized void addRequest(Request request) {
		queue.add(request);
		notifyAll();
	}
}
