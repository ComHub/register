/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.comhub.register.android.presentation.view.user.register;

import android.support.annotation.NonNull;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.comhub.register.android.domain.exception.ErrorBundle;
import io.comhub.register.android.presentation.exception.ErrorMessageFactory;
import io.comhub.register.android.presentation.internal.di.PerActivity;
import io.comhub.register.android.presentation.presenter.Presenter;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
class UserRegisterPresenter implements Presenter {

  private UserRegisterView viewRegister;

  @Inject
  public UserRegisterPresenter() {
  }

  public void setView(@NonNull UserRegisterView view) {
    this.viewRegister = view;
  }

  @Override
  public void resume() {}

  @Override
  public void pause() {}

  @Override
  public void destroy() {
    this.viewRegister = null;
  }

  /**
   * Initializes the presenter
   */
  void initialize() {
    this.initWebView();
    this.loadRegistrationUrl();
  }

  void onButtonRetryClicked() {
    this.loadRegistrationUrl();
  }

  private void initWebView() {
    this.viewRegister.getWebView().setWebViewClient(new WebViewClient() {

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        UserRegisterPresenter.this.hideViewLoading();
      }

      @Override
      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
      }
    });
  }

  private void loadRegistrationUrl() {
    this.showViewLoading();
    this.viewRegister.loadRegistrationUrl("http://www.google.com");
  }

  private void showViewLoading() {
    this.viewRegister.showLoading();
  }

  private void hideViewLoading() {
    this.viewRegister.hideLoading();
  }

  private void showViewRetry() {
    this.viewRegister.showRetry();
  }

  private void hideViewRetry() {
    this.viewRegister.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewRegister.context(),
                                                     errorBundle.getException());
    this.viewRegister.showError(errorMessage);
  }
}
