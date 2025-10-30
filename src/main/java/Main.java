public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BasedBox(80, 50, 65, 2, 8);
    SvgGen.generateFile(box.panels);
  }  
}
