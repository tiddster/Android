package model;

import java.util.List;

import bean.ClockInLabel;

public interface ClockInResponseListener {
    void clockInRequestLabelSuccess(List<ClockInLabel> list);

    void clockInRequestFail(String message);

    void removeLabelSuccess(String message);
}
