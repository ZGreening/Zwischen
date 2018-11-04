///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Globals.java
// Group:       3
// Date:        November 3, 2018
// Description: A global class of public variables for use throughout the
//    program.
///////////////////////////////////////////////////////////////////////////////

package other;

import java.util.ArrayList;

public class Globals {

  //FindBugs flags this, however the purpose is to leave it
  //mutable from anywhere in code.
  public static ArrayList<User> availableDrivers = new ArrayList<>();

}
