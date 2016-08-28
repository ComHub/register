/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package io.comhub.register.android.presentation.view.user.login;

import io.comhub.register.android.presentation.model.UserModel;
import io.comhub.register.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user login
 */
public interface UserLoginView extends LoadDataView {


  void renderUser(UserModel userModel);
}
