package io.comhub.register.android.presentation.view.base;

import android.app.DialogFragment;
import io.comhub.register.android.presentation.internal.di.HasComponent;

/**
 * Base {@link android.app.DialogFragment} class for every dialog fragment in this application.
 */
public class BaseDialogFragment extends DialogFragment {

  /**
   * Gets a component for dependency injection by its type.
   */
  @SuppressWarnings("unchecked")
  protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
  }
}
