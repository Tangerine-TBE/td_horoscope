package com.example.td_horoscope.ui.activity

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.module_base.base.BaseActivity
import com.example.module_base.base.BaseApplication
import com.example.module_base.util.GsonUtils
import com.example.module_base.util.MD5Utlis
import com.example.module_base.util.maputils.MapUtils
import com.example.module_base.widget.LoadingDialog
import com.example.td_horoscope.R
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import kotlinx.android.synthetic.main.activity_feedback.*
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException

class FeedbackActivity : BaseActivity() {
    private val REQUEST_CODE_GALLERY_1 = 100
    private val REQUEST_CODE_GALLERY_2 = 200
    private var imageUrl1 : String = ""
    private var imageUrl2 : String = ""
    private lateinit var loadingDialog: LoadingDialog
    private val BaseUrl = "https://catapi.aisou.club"
    private val TOKEN_VALUE = "x389fh^feahykge"
    private val NONCE_VALUE = "523146"
    private val TIMESTAMP = "timestamp"
    private val SIGNATURE = "signature"

    override fun getLayoutView(): Int = R.layout.activity_feedback

    override fun initView() {
        loadingDialog = LoadingDialog(this)
        barBack.setOnClickListener {
            finish()
        }
        btn.setOnClickListener {
            FeedBack()
        }
        image1.setOnClickListener {
            askPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE){
                gallery(REQUEST_CODE_GALLERY_1)
            }

        }
        image2.setOnClickListener {
            askPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE){
                gallery(REQUEST_CODE_GALLERY_2)
            }
        }

        body.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                text_num_tip.text = "${p0?.length}/255字"
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    private fun gallery(code: Int) {
        val intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 以startActivityForResult的方式启动一个activity用来获取返回的结果
        startActivityForResult(intent, code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_GALLERY_1 -> {
                    var uri = data?.data
                    Glide.with(this).load(uri).into(image1)
                    uploadImage("https://catapi.aisou.club/gameskin/person/uploadImg", uri!!, 1)
                }
                REQUEST_CODE_GALLERY_2 -> {
                    var uri = data?.data
                    Glide.with(this).load(uri).into(image2)
                    uploadImage("https://catapi.aisou.club/gameskin/person/uploadImg", uri!!, 2)
                }
            }
        }
    }

    /**
     * 上传图片
     * @param url
     * @param imagePath 图片路径
     * @return 新图片的路径
     * @throws IOException
     * @throws JSONException
     */
    @Throws(IOException::class, JSONException::class)
    fun uploadImage(url: String, imageUri: Uri, id: Int) {
        loadingDialog.show()
        val okHttpClient = OkHttpClient()
        val file = File(getRealFilePath(this, imageUri))
        val image: RequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file)
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("thumb", getRealFilePath(this, imageUri), image)
            .build()
        val request: Request = Request.Builder()
            .url("$url")
            .post(requestBody)
            .build()
//            val response = okHttpClient.newCall(request).execute()
//            LogUtils.e("response---------------------->${response.body()?.string()}")
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    loadingDialog.dismiss()
                    Toast.makeText(this@FeedbackActivity,"上传失败",Toast.LENGTH_SHORT).show()
                    if (id == 1)
                        image1.setImageResource(R.drawable.ic_feedback_image_add)
                    else
                        image2.setImageResource(R.drawable.ic_feedback_image_add)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val str = response.body()?.string()
                    if (str != null) {
                        val bean = GsonUtils.parseObject(str, UpImageBean::class.java)
                        if (bean.code == "200") {
                            if (id == 1)
                                imageUrl1 = bean.data?.img_url!!
                            else
                                imageUrl2 = bean.data?.img_url!!
                            runOnUiThread {
                                Toast.makeText(this@FeedbackActivity,"上传成功",Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this@FeedbackActivity,"${bean.msg}",Toast.LENGTH_SHORT).show()
                            }
                        }
                        runOnUiThread {
                            loadingDialog.dismiss()
                        }
                    } else {
                        runOnUiThread {
                            loadingDialog.dismiss()
                            Toast.makeText(this@FeedbackActivity,"上传失败",Toast.LENGTH_SHORT).show()
                            if (id == 1)
                                image1.setImageResource(R.drawable.ic_feedback_image_add)
                            else
                                image2.setImageResource(R.drawable.ic_feedback_image_add)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread {
                        loadingDialog.dismiss()
                        Toast.makeText(this@FeedbackActivity,"上传失败",Toast.LENGTH_SHORT).show()
                        if (id == 1)
                            image1.setImageResource(R.drawable.ic_feedback_image_add)
                        else
                            image2.setImageResource(R.drawable.ic_feedback_image_add)
                    }
                }
            }
        })
    }

    /**
     * 获取绝对路径
     * */
    fun getRealFilePath(context: Context, uri: Uri?): String? {
        if (null == uri) return null
        val scheme: String = uri.getScheme()!!
        var data: String? = null
        if (scheme == null) data =
            uri.getPath() else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath()
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            val cursor: Cursor = context.getContentResolver()
                .query(uri, arrayOf<String>(MediaStore.Images.ImageColumns.DATA), null, null, null)!!
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    val index: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    if (index > -1) {
                        data = cursor.getString(index)
                    }
                }
                cursor.close()
            }
        }
        return data
    }

    private fun askPermission(vararg permission: String, method: () -> Unit) {
        XXPermissions.with(this)
            .permission(Permission.MANAGE_EXTERNAL_STORAGE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                    if (all) {
                        method.invoke()
                    }
                }

                override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                    if (never) {
                        XXPermissions.startPermissionActivity(this@FeedbackActivity, permissions)
                    }
                }

            })
    }

    private fun FeedBack(){
        if (body.text.isEmpty()){
            Toast.makeText(this@FeedbackActivity,"意见不可为空",Toast.LENGTH_SHORT).show()
            return
        }
        var user_id_ = 111
        val map1: MutableMap<String, String> = HashMap()
        map1["user_id"] = "$user_id_"
        map1["content"] = body.text.toString()
        map1["contact"] = "${qq.text}--${email.text}"
        map1["user_system"] = "1"
        map1["user_package"] = BaseApplication.application.packageName
        map1["package_chn"] = "优话实时距离测量"
        val map2: MutableMap<String, String> = HashMap()
        if (imageUrl1 != "")
            map2["img_one"] = imageUrl1
        if (imageUrl2 != "")
            map2["img_two"] = imageUrl2
        val map = setMapValues(map1, map2)
        //创建okHttpClient对象
        val client = OkHttpClient()
        val builder = FormBody.Builder()
        map.forEach {
            builder.add(it.key, it.value)
        }
        val resBody: RequestBody = builder.build()
        val request = Request.Builder()
            .url("https://catapi.aisou.club/usercenter/public/feedback")
            .post(resBody)
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@FeedbackActivity,"无法连接服务器，请稍后重试",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val str = response.body()?.string()
                    if (!str.isNullOrEmpty()) {
                        val resp: JSONObject = JSONObject(str)
                        val code = resp.getString("code")
                        val msg = resp.getString("msg")
                        runOnUiThread {
                            body.setText("")
                            qq.setText("")
                            email.setText("")
                            imageUrl1 = ""
                            imageUrl2 = ""
                            image1.setImageResource(R.drawable.ic_feedback_image_add)
                            image2.setImageResource(R.drawable.ic_feedback_image_add)
                            Toast.makeText(this@FeedbackActivity,msg,Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@FeedbackActivity,"连接服务器出错",Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread {
                        Toast.makeText(this@FeedbackActivity,"连接服务器出错",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    /**
     * @param userInfo 必填参数
     * @param map1 非必填参数
     * */
    private fun setMapValues(userInfo: MutableMap<String, String>, map1: MutableMap<String, String>):MutableMap<String, String>{
        //获取时间戳
        val currentTimeMillis = System.currentTimeMillis() / 1000
        val md5 = MD5Utlis.md5(TOKEN_VALUE + currentTimeMillis + NONCE_VALUE + "public/feedback" + MapUtils.sortMapByValue2(userInfo))
        val map: MutableMap<String, String> = HashMap()
        map[TIMESTAMP] = "$currentTimeMillis"
        map[SIGNATURE] = md5
        map.putAll(userInfo)
        map.putAll(map1)
        return map
    }

    class UpImageBean {
        var code: String? = null
        var msg: String? = null
        var data: Data? = null

        inner class Data {
            var img_url: String? = null

        }
    }

    private fun getUserId(): Int {
//        if (SPUtil.getInstance().getString(Constants.USER_BEAN) == null || SPUtil.getInstance().getString(Constants.USER_BEAN).isEmpty()) return 0
//        try {
//            val bean: UserBean = GsonUtils.parseObject(SPUtil.getInstance().getString(Constants.USER_BEAN), UserBean::class.java)
//            return bean.data.id
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
        return 0
    }
}