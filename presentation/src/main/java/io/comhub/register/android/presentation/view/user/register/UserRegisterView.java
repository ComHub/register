/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package io.comhub.register.android.presentation.view.user.register;

import android.support.annotation.NonNull;
import io.comhub.register.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user registration process
 */
interface UserRegisterView extends LoadDataView {

  void loadRegistrationUrl(@NonNull  String registrationUrl, String mimeType, String encoding);
}
