package cs3500.music.view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.model.Note;

/**
 * A dummy view that simply draws a string 
 */
public class ConcreteGuiViewPanel extends JPanel {

  private final MusicControllerImpl<Note> controller;
  private static final int boxSize = 20;
  private final  Note minNote;
  private final int numberOfRows;
  private final int numberOfColumns;

  ConcreteGuiViewPanel(MusicControllerImpl<Note> piece) {
    super();
    this.controller = piece;
    Note maxNote = Collections.max(controller.getAllNotes());
    this.minNote = Collections.min(controller.getAllNotes());
    this.numberOfRows = maxNote.compareTo(minNote) + 1;
    // rounds up numberOfColumns to the closest divisor of 4
    this.numberOfColumns = ((piece.lastBeat() + 3) / 4) * 4;
  }

  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful

    g.setColor(Color.black);

    // draws the Pitch Strings
    for (int i = 0; i < this.numberOfRows; i++) {
      g.drawString(new Note(this.minNote.notePlace() + this.numberOfRows - i - 1, 0, 0).toString(),
              0, boxSize * (i + 2) - boxSize / 4);
    }

    // draws beat numbers at the top
    for (int i = 0; i <= this.numberOfColumns; i += 16) {
      g.drawString(Integer.toString(i), boxSize * (i + 2), boxSize * 3 / 4);
    }

    // draws row dividers
    for (int i = 0; i <= this.numberOfRows; i++) {
      g.fillRect(boxSize * 2, boxSize * (i + 1), boxSize * this.numberOfColumns + 2, 2);
    }

    // draws the notes
    for (int i = 0; i <= this.numberOfColumns; i++) {
      List<Note> notes = new LinkedList<Note>();
      for (Note n : this.controller.getBeatNotes(i)) {
        int noteRow = this.numberOfRows - n.compareTo(this.minNote) - 1;
        // draws this note no matter what if it starts at this beat
        if (i == n.getStartBeat()) {
          g.setColor(Color.darkGray);
          g.fillRect(boxSize * (i + 2) + 2, boxSize * (noteRow + 1) + 2, boxSize, boxSize - 2);
        } else {
          // makes sure not to draw over a note that has already been drawn
          if (!notes.contains(n)) {
            g.setColor(Color.green);
            g.fillRect(boxSize * (i + 2) + 2, boxSize * (noteRow + 1) + 2, boxSize, boxSize - 2);
          }
        }
      }
    }

    g.setColor(Color.black);

    // draws column dividing lines every 4 beats
    for (int i = 0; i <= this.numberOfColumns; i += 4) {
      g.fillRect(boxSize * (i + 2), boxSize, 2, boxSize * this.numberOfRows);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(boxSize * (numberOfColumns + 5), boxSize * (numberOfRows + 5));
  }

}
