package io.comhub.register.android.data.user.repository.datasource;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import io.comhub.register.android.data.user.entity.UserEntity;
import io.comhub.register.android.data.user.entity.mapper.UserEntityFirebaseMapper;
import io.comhub.register.android.data.user.exception.WrongCredentailsException;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * {@link UserDataStore} implementation based on connections to Firebase
 */
public class FirebaseUserDataStore implements UserDataStore {

  private final UserEntityFirebaseMapper mapper;
  private FirebaseAuth fbAuth;

  public FirebaseUserDataStore(UserEntityFirebaseMapper mapper) {
    this.mapper = mapper;
    this.fbAuth = FirebaseAuth.getInstance();
  }

  @Override
  public Observable<List<UserEntity>> userEntityList() {
    return null;
  }

  @Override
  public Observable<UserEntity> userEntity(String userEmail, String password) {
    return signInWithEmailAndPassword(userEmail, password)
      .map(new Func1<AuthResult, UserEntity>() {
        @Override
        public UserEntity call(AuthResult authResult) {
          return mapper.transformUserEntity(authResult.getUser());
        }
      });
  }

  private Observable<AuthResult> signInWithEmailAndPassword(final String userName, final String password) {
    return Observable.create(new Observable.OnSubscribe<AuthResult>() {
      @Override
      public void call(Subscriber<? super AuthResult> subscriber) {
        fbAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(
          new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()) {
                subscriber.onNext(task.getResult());
              } else {
                subscriber.onError(new WrongCredentailsException());
              }
            }
          });
      }
    });
  }

  @Override
  public Observable<UserEntity> userEntityDetails(String userId) {
    return null;
  }
}
