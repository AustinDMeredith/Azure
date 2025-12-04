package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 10.5.25
 * Last Changed: 11.15.25
 * Description: This class will have a function to set the roles of each edge with some sort of methodalegy, I have no idea what 
 *              that will look like right now tho.
 * */

// SOMEBODY PLEASE FIX THIS, I CANT BARE TO LOOK AT IT, IT IS SO UGLY \:(
// this genuinly might be the worst thing i have ever constructed
import java.util.ArrayList;

import com.azure.objects.Panel;

public class SetEdgeRoles {
  public static void setRoles (ArrayList<Panel> panels) {
    for (Panel panel : panels) {
      if (panel.role == Panel.PanelRole.front) { // Sets all edge roles to male for front panels
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.bottom) { // Sets all edge roles to female for bottom panels
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.right) { // Alternates edge roles male and female for right panels
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.left) { // Alternates edge roles male and female for right panels
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.basedBottom) { // Sets all edge roles to bottom for Based Bottom panels
        panel.edges.add(Panel.EdgeRole.bottom);
        panel.edges.add(Panel.EdgeRole.bottom);
        panel.edges.add(Panel.EdgeRole.bottom);
        panel.edges.add(Panel.EdgeRole.bottom);
      } else if (panel.role == Panel.PanelRole.top) {
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.back) {
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.slidableFront) {
        panel.edges.add(Panel.EdgeRole.slidableFront);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.slidableBack) {
        panel.edges.add(Panel.EdgeRole.slidableBack);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.slidableLeft) {
        panel.edges.add(Panel.EdgeRole.slidableSide);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.slidableRight) {
        panel.edges.add(Panel.EdgeRole.slidableSide);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.slidingLid || panel.role == Panel.PanelRole.liftingLid || panel.role == Panel.PanelRole.innerLid) {
        panel.edges.add(Panel.EdgeRole.flatLid);
        panel.edges.add(Panel.EdgeRole.flatLid);
        panel.edges.add(Panel.EdgeRole.flatLid);
        panel.edges.add(Panel.EdgeRole.flatLid);
      } else if (panel.role == Panel.PanelRole.bottomLeftRail) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.flat);
      } else if (panel.role == Panel.PanelRole.bottomRightRail) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.flat);
      } else if (panel.role == Panel.PanelRole.topLeftRail) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.flat);
      } else if (panel.role == Panel.PanelRole.topRightRail) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.flat);
      } else if (panel.role == Panel.PanelRole.backRail) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.flat);
      } else if (panel.role == Panel.PanelRole.frontTop) {
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.frontBottom) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.female);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.backTop) {
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.femaleConnector);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.femaleConnector);
      } else if (panel.role == Panel.PanelRole.backBottom) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.femaleBack);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.femaleBack);
      } else if (panel.role == Panel.PanelRole.rightTop) {
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.maleCutOut);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.rightBottom) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.maleHinge);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.leftTop) {
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.maleCutOut);
      } else if (panel.role == Panel.PanelRole.leftBottom) {
        panel.edges.add(Panel.EdgeRole.flat);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.male);
        panel.edges.add(Panel.EdgeRole.maleHinge);
      }    
    } 
  }
}
