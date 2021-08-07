package view;

import java.util.List;

import bean.ClockInLabel;

public interface ClockInView {
    void showLabelInfo(List<ClockInLabel> list);
    void showError(String message);
    void showRemoveSuccess(String message);
}
