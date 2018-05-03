package com.rockthreading.basic;

public class BasicThread {

	public void hello(final String msg) {
		
		new Thread(new Runnable() {

			public void run() {
				
				for (int i=0; i<1000; i++) {
					
					System.out.println(Thread.currentThread().getId() + ", Basic Thread: " + (i + 1) + ", " + msg);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
	}
	
	private int _count;
	
	public void count(final String msg) {
		
		final BasicThread basicThread = this;
		
		new Thread(new Runnable() {

			public void run() {
				
				for (int i = 0; i<100000; i++) {
					
					// increment();
					
					synchronized(basicThread) {
						++_count;
					}

					System.out.println(Thread.currentThread().getId() + ", Basic Thread Count: " + _count + ", " + msg);
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} */
				}
			}
			
		}).start();
		
	}
	
	private synchronized void increment() {
		++_count;
	}
	
	
	public int getCount() {
		return this._count;
	}
	
}
