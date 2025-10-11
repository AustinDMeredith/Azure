public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BoxSpec(100, 100, 100, 2, 8);
    // SvgGen.generateFile();
    
    for (Panel panel : box.panels){
      System.out.print(panel.path);
      System.out.print("\n \n");
    }
  }  
}
