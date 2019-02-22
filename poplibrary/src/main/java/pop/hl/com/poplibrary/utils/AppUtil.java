package pop.hl.com.poplibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/*
*@Description: APP工具类
*@Author: hl
*@Time: 2019/2/22 12:01
*/
public class AppUtil {
    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "我很酷";
    }

    /**
     * 应用安装
     * @param context
     * @param fileProvider
     * @param apkPath
     */
    public static void installApp(Context context, String fileProvider, String apkPath){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        ///< 判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, fileProvider, new File(apkPath));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /*
     * 採用了新的办法获取VersionCode。之前的失败是由于android中存在的一个BUG,通过
     * appInfo.publicSourceDir = apkPath;来修正这个问题，详情參见:
     * http://code.google.com/p/android/issues/detail?id=9151
     */
    public static int getApkVersionCode(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            try {
                return info.versionCode;
            } catch (OutOfMemoryError e) {
                return -1;
            }
        }
        return -1;
    }
}
