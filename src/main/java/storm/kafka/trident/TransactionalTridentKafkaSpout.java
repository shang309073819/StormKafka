package storm.kafka.trident;

import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;
import storm.kafka.Partition;
import storm.trident.spout.IPartitionedTridentSpout;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;


public class TransactionalTridentKafkaSpout implements IPartitionedTridentSpout<GlobalPartitionInformation, Partition, Map> {

    TridentKafkaConfig _config;
    String _topologyInstanceId = UUID.randomUUID().toString();

    public TransactionalTridentKafkaSpout(TridentKafkaConfig config) {
        _config = config;
    }


    
    public IPartitionedTridentSpout.Coordinator getCoordinator(Map conf, TopologyContext context) {
        try {
			return new storm.kafka.trident.Coordinator(conf, _config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    
    public IPartitionedTridentSpout.Emitter getEmitter(Map conf, TopologyContext context) {
        try {
			return new TridentKafkaEmitter(conf, context, _config, _topologyInstanceId).asTransactionalEmitter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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