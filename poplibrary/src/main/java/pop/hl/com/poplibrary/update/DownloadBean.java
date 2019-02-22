package pop.hl.com.poplibrary.update;

/**
 * Author:hl
 * Time:  2018/4/1 11:29
 * Des:   This is DownloadBean
 */
public class DownloadBean {
    private long contentLength;
    private long downBytes;
    private int status;
    private String message;

    public DownloadBean(long contentLength, long downBytes, int status, String message) {
        this.contentLength = contentLength;
        this.downBytes = downBytes;
        this.status = status;
        this.message = message;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getDownBytes() {
        return downBytes;
    }

    public void setDownBytes(long downBytes) {
        this.downBytes = downBytes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
