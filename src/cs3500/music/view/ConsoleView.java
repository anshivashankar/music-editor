package cs3500.music.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import cs3500.music.model.MusicController;
import cs3500.music.model.Note;

/**
 * Console view of an IMusicModel, displays it through console, using System.out.
 */
public class ConsoleView implements IView {
  MusicController controller = new MusicController();
  Appendable app;

  public ConsoleView(Appendable app) {
    this.app = app;
  }

  public static void main(String[] args) {

    IView console = new ConsoleView(System.out);
    console.view();
  }

  /**
   * outputs to System.out that represents the piece of music in and only shows the min to max notes
   * required
   */
  public void view() {

    if (controller.getAllNotes().size() == 0) {
      System.out.println("");
      return;

    }

    StringBuilder pieceString = new StringBuilder();

    Note maxNote = Collections.max(controller.getAllNotes());
    Note minNote = Collections.min(controller.getAllNotes());

    int numberOfColumns = maxNote.compareTo(minNote) + 1;
    int lastBeat = controller.lastBeat();
    int beatNumberLength = Integer.toString(lastBeat - 1).length();

    // the notes printed

    pieceString.append(this.charMultiply(' ', beatNumberLength));

    for (int i = minNote.notePlace(); i <= maxNote.notePlace(); i++) {
      String noteToString = new Note(i, 1, 0).toString();

      switch (noteToString.length()) {
        case 2:
          pieceString.append("  ");
          pieceString.append(noteToString);
          pieceString.append(" ");
          break;

        case 3:
          pieceString.append(" ");
          pieceString.append(noteToString);
          pieceString.append(" ");
          break;

        case 4:
          pieceString.append(" ");
          pieceString.append(noteToString);
          break;

        default:
          throw new RuntimeException("Note toString giving wrong size");
      }
    }

    pieceString.append("\n");

    // adds all the values of the beats
    for (int i = 0; i <= lastBeat; i++) {


      String number = String.format("%" + beatNumberLength + "d", i);
      pieceString.append(number);

      for (char c : this.processBeat(i, minNote.notePlace(), numberOfColumns)) {
        pieceString.append("  ");
        pieceString.append(c);
        pieceString.append("  ");
      }

      pieceString.append("\n");
    }

    // just puts whatever pieceString was into Appendable app, so that it can be tested
    try {
      app.append(pieceString.toString()  + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  // returns string that repeats the given string s factor number times
  private char[] charMultiply(char c, int factor) {
    char[] multiplied = new char[factor];
    Arrays.fill(multiplied, c);

    return multiplied;
  }

  private char[] processBeat(int beat, int minNotePlace, int numberOfColumns) {
    char[] beatValues = new char[numberOfColumns];
    Arrays.fill(beatValues, ' ');

    for (Note n : controller.getBeatNotes(beat)) {
      // the column that this note gets placed into
      int column = n.notePlace() - minNotePlace;

      if (n.getStartBeat() == beat) {
        beatValues[column] = 'X';
      } else {
        if (beatValues[column] == ' ') {
          beatValues[column] = '|';
        }
      }
    }
    return beatValues;

  }
}
