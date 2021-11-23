package com.example.td_horoscope.ui.activity

import android.graphics.Color
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.example.module_base.bean.JsonBean
import com.example.module_base.util.CheckUtil
import com.example.module_base.util.GetJsonDataUtil
import com.example.module_base.util.Rx.RxKeyboardTool
import com.example.module_base.util.Rx.RxTimeTool
import com.example.module_base.util.ToastUtil
import com.example.module_base.widget.MyToolbar
import com.example.module_base.widget.SmoothCheckBox
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.bean.consplate.ConsPlateBean
import com.example.td_horoscope.present.impl.ConsPlateImpl
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.LogUtil
import com.example.td_horoscope.util.MyContentProvider
import com.example.td_horoscope.util.top.deleteLastStr
import com.example.td_horoscope.util.top.getAstro
import com.example.td_horoscope.util.top.toOtherActivity
import com.example.td_horoscope.view.IPlateCallback
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_alloy_plate.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class   AlloyPlateActivity : MainBaseActivity() , SmoothCheckBox.OnCheckedChangeListener,
    View.OnClickListener, IPlateCallback {

    private var mPlateHintList:MutableList<IconTitleBean>?=ArrayList()
    private var options1Items: List<JsonBean> = ArrayList()
    private val options2Items =
        ArrayList<ArrayList<String>>()
    private val options3Items =
        ArrayList<ArrayList<ArrayList<String>>>()

    private val mCalendar by lazy {
        Calendar.getInstance()
    }

    //信息1
    private var mSex1="男"
    private var mName1=""
    private var mDate1=""
    private var mPlace1=""
    private var mConstellation1=""

    //信息2
    private var   mSex2="男"
    private var  mName2=""
    private var  mDate2=""
    private var mPlace2=""
    private var mConstellation2=""


    override fun getLayoutView(): Int = R.layout.activity_alloy_plate
    override fun initView() {
        //初始化控件状态
        mCheMan1.isChecked=true
        mCheMan2.isChecked=true
        mBirthEdt1.inputType = InputType.TYPE_NULL
        initCustomTimePicker()
        //读取城市Json信息
        GlobalScope.launch (Dispatchers.Main){
            withContext(Dispatchers.IO){
                initJsonData()
            }
            LogUtil.i(this,Thread.currentThread().name+"------------initJsonData---------------end")
        }
    }


    override fun initPresent() {

        ConsPlateImpl.registerCallback(this)
    }

    override fun initEvent() {
        //性别选择
        mCheMan1.setOnCheckedChangeListener(this)
        mCheMan2.setOnCheckedChangeListener(this)
        mCheWoman1.setOnCheckedChangeListener(this)
        mCheWoman2.setOnCheckedChangeListener(this)
        //出生日期1
        mBirthEdt1.setOnClickListener(this)
        //出生地点1
        mPlaceEdt1.setOnClickListener(this)
        //出生日期2
        mBirthEdt2.setOnClickListener (this)
        //出生地点2
        mPlaceEdt2.setOnClickListener (this)
        //标题栏
        mPlateBar.setOnBackClickListener(object : MyToolbar.OnBackClickListener {
            override fun onBack() {
                finish()
            }

            override fun onRightTo() {
                toOtherActivity<PlateDocumentActivity>(this@AlloyPlateActivity,false) {}
            }
        })

        // 开始配对
        mPlateBtn.setOnClickListener {
             mName1 = mNameEdt1.text.toString()
             mDate1 = mBirthEdt1.text.toString()
            mPlace1 = mPlaceEdt1.text.toString()

             mName2= mNameEdt2.text.toString()
             mDate2= mBirthEdt2.text.toString()
            mPlace2= mPlaceEdt2.text.toString()

            if (TextUtils.isEmpty(mName1) || TextUtils.isEmpty(mDate1) || TextUtils.isEmpty(mPlace1) ||
                TextUtils.isEmpty(mName2) || TextUtils.isEmpty(mDate2) || TextUtils.isEmpty(mPlace2)) {
                    ToastUtil.showShortToast("以上信息不能为空")
            } else {
                if (!CheckUtil.validateName(mName1) ||! CheckUtil.validateName(mName2)) {
                    ToastUtil.showLongToast("请输入正确的姓名")
                } else {
                    if (mSex1 == mSex2) {
                        ToastUtil.showLongToast("请输选择不同性别合盘")
                    } else {
                        ConsPlateImpl.getPlateMsg(mConstellation1.deleteLastStr(),mConstellation2.deleteLastStr())
                        savePlateHint()
                    }
                }

            }

        }
    }

    //保存合盘信息
    private fun savePlateHint() {
        mPlateHintList?.clear()
        var iconTitleBean = IconTitleBean()
        iconTitleBean.hint = mName1
        iconTitleBean.title = mConstellation1
        iconTitleBean.sex=mSex1
        iconTitleBean.birth=mDate1
        iconTitleBean.place=mPlace1
        var iconTitleBean2 = IconTitleBean()
        iconTitleBean2.hint = mName2
        iconTitleBean2.title = mConstellation2
        iconTitleBean2.sex = mSex2
        iconTitleBean2.birth = mDate2
        iconTitleBean2.place = mPlace2
        MyContentProvider.homeHoroscope.forEach {
            if (it.title == mConstellation1) {
                iconTitleBean.icon = it.icon
            }
            if (it.title == mConstellation2) {
                iconTitleBean2.icon = it.icon
            }
        }
        mPlateHintList?.add(iconTitleBean)
        mPlateHintList?.add(iconTitleBean2)
    }


    //合盘成功回调
    override fun onLoadPlateSuccess(consPlate: ConsPlateBean) {
        dismissLoading()
        if (consPlate.result == null) {
            ToastUtil.showShortToast("错误码：${consPlate.error_code}")
        } else {
            toOtherActivity<PlateIndexActivity>(this,false){
                putExtra(Contents.PLATE_HINT,Gson().toJson(mPlateHintList))
                putExtra(Contents.PLATE_RESULT,Gson().toJson(consPlate))
            }
        }
    }


    override fun onClick(v: View?) {
        RxKeyboardTool.hideSoftInput(v as EditText)
        when (v) {
            mBirthEdt1 -> {
                mPvCustomTime.show(mBirthEdt1)
            }
            mPlaceEdt1 -> {
                showPickerView(1)
            }
            mBirthEdt2 -> {
                mPvCustomTime.show(mBirthEdt2)
            }
            mPlaceEdt2 -> {
                showPickerView(2)
            }
        }

    }


    override fun onCheckedChanged(checkBox: SmoothCheckBox?, isChecked: Boolean) {
        when (checkBox) {
            mCheMan1 -> {
                if (isChecked) {
                    mCheWoman1.isChecked=false
                    mSex1="男"
                }
            }
            mCheWoman1 -> {
                if (isChecked) {
                    mCheMan1.isChecked=false
                    mSex1="女"
                }
            }

            mCheMan2-> {
                if (isChecked) {
                    mCheWoman2.isChecked=false
                    mSex2="男"
                }
            }

            mCheWoman2 -> {
                if (isChecked) {
                    mCheMan2.isChecked=false
                    mSex2="女"
                }
            }

        }
    }


    private lateinit var mPvCustomTime: TimePickerView
    private fun initCustomTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        val selectedDate = Calendar.getInstance() //系统当前时间
        /* Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);*/
        //时间选择器 ，自定义布局
        mPvCustomTime = TimePickerBuilder(this, OnTimeSelectListener { date, v -> //选中事件回调
            when (v) {
                mBirthEdt1 -> {
                    mBirthEdt1.setText(RxTimeTool.date2String(date, SimpleDateFormat("yyyy年MM月dd日")))
                    mConstellation1=getConstellationData(date)

                }
                mBirthEdt2 -> {
                    mBirthEdt2.setText(RxTimeTool.date2String(date, SimpleDateFormat("yyyy年MM月dd日")))
                    mConstellation2=getConstellationData(date)
                }
            }
        }) /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               / *.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                 .setRangDate(startDate, endDate)*/
            /*.animGravity(Gravity.RIGHT)// default is center*/
            .setDate(selectedDate)
            .setLayoutRes(R.layout.pickerview_custom_time) { v ->
                val tvSubmit = v.findViewById<TextView>(R.id.tvSubmit)
                val ivCancel = v.findViewById<TextView>(R.id.ivCancel)
                tvSubmit.setOnClickListener {
                    mPvCustomTime.returnData()
                    mPvCustomTime.dismiss()
                }
                ivCancel.setOnClickListener { mPvCustomTime.dismiss() }
            }
            .setContentTextSize(18)
            .setType(booleanArrayOf(true, true, true, false, false, false))
            .setLabel("年", "月", "日", "时", "分", "秒")
            .setLineSpacingMultiplier(2.0f)
            .setTextXOffset(0, 0, 0, 40, 0, -40)
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .setDividerColor(Color.parseColor("#FF0ABE"))
            .build()
    }

    private fun getConstellationData(date: Date?):String{
        mCalendar.time = date
        val month = mCalendar.get(Calendar.MONTH)+1
        val day = mCalendar.get(Calendar.DAY_OF_MONTH)
        LogUtil.i(this,"------------getConstellationData---------------${month}-----$day---")

        return getAstro(month, day)
    }

    private fun showPickerView(index:Int) { // 弹出选择器
        val pvOptions = OptionsPickerBuilder(this,
            OnOptionsSelectListener { options1, options2, options3, v -> //返回的分别是三个级别的选中位置
                val opt1tx =
                    if (options1Items.size > 0) options1Items[options1]
                        .pickerViewText else ""
                val opt2tx = if (options2Items.size > 0
                    && options2Items[options1].size > 0
                ) {
                    val s = options2Items[options1][options2]
                    if (s=="北京市"||s=="上海市"||s=="重庆市"||s=="天津市") "" else s
                }else ""
                val opt3tx =
                    if (options2Items.size > 0 && options3Items[options1].size > 0 && options3Items[options1][options2].size > 0
                    ) options3Items[options1][options2][options3] else ""

                if (index == 1) mPlaceEdt1.setText(opt1tx + opt2tx + opt3tx) else mPlaceEdt2.setText(opt1tx + opt2tx + opt3tx)

            })
            .setCancelColor(Color.parseColor("#FF0ABE"))
            .setSubmitColor(Color.parseColor("#FF0ABE"))
            .setDividerColor(Color.parseColor("#FF0ABE"))
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setContentTextSize(20)
            .build<Any>()
        pvOptions.setPicker(options1Items, options2Items.toList(), options3Items.toList()) //三级选择器
        pvOptions.show()

    }

    private fun initJsonData() { //解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         */
        val JsonData: String =
            GetJsonDataUtil().getJson(this, "province.json") //获取assets目录下的json文件数据
        val jsonBean: ArrayList<JsonBean> = parseData(JsonData) //用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean
        for (i in jsonBean.indices) { //遍历省份
            val cityList =
                ArrayList<String>() //该省的城市列表（第二级）
            val province_AreaList =
                ArrayList<ArrayList<String>>() //该省的所有地区列表（第三极）
            for (c in 0 until jsonBean[i].cityList.size) { //遍历该省份的所有城市
                val cityName: String = jsonBean[i].cityList[c].name
                cityList.add(cityName) //添加城市
                val city_AreaList =
                    ArrayList<String>() //该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/

                city_AreaList.addAll(jsonBean[i].cityList[c].area)
                province_AreaList.add(city_AreaList) //添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList)
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList)
        }
    }
    private fun parseData(result: String?): ArrayList<JsonBean> { //Gson 解析
        val detail = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity =
                    gson.fromJson(data.optJSONObject(i).toString(), JsonBean::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return detail
    }

    override fun onLoading() {
        showLoading()
    }

    override fun onError(t: String) {
       dismissLoading()
        ToastUtil.showShortToast(t)
    }

    override fun onEmpty() {
        dismissLoading()
    }

    override fun release() {
        ConsPlateImpl.unregisterCallback(this)
        mPlateHintList=null

    }


}