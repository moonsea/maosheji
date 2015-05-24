package maosheji.core.oss;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maosheji.core.front.SystemManager;
import maosheji.services.front.systemSetting.bean.SystemSetting;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.ListObjectsRequest;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;

/**
 * 读取阿里云OSS的目录列表或文件列表
 * 
 * 参考：http://aliyun_portal_storage.oss.aliyuncs.com/oss_api/oss_javahtml/object.html#id6
 * @author jqsl2012@163.com
 *
 */
public class OSSFileManagerJson {
	private static final Logger logger = LoggerFactory.getLogger(OSSFileManagerJson.class);
	private OSSClient client;
	
	public String write(HttpServletRequest request,HttpServletResponse response){
		logger.error("write...");
		SystemSetting systemSetting = SystemManager.systemSetting;
		String rootPath = "attached/";//pageContext.getServletContext().getRealPath("/") + "attached/";
		String rootUrl  = request.getContextPath() + "/attached/";
		rootPath = rootUrl;
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

		String dirName = request.getParameter("dir");
		logger.error("dirName = "+dirName);
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
//				out.println("Invalid Directory name.");
				return "Invalid Directory name.";
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			System.out.println(rootPath);
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		
		return "success";

	}

	// 如果Bucket不存在，则创建它。
    private static void ensureBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException{

        if (client.isBucketExist(bucketName)){
        	logger.error("isBucketExist true");
            return;
        }
    }
    
	// 把Bucket设置为所有人可读
    private static void setBucketPublicReadable(OSSClient client, String bucketName)
            throws OSSException, ClientException {
        //创建bucket
        client.createBucket(bucketName);

        //设置bucket的访问权限，public-read-write权限
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }
    
    class NameComparator implements Comparator<Object> {
    	public int compare(Object a, Object b) {
    		Hashtable hashA = (Hashtable)a;
    		Hashtable hashB = (Hashtable)b;
    		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
    			return -1;
    		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
    			return 1;
    		} else {
    			return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
    		}
    	}
    }
    class SizeComparator implements Comparator<Object> {
    	public int compare(Object a, Object b) {
    		Hashtable hashA = (Hashtable)a;
    		Hashtable hashB = (Hashtable)b;
    		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
    			return -1;
    		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
    			return 1;
    		} else {
    			if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
    				return 1;
    			} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
    				return -1;
    			} else {
    				return 0;
    			}
    		}
    	}
    }
    class TypeComparator implements Comparator<Object> {
    	public int compare(Object a, Object b) {
    		Hashtable hashA = (Hashtable)a;
    		Hashtable hashB = (Hashtable)b;
    		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
    			return -1;
    		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
    			return 1;
    		} else {
    			return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
    		}
    	}
    }
}
