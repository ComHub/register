package io.comhub.register.android.presentation.view.home.product;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.internal.di.components.HomeComponent;
import io.comhub.register.android.presentation.view.base.BaseFragment;
import javax.inject.Inject;

/**
 * Fragment that shows the grid of products
 */
public class ProductGridFragment extends BaseFragment implements ProductGridView {

  @Inject ProductGridPresenter productGridPresenter;

  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  public ProductGridFragment() {
    setRetainInstance(true);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(HomeComponent.class).inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_product_grid, container, false);
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
    this.productGridPresenter.setView(this);
  }

  private void setupUI() {
  }

  @Override
  public void onResume() {
    super.onResume();
    this.productGridPresenter.resume();
  }

  @Override
  public void onPause() {
    super.onPause();
    this.productGridPresenter.pause();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    this.productGridPresenter.destroy();
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

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
  }
}
