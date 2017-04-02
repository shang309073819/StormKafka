package storm.kafka.trident;

import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;
import storm.kafka.Partition;
import storm.trident.spout.IOpaquePartitionedTridentSpout;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;


public class OpaqueTridentKafkaSpout implements IOpaquePartitionedTridentSpout<GlobalPartitionInformation, Partition, Map> {


    TridentKafkaConfig _config;
    String _topologyInstanceId = UUID.randomUUID().toString();

    public OpaqueTridentKafkaSpout(TridentKafkaConfig config) {
        _config = config;
    }

    
    public IOpaquePartitionedTridentSpout.Emitter<GlobalPartitionInformation, Partition, Map> getEmitter(Map conf, TopologyContext context) {
        try {
			return new TridentKafkaEmitter(conf, context, _config, _topologyInstanceId).asOpaqueEmitter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    
    public IOpaquePartitionedTridentSpout.Coordinator getCoordinator(Map conf, TopologyContext tc) {
        try {
			return new storm.kafka.trident.Coordinator(conf, _config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }

    
    public Fields getOutputFields() {
        return _config.scheme.getOutputFields();
    }

    
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}
