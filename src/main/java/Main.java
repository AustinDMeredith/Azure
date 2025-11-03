/* Author: Austin Meredith
 * Date Created: 10.3.25
 * Last Changed: 11.3.25
 * Description: This class is incharge of calling constuctors for boxes and calling the function to generate svg files
 */

public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BasedBox(50, 50, 50, 2, 6);
    SvgGen.generateFile(box.panels);
  }  
}
