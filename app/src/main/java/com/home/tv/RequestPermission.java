package com.home.tv;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RequestPermission
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/12 17:44
 * @Version 1.0
 */
public class RequestPermission {

    private static final String[] requestPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };


    public void RequestPermission(Activity activity) {
        String[] permissions;
        List<String> mPermissionList = new ArrayList<>();

        mPermissionList.clear();
        for (int i = 0; i < requestPermissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, requestPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(requestPermissions[i]);
            }
        }

        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了

        } else {//请求权限方法
            permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(activity, permissions, 1);
        }
    }


}
