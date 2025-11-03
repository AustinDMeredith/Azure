public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BasedBox(50, 50, 50, 2, 6);
    SvgGen.generateFile(box.panels);
  }  
}
