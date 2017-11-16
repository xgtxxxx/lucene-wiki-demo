package com.b2s.wiki.model;

import java.util.Arrays;
import java.util.List;

public class AppConfig {
    /* 上传图片配置项 */
    private final String imageActionName = "uploadimage"; /* 执行上传图片的action名称 */
    private final String imageFieldName = "upfile"; /* 提交的图片表单名称 */
    private final long imageMaxSize = 2048000; /* 上传大小限制，单位B */
    private final List<String> imageAllowFiles = Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"); /* 上传图片格式显示 */
    private final boolean imageCompressEnable = true; /* 是否压缩图片,默认是true */
    private final long imageCompressBorder = 1600; /* 图片压缩最长边限制 */
    private final String imageInsertAlign = "none"; /* 插入的图片浮动方式 */
    private final String imageUrlPrefix = ""; /* 图片访问路径前缀 */
    private final String imagePathFormat = "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,
    可以自定义保存路径和文件名格式 */
                                /* {filename} 会替换成原文件名,配置这项需要注意中文乱码问题 */
                                /* {rand:6} 会替换成随机数,后面的数字是随机数的位数 */
                                /* {time} 会替换成时间戳 */
                                /* {yyyy} 会替换成四位年份 */
                                /* {yy} 会替换成两位年份 */
                                /* {mm} 会替换成两位月份 */
                                /* {dd} 会替换成两位日期 */
                                /* {hh} 会替换成两位小时 */
                                /* {ii} 会替换成两位分钟 */
                                /* {ss} 会替换成两位秒 */
                                /* 非法字符 \ : * ? " < > | */
                                /* 具请体看线上文档: fex.baidu.com/ueditor/#use-format_upload_filename */

    /* 涂鸦图片上传配置项 */
    private final String scrawlActionName = "uploadscrawl"; /* 执行上传涂鸦的action名称 */
    private final String scrawlFieldName = "upfile"; /* 提交的图片表单名称 */
    private final String scrawlPathFormat = "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,
    可以自定义保存路径和文件名格式 */
    private final long scrawlMaxSize = 2048000; /* 上传大小限制，单位B */
    private final String scrawlUrlPrefix = ""; /* 图片访问路径前缀 */
    private final String scrawlInsertAlign = "none";

    /* 截图工具上传 */
    private final String snapscreenActionName = "uploadimage"; /* 执行上传截图的action名称 */
    private final String snapscreenPathFormat = "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,
    可以自定义保存路径和文件名格式 */
    private final String snapscreenUrlPrefix = ""; /* 图片访问路径前缀 */
    private final String snapscreenInsertAlign = "none"; /* 插入的图片浮动方式 */

    /* 抓取远程图片配置 */
    private final List<String> catcherLocalDomain = Arrays.asList("127.0.0.1", "localhost", "img.baidu.com");
    private final String catcherActionName = "catchimage"; /* 执行抓取远程图片的action名称 */
    private final String catcherFieldName = "source"; /* 提交的图片列表表单名称 */
    private final String catcherPathFormat = "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,
    可以自定义保存路径和文件名格式 */
    private final String catcherUrlPrefix = ""; /* 图片访问路径前缀 */
    private final long catcherMaxSize = 2048000; /* 上传大小限制，单位B */
    private final List<String> catcherAllowFiles = Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"); /*
    抓取图片格式显示 */

    /* 上传视频配置 */
    private final String videoActionName = "uploadvideo"; /* 执行上传视频的action名称 */
    private final String videoFieldName = "upfile"; /* 提交的视频表单名称 */
    private final String videoPathFormat = "/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,
    可以自定义保存路径和文件名格式 */
    private final String videoUrlPrefix = ""; /* 视频访问路径前缀 */
    private final long videoMaxSize = 102400000; /* 上传大小限制，单位B，默认100MB */
    private final List<String> videoAllowFiles = Arrays.asList(".flv",
        ".swf",
        ".mkv",
        ".avi",
        ".rm",
        ".rmvb",
        ".mpeg",
        ".mpg",
        ".ogg",
        ".ogv",
        ".mov",
        ".wmv",
        ".mp4",
        ".webm",
        ".mp3",
        ".wav",
        ".mid"); /* 上传视频格式显示 */

    /* 上传文件配置 */
    private final String fileActionName = "uploadfile"; /* controller里,执行上传视频的action名称 */
    private final String fileFieldName = "upfile"; /* 提交的文件表单名称 */
    private final String filePathFormat = "/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,
    可以自定义保存路径和文件名格式 */
    private final String fileUrlPrefix = ""; /* 文件访问路径前缀 */
    private final long fileMaxSize = 51200000; /* 上传大小限制，单位B，默认50MB */
    private final List<String> fileAllowFiles = Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp",
        ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
        ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
        ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"); /* 上传文件格式显示 */

    /* 列出指定目录下的图片 */
    private final String imageManagerActionName = "listimage"; /* 执行图片管理的action名称 */
    private final String imageManagerListPath = "/ueditor/jsp/upload/image/"; /* 指定要列出图片的目录 */
    private final long imageManagerListSize = 20; /* 每次列出文件数量 */
    private final String imageManagerUrlPrefix = ""; /* 图片访问路径前缀 */
    private final String imageManagerInsertAlign = "none"; /* 插入的图片浮动方式 */
    private final List<String> imageManagerAllowFiles = Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"); /*
    列出的文件类型 */

    /* 列出指定目录下的文件 */
    private final String fileManagerActionName = "listfile"; /* 执行文件管理的action名称 */
    private final String fileManagerListPath = "/ueditor/jsp/upload/file/"; /* 指定要列出文件的目录 */
    private final String fileManagerUrlPrefix = ""; /* 文件访问路径前缀 */
    private final long fileManagerListSize = 20; /* 每次列出文件数量 */
    private final List<String> fileManagerAllowFiles = Arrays.asList(
        ".png", ".jpg", ".jpeg", ".gif", ".bmp",
        ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
        ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
        ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml");/* 列出的文件类型 */


    public static AppConfig newInstance() {

       return new AppConfig();
    }

    public String getImageActionName() {
        return imageActionName;
    }

    public String getImageFieldName() {
        return imageFieldName;
    }

    public long getImageMaxSize() {
        return imageMaxSize;
    }

    public List<String> getImageAllowFiles() {
        return imageAllowFiles;
    }

    public boolean isImageCompressEnable() {
        return imageCompressEnable;
    }

    public long getImageCompressBorder() {
        return imageCompressBorder;
    }

    public String getImageInsertAlign() {
        return imageInsertAlign;
    }

    public String getImageUrlPrefix() {
        return imageUrlPrefix;
    }

    public String getImagePathFormat() {
        return imagePathFormat;
    }

    public String getScrawlActionName() {
        return scrawlActionName;
    }

    public String getScrawlFieldName() {
        return scrawlFieldName;
    }

    public String getScrawlPathFormat() {
        return scrawlPathFormat;
    }

    public long getScrawlMaxSize() {
        return scrawlMaxSize;
    }

    public String getScrawlUrlPrefix() {
        return scrawlUrlPrefix;
    }

    public String getScrawlInsertAlign() {
        return scrawlInsertAlign;
    }

    public String getSnapscreenActionName() {
        return snapscreenActionName;
    }

    public String getSnapscreenPathFormat() {
        return snapscreenPathFormat;
    }

    public String getSnapscreenUrlPrefix() {
        return snapscreenUrlPrefix;
    }

    public String getSnapscreenInsertAlign() {
        return snapscreenInsertAlign;
    }

    public List<String> getCatcherLocalDomain() {
        return catcherLocalDomain;
    }

    public String getCatcherActionName() {
        return catcherActionName;
    }

    public String getCatcherFieldName() {
        return catcherFieldName;
    }

    public String getCatcherPathFormat() {
        return catcherPathFormat;
    }

    public String getCatcherUrlPrefix() {
        return catcherUrlPrefix;
    }

    public long getCatcherMaxSize() {
        return catcherMaxSize;
    }

    public List<String> getCatcherAllowFiles() {
        return catcherAllowFiles;
    }

    public String getVideoActionName() {
        return videoActionName;
    }

    public String getVideoFieldName() {
        return videoFieldName;
    }

    public String getVideoPathFormat() {
        return videoPathFormat;
    }

    public String getVideoUrlPrefix() {
        return videoUrlPrefix;
    }

    public long getVideoMaxSize() {
        return videoMaxSize;
    }

    public List<String> getVideoAllowFiles() {
        return videoAllowFiles;
    }

    public String getFileActionName() {
        return fileActionName;
    }

    public String getFileFieldName() {
        return fileFieldName;
    }

    public String getFilePathFormat() {
        return filePathFormat;
    }

    public String getFileUrlPrefix() {
        return fileUrlPrefix;
    }

    public long getFileMaxSize() {
        return fileMaxSize;
    }

    public List<String> getFileAllowFiles() {
        return fileAllowFiles;
    }

    public String getImageManagerActionName() {
        return imageManagerActionName;
    }

    public String getImageManagerListPath() {
        return imageManagerListPath;
    }

    public long getImageManagerListSize() {
        return imageManagerListSize;
    }

    public String getImageManagerUrlPrefix() {
        return imageManagerUrlPrefix;
    }

    public String getImageManagerInsertAlign() {
        return imageManagerInsertAlign;
    }

    public List<String> getImageManagerAllowFiles() {
        return imageManagerAllowFiles;
    }

    public String getFileManagerActionName() {
        return fileManagerActionName;
    }

    public String getFileManagerListPath() {
        return fileManagerListPath;
    }

    public String getFileManagerUrlPrefix() {
        return fileManagerUrlPrefix;
    }

    public long getFileManagerListSize() {
        return fileManagerListSize;
    }

    public List<String> getFileManagerAllowFiles() {
        return fileManagerAllowFiles;
    }
}
