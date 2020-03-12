package club.zhuol.qqcopyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //设置密码状态为不可见
    boolean isPwdVisible = false;
    EditText et_input_account, et_input_password;
    ImageView iv_clear_account, iv_clear_password;
    CheckBox cb_login_drop_down, iv_login_open_eye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //解决EditText输入时界面变形问题
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //初始化组件
        initView();
        /**
         * 当账号输入时密码框组件不可见
         * 当密码输入时账号框组件不可见
         */
        hideEditTextView();
    }

    private void hideEditTextView() {
        et_input_account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //当账号输入时 密码框组件不可见
                if (hasFocus) {
                    iv_login_open_eye.setVisibility(View.INVISIBLE);
                    iv_clear_password.setVisibility(View.INVISIBLE);
                }else {
                    iv_login_open_eye.setVisibility(View.VISIBLE);
                    iv_clear_password.setVisibility(View.VISIBLE);
                }
            }
        });
        et_input_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //当密码框输入时 账号框组件不可见
                if (hasFocus){
                    iv_clear_account.setVisibility(View.INVISIBLE);
                }else{
                    iv_clear_account.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void initView() {
        et_input_account = findViewById(R.id.et_input_account);
        et_input_password = findViewById(R.id.et_input_password);
        iv_clear_account = findViewById(R.id.iv_clear_account);
        iv_clear_password = findViewById(R.id.iv_clear_password);
        cb_login_drop_down = findViewById(R.id.cb_login_drop_down);
        iv_login_open_eye = findViewById(R.id.iv_login_open_eye);
    }

    public void onLogin(View view) {
        //获取账号编辑框和密码编辑框中的输入内容
        String userName = et_input_account.getText().toString();
        String passWord = et_input_password.getText().toString();
        /**
         * 判断账号和密码是否为admin和123456
         * 是 跳转到主活动界面(联系人界面)
         * 否 清空密码框提示密码错误
         */
        if (userName.equals("111111") && passWord.equals("123456")) {
            Toast.makeText(this, "密码正确,正在跳转", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        } else if (userName.equals("") || passWord.equals("")) {
            Toast.makeText(this, "请将账号和密码输入完整", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "密码错误,请重新输入!", Toast.LENGTH_SHORT).show();
            //清空密码框
            et_input_password.setText("");
        }
    }

    //点击清空密码框和账号框
    public void iv_clear_account(View view) {
        et_input_account.setText("");
    }

    public void iv_clear_password(View view) {
        et_input_password.setText("");
    }

    //点击显示和隐藏密码
    public void iv_login_open_eye(View view) {
        if (isPwdVisible == false) {
            //显示密码方法一
            HideReturnsTransformationMethod method2 = HideReturnsTransformationMethod.getInstance();
            et_input_password.setTransformationMethod(method2);
            isPwdVisible = !isPwdVisible;
        } else {
            //隐藏密码方法一
            PasswordTransformationMethod method1 = PasswordTransformationMethod.getInstance();
            et_input_password.setTransformationMethod(method1);
            isPwdVisible = !isPwdVisible;
        }
        //切换后将EditText光标置于末尾
        et_input_password.setSelection(et_input_password.getText().toString().length());
    }

    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);//新建一个对话框
            dialog.setMessage("确定要退出吗?");//设置提示信息
            //设置确定按钮并监听
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);//结束程序
                }
            });
            //设置取消按钮并监听
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //这里什么也不用做
                }
            });
            dialog.show();//最后不要忘记把对话框显示出来
        }
        return false;
    }
}
