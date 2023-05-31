package swim.flink.connector;

import swim.structure.Value;

@FunctionalInterface
public interface RecordValueMolder<InputT> {

  Value mold(final InputT record);

}
