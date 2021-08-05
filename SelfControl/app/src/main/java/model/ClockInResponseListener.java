package model;

import java.util.List;

import bean.ClockInLabel;

public interface ClockInResponseListener {
    void clockInRequestSuccess(List<ClockInLabel> list);

    void clockInRequestFail(Throwable t);
}
