package com.ivaangb.cucgram.login.interactor;

import com.ivaangb.cucgram.login.presenter.LoginPresenter;
import com.ivaangb.cucgram.login.repository.LoginRepository;
import com.ivaangb.cucgram.login.repository.LoginRespositoryImpl;

/**
 * Created by ivang on 5/6/2018.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter)
    {
        this.presenter = presenter;
        repository = new LoginRespositoryImpl(presenter);
    }

    @Override
    public void sigIn(String username, String password) {
        repository.signIn(username, password);
    }
}
