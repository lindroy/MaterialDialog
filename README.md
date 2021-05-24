# MaterialDialog

![版本信息](https://img.shields.io/badge/MateriaDialog-0.0.1--beta-ff63b4)
------------
以Material Design风格为主的对话框，基于Kotlin+DialogFragment编写。

## 目录
* [功能介绍](#功能介绍)
* [配置方法](#配置方法)
* [使用方法](#使用方法)
* [资源文件](#资源文件)
* [更新日志](#更新日志)

## 功能介绍
- [x] 样式丰富的提示对话框，可显示文字、单选列表和多选列表
- [x] 可自定义子布局的底部列表对话框
- [x] 可自定义子布局的表格对话框

## 配置方法
1.在工程的build.gradle中配置：

```
     allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
2.添加依赖：

```
    implementation 'com.github.lindroy:MaterialDialog:0.0.1--beta'
```
`latest-version`改为徽章中的版本号。

3.初始化
初始化`MaterialDialog`，建议在Application中全局初始化：
```kotlin
     MaterialDialog.init(this)
```

## 使用方法
### 全局配置
你可以在初始化时全局配置`MaterialDialog`的UI细节，这里采用了`DSL`风格，在Kotlin中调用非常清晰：
```kotlin
   MaterialDialog.init(this.application)
       //提示对话框
       .initAlert {
           setAnimStyle(0)                 //设置对话框的动画样式
           setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
           setWidth(0)                     //设置对话框宽度，单位为px
           setWidthScale(0.85F)            //设置宽度与屏幕宽度比例， 如果已经设置了具体宽度则该方法无效
           setHeightScale(0F)              //设置高度与屏幕高度比例
           setKeepPortraitWidth(true)      //横屏时是否保持竖屏时的宽度，默认为true
           setCornerRadius(0F)             //设置背景圆角半径，默认为4dp
           setBackgroundColor(0)           //设置背景颜色
           setBackgroundColorRes(0)        //设置背景颜色，参数为资源Id
           setBackgroundAlpha(1F)          //设置背景透明度
           setTitleColor(0)                //设置标题文字颜色
           setTitleColorRes(0)             //设置标题文字颜色，参数为资源Id
           setTitleSize(0)                 //设置标题文字大小
           setMessageColor(0)              //设置信息文字颜色
           setMessageColorRes(0)           //设置信息文字颜色，参数为资源Id
           setMessageSize(0)               //设置信息文字大小
           setPositiveText("Ok")           //设置Positive按钮文字，为空时不显示
           setNegativeText("Cancel")       //设置Negative按钮文字，为空时不显示
           setPositiveTextColor(0)         //设置Positive按钮的文字颜色
           setPositiveTextColorRes(0)      //设置Positive按钮的文字颜色，参数为资源Id
           setNegativeTextColor(0)         //设置Negative按钮的文字颜色
           setNegativeTextColorRes(0)      //设置Negative按钮的文字颜色，参数为资源Id
           setNeutralTextColor(0)          //设置Neutral按钮的文字颜色
           setNeutralTextColorRes(0)       //设置Neutral按钮的文字颜色，参数为资源Id
       }
       .initBottom {
           setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
           setCancelableOutside(true)      //点击对话框外部是否关闭对话框
           setMaxHeight(0)                 //设置最大高度
           setFullExpanded(false)          //是否完全展开，受限于setMaxHeight
           setPeekHeight(0)                //初始高度，默认最大为屏幕高度的一半，受限于setMaxHeight和setFullExpanded
       }
       .initBottomList {
           setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
           setCancelableOutside(true)      //点击对话框外部是否关闭对话框
           setMaxHeight(0)                 //设置最大高度
           setFullExpanded(false)          //是否完全展开，受限于setMaxHeight
           setPeekHeight(0)                //初始高度，默认最大为屏幕高度的一半，受限于setMaxHeight和setFullExpanded
           setCancelableOutside(true)      //对话框外部是否关闭对话框
           setItemMinHeight(0)             //列表子布局的最小高度
           setItemTextStyle()              //设置列表子布局文字的大小、颜色等样式

       }
       .initBottomGrid {
           setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
           setCancelableOutside(true)      //点击对话框外部是否关闭对话框
           setSpanCount(3)                 //表格列数
           setMaxHeight(0)                 //设置最大高度
           setFullExpanded(false)          //是否完全展开，受限于setMaxHeight
           setPeekHeight(0)                //初始高度，默认最大为屏幕高度的一半，受限于setMaxHeight和setFullExpanded
           setItemTextStyle()              //设置列表子布局文字的大小、颜色等样式
           setItemIconStyle(               //图片的样式
               iconSize = 0,               //图片大小，小于0则为wrap_content
               iconMaxSize = 0,            //图片最大尺寸，默认为60dp
               scaleType = CENTER_CROP     //图片放缩类型，默认为ImageView.ScaleType.CENTER_CROP

           )
       }
```
### 局部使用

#### 提示对话框
```kotlin
    MaterialDialog.alert(this)
        .setTitle(R.string.location_services_title)
        .setMessage(R.string.location_services_msg)
        .setDismissible(true)       //点击按钮是否默认关闭对话框，默认为true
        .setPositiveText(R.string.agree)
        .setOnPositiveClickListener {
            Toast.makeText(mContext, R.string.agree, Toast.LENGTH_LONG).show()
        }
        .setNegativeText(R.string.disagree)
        .setOnNegativeClickListener {
            Toast.makeText(mContext, R.string.disagree, Toast.LENGTH_LONG).show()
        }
        .setNeutralText(R.string.learn_more)
        .setOnNeutralClickListener {
            Toast.makeText(mContext, R.string.learn_more, Toast.LENGTH_LONG).show()
        }
        .setOnDismissListener {
            //对话框消失监听
        }
        .show()
```
#### 单选对话框
```kotlin
    MaterialDialog.alert(this)
        .setTitle("请选择一个城市")
        .setSingleChoiceItems(
            cities,                   //选项列表数组
            0                           //预先选中项
        ) { dialog, checked, preChecked ->
            Toast.makeText(mContext, "之前选中${cities[preChecked]},当前选中${cities[checked]}", Toast.LENGTH_LONG).show()
        }
        .setPositiveButton(text = getString(R.string.ok)) {
            Toast.makeText(mContext, R.string.ok, Toast.LENGTH_LONG).show()
            it.dismiss()
        }
        .setNegativeButton(text = getString(R.string.cancel)) {
            Toast.makeText(mContext, R.string.cancel, Toast.LENGTH_LONG).show()
            it.dismiss()
        }
        .show()
```
#### 多选对话框
```kotlin
    MaterialDialog.alert(this)
        .setTitle("请选择你喜欢的城市")
        .setMultiChoiceItems(
            cities,             //选项列表数组
            null                //选中项的下标数组，为null表示全不选中
        ) { position, isChecked, checkedArray, dialog ->
            Toast.makeText(mContext, "你点击了${cities[position]}，选中：$isChecked", Toast.LENGTH_LONG).show()
        }
        .setPositiveButton(text = getString(R.string.ok)) {
            Toast.makeText(mContext, R.string.ok, Toast.LENGTH_LONG).show()
            it.dismiss()
        }
        .setNegativeButton(text = getString(R.string.cancel)) {
            Toast.makeText(mContext, R.string.cancel, Toast.LENGTH_LONG).show()
            it.dismiss()
        }
        .show()
```
#### 带有自定义布局的提示对话框
```kotlin
    MaterialDialog.alert(this)
        .setView(R.layout.layout_input) //自定义布局Id
        .setTitle("请输入密码")
        .setMessage("密码长度为6~20")
        .setOnViewHandler { holder, dialog ->
            //处理自定义布局里的控件
            holder.getView<EditText>(R.id.editText).apply {
                hint = "请输入"
            }
        }
        .setPositiveButton(text = getString(R.string.done)){
            Toast.makeText(mContext, R.string.done, Toast.LENGTH_LONG).show()
        }
        .setNegativeButton(text = getString(R.string.cancel)) {
            Toast.makeText(mContext, R.string.cancel, Toast.LENGTH_LONG).show()
        }
        .show()
```

#### 底部对话框
底部对话框封装了design包中的`BottomSheetDialog`，具有`BottomSheetDialog`的动画样式，布局则由开发者自定义：
```kotlin
    MaterialDialog.bottom(this)
        .setView(R.layout.layout_custon_bottom) //传入自定义的布局Id
        .setOnViewHandler { holder, dialog ->   //处理自定义布局上的控件
            holder.setOnClickListener(R.id.tvCancel) {
                shortToast("关闭底部对话框")
                dialog.dismiss()
            }
        }
        .show()
```
#### 底部列表对话框
底部列表中使用了`RecyclerView`，子布局为简单的文字:
```kotlin
    MaterialDialog.bottomList(this)
    //.addItem()                  //往列表中添加一项
        .addItems(cities.toList())  //添加列表集合
        .setOnItemClickListener { position, item, dialog ->
            //item的点击事件
            shortToast("你选择了${item.text}")
        }
        .show()
```
#### 底部表格对话框
底部表格对话框的子布局为一个图标和一段描述文字：
```kotlin
    MaterialDialog.bottomGrid(this)
        .setSpanCount(3)
        .addItem("item1", R.mipmap.ic_launcher_round)
        .addItem("item2", R.mipmap.ic_launcher_round)
        .addItem("item3", R.mipmap.ic_launcher_round)
        .addItem("item4", R.mipmap.ic_launcher_round)
        .addItem("item5", R.mipmap.ic_launcher_round)
        .addItem("item6", R.mipmap.ic_launcher_round)
        .addItem("item7", R.mipmap.ic_launcher_round)
        .setOnItemClickListener { position, item, dialog ->
            shortToast("你点击了${item.text}")
        }
        .show()
```

#### 自定义子布局的选项对话框
`MaterialDialog`内置的选项子布局可能无法满足你的需求，这时你可以调用`setAdapter()`扩展自己的子布局：
```kotlin
    MaterialDialog.bottomList(this)
        //传入一个MDAdapter的匿名对象，依次设置自定义的子布局Id和数据
        .setAdapter(object : MDAdapter<ListItemBean>(mContext, R.layout.item_list, list) {
            override fun onConvert(
                holder: RecyclerViewHolder,
                position: Int,
                item: ListItemBean
            ) {
                holder.setText(R.id.tvItem, item.text)
                    .setBackgroundRes(
                        R.id.ivItem,
                        if (item.isSelected) R.drawable.ic_selected else R.drawable.ic_unselected
                    )
            }
        })
        //子布局中的控件点击事件
        .setOnItemChildClickListener<ListItemBean>(
            R.id.llRoot,
            R.id.tvItem
        ) { adapter, position, item, view, dialog ->
            when (view.id) {
                R.id.llRoot -> {
                    shortToast("你选择了${item.text}")
                    item.isSelected = !item.isSelected
                    adapter.notifyItemChanged(position)
                }
                R.id.tvItem -> shortToast("${item.text}")
            }
        }
        .show()
```
#### 完全自定义布局对话框

## 资源文件
### colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="md_dialog_theme_color">#6200ee</color>
    <color name="md_dialog_bg_color">#ffffff</color>
    <color name="md_dialog_title_text_color">#de000000</color>
    <color name="md_dialog_message_text_color">#99000000</color>
    <color name="md_dialog_button_text_color">#6200ee</color>
    <color name="md_single_choice_radio_button_checked_color">@color/md_dialog_theme_color</color>
    <color name="md_single_choice_radio_button_unchecked_color">#757575</color>
    <color name="md_single_choice_item_text_color">#de000000</color>
    <color name="md_multi_choice_check_box_checked_color">@color/md_dialog_theme_color</color>
    <color name="md_multi_choice_check_box_unchecked_color">#757575</color>
    <color name="md_list_divider_color">#e0e0e0</color>
    <color name="md_list_item_text_color">#de000000</color>
    <color name="md_grid_item_text_color">#de000000</color>
    <color name="md_bottom_list_item_text_color">#e0e0e0</color>
</resources>
```

### dimens.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="md_dialog_title_size">18sp</dimen>
    <dimen name="md_dialog_message_size">16sp</dimen>
    <dimen name="md_dialog_button_text_size">14sp</dimen>
    <dimen name="md_dialog_bg_corner_radius">4dp</dimen>
    <dimen name="md_dialog_padding_start">24dp</dimen>
    <dimen name="md_dialog_button_margin">8dp</dimen>
    <dimen name="md_dialog_button_panel_height">52dp</dimen>
    <dimen name="md_single_choice_text_size">16sp</dimen>
    <dimen name="md_list_item_height">56dp</dimen>
    <dimen name="md_list_item_text_size">16sp</dimen>
    <dimen name="md_grid_item_text_size">14sp</dimen>
    <dimen name="md_grid_item_icon_max_size">60dp</dimen>
    <dimen name="md_grid_item_padding_top_bottom">10dp</dimen>
</resources>
```
## 更新日志
### 0.0.1-beta
- 发表第一个测试版本
