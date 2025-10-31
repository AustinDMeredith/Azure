public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BasedBox(15, 15, 15, 2, 3.176);
    SvgGen.generateFile(box.panels);
  }  
}
