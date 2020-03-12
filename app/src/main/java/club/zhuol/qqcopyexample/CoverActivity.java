package club.zhuol.qqcopyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class CoverActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        myIntent();
        init();
    }

    private void myIntent() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what != 0) {
                    //tv_skip.setText(msg.what + "秒后进入APP");
                } else {
                    click();
                }
            }
        };
    }

    private void init() {
        //全屏显示

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //tv_skip = findViewById(R.id.tv_skip);
        //textView.setText("zhuoL");
        new CountDown().start();
    }

    private void click() {
        Intent intent = new Intent(CoverActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //进入APP倒计时
    class CountDown extends Thread {
        int count = 1;

        @Override
        public void run() {
            try {
                while (count >= 0) {
                    sleep(1000);
                    Message message = new Message();
                    message.what = count;
                    handler.sendMessage(message);
                    count--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

