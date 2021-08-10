package model;

import java.util.List;

import bean.ClockInLabel;

public interface ClockInResponseListener {
    default void clockInRequestLabelSuccess(List<ClockInLabel> list) {}

    default void removeLabelSuccess(String message){}

    default void isClockInToday(boolean isClockIn){}

    void clockInRequestFail(String message);
}
