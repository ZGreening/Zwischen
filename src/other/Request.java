package other;

public class Request {

  private String going;
  private String coming;
  private String time;

  //A constructor to create an object, which allows search(in riderequest) to compare database values and make objects to populate a tableview
  public Request(String going, String coming, String time) {

    this.going = going;
    this.coming = coming;
    this.time = time;

  }

  public Request() {
  }

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
}
