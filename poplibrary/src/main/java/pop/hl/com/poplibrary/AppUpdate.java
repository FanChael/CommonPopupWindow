package pop.hl.com.poplibrary;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.update.DownLoadIntentService;
import pop.hl.com.poplibrary.update.DownloadBean;
import pop.hl.com.poplibrary.utils.AppUtil;
import pop.hl.com.poplibrary.utils.FileHelper;
import pop.hl.com.poplibrary.utils.MD5Util;
import pop.hl.com.poplibrary.utils.SystemUtils;

/*
 *@Description: APP更新
 *@Author: hl
 *@Time: 2019/2/22 15:10
 */
public class AppUpdate {
    private Context context;
    private BasePop.Builder builder = null;
    private AlertDialog alertDialog = null;
    private View achor;
    private int titleBgId;
    private float h_dived_w;
    private String allColor;
    private boolean bforce;
    private String title;
    private String updateMessage;

    private TextView textView = null;
    private ProgressBar progress = null;

    /**
     * 创建更新弹窗参数
     *
     * @param _context
     * @param _achor
     * @param _titleBgId     - 弹窗抬头图片资源
     * @param _h_dived_w     - 弹窗抬头图片资源h/w
     * @param _allColor      - 弹窗主体颜色
     * @param _bforce        - 是否强制更新
     * @param _updateMessage - 更新信息，多行换行
     */
    public AppUpdate(Context _context, View _achor,
                     int _titleBgId, float _h_dived_w,
                     String _allColor, boolean _bforce,
                     String _updateMessage) {
        this.context = _context;
        this.achor = _achor;
        this.titleBgId = _titleBgId;
        this.h_dived_w = _h_dived_w;
        this.allColor = _allColor;
        this.bforce = _bforce;
        this.updateMessage = _updateMessage;
        ///< 消息订阅
        EventBus.getDefault().register(AppUpdate.this);
    }

    /**
     * 创建原生更新弹窗参数
     *
     * @param _context
     * @param _achor
     * @param _allColor      - 弹窗主体颜色
     * @param _bforce        - 是否强制更新
     * @param _title         - 标题
     * @param _updateMessage - 更新信息，多行换行
     */
    public AppUpdate(Context _context, View _achor,
                     String _allColor, boolean _bforce,
                     String _title, String _updateMessage) {
        this.context = _context;
        this.achor = _achor;
        this.allColor = _allColor;
        this.bforce = _bforce;
        this.title = _title;
        this.updateMessage = _updateMessage;
        ///< 消息订阅
        EventBus.getDefault().register(AppUpdate.this);
    }

    /**
     * 启动更新App
     *
     * @param _appUrl       - apk下载地址
     * @param _notifyIcon   - 标题栏通知图标
     * @param _fileProvider - 应用的fileProvider(下载和启动安装使用)
     * @param _md5          - apk包的md5 - 防止重复下载
     * @param _versionCode  apk包的_versionCode - 防止重复下载
     * @param _apkSize      apk包的大小 - 防止重复下载
     */
    public void startAppUpdate(final String _appUrl, final int _notifyIcon,
                               final String _fileProvider,
                               final String _md5, final int _versionCode, final long _apkSize) {
        builder = UpdatePopView.showNormalUpdate(context, achor,
                titleBgId, h_dived_w,
                allColor, bforce,
                updateMessage,
                new OnEventListenner.OnUpdateClickListenner() {
                    @Override
                    public void onClick(View _view, View _progress) {
                        startDownload(_view, _progress, _appUrl,
                                _notifyIcon, _fileProvider, _md5, _versionCode, _apkSize);
                    }
                });
        builder.setOnDissmiss(new OnEventListenner.OnBaseListenner() {
            @Override
            public void onDissmiss() {
                ///< 取消订阅消息
                if (EventBus.getDefault().isRegistered(AppUpdate.this)) {
                    EventBus.getDefault().unregister(AppUpdate.this);
                }
            }
        });
    }

    /**
     * 启动原生(警告)a类弹窗更新App
     *
     * @param _appUrl       - apk下载地址
     * @param _fileProvider - 应用的fileProvider(下载和启动安装使用)
     * @param _md5          - apk包的md5 - 防止重复下载
     * @param _versionCode  apk包的_versionCode - 防止重复下载
     * @param _apkSize      apk包的大小 - 防止重复下载
     */
    public void startOriginaAppUpdate(final String _appUrl, final int _notifyIcon,
                                      final String _fileProvider,
                                      final String _md5, final int _versionCode, final long _apkSize) {
        alertDialog = UpdatePopView.showOriginAlertUpdate(context, achor,
                allColor, bforce,
                title, updateMessage,
                new OnEventListenner.OnUpdateClickListenner() {
                    @Override
                    public void onClick(View _view, View _progress) {
                        startDownload(_view, _progress, _appUrl,
                                _notifyIcon, _fileProvider, _md5, _versionCode, _apkSize);
                    }
                });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ///< 取消订阅消息
                if (EventBus.getDefault().isRegistered(AppUpdate.this)) {
                    EventBus.getDefault().unregister(AppUpdate.this);
                }
            }
        });
    }

    /**
     * 启动安装 - @local
     *
     * @param _view
     * @param _progress
     * @param _appUrl
     * @param _notifyIcon
     * @param _fileProvider
     * @param _md5
     * @param _versionCode
     * @param _apkSize
     */
    private void startDownload(View _view, View _progress,
                               String _appUrl, int _notifyIcon,
                               String _fileProvider,
                               String _md5, int _versionCode, long _apkSize) {
        ///< 保存控件
        if (null != _view) textView = (TextView) _view;
        if (null != _progress) progress = (ProgressBar) _progress;
        ///< 防止再次点击
        if (null != _view) textView.setEnabled(false);

        String url = _appUrl;
        String apkPath = SystemUtils.getCacheDirectory(context,
                Environment.DIRECTORY_DOWNLOADS).getPath() + "/" +
                url.substring(url.lastIndexOf("/") + 1);

        ///< 校验本地安装包，如果不需要下载，就直接安装
        if (null != _md5 && !_md5.equals("") && _versionCode > 0 && _apkSize > 0) {
            if (FileHelper.getFileOrFilesSize(apkPath, FileHelper.SIZETYPE_B) == _apkSize) {
                try {
                    String md1 = MD5Util.getApkSignatureMD5New(context, apkPath).toUpperCase();
                    if (md1.equals(_md5) && (AppUtil.getApkVersionCode(context, apkPath) == _versionCode)) {
                        ///< 本地存在直接启动安装
                        if (null != builder) builder.dissmiss();
                        if (bforce) {
                            if (context instanceof Activity) {
                                ((Activity) context).finish();
                            }
                        }
                        AppUtil.installApp(context, _fileProvider, apkPath);
                        return;
                    }
                } catch (Exception e) {

                }
            }
        }

        DownLoadIntentService.startUpdateService(
                context, _fileProvider,
                _notifyIcon, url, apkPath);

        ///< 非强制更新，点击更新后弹窗消失
        if (!bforce) {
            if (null != builder) builder.dissmiss();
        }
    }

    /**
     * 更新通知接收
     *
     * @param downloadBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEventUpdate(DownloadBean downloadBean) {
        ///< 强制更新的情况下才处理进度条
        if (bforce) {
            ///< 更新进度
            if (-1 == downloadBean.getStatus()) {
                int progresss = (int) Math.round(downloadBean.getDownBytes() / (double) downloadBean.getContentLength() * 100);
                if (null != textView) textView.setText(progresss + "%");
                if (null != progress) progress.setProgress(progresss);
            } else if (0 == downloadBean.getStatus()) {   ///< 下载完成
                if (null != builder) builder.dissmiss();         ///< 用的是自定义更新弹窗
                if (null != alertDialog) alertDialog.dismiss(); ///< 用的是原生更新弹窗
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
            ///< 1表示异常错误 2-读写错误
            else if (1 == downloadBean.getStatus() ||
                    2 == downloadBean.getStatus()) {
                if (null != textView) textView.setEnabled(true);
                if (null != textView) textView.setText("点击重试");
                if (null != progress) progress.setProgress(0);
            }
        }
    }
}
