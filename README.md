# RecyclerViewDemo

这是一个RecyclerView的教程项目

# 使用教程

## 1.准备工作

在 module 的 build.gradle 中 加入 `compile 'com.android.support:recyclerview-v7:23.3.0'` 代码:

```
dependencies {
    ...
    compile 'com.android.support:recyclerview-v7:23.3.0'//1.1
    ...
}
```

## 2.添加RecyclerView

### 2.1.在xml中添加RecyclerView

activity_linear_layout_manager.xml

```
<RelativeLayout
    ...>
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:listitem="@layout/item_linear"/>
</RelativeLayout>

```

注:`tools:listitem="@layout/item_linear"`是用于在Android Studio的Preview中查看效果用的

### 2.2.在java中添加RecyclerView

#### 2.3.绑定

`mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));`

