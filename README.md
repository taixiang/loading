> 原文链接：[https://mp.weixin.qq.com/s/wBbQgOfr59wntNK9ZJ5iRw](https://mp.weixin.qq.com/s/wBbQgOfr59wntNK9ZJ5iRw)

项目中经常会用到加载数据的loading显示图，除了设计根据app自身设计的动画loading，一般用的比较多的是仿照ios 的菊花加载loading 图，当然一些条件下还会涉及到加载成功/ 失败情况的显示，还有显示文字。   
![](https://user-gold-cdn.xitu.io/2018/9/15/165dc137a27278e4?w=136&h=131&f=png&s=3276)
![](https://user-gold-cdn.xitu.io/2018/9/15/165dc13e162232f8?w=150&h=137&f=png&s=3405)
![](https://user-gold-cdn.xitu.io/2018/9/15/165dc1403988c781?w=141&h=136&f=png&s=3594)

使用ProgressBar 来加载动画转圈，这里使用drawable文件 定义转圈动画，`indeterminateDrawable`属性进行加载。
```
<?xml version="1.0" encoding="utf-8"?>
<animated-rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@mipmap/load"
    android:pivotX="50%"
    android:pivotY="50%" />
    
<ProgressBar
    android:id="@+id/progressBar"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:indeterminateDrawable="@drawable/anim" />
```

部分情况下，在加载成功/ 失败之后会显示对应的静态图片，所以一开始想直接通过`setIndeterminateDrawable(Drawable d)` 来加载静态图片，但是直接写是显示不出图片的，还要设置Drawable 的位置 `d.setBounds(Rect bounds)`，即使这样加载出了静态图片，但是设置`R.drawable.anim` 的转圈动画时 却没有了转圈的效果，好气哟 ~~  

所以在自定义view 的布局里 成功/失败的状态单独用`imageView`显示
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="110dp"
    android:layout_height="110dp"
    android:background="@drawable/shape_dialog_bg"
    android:gravity="center"
    android:orientation="vertical">

    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:indeterminateDrawable="@drawable/anim" />

    <ImageView
        android:id="@+id/iv"
        android:visibility="gone"
        android:layout_width="50dp"
        android:layout_height="50dp" />

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:text="正在加载"
        android:textColor="#fff" />

</LinearLayout>
```
自定义view，提供三种状态的方法。
```
public class LoadingView extends LinearLayout {

    ...构造函数...
    
    /**
     * loading
     */
    public void showLoading() {
        iv.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    /**
     * 成功
     */
    public void showSuccess() {
        iv.setImageResource(R.mipmap.load_success);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
    }

    /**
     *失败
     */
    public void showFail() {
        iv.setImageResource(R.mipmap.load_fail);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
    }

    /**
     * 提示文字
     *
     * @param txt string
     */
    public void setText(String txt) {
        tv.setText(txt);
    }

    /**
     * 提示文字
     */
    public void setText(@StringRes int txtId) {
        tv.setText(txtId);
    }
    
}
```

效果图：  
![](https://user-gold-cdn.xitu.io/2018/9/15/165dc18f1601c172?w=364&h=410&f=gif&s=2002026)


github地址：[https://github.com/taixiang/loading](https://github.com/taixiang/loading)

欢迎关注我的个人博客：[https://www.manjiexiang.cn/](https://www.manjiexiang.cn/)  

更多精彩欢迎关注微信号：春风十里不如认识你  
一起学习，一起进步，有问题随时联系，一起解决！！！

![](https://user-gold-cdn.xitu.io/2018/8/12/1652cd77eaebeb98?w=900&h=540&f=jpeg&s=64949)    
