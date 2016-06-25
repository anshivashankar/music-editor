package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.controller.GUIMusicControllerImpl;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.ReadOnlyModelImpl;
import cs3500.music.view.MockGuiViewFrame;

import static org.junit.Assert.*;

/**
 * GUIMusicControllerImpl Test class.
 */
public class GUIMusicControllerImplTest {
  @Test
  public void getBeatNotes() throws Exception {

    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 0, 10, 1));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    MockGuiViewFrame view = new MockGuiViewFrame(roModel);
    GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, view);
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getBeatNotes(0), notes);
  }

  @Test
  public void getAllNotes() throws Exception {
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 0, 10, 1));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    MockGuiViewFrame view = new MockGuiViewFrame(roModel);
    GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, view);
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);
  }

  @Test
  public void lastBeat() throws Exception {
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 0, 10, 1));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    MockGuiViewFrame view = new MockGuiViewFrame(roModel);
    GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, view);
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);
  }

  @Test
  public void getTempo() throws Exception {
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 0, 10, 1));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    MockGuiViewFrame view = new MockGuiViewFrame(roModel);
    GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, view);
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getTempo(), 5);
  }

  @Test
  public void add() throws Exception {
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 0, 10, 1));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    MockGuiViewFrame view = new MockGuiViewFrame(roModel);
    GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, view);
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);

    // tests the add method here, with several different kinds of notes
    controller.add(new Note(62, 10, 1, 10, 2));
    notes.add(new Note(62, 10, 1, 10, 2));
    controller.add(new Note(Note.Pitch.C, Note.Octave.Four, 10, 1));
    notes.add(new Note(Note.Pitch.C, Note.Octave.Four, 10, 1));
    assertEquals(controller.getAllNotes(), notes);
  }

  @Test
  public void remove() throws Exception {
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 0, 10, 1));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl<Note>(model);
    MockGuiViewFrame view = new MockGuiViewFrame(roModel);
    GUIMusicControllerImpl<Note> controller = new GUIMusicControllerImpl<Note>(model, view);
    controller.add(new Note(61, 10, 1, 10, 1));
    controller.add(new Note(60, 10, 0, 10, 1));
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(61, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);

    // tests the remove method, with several different kinds of notes

    controller.remove(new Note(60, 10, 0, 10, 1));
    notes.remove(new Note(60, 10, 0, 10, 1));
    assertEquals(controller.getAllNotes(), notes);

    controller.remove(new Note(Note.Pitch.C, Note.Octave.Four, 10, 0, 10, 1));
    notes.remove(new Note(60, 10, 0, 10, 1));
  }
}