package io.comhub.register.android.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.view.base.BaseActivity;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  @Bind(R.id.btn_LoadData) Button btn_LoadData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  /**
   * Goes to the user list screen.
   */
  @OnClick(R.id.btn_LoadData)
  void navigateToUserList() {
    this.navigator.navigateToUserList(this);
  }

  /**
   * Goes to the user login screen.
   */
  @OnClick(R.id.btn_userLogin)
  void navigateToUserLogin() {
    this.navigator.navigateToUserLogin(this);
  }

  /**
   * Goes to the user registration screen.
   */
  @OnClick(R.id.btn_userRegistration)
  void navigateToUserRegistration() {
    this.navigator.navigateToRegistration(this);
  }
}
