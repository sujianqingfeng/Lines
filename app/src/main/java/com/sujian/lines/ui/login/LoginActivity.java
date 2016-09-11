package com.sujian.lines.ui.login;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.NetWorkUtil;
import com.sujian.lines.base.util.ToastUtil;
import com.sujian.lines.ui.home.HomeActivity;
import com.sujian.lines.ui.register.RegisterActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登陆页面view
 */
public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginContract.View,Validator.ValidationListener {

    @Bind(R.id.fab_login)
    FloatingActionButton fab_login;
    @Order(1)
    @Length(min = 1,max = 8,message = "用户名1到8个字符")
    @Bind(R.id.et_username)
    EditText et_username;
    @Order(2)
    @Password(message = "密码无效 6个字符以上")
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.bt_go)
    Button bt_go;

    private Validator validator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        validator=new Validator(this);
        validator.setValidationListener(this);
    }

    //注册按钮点击事件
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.fab_login)
    public void fab(View v){
        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this, fab_login, fab_login.getTransitionName());
            startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    @OnClick(R.id.bt_go)
    public void login(View view){
        validator.validate();
    }

    //登陆成功
    @Override
    public void loginSuccess() {
        startActivity(new Intent(mContext, HomeActivity.class));
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }

    //验证成功后就开始登录操作
    @Override
    public void onValidationSucceeded() {
        if(NetWorkUtil.isNetConnected(mContext)){
            String user=et_username.getText().toString().trim();
            String password=et_password.getText().toString().trim();
            Log.e("用户与密码",user+"---------"+password);
            mPresenter.login(user,password);
        }else {
            showMsg("没有网络 骚年");
        }

    }
    //验证失败提示
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
               showMsg(message);
            }
        }
    }
}
