package pop.hl.com.commonpopupwindow;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by hl on 2018/3/15.
 */

/**
 * 权限管理工具
 */
public class PermissionTool {
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_CAMERA = 2;
    private static String[] PERMISSIONS_ALL = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            //            Manifest.permission.ACCESS_FINE_LOCATION,
            //            Manifest.permission.CALL_PHONE,
            //            Manifest.permission.READ_LOGS,
            //            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            //            Manifest.permission.SET_DEBUG_APP,
            //            Manifest.permission.SYSTEM_ALERT_WINDOW,
            //            Manifest.permission.GET_ACCOUNTS,
            //            Manifest.permission.WRITE_APN_SETTINGS
            Manifest.permission.CAMERA
    };
    private static String[] PERMISSIONS_CAMERA = {
            Manifest.permission.CAMERA
    };

    /**
     * 动态申请权限(读写权限)
     *
     * @param context
     */
    public static void checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            ///< 检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                ///< 用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission
                                .WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(context, "请开通相关权限，否则有些功能无法正常使用！", Toast.LENGTH_SHORT).show();
                }
                ///< 申请权限
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        (Activity) context,
                        PERMISSIONS_ALL,
                        REQUEST_EXTERNAL_STORAGE
                );

            } else {
                //Toast.makeText(context, "授权成功！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 摄像头权限判断
     *
     * @param context
     */
    public static boolean checkPermissionCamera(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            ///< 检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        (Activity) context,
                        PERMISSIONS_CAMERA,
                        REQUEST_CAMERA
                );
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * 静音设置权限判断 - 不做通知栏通知了....
     *
     * @param context
     * @return
     */
    public static boolean checkPermissionAudio(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intentSound = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            context.startActivity(intentSound);
            return false;
        }
        return true;
    }

    /**
     * 通知权限申请
     * @param context
     */
    public static void requestNotify(Context context) {
        /**
         * 跳到通知栏设置界面
         * @param context
         */
        Intent localIntent = new Intent();
        ///< 直接跳转到应用通知设置的代码
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            localIntent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            localIntent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        }
        else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
            android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            localIntent.putExtra("app_package", context.getPackageName());
            localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.addCategory(Intent.CATEGORY_DEFAULT);
            localIntent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            ///< 4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        context.startActivity(localIntent);
    }
}
