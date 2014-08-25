package com.jomolangma.app.chinamobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class GenerateTotalKeys {
	
	public static final String fieldSeperator = String.valueOf((char)0X1F);
	
	@Test
	public void generateTotalKeys() {
		long startTime = System.currentTimeMillis();
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		HostAndPort hostAndport = new HostAndPort("192.168.10.211", 7001);
		nodes.add(hostAndport);
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		String fileName = "/Users/zhanglijun/Documents/ChinaMobile/CreativeDept/APP_CID_ALL/app_cid_20140816.dat_06/app_cid_20140816.dat_06";
		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>(1 << 12);
			int i = 0;
			while (line != null) {
				i++;
				String[] fields = line.split(fieldSeperator);
				String key = "china:mobile:" + fields[0];
				String field = fields[1];
				if (map.get(key) == null){
					Map<String,String> fieldValue = new HashMap<String,String>();
					fieldValue.put(field, "1");
					map.put(key, fieldValue);
				}else{
					map.get(key).put(field, "1");
				}
				
				line = reader.readLine();
				
				if (i%5000000 == 0 || line == null){
					Set<String> keySet = map.keySet();
					for (String tempKey:keySet){
						jedisCluster.hmset(tempKey, map.get(tempKey));
					}
					
					map.clear();
					System.out.println(i);
				}
			}

			reader.close();
			
			System.out.println("Total spent time is " + (System.currentTimeMillis()-startTime));

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}

}
