package com.leewaiho.togogo.common;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
public class Const {
    
    public static final String CLIENT_ID = "togogo-wxapp";
    public static final String CLIENT_SECRET = "pigpigpig";
    public static final String WECHAT_OPENID_KEY = "openid";
    public static final String WECHAT_ERRMSG_KEY = "errmsg";
    public static final String WECHAT_ERRCODE_KEY = "errcode";
    public static final String OAUTH_TOKEN_URI = "oauth.token";
    public static final String BASE_PATH = "/api";
    
    public enum ServiceCode {
        UNKNOWED(00000, "未知状态", false), FAILED(10000, "操作失败", false),
        NOTFOUND(10001, "查找无结果", false), UNLOGIN(11000, "用户未登录", false),
        UNREGISTER(11010, "用户未注册", false), BADREQUEST(10002, "参数不合法", false),
        FREQUENTLY(12001, "请求验证码过于频繁", false),
        HAD_BEEN_REGISTERD(11011, "已经被占用", false), SUCCESS(20000, "操作成功"),
        LOGIN(21000, "登录成功"), REGISTER(21010, "注册成功"),
        WECHAT_INVALID_CODE(40029, "无效的CODE值,请重新登录", false), WECHAT_CODE_BEEN_USED(40163, "登录会话被重复使用,请刷新后重试");
        
        private int code;
        private String message;
        private boolean success;
        
        ServiceCode(int code, String message) {
            this.code = code;
            this.message = message;
            this.success = true;
        }
        
        ServiceCode(int code, String message, boolean success) {
            this.code = code;
            this.message = message;
            this.success = success;
        }
        
        public int getCode() {
            return code;
        }
        
        public String getMessage() {
            return message;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        @Override
        public String toString() {
            return "Code{" +
                           "code=" + code +
                           ", message='" + message + '\'' +
                           ", success=" + success +
                           "} " + super.toString();
        }
    }
    
    
    public enum TAG_OPTIONS_TYPE {
        ADDPOINT("加减分", "addPoint"), COEFFICIENT("系数", "coefficient");
        private String name;
        private String value;
        
        TAG_OPTIONS_TYPE(String name, String value) {
            this.name = name;
            this.value = value;
        }
        
        public String getName() {
            return name;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    public static class WeChat {
        public static final String QCODE_C = "QCODE_C";
        public static final String QCODE_A = "QCODE_A";
        public static final String QCODE_B = "QCODE_B";
        public static final String PATH = "path";
        public static final String DEFAULT_PAGE = "";
        public static final String WIDTH = "width";
        public static final int DEFAULT_WIDTH = 430;
        public static final String AUTO_COLOR = "auto_color";
        public static final boolean DEFAULT_AUTO_COLOR = true;
        public static final String LINE_COLOR = "line_color";
        public static final String LINE_COLOR_R = "r";
        public static final String LINE_COLOR_G = "g";
        public static final String LINE_COLOR_B = "b";
        public static final String DEFAULT_LINE_COLOR = "{\"r\":\"000\",\"g\":\"000\",\"b\":\"000\"}";
        public static final String SCENE = "scene";
        public static final String DEFAULT_SCENE = "null";
        public static final String PAGE = "page";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String EXPIRES_IN = "expires_in";
        public static final String APPID = "appid";
        public static final String SECRET = "secret";
        public static final String GRANT_TYPE = "grant_type";
        public static final String ERRCODE = "errcode";
        public static final String ERRMSG = "errmsg";
        public static final String QCODE_DIR = "/QCode";
    }
}
