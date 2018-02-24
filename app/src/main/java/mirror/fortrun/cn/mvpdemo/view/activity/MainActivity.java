package mirror.fortrun.cn.mvpdemo.view.activity;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import mirror.fortrun.cn.mvpdemo.R;
import mirror.fortrun.cn.mvpdemo.presenter.MainPresenter;
import mirror.fortrun.cn.mvpdemo.view.MainView;
import mirror.fortrun.cn.mvpdemo.view.common.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {

    private TextView mTvTime;
    private Button mBtn_click;
    private MainPresenter mMainPresenter = new MainPresenter();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mBtn_click = (Button) findViewById(R.id.btn_click);
     }

    @Override
    protected void initData() {
        mMainPresenter.attachView(this);
        mMainPresenter.init(60 * 1000);
        mBtn_click.setOnClickListener(this);
    }

    @Override
    protected Object getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                try {
                    mMainPresenter.RequestData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void toTime(int millisUntil, CountDownTimer timer) {
        mTvTime.setText(millisUntil + "秒");
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void toMainActivity(Object data) {
        if (data == null) {
            Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
        }
    }
}
