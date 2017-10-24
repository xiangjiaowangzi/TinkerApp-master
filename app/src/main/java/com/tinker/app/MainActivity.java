package com.tinker.app;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mPathText, mPatchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.loadPatch).setOnClickListener(this);
        findViewById(R.id.cleanPatch).setOnClickListener(this);
        findViewById(R.id.killSelf).setOnClickListener(this);
        mPathText = (TextView) findViewById(R.id.tinkerPath);
        mPatchText = (TextView) findViewById(R.id.patchText);
        /**
         * 第一次运行安装之后，在将这里注释部分取消，调用tinkerPatchDebug命令生成补丁文件。
         * 将补丁文件patch_signed_7zip.apk复制到手机指定地址加载补丁可看到实际效果。
         */
//        mPathText.setText(Html.fromHtml(getTitleStr()));
//        mPatchText.setText("直接退出行不行啊");
        iniFile();
    }

    void iniFile(){
        Log.e("aaa" , " s 2222222222");
        String s = Environment.getExternalStorageDirectory().getAbsolutePath() ;
        Log.e("aaa" , " s " + s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loadPatch:// 加载本地补丁，Tinker还提供了其他集中加载方式，更多请浏览Tinker官方文档
                String patch = Environment.getExternalStorageDirectory()+File.separator+"tinker" + "/patch_signed_7zip.apk" ;
                File file = new File(patch);
                if (file!=null && file.exists()){

                }else{
                    Log.e("aaa" , " patch " + patch);
                }
                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patch);
                break;
            case R.id.cleanPatch:// 清除，卸载补丁
                Tinker.with(getApplicationContext()).cleanPatch();
                break;
            case R.id.killSelf:// 退出
                ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }

    private String getTitleStr(){
        return "<font size=\"4\" color=\"#000000\">Thinker:</font><font size=\"4\" color=\"#3F51B5\">https://github.com/Tencent/tinker</font>";
    }
}
