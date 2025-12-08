package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.9.25
 * Last Changed: 11.9.25
 * Description: This class validates user input and repromts for CLI version or clamps value for GUI version
 */

import com.azure.objects.BoxSpec;
import com.azure.objects.Panel;
public class ValidationService {
  // verifys the user input and throws an exception if any input is invalid (exception gets caught in the front end and displays which text box the error came from)
  public static void verifyInput (double height, double width, double depth, double toothWidth, String engraving, double fontSize, BoxSpec.BoxType boxType, Panel.PanelRole lidType) {
    if (!isValidHeigth(height, boxType)) throw new IllegalArgumentException("Entered height is not valid");
    else if (!isValidWidth(width, lidType)) throw new IllegalArgumentException("Entered width is not valid");
    else if (!isValidDepth(depth, lidType)) throw new IllegalArgumentException("Entered depth is not valid");
    else if (!isValidToothWidth(toothWidth, height)) throw new IllegalArgumentException("Entered tooth size is not valid");
    else if (!isValidToothWidth(toothWidth, width)) throw new IllegalArgumentException("Entered tooth size is not valid");
    else if (!isValidToothWidth(toothWidth, depth)) throw new IllegalArgumentException("Entered tooth size is not valid");
    else if (!isValidEngraving(engraving, fontSize, width, height, depth, boxType)) throw new IllegalArgumentException("Entered font size or engraving is not valid");
  }

  // if the value is too small or the value is too big for an edge, return false
  public static boolean isValidToothWidth (double toothWidth, double length) {
    if (toothWidth < 3 && toothWidth >= 0) return false;
    else if (length / toothWidth < 3) return false;
    return true;
  }

  // if the value is too small or too large for the associated box, return false 
  public static boolean isValidHeigth (double height, BoxSpec.BoxType boxType) {
    boolean isHinged = (boxType == BoxSpec.BoxType.hinged);
    if (isHinged && height < 60) return false;
    else if (height < 20 || height > 300) return false;
    return true;
  }

  // if the value is too small or too large for the associated box, return false 
  public static boolean isValidWidth (double width, Panel.PanelRole lidType) {
    boolean isLiftingLid = (lidType == Panel.PanelRole.liftingLid);
    if (isLiftingLid && width < 50) return false;
    else if (width < 20 || width > 300) return false;
    return true;
  }

  // if the value is too small or too large for the associated box, return false 
  public static boolean isValidDepth (double depth, Panel.PanelRole lidType) {
    boolean isSlidingLid = (lidType == Panel.PanelRole.slidingLid);
    if (isSlidingLid && depth < 30) return false;
    else if (depth < 20 || depth > 300) return false;
    return true;
  }

  // if the engraving is too large for the any of the panels on the box, return false 
  public static boolean isValidEngraving (String engraving, double size, double width, double height, double depth, BoxSpec.BoxType boxType) {
    final double AVG_CHAR_WIDTH_FACTOR = 0.6;   // width per char in "fontSize units"
    final double LINE_HEIGHT_FACTOR    = 1.2;   // line height in "fontSize units"
    double length = engraving.length();
    boolean isHinged = (boxType == BoxSpec.BoxType.hinged);
    if (isHinged && size * LINE_HEIGHT_FACTOR > height * .60 - 4) return false;
    else if (length * AVG_CHAR_WIDTH_FACTOR * size > width -8 || length * AVG_CHAR_WIDTH_FACTOR * size > depth - 8) return false;
    else if (size * LINE_HEIGHT_FACTOR > height - 8) return false;
    else if (size < 2) return false;
    return true;
  }
}
