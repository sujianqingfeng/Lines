package com.sujian.lines;

/**
 * Created by baixiaokang on 16/4/23.
 */
public class C {

    //================= TYPE ====================

    public static final int TYPE_ZHIHU = 101;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final int TYPE_WECHAT = 106;

    public static final int TYPE_GANK = 107;

    public static final int TYPE_SETTING = 108;

    public static final int TYPE_LIKE = 109;

    public static final int TYPE_ABOUT = 110;


    //================= PREFERENCE ====================

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_CURRENT_ITEM = "current_item";

    public static final String SP_LIKE_POINT = "like_point";



    //base
    public static final int PAGE_COUNT = 10;

    // intent
    public static final String HEAD_DATA = "data";
    public static final String COUNT = "count";
    public static final String VH_CLASS = "vh";
    // RxBusEventName

    public static final String EVENT_LOGIN = "login";
    public static final String EVENT_DEL_ITEM = "delete_item";
    public static final String EVENT_UPDATE_ITEM = "update_item";
    public static final String EVENT_HEADDATA = "get_headdata";
    public static final String EVENT_COUNT = "get_count";

    // TagName
    public static final String TAG_EDITABLE = "editable";
    public static final String TAG_HEADDATA = "with_headdata";

}
