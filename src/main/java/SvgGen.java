/* Author: Austin Meredith
 * Date: 10.5.25
 * Description: This class will generate the svg. We will probably have a .svg that has all the boilerplate that we just manipulate
 *              the stuff we need to change.
 * */
import java.io.FileWriter;
import java.io.IOException;

public class SvgGen {
  static String line1 = "<?xml version='1.0' encoding='utf-8'?>\n";
  static String line2 = """ 
    <svg height="1000mm" viewBox="0.0 0.0 1000 1000" width="1000mm"
     xmlns:xlink="http://www.w3.org/1999/xlink">\n
  """;
  
  static String line3 = "<g id=\"" + BoxSpec.panels.get(0).id + "\" style=\"fill:none;stroke-linecap:round;stroke-linejoin:round;\">";
  static String line4 = "<path d=\"" + BoxSpec.panels.get(0).path + "\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.10\" />";
  static String line5 = "</g>";
  static String line6 = "</svg>";

  static public void generateFile () {
    try {
      FileWriter clear = new FileWriter("box.svg");
      clear.write("");
      clear.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    try (FileWriter writer = new FileWriter("box.svg", true)) {
      writer.write(line1);
      writer.write(line2);
      writer.write(line3);
      writer.write(line4);
      writer.write(line5);
      writer.write(line6);
      System.out.println("Successfully wrote to box.svg");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
}


