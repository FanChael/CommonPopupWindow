package pop.hl.com.poplibrary.update;

import android.content.Context;

import java.io.File;
import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import pop.hl.com.poplibrary.utils.AppUtil;
import rx.Observer;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hl on 2018/4/1.
 */

public class UpdateManager {
    /**
     * 是否需要更新,需要则下载
     *
     * @param context     上下文
     * @param url         新版本地址
     * @param apkPath     本地apk保存路径
     * @param cd          订阅关系集合，在数据传输完毕时解除订阅
     */
    public static void downloadApk(final Context context, final String _fileProvider, final String url, final String apkPath, final CompositeDisposable cd, final ProgressListener progressListener) {
        NetWork.getInstance().down(url, progressListener)
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        try {
                            writeFile(responseBody.source(), new File(apkPath));
                        } catch (IOException e) {
                            return "IO:" + e.getMessage();
                        }
                        return apkPath;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        //unSubscribe(cd);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //unSubscribe(cd);
                        progressListener.onFinish(1, "error:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String o) {
                        if (o.contains("IO:")){
                            progressListener.onFinish(2, o);
                        }
                        else{
                            progressListener.onFinish(0, "completed");
                            ///< 下载完成，直接提示安装即可
                            AppUtil.installApp(context, _fileProvider, apkPath);
                        }
                    }
                });
    }


    /**
     * 写入文件
     * @param source - BufferedSource
     * @param file
     * @throws IOException
     */
    private static void writeFile(BufferedSource source, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file.exists())
            file.delete();

        BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
        bufferedSink.writeAll(source);

        bufferedSink.close();
        source.close();
    }

    /**
     * 解除订阅
     *
     * @param cd 订阅关系集合
     */
    private static void unSubscribe(CompositeDisposable cd) {
        if (cd != null && !cd.isDisposed())
            cd.dispose();
    }
}
