import java.util.*;

public class Test2App
{
  public static void main(String[] args)
    throws java.io.FileNotFoundException
  {
    Target tt = new TargetExample4();
    Program pp = new Program("example4.prg");
    DataSet ds = new DataSet(pp.getNoInputVar()," Complete 1-4");
    ds.initialiseComplete(1,4);

    Spectrum spec = new Spectrum(pp,ds,tt);

    spec.analyse();

  }
}
