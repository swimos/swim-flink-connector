package example;

public class User {

  private final int id;
  private final double longitude;
  private final double latitude;
  private final double score;

  public User(
          final int id,
          final double longitude,
          final double latitude,
          final double score) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
    this.score = score;
  }

  public int getId() {
    return this.id;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public double getScore() {
    return this.score;
  }

}
