package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles Keyboard events
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> keyTyped;
  private Map<Integer, Runnable> keyPressed;
  private Map<Integer, Runnable> keyReleased;

  public KeyboardHandler() {
    this.keyTyped = new HashMap<Integer, Runnable>();
    this.keyPressed = new HashMap<Integer, Runnable>();
    this.keyReleased = new HashMap<Integer, Runnable>();
  }

  public void addKeyTypedRunnable(int keyCode, Runnable runnable) {
    // check how hashmaps act when the same key is put in twice
    if (runnable == null) {
      throw new IllegalArgumentException("The given Runnable should not be null");
    }
    this.keyTyped.put(keyCode, runnable);
  }

  public void addKeyPressedRunnable(int keyCode, Runnable runnable) {
    if (runnable == null) {
      throw new IllegalArgumentException("The given Runnable should not be null");
    }
    this.keyPressed.put(keyCode, runnable);
  }

  public void addKeyReleasedRunnable(int keyCode, Runnable runnable) {
    if (runnable == null) {
      throw new IllegalArgumentException("The given Runnable should not be null");
    }
    this.keyReleased.put(keyCode, runnable);
  }

  @Override
  public void keyTyped(KeyEvent ke) {
    if (this.keyTyped.containsKey(ke.getKeyCode())) {
      this.keyTyped.get(ke.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent ke) {
    if (this.keyPressed.containsKey(ke.getKeyCode())) {
      this.keyPressed.get(ke.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent ke) {
    if (this.keyReleased.containsKey(ke.getKeyCode())) {
      this.keyReleased.get(ke.getKeyCode()).run();
    }
  }
}
