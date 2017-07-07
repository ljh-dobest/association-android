package com.min.threeminutestoteach.utils;

import com.min.threeminutestoteach.domain.MyBusinessInfLocal;

import java.util.ArrayList;
import java.util.List;

import cn.woblog.android.downloader.domain.DownloadInfo;

/**
 * Created by Min on 2017/6/16.
 */

public class DataUtils {
    public static String checkVip="0";
    public static int DOWNLOAD_FILE_SIZE=0;
    public static List<MyBusinessInfLocal> DOWNLOAD_LIST=new ArrayList<>();
    public static List<DownloadInfo> DOWNLOAD_COMMPLETE_LIST=new ArrayList<>();
    public static boolean canAddDownloadList(MyBusinessInfLocal teacheBean){
        for (MyBusinessInfLocal myBusinessInfo : DOWNLOAD_LIST) {
             if(teacheBean.getUrl().equals(myBusinessInfo.getUrl())){
                      return false;
             }
        }
        return true;
    }

}
