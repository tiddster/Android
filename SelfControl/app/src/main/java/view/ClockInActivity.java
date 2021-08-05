package view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selfcontrol.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.ClockInLabel;
import present.ClockInPresenter;

public class ClockInActivity extends AppCompatActivity implements ClockInView{
    List<ClockInLabel> mClockInLabelList = new ArrayList<>();
    RecyclerView mRecyclerView;
    ClockInAdapter mClockInAdapter;
    ClockInPresenter mClockInPresenter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_clockin);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initView();
        Listener();
    }

    /**
     * 绑定与监听
     */
    public void initView() {
        mClockInPresenter = new ClockInPresenter(this);

        mRecyclerView = findViewById(R.id.clockin_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        getClockInLabel("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzQ4MiIsImV4cCI6MTYyODg3MjU4NSwiaWF0IjoxNjI4MTUyNTg1fQ.w1cYO0TAaB4iiqrQVciKhHiMZmnVuN1VpuNh5kEXqhQ");
        updateRVUI();
    }

    public void Listener() {
        mClockInAdapter.setClockInClickListener(new ClockInClickListener() {
            @Override
            public void clockInClickListener() {
                mClockInAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * mvp：获取信息后更新列表、获取错误返回错误信息
     * @param clockInLabels
     */

    @Override
    public void showLabelInfo(List<ClockInLabel> clockInLabels) {
        mClockInLabelList = clockInLabels;
        updateRVUI();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show();
        System.out.println(throwable.toString());
    }

    /**
     * 网络请求: 删除、打卡、获取标签
     */
    public void removeLabel() {
    }

    public void toClockInLabel() {
    }

    public void getClockInLabel(String token){
        mClockInPresenter.getLabels(token);
    }

    /**
    *
    *   列表相关
    *
     */
    public void updateRVUI() {
        mClockInAdapter = new ClockInAdapter(mClockInLabelList);
        mRecyclerView.setAdapter(mClockInAdapter);
    }

    private class ClockInHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        TextView clockIn_title, clockIn_times;
        Button clockIn_button, clockIn_delete;
        ImageView clockIn_image;

        public ClockInHolder(@NonNull View itemView) {
            super(itemView);
            clockIn_title = itemView.findViewById(R.id.clockin_title);
            clockIn_times = itemView.findViewById(R.id.clockin_times);
            clockIn_button = itemView.findViewById(R.id.punch);
            clockIn_image = itemView.findViewById(R.id.clockin_label_image);
            clockIn_delete = itemView.findViewById(R.id.clockin_delete);
            mConstraintLayout = itemView.findViewById(R.id.clockin_item);
        }

        public void bind(ClockInLabel clockInLabel) {
            clockIn_title.setText(clockInLabel.getTitle());
            clockIn_times.setText(String.valueOf(clockInLabel.getNumber()));
        }
    }

    private class ClockInAdapter extends RecyclerView.Adapter<ClockInHolder> {
        List<ClockInLabel> mClockInLabels;
        ClockInClickListener mClockInClickListener;
        ClockInLongClickListener mClockInLongClickListener;
        boolean[] isClockIn;
        int lastPosition = -1;

        public ClockInAdapter(List<ClockInLabel> labels) {
            mClockInLabels = labels;
            isClockIn = new boolean[labels.size()];
            Arrays.fill(isClockIn,false);
        }

        public void setClockInClickListener(ClockInClickListener clockInClickListener) {
            mClockInClickListener = clockInClickListener;
        }

        public void setClockInLongClickListener(ClockInLongClickListener clockInLongClickListener) {
            mClockInLongClickListener = clockInLongClickListener;
        }

        @NonNull
        @Override
        public ClockInHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_clockin_item, parent, false);
            return new ClockInHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ClockInHolder holder, int position) {
            ClockInLabel clockInLabel = mClockInLabels.get(position);
            holder.bind(clockInLabel);

            if(isClockIn[position]){
                holder.clockIn_button.setBackgroundResource(R.drawable.punch_done);
                holder.clockIn_button.setEnabled(false);
                holder.clockIn_button.setText("已打卡");
            }

            holder.clockIn_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = clockInLabel.getNumber();
                    clockInLabel.setNumber(++i);
                    isClockIn[position] = true;
                    notifyDataSetChanged();
                }
            });

            holder.mConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    holder.clockIn_delete.setVisibility(View.VISIBLE);
                    holder.clockIn_button.setVisibility(View.GONE);
                    lastPosition = position;
                    return true;
                }
            });

            holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(lastPosition != position){
                        updateRVUI();
                    }
                }
            });

            holder.clockIn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClockInLabelList.remove(position);
                    updateRVUI();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mClockInLabels.size();
        }
    }
}