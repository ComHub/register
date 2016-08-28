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
package io.comhub.register.android.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import io.comhub.register.android.presentation.view.activity.UserDetailsActivity;
import io.comhub.register.android.presentation.view.activity.UserListActivity;
import io.comhub.register.android.presentation.view.user.login.UserLoginActivity;
import io.comhub.register.android.presentation.view.user.register.UserRegisterActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
  }

  /**
   * Goes to the user login screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToUserLogin(Context context) {
    if (context != null) {
      Intent intentToLaunch = UserLoginActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  /**
   * Goes to the user registration screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToRegistration(Context context) {
    if (context != null) {
      Intent intentToLaunch = UserRegisterActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }


  /**
   * Goes to the user list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToUserList(Context context) {
    if (context != null) {
      Intent intentToLaunch = UserListActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  /**
   * Goes to the user details screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToUserDetails(Context context, String userId) {
    if (context != null) {
      Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId);
      context.startActivity(intentToLaunch);
    }
  }
}
