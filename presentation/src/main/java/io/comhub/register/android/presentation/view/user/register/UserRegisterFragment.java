package io.comhub.register.android.presentation.view.user.register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.internal.di.components.UserComponent;
import io.comhub.register.android.presentation.view.fragment.BaseFragment;
import javax.inject.Inject;

/**
 * Fragment that shows the login view
 */
public class UserRegisterFragment extends BaseFragment implements UserRegisterView {

  @Inject UserRegisterPresenter userRegisterPresenter;

  @Bind(R.id.wv_user_register) WebView wv_register;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  public UserRegisterFragment() {
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
    this.userRegisterPresenter.setView(this);
    this.userRegisterPresenter.initialize();
  }
  
  private void setupUI() {

  }

  @Override
  public void onResume() {
    super.onResume();
    this.userRegisterPresenter.resume();
  }

  @Override
  public void onPause() {
    super.onPause();
    this.userRegisterPresenter.pause();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    this.userRegisterPresenter.destroy();
  }

  @Override
  public void loadRegistrationUrl(@NonNull String registrationUrl, String mimeType, String encoding) {
    this.wv_register.loadData(registrationUrl, mimeType, encoding);
  }

  @Override
  public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override
  public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
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

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    UserRegisterFragment.this.userRegisterPresenter.onButtonRetryClicked();
  }
}
