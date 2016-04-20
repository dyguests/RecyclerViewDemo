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

LinearLayoutManagerActivity.java

#### 2.2.1.绑定

`mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));`

#### 2.2.2.添加`LayoutManager`(布局方式)

```
mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL/*方向*/,
                false/*是否反向*/
        ));
```

注:这里用`LinearLayoutManager`表示这是使用线性布局,也可以使用`GridLayoutManager`等.

#### 2.2.3.绑定Adapter(参考 2.3.创建Adapter)

```
mAdapter = new LinearLayoutManagerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
```

#### 2.2.4.添加数据

```
...
mAdapter.addItem(user);
...
```
也可以使用
```
...
mAdapter.addItems(users);
...
```

注:`addItem()`,`addItems()`方法是自定义的,主要是为了把数据添加操作和动画放在一起.

### 2.3.创建Adapter

#### 2.3.1.创建`LinearLayoutManagerAdapter`类

```
public class LinearLayoutManagerAdapter{
    
}
```

#### 2.3.2.让类继承`RecyclerView.Adapter`

```
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter{
    
}
```

#### 2.3.3.注意,`RecyclerView.Adapter`是泛型类,需要一个继承至`RecyclerView.ViewHolder`的泛型参数(?),这里我们创建一个`ViewHolder`的内部类:

```
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter{
    
    public class ViewHolder extends RecyclerView.ViewHolder{
        
    }
}
```

#### 2.3.4.添加泛型声明

```
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter<LinearLayoutManagerAdapter.ViewHolder>{
    
    public class ViewHolder extends RecyclerView.ViewHolder{
        
    }
}
```

#### 2.3.5.实现方法

```
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter<LinearLayoutManagerAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
```

#### 2.3.6.添加构造器

```
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter<LinearLayoutManagerAdapter.ViewHolder>{

    private final Context context;

    public LinearLayoutManagerAdapter(Context context) {
        this.context = context;
    }

    ...
}
```

#### 2.3.7.指定`item_view`,注意这里与ListView不同,这里不返回View,而是返回ViewHolder(自已定义的).

```
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_linear, parent, false);
        return new ViewHolder(view);
    }
```

#### 2.3.8.添加数据源

```
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter<LinearLayoutManagerAdapter.ViewHolder>{
    ...
    private final ArrayList<User> list;


    public LinearLayoutManagerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    ...
    @Override
    public int getItemCount() {
        return list.size();
    }
    ...
}
```



#### 2.3.9.实现`list_item`与数据的绑定

```
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter<LinearLayoutManagerAdapter.ViewHolder>{
    ...
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }
    ...
    public class ViewHolder extends RecyclerView.ViewHolder{
        ...
        public void bind(User data) {
        }
    }
}
```

#### 2.3.10.在`ViewHolder`中实现布局元素的绑定与数据的绑定

```
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;
        private       User     data;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = ((TextView) itemView.findViewById(R.id.text_view));
        }

        public void bind(User data) {
            mTextView.setText(data.getName());

            this.data = data;
        }
    }
```

这里注意一下,请尽量把`ViewHolder`相关的布局元素绑定,数据绑定放到`ViewHolder`中来实现,而不要放到`onCreateViewHolder()`,`onBindViewHolder()`中去实现.

#### 2.3.11.其它相关代码
