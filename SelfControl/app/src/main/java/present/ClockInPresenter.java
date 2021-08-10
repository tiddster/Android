package present;

import java.util.List;

import bean.ClockInLabel;
import bean.ClockInLabelTitle;
import model.ClockInModel;
import model.ClockInResponseListener;
import view.ClockInView;

public class ClockInPresenter {
    ClockInView mClockInView;
    ClockInModel mClockInModel;

    public ClockInPresenter(ClockInView clockInView) {
        this.mClockInView = clockInView;
        this.mClockInModel = new ClockInModel();
    }

    public void getLabels(String token){
        mClockInModel.getClockInLabels(token, new ClockInResponseListener() {
            @Override
            public void clockInRequestLabelSuccess(List<ClockInLabel> list) { mClockInView.showLabelInfo(list); }

            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }

    public void toClockIn(String token, ClockInLabelTitle clockInLabelTitle){
        mClockInModel.toClockIn(token, clockInLabelTitle, new ClockInResponseListener() {
            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }

    public void removeLabel(String token, ClockInLabelTitle clockInLabelTitle){
        mClockInModel.removeLabel(token, clockInLabelTitle, new ClockInResponseListener() {
            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }

            @Override
            public void removeLabelSuccess(String message) {
                mClockInView.showRemoveSuccess(message);
            }
        });
    }

    public void isClockInToday(String token, String url, ClockInLabel clockInLabel){
        mClockInModel.isClockInToday(token, url, new ClockInResponseListener() {
            @Override
            public void isClockInToday(boolean isClockIn) {
                clockInLabel.setClockInToday(isClockIn);
            }

            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }
}
