package spark;

import org.junit.rules.ExternalResource;
import spark.Spark;

public class LeapYearServerRule extends ExternalResource {

  protected void before() {
    String[] args = {};
    LeapYear.main(args); // for other apps, replace LeapYear with the name of the main app class (often App)
   }

  protected void after() {
    Spark.stop();
  }
}