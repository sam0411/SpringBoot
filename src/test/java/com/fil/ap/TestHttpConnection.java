package com.fil.ap;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class TestHttpConnection {

	
	
	private static String[] urls = {
			"https://www.fidelity.com.hk/en",
			"https://www.fidelity.com.tw/index.html",	
			"https://www.runoob.com/?language=cn#j2se",
			"https://www.baidu.com/",
			"https://www.fidelity.com.hk/en",
			"https://www.fidelity.com.tw/index.html",	
			"https://www.runoob.com/?language=cn#j2se",
			"https://www.baidu.com/",
			"https://www.fidelity.com.hk/en",
			"https://www.fidelity.com.tw/index.html",	
			"https://www.runoob.com/?language=cn#j2se",
			"https://www.baidu.com/",
	};
	
	public static void main(String args[]) {
		
		for(int i = 0; i < urls.length; i++) {
			
			new Thread(new HRunner(urls[i])).start();
			new Thread(new HRunner(urls[i])).start();
			new Thread(new HRunner(urls[i])).start();
			new Thread(new HRunner(urls[i])).start();
			new Thread(new HRunner(urls[i])).start();
		}
	}
}

class HRunner implements Runnable {
	
	private static final AtomicLong counter = new AtomicLong();
	private String url;
	
	public HRunner(String url) {
		
		this.url = url;
	}
	
	@Override
	public void run() {
		System.out.println("Thread = " + Thread.currentThread().getId() + " Start");
		long stT = System.currentTimeMillis();
		
		InputStream in = null;
		String content = null;
		HttpsURLConnection conn = null;
		
		try {
			URL ddd = new URL(url);
			conn = (HttpsURLConnection) ddd.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(false);
			conn.setDoInput(true);
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(10000);

			int responseCode = conn.getResponseCode();
			
			if(responseCode == 200) {
				in = conn.getInputStream();
				
				byte[] bytes = IOUtils.toByteArray(in);
				content = new String(bytes, "UTF-8");
				
//				System.out.println("Content = " + content);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				
				if(conn != null) {
					conn.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		long edT = System.currentTimeMillis();
		
		long cc = counter.incrementAndGet();
		System.out.println("Thread = " + Thread.currentThread().getId() + ", Cost = " + (edT-stT) + ", length = " + content.length() + ", counter = " + cc + ", url = " +url );
	}
	
}
