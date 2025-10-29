public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BasedBox(100, 100, 100, 2, 8);
    SvgGen.generateFile(box.panels);
  }  
}
