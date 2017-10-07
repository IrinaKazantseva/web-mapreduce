package model;

/**
 * Created by Irina Kazantseva on 07.10.2017.
 */
public class ResponseDto {
    private double result;
    private long time;

    public ResponseDto(double result, long time) {
        this.result = result;
        this.time = time;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}