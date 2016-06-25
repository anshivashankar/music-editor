package cs3500.music.view;

import java.util.Arrays;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

/**
 * Mocks the Sequence class to test it.
 */
public class MockSequence extends Sequence {

  StringBuilder string;

  public MockSequence(float divisionType, int resolution, StringBuilder string) throws InvalidMidiDataException {
    super(divisionType, resolution);
    this.string = string;
  }


  @Override
  public float getDivisionType() {
    for (Track t : super.getTracks()) {
      string.append("accessed track\n");
      for (int count = 0; count != t.size(); count++) {
        string.append(Arrays.toString(t.get(count).getMessage().getMessage())).append(" ")
                .append(t.get(count).getTick()).append("\n");
      }
    }
    return 0;
  }
}
