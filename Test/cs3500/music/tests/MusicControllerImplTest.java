package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.ReadOnlyModelImpl;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.View;

import static org.junit.Assert.*;

/**
 * MusicControllerImpl tests.
 */
public class MusicControllerImplTest {
  @Test
  public void getBeatNotes() throws Exception {

    IMusicModel<Note> model = new Piece();
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    StringBuilder string = new StringBuilder();
    View view = new ConsoleView(string, roModel);
    MusicControllerImpl<Note> controller = new MusicControllerImpl<>(model, view);
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.view();
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getBeatNotes(0), notes);
  }

  @Test
  public void getAllNotes() throws Exception {
    IMusicModel<Note> model = new Piece();
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    StringBuilder string = new StringBuilder();
    View view = new ConsoleView(string, roModel);
    MusicControllerImpl<Note> controller = new MusicControllerImpl<>(model, view);
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.view();
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);
  }

  @Test
  public void lastBeat() throws Exception {
    IMusicModel<Note> model = new Piece();
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    StringBuilder string = new StringBuilder();
    View view = new ConsoleView(string, roModel);
    MusicControllerImpl<Note> controller = new MusicControllerImpl<>(model, view);
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.view();
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.lastBeat(), 11);
  }

  @Test
  public void getTempo() throws Exception {
    IMusicModel<Note> model = new Piece();
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    StringBuilder string = new StringBuilder();
    View view = new ConsoleView(string, roModel);
    MusicControllerImpl<Note> controller = new MusicControllerImpl<>(model, view);
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.view();
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getTempo(), 5);
  }

  @Test
  public void add() throws Exception {
    IMusicModel<Note> model = new Piece();
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    StringBuilder string = new StringBuilder();
    View view = new ConsoleView(string, roModel);
    MusicControllerImpl<Note> controller = new MusicControllerImpl<>(model, view);
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.view();
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);
  }

  @Test
  public void remove() throws Exception {
    IMusicModel<Note> model = new Piece();
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    StringBuilder string = new StringBuilder();
    View view = new ConsoleView(string, roModel);
    MusicControllerImpl<Note> controller = new MusicControllerImpl<>(model, view);
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.remove(new Note(60, 10, 0, 10, 1));
    controller.view();
    ArrayList<Note> notes = new ArrayList<Note>();
    //notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);
  }

  @Test
  public void view() throws Exception {
    IMusicModel<Note> model = new Piece();
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    StringBuilder string = new StringBuilder();
    View view = new ConsoleView(string, roModel);
    MusicControllerImpl<Note> controller = new MusicControllerImpl<>(model, view);
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    controller.remove(new Note(60, 10, 0, 10, 1));
    controller.view();
    ArrayList<Note> notes = new ArrayList<Note>();
    //notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(string.toString(), "    C4  C#4 \n" +
            " 0  X       \n" +
            " 1  |    X  \n" +
            " 2  |    |  \n" +
            " 3  |    |  \n" +
            " 4  |    |  \n" +
            " 5  |    |  \n" +
            " 6  |    |  \n" +
            " 7  |    |  \n" +
            " 8  |    |  \n" +
            " 9  |    |  \n" +
            "10       |  \n");
  }

}