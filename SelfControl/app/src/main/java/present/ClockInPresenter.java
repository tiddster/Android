package present;

import java.util.List;

import bean.ClockInLabel;
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
            public void clockInRequestSuccess(List<ClockInLabel> list) {
                mClockInView.showLabelInfo(list);
            }

            @Override
            public void clockInRequestFail(Throwable t) {
                mClockInView.showError(t);
            }
        });
    }
}
