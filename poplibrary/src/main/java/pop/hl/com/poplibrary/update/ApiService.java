package pop.hl.com.poplibrary.update;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Author:hl
 * Time:  2018/5/24 11:29
 * Des:   This is ApiService
 */
public interface ApiService {
    @Streaming                                      ///< 注明为流文件，防止retrofit将大文件读入内存
    @GET()
    Observable<ResponseBody> down(@Url String url); ///< 通过@Url覆盖baseurl
    @Streaming                                      ///< 注明为流文件，防止retrofit将大文件读入内存
    @GET()
    Call<ResponseBody> downSync(@Url String url); ///< 通过@Url覆盖baseurl
}
