package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 10.9.25 
 * Last Changed: 11.15.25
 * Description: Layout panels in a 2-column grid. Each grid cell is sized using
 *              the max width (for its column) and max height (for its row).
 *              Panels are centered inside their cells to avoid overlap even
 *              when sizes differ. Male starting edge adds a downward Y offset.
 */
import java.util.ArrayList;
import java.util.List;

import com.azure.objects.Panel;

public class LayoutService {

  // Grid config
  private static final int NUM_COLS = 2;

  // Margins/gaps (mm)
  private static final double LEFT_MARGIN = 10.0;
  private static final double TOP_MARGIN  = 10.0;
  private static final double COL_GAP     = 10.0;
  private static final double ROW_GAP     = 10.0;

  // Offset applied when the first edge is male (downward only)
  private static final double MALE_Y_OFFSET = 3.175;

  public static void findStartPoint(ArrayList<Panel> panels) {
    if (panels == null || panels.isEmpty()) return;

    // Build layout items with parsed indices (p-0, p-1, ...)
    List<Item> items = new ArrayList<>(panels.size());
    int maxRow = -1;
    for (Panel p : panels) {
      if (p == null) continue;
      int idx = parseIndex(p.id);
      if (idx < 0) continue; // skip unknown ids
      int col = idx % NUM_COLS;
      int row = idx / NUM_COLS;
      maxRow = Math.max(maxRow, row);
      items.add(new Item(p, idx, row, col));
    }
    if (items.isEmpty()) return;

    int numRows = maxRow + 1;

    // Compute max width per column, max height per row
    double[] colWidths = new double[NUM_COLS];
    double[] rowHeights = new double[numRows];

    for (Item it : items) {
      colWidths[it.col] = Math.max(colWidths[it.col], it.panel.width);
      rowHeights[it.row] = Math.max(rowHeights[it.row], it.panel.height);
    }

    // Prefix sums → top-left origin of each column/row cell
    double[] colX = new double[NUM_COLS];
    double[] rowY = new double[numRows];

    // Columns
    double xCursor = LEFT_MARGIN;
    for (int c = 0; c < NUM_COLS; c++) {
      colX[c] = xCursor;
      xCursor += colWidths[c] + COL_GAP;
    }

    // Rows
    double yCursor = TOP_MARGIN;
    for (int r = 0; r < numRows; r++) {
      rowY[r] = yCursor;
      yCursor += rowHeights[r] + ROW_GAP;
    }

    // Place each panel centered within its cell
    for (Item it : items) {
      Panel p = it.panel;

      double cellLeft   = colX[it.col];
      double cellTop    = rowY[it.row];
      double cellWidth  = colWidths[it.col];
      double cellHeight = rowHeights[it.row];

      // Center the panel inside the cell
      double x = cellLeft + (cellWidth  - p.width)  / 2.0;
      double y = cellTop  + (cellHeight - p.height) / 2.0;

      // Apply downward offset if first edge is male
      if (p.edges != null && !p.edges.isEmpty() && p.edges.get(3) == Panel.EdgeRole.male) {
        x += MALE_Y_OFFSET;
      }

      // Write deterministically as [x, y]
      if (p.startPoint == null) {
        p.startPoint = new ArrayList<>();
      } else {
        p.startPoint.clear();
      }
      p.startPoint.add(x);
      p.startPoint.add(y);
    }
  }

  // Helpers

  private static int parseIndex(String id) {
    if (id == null) return -1;
    int dash = id.lastIndexOf('-');
    if (dash < 0 || dash == id.length() - 1) return -1;
    try {
      return Integer.parseInt(id.substring(dash + 1));
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  private static final class Item {
    final Panel panel;
    final int idx, row, col;
    Item(Panel panel, int idx, int row, int col) {
      this.panel = panel;
      this.idx = idx;
      this.row = row;
      this.col = col;
    }
  }
}
