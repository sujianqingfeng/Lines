package com.sujian.lines.ui.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册页面 view
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter,RegisterModel> implements RegisterContract.View,Validator.ValidationListener {

    @Bind(R.id.fab_register)
    FloatingActionButton fab_register;
    @Bind(R.id.cv_add)
    CardView cv_add;
    @Order(1)
    @Length(min = 1,max = 8,message = "用户名1到8个字符")
    @Bind(R.id.et_username)
    EditText et_username;
    @Order(2)
    @Password(message = "密码无效 6个字符以上")
    @Bind(R.id.et_password)
    EditText et_password;
    @Order(3)
    @ConfirmPassword(message = "密码不一致")
    @Bind(R.id.et_repeatpassword)
    EditText et_repeatpassword;

    @Bind(R.id.bt_go)
    Button bt_go;
    @Bind(R.id.rl_root)
    RelativeLayout rl_root;

    private Validator validator;


    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }

        rl_root.setBackgroundResource(R.mipmap.log_bg);
        validator=new Validator(this);
        validator.setValidationListener(this);
    }


    //点击事件
    @OnClick({R.id.fab_register,R.id.bt_go})
    public void fab(View view){
        switch (view.getId()){
            case R.id.fab_register:
                animateRevealClose();
                break;
            case R.id.bt_go:
                validator.validate();
                break;
        }

    }

    //展示进入动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cv_add.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }


    //波纹扩展
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cv_add, cv_add.getWidth()/2,0, fab_register.getWidth() / 2, cv_add.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cv_add.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    //波纹收缩
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cv_add,cv_add.getWidth()/2,0, cv_add.getHeight(), fab_register.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cv_add.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab_register.setImageResource(R.mipmap.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    @Override
    public void signSuccess() {
        finish();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }
    //验证成功进行注册
    @Override
    public void onValidationSucceeded() {
        String user = et_username.getText().toString().trim();
        String passwosrd = et_password.getText().toString().trim();
        mPresenter.sign(user,passwosrd);
    }

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
