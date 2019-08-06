package pop.hl.com.poplibrary.update;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * 下载文件
 * Author:hl
 * Time:  2018/5/24 11:31
 * Des:   This is FileResponseBody
 */
public class FileResponseBody extends ResponseBody {

    private Response originalResponse;//原结果
    private ProgressListener progressListener;

    public FileResponseBody(Response originalResponse) {
        this.originalResponse = originalResponse;
    }

    public FileResponseBody(Response responseBody, ProgressListener progressListener) {
        this.originalResponse = responseBody;
        this.progressListener = progressListener;
    }

    /**
     * 返回内容类型
     * @return
     */
    @Override
    public MediaType contentType() {
        return originalResponse.body().contentType();
    }

    /**
     * 返回内容长度，没有则返回-1
     * @return
     */
    @Override
    public long contentLength() {
        return originalResponse.body().contentLength();
    }

    /**
     * 返回缓存源，类似于io中的BufferedReader
     * @return
     */
    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
            long bytesReaded = 0;
            //返回读取到的长度
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                ///< 进度回调
                progressListener.onProgress(bytesReaded, contentLength(), bytesRead == -1);
                return bytesRead;
            }
        });
    }
}
