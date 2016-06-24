package cs3500.music.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import cs3500.music.controller.MusicController;
import cs3500.music.model.Note;

/**
 * Console view of an IMusicModel, displays it through console, using System.out.
 */
public class ConsoleView implements View {
  private MusicController<Note> controller;
  private Appendable app;

  public ConsoleView(Appendable app, MusicController<Note> piece) {
    this.app = app;
    controller = piece;
    piece.setView(this);
  }

  /**
   * outputs to System.out that represents the piece of music in and only shows the min to max
   * notes required
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
    for (int i = 0; i < lastBeat; i++) {


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
      app.append(pieceString.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void play() {
    return;
  }

  @Override
  public void pause() {
    return;
  }


  /**
   * returns string that repeats the given string s factor number times
   *
   * @param c      of type character
   * @param factor of type int
   * @return string that repeats the given string that many times.
   */
  private char[] charMultiply(char c, int factor) {
    char[] multiplied = new char[factor];
    Arrays.fill(multiplied, c);

    return multiplied;
  }

  /**
   * Processes the Beat so that it will display properly.
   *
   * @param beat            of type int
   * @param minNotePlace    of type int
   * @param numberOfColumns of type int
   * @return An array of chars that represents how the note will be displayed in the console.
   */
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
