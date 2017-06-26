package com.fileintersend.view.activity.FileInterSend;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fileintersend.view.service.FileInterSendService;
import com.github.B.BaseActivity;
import com.github.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.github.VP.Constants.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE;

/**
 * Created by axnshy on 2017/6/24.
 */

public class FileInterSendActivity extends BaseActivity<IInterSend, FileInterSendPresenter> implements IInterSend {

    private static final String TAG = "FileInterSendActivity";
    @BindView(R.id.btn_send)
    Button sendBtn;
    @BindView(R.id.btn_accept)
    Button acceptBtn;
    @BindView(R.id.btn_select_file)
    Button selectFile;

    @BindView(R.id.tv_file_path)
    TextView filepathTv;

    FileInterSendService mService;



    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((FileInterSendService.MyBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };


    @OnClick({R.id.btn_send,R.id.btn_accept,R.id.btn_select_file,R.id.tv_file_path})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_send:
                mService.sendContentUri(filepathTv.getText().toString());
                break;
            case R.id.btn_select_file:


                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                } else {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent,1);
                }

                break;


        }
    }


    @Override
    public FileInterSendPresenter createPresenter() {
        return new FileInterSendPresenter();
    }


    @Override
    public int layoutId() {
        return R.layout.activity_file_intersend;
    }

    @Override
    public void initData() {

        Intent intent = new Intent();

        intent.setClass(this, FileInterSendService.class);

        startService(intent);

        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Uri uri = data.getData();
            Log.d(TAG,"content uri = "+uri.toString());
            filepathTv.setText(uri.toString());


        }

    }



}
