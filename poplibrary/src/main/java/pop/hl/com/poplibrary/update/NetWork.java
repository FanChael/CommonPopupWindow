package pop.hl.com.poplibrary.update;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by hl on 2018/4/1.
 */

public class NetWork {

    private static NetWork netWork = null;

    public static NetWork getInstance() {
        if (null == netWork){
            netWork = new NetWork();
        }
        return netWork;
    }

    /**
     * 下载文件 - 超时10秒
     * @param url
     * @param progressListener
     * @return
     */
    public Observable<ResponseBody> down(String url, final ProgressListener progressListener) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());//对结果重新处理
                        return originalResponse
                                .newBuilder()
                                .body(new FileResponseBody(originalResponse, progressListener))//将自定义的ResposeBody设置给它
                                .build();
                    }
                })
                .build();

        String baseUrl = url.substring(0, url.indexOf(":") + 1) + "//" +  url.split("/")[2] + "/" ; ///< new URL(url).getHost()
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        return api.down(url);
    }

    /**
     * 下载文件 - 超时10秒
     * @param url
     * @param progressListener
     * @return
     */
    public Call<ResponseBody> downSync(String url, final ProgressListener progressListener) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());//对结果重新处理
                        return originalResponse
                                .newBuilder()
                                .body(new FileResponseBody(originalResponse, progressListener))//将自定义的ResposeBody设置给它
                                .build();
                    }
                })
                .build();

        String baseUrl = url.substring(0, url.indexOf(":") + 1) + "//" +  url.split("/")[2] + "/" ; ///< new URL(url).getHost()
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        return api.downSync(url);
    }
}
