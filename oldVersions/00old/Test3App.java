/** testing programmatic use of weka **/

import weka.core.*;

public class Test3App
{
  public static void main(String[] args)
  {
    int[][] data = new int[3][5];
    data[0][0] = 1;
    data[0][1] = 2;
    data[0][2] = 3;
    data[0][3] = 2;
    data[0][4] = 3;
    data[1][0] = 4;
    data[1][1] = 3;
    data[1][2] = 4;
    data[1][3] = 5;
    data[1][4] = 4;
    data[2][0] = 5;
    data[2][1] = 6;
    data[2][2] = 6;
    data[2][3] = 7;
    data[2][4] = 8;

    Attribute[] att = new Attribute[3];
    att[0] = new Attribute("Att 0");
    att[1] = new Attribute("Att 1");
    att[2] = new Attribute("Att 2");

  }
}
