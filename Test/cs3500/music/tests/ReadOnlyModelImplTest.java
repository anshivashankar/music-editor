package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.ReadOnlyModelImpl;

import static org.junit.Assert.*;

/**
 * Created by ashwinshivashankar on 6/25/16.
 */
public class ReadOnlyModelImplTest {
  @Test
  public void getBeatNotes() throws Exception {
    IMusicModel<Note> model = new Piece();
    model.add(new Note(60, 10, 1, 10, 1));
    model.add(new Note(60, 10, 0, 10, 1));
    model.add(new Note(59, 10, 2, 10, 2));
    ReadOnlyModelImpl<Note> roModel = new ReadOnlyModelImpl(model);
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(60, 10, 1, 10, 1));
    notes.add(new Note(60, 10, 0, 10, 1));
    //notes.add(new Note(59, 10, 2, 10, 2));
    assertEquals(roModel.getBeatNotes(1), notes);

  }

  @Test
  public void getAllNotes() throws Exception {
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
  }

  @Test
  public void lastBeat() throws Exception {
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
  }

  @Test
  public void getTempo() throws Exception {
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
  }

}