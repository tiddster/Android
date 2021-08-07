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

            @Override
            public void removeLabelSuccess(String message) {

            }
        });
    }

    public void toClockIn(String token, String title){
        mClockInModel.toClockIn(token, title, new ClockInResponseListener() {
            @Override
            public void clockInRequestLabelSuccess(List<ClockInLabel> list) { }

            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }

            @Override
            public void removeLabelSuccess(String message) {

            }
        });
    }

    public void removeLabel(String token, ClockInLabelTitle clockInLabelTitle){
        mClockInModel.removeLabel(token, clockInLabelTitle, new ClockInResponseListener() {
            @Override
            public void clockInRequestLabelSuccess(List<ClockInLabel> list) {

            }

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
}
