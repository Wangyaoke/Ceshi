package com.ansiyida.cgjl.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.ansiyida.cgjl.util.ToastUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ansiyida on 2017/12/5.
 */
public class SavePhone {
    /** 首先默认个文件保存路径 */
    private static final String SAVE_PIC_PATH=Environment.getExternalStorageDirectory().getAbsolutePath();//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH+" /good/savePic";//保存的确切位置


    public static void saveFile(Bitmap bm, String fileName,Context context) throws IOException {
        String subForder = SAVE_REAL_PATH;
        File foder = new File(subForder);
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(subForder, fileName);
        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        context.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
        ToastUtils.showMessage(context,"存储成功");
    }



}
