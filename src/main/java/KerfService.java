import java.util.ArrayList;

public class KerfService {
  static double kerf = .16;
  public static ArrayList<Double> getKerf (double n) {
    ArrayList<Double> settings = new ArrayList<>();
    double toothKerf = kerf / 2;
    double cornerKerf = kerf / 4;    
    settings.add(toothKerf);
    settings.add(cornerKerf);


    return settings;
  }
}
