package pop.hl.com.poplibrary.update;

/**
 * Created by hl on 2018/4/1.
 */

public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);

    /**
     * 结束状态
     * @param status -1 -> 发送中 0 -> 成功   1 -> 发送错误/失败  2 -> 读写异常
     * @param msg
     */
    void onFinish(int status, String msg);
}
