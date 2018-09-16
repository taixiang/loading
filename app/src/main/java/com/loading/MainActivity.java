package com.loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LoadingView loadingView;
    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingView = findViewById(R.id.loadingView);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                loadingView.showLoading();
                loadingView.setText("正在加载");
                break;
            case R.id.btn2:
                loadingView.showSuccess();
                loadingView.setText("加载成功");
                break;
            case R.id.btn3:
                loadingView.showFail();
                loadingView.setText("加载失败");
                break;
            default:
                break;
        }
    }
}
