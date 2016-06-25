package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.ReadOnlyModelImpl;

import static org.junit.Assert.*;

/**
 * ReadOnlyModelImpl test. This is very similar to a controller test, however this only has the
 * read-only methods.
 */
public class ReadOnlyModelImplTest {
  @Test
  public void getBeatNotes() throws Exception {
    // test with a model that has notes in it
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 1, 10, 1));
    model.add(new Note(60, 10, 0, 9, 1));
    model.add(new Note(59, 10, 2, 10, 2));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl(model);
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 9, 1));
    //notes.add(new Note(59, 10, 2, 10, 2));
    assertEquals(roModel.getBeatNotes(1), notes);

    // test with an empty roModel
    model = new Piece();
    roModel = new ReadOnlyModelImpl(model);

    assertEquals(roModel.getBeatNotes(0), new ArrayList<Note>());

  }

  @Test
  public void getAllNotes() throws Exception {
    // test with a model that has notes in it
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 1, 10, 1));
    model.add(new Note(60, 10, 0, 10, 1));
    model.add(new Note(59, 10, 2, 10, 2));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl(model);
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    notes.add(new Note(59, 10, 2, 10, 2));
    assertEquals(roModel.getAllNotes(), notes);

    // test with an empty roModel
    model = new Piece();
    roModel = new ReadOnlyModelImpl(model);

    assertEquals(roModel.getAllNotes(), new ArrayList<Note>());
  }

  @Test
  public void lastBeat() throws Exception {
    // test with a model that has notes in it
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 1, 10, 1));
    model.add(new Note(60, 10, 0, 10, 1));
    model.add(new Note(59, 10, 2, 10, 2));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl(model);
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    //notes.add(new Note(59, 10, 2, 10, 2));
    assertEquals(roModel.lastBeat(), 12);

    // test with an empty roModel
    model = new Piece();
    roModel = new ReadOnlyModelImpl(model);

    assertEquals(roModel.lastBeat(), 0);
  }

  @Test
  public void getTempo() throws Exception {
    // test with a model that has notes in it
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 1, 10, 1));
    model.add(new Note(60, 10, 0, 10, 1));
    model.add(new Note(59, 10, 2, 10, 2));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl(model);
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    //notes.add(new Note(59, 10, 2, 10, 2));
    assertEquals(roModel.getTempo(), 5);

    // test with an empty roModel
    model = new Piece();
    roModel = new ReadOnlyModelImpl(model);

    assertEquals(roModel.getTempo(), 5);
  }

}