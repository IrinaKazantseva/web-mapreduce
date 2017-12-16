package model;

/**
 * Created by Irina Kazantseva on 07.10.2017.
 */
public class StressResponseDto {
    private long time;

    public StressResponseDto(double t, double y, int n, int k, long time) {
        this.time = time;
    }



    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}