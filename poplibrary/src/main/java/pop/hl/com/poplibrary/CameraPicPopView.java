package pop.hl.com.poplibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import pop.hl.com.poplibrary.base.BasePop;

/*
 *@Description: 打开相机/相册弹窗 + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/15 11:37
 */
public class CameraPicPopView {
    /**
     * 点击事件回调类型
     */
    public enum CALLBACK_TYPE {
        PHOTO, CAMERA
    }

    /**
     * 相册/相机选择
     *
     * @param _context
     * @param _achor
     * @param _funtionbgColor         - 功能区背景颜色
     * @param _funtionTextColor       - 功能选择字体颜色
     * @param _cancelBgColor          - 取消按钮背景颜色
     * @param _cancelTextColor        - 取消按钮字体颜色
     * @param _onCameraClickListenner
     * @return
     */
    public static BasePop.Builder showCamera(Context _context, View _achor,
                                             String _funtionbgColor, String _funtionTextColor,
                                             String _cancelBgColor, String _cancelTextColor,
                                             OnEventListenner.OnCameraClickListenner _onCameraClickListenner) {
        return new Builder(_context)
                .create(_achor, _funtionbgColor, _funtionTextColor, _cancelBgColor, _cancelTextColor)
                .showCamera(_onCameraClickListenner);
    }

    /*
     *@Description: 打开相机/相册弹窗建造器
     *@Author: hl
     *@Time: 2019/2/15 16:04
     */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private String funtionbgColor, funtionTextColor, cancelBgColor, cancelTextColor;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        /**
         * 创建视图弹窗
         *
         * @param _anchor
         * @return
         */
        public CameraPicPopView.Builder create(View _anchor,
                                               String _funtionbgColor, String _funtionTextColor,
                                               String _cancelBgColor, String _cancelTextColor) {
            this.funtionbgColor = _funtionbgColor;
            this.funtionTextColor = _funtionTextColor;
            this.cancelBgColor = _cancelBgColor;
            this.cancelTextColor = _cancelTextColor;
            ///< 创建弹窗视图
            builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(R.layout.pop_camerapic)
                    .setAnimation(BasePopView.ANIMATION.TRANSLATE)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return this;
        }

        public BasePop.Builder showCamera(final OnEventListenner.OnCameraClickListenner _onCameraClickListenner) {
            ///< 获取弹窗视图
            View popView = builder.getView();
            ///< 获取视图控件
            LinearLayout functionRoot = popView.findViewById(R.id.pcp_functionRoot);
            TextView screenshot = popView.findViewById(R.id.image_screenshot);
            TextView camerar = popView.findViewById(R.id.image_camerar);
            Button cancel_btn = popView.findViewById(R.id.image_cancel_btn);

            if (null != funtionbgColor && !funtionbgColor.equals("")){
                functionRoot.setBackgroundColor(Color.parseColor(funtionbgColor));
            }
            if (null != cancelBgColor && !cancelBgColor.equals("")){
                cancel_btn.setBackgroundColor(Color.parseColor(cancelBgColor));
            }
            if (null != funtionTextColor && !funtionTextColor.equals("")){
                screenshot.setTextColor(Color.parseColor(funtionTextColor));
                camerar.setTextColor(Color.parseColor(funtionTextColor));
            }
            if (null != cancelTextColor && !cancelTextColor.equals("")){
                cancel_btn.setTextColor(Color.parseColor(cancelTextColor));
            }

            screenshot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != _onCameraClickListenner){
                        _onCameraClickListenner.onClick(v, CALLBACK_TYPE.PHOTO);
                    }
                }
            });

            camerar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != _onCameraClickListenner){
                        _onCameraClickListenner.onClick(v, CALLBACK_TYPE.CAMERA);
                    }
                }
            });

            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dissmiss();
                }
            });

            ///< 显示弹窗
            return builder.show(BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM);
        }
    }
}
