public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BoxSpec(100, 100, 100, 2, 10);
    SvgGen.generateFile(box.panels);
  }  
}
