package example;

import java.io.Serializable;
import java.util.Random;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class RandomUserDataSource implements SourceFunction<User>, Serializable {

  private volatile boolean running = true;

  @Override
  public void run(SourceContext<User> ctx) throws Exception {
    final Random random = new Random();

    while (this.running) {

      final User data = new User(
              random.nextInt(10),
              random.nextDouble(),
              random.nextDouble(),
              random.nextDouble());

      ctx.collect(data);

      Thread.sleep(100);
    }

  }

  @Override
  public void cancel() {
    this.running = false;
  }

}