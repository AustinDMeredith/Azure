public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BoxSpec(85, 50, 70, 2, 10);
    SvgGen.generateFile(box.panels);
  }  
}
