package example;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import swim.flink.connector.SwimSink;
import swim.structure.Form;

public class ExampleFlinkJob {

  public static void main(String[] args) throws Exception {

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    DataStream<User> randomUserDataSource = env.addSource(new RandomUserDataSource());

    randomUserDataSource.sinkTo(SwimSink.<User>builder()
            .setHostUri("warp://localhost:9001")
            .setNodeUri(user -> "/agent/" + user.getId())
            .setLaneUri("latest")
            .setRecordValueMolder(user -> Form.forClass(User.class).mold(user).toValue())
            .build()
    );

    env.execute();
  }

}
