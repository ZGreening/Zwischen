package other;

public class Request {

  public String getGoing() {
    return going;
  }

  public void setGoing(String going) {
    this.going = going;
  }

  public String getComing() {
    return coming;
  }

  public void setComing(String coming) {
    this.coming = coming;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  private String going;
private String coming;
private String time;

  public Request(String going, String coming, String time) {
    this.going = going;
    this.coming = coming;
    this.time = time;
  }

  public Request() {
  }
}
