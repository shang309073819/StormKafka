package storm.kafka;

import com.google.common.base.Objects;
import storm.trident.spout.ISpoutPartition;


public class Partition implements ISpoutPartition {

    public final Broker host;
    public final int partition;

    public Partition(Broker host, int partition) {
        this.host = host;
        this.partition = partition;
    }

    
    public int hashCode() {
        return Objects.hashCode(host, partition);
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Partition other = (Partition) obj;
        return Objects.equal(this.host, other.host) && Objects.equal(this.partition, other.partition);
    }

    
    public String toString() {
        return "Partition{" +
                "host=" + host +
                ", partition=" + partition +
                '}';
    }

    
    public String getId() {
        return "partition_" + partition;
    }

}
