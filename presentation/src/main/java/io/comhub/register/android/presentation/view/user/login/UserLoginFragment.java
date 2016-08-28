package io.comhub.register.android.presentation.view.user.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.internal.di.components.UserComponent;
import io.comhub.register.android.presentation.model.UserModel;
import io.comhub.register.android.presentation.view.base.BaseFragment;
import javax.inject.Inject;

/**
 * Fragment that shows the login view
 */
public class UserLoginFragment extends BaseFragment implements UserLoginView {

  @Inject UserLoginPresenter userLoginPresenter;

  @Bind(R.id.et_email) EditText et_email;
  @Bind(R.id.et_password) EditText et_password;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  public UserLoginFragment() {
    setRetainInstance(true);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(UserComponent.class).inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_user_login, container, false);
    ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    this.initialize();
    this.setupUI();
  }

  private void initialize() {
    this.userLoginPresenter.setView(this);
    this.loadUserDetails();
  }

  private void setupUI() {
    this.et_email.setText("javier.tarazaga@comhub.io");
    this.et_password.setText("devtest");
  }

  @Override
  public void onResume() {
    super.onResume();
    this.userLoginPresenter.resume();
  }

  @Override
  public void onPause() {
    super.onPause();
    this.userLoginPresenter.pause();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    this.userLoginPresenter.destroy();
  }

  @Override
  public void renderUser(UserModel user) {
    if (user != null) {
      Toast.makeText(getActivity(), user.getFullName(), Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
  }

  @Override
  public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override
  public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override
  public Context context() {
    return getActivity().getApplicationContext();
  }

  @OnClick(R.id.bt_login)
  public void onLoginClicked(View view) {
    this.userLoginPresenter.performLogin(this.et_email.getText().toString(), this.et_password.getText().toString());
  }

  /**
   * Loads all users.
   */
  private void loadUserDetails() {
    if (this.userLoginPresenter != null) {
      this.userLoginPresenter.initialize();
    }
  }

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    UserLoginFragment.this.loadUserDetails();
  }
}
