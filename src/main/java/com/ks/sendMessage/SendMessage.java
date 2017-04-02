package com.ks.sendMessage;

import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class SendMessage {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zookeeper.connect",
				"192.168.2.5:2181,192.168.2.6:2181,192.168.2.7:2181");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("producer.type", "async");
		props.put("compression.codec", "1");
		props.put("metadata.broker.list", "192.168.2.3:9092,192.168.2.4:9092");

		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);

		// 发送数据
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			int id = r.nextInt(10000000);
			int memberid = r.nextInt(100000);
			int totalprice = r.nextInt(1000) + 100;
			int youhui = r.nextInt(100);
			int sendpay = r.nextInt(3);

			StringBuffer data = new StringBuffer();
			data.append(String.valueOf(id)).append("\t")
					.append(String.valueOf(memberid)).append("\t")
					.append(String.valueOf(totalprice)).append("\t")
					.append(String.valueOf(youhui)).append("\t")
					.append(String.valueOf(sendpay)).append("\t")
					.append("2014-04-19");
			System.out.println(data.toString());
			producer.send(new KeyedMessage<String, String>("order", data
					.toString()));
		}
		producer.close();
		System.out.println("发送数据完成");
	}

}
