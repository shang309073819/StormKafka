package com.ks.bolt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class CounterBolt extends BaseBasicBolt {

	private static final long serialVersionUID = -5508421065181891596L;
	private Log log = LogFactory.getLog(getClass().getName());

	private static long counter = 0;

	public void execute(Tuple tuple, BasicOutputCollector collector) {

		log.info("msg = " + tuple.getString(0) + " -------------counter = "
				+ (counter++));
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

}
