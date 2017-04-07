package palyshoping.example.com.shopingforplay.loginmvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import palyshoping.example.com.shopingforplay.R;
import palyshoping.example.com.shopingforplay.loginmvp.modle.ILoginModle;
import palyshoping.example.com.shopingforplay.loginmvp.presenter.LoginPresenter;
import palyshoping.example.com.shopingforplay.loginmvp.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @InjectView(R.id.login_count)
    EditText loginCount;
    @InjectView(R.id.login_password)
    EditText loginPassword;
    @InjectView(R.id.login_button)
    Button loginButton;
    private String name;
    private String password;
    private ProgressDialog dialog;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        dialog=new ProgressDialog(this);
        presenter=new LoginPresenter(this,new ILoginModle());
        presenter.detachView(this);
    }


    @Override
    public String getUserName() {
        return loginCount.getText().toString();
    }

    @Override
    public String getPassWord() {
        return loginPassword.getText().toString();
    }

    @Override
    public void showDialog() {
        if (dialog!=null)
        dialog.show();
    }

    @Override
    public void hideDialog() {
        if (dialog!=null)
            dialog.hide();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showResult(String result) {
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.login_button)
    public void onClick() {
        if (loginCount.getText().toString()!=null&&loginPassword.getText().toString()!=null){
            showDialog();
            presenter.login();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }

    }
}
