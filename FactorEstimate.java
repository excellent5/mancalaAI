import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by zhanyang on 15/11/9.
 */
public class FactorEstimate {
    float time;
    int allremainedstones;
    int nonemptyholes;

    public FactorEstimate(float time, int allremainedstones, int nonemptyholes) {
        this.time = time;
        this.allremainedstones = allremainedstones;
        this.nonemptyholes = nonemptyholes;
    }

    public int estimateDepth() {
        if (nonemptyholes == 1) {
            return 1;
        }
        int estimatedremainedsteps = allremainedstones;
        float timeupbound = time / estimatedremainedsteps;
        double timetarget = timeupbound / calFactor();
        return searchStandardDepth(timetarget);
    }

    public int searchStandardDepth(double time) {
        try {
            BufferedReader filein = new BufferedReader(new FileReader("speedTest.txt"));
            String line;
            int depth = 0;
            while ((line = filein.readLine()) != null) {
                depth++;
                if (Integer.parseInt(line) > time) {
                    return Math.max(depth - 1, 1);
                }
            }
            return depth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public double calFactor() {
        return Math.sqrt(allremainedstones / 48) * Math.pow(nonemptyholes / 6, 2);
    }

}
