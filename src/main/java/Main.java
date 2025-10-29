public class Main {
  public static void main (String args[]) {
    BoxSpec box = new SimpleBox(80, 55, 40, 2, 8);
    SvgGen.generateFile(box.panels);
  }  
}
