package com.leewaiho.togogo.module.wechat;

import com.alibaba.druid.util.StringUtils;
import com.leewaiho.togogo.common.Const;
import com.leewaiho.togogo.common.exception.WeChatException;
import com.leewaiho.togogo.common.util.TimeUtil;
import com.leewaiho.togogo.module.wechat.api.WeChatApi;
import com.leewaiho.togogo.module.wechat.pojo.WeChatToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/8/26
 */

@Component
public class WeChat implements WeChatApi {
    
    private static final Logger log = LoggerFactory.getLogger(WeChat.class);
    
    @Value("${wechat.app.id}")
    private String appId; // 微信 APPID
    @Value("${wechat.app.secret}")
    private String secret; // 微信 SECRET
    
    private String grantType = "client_credential"; // 微信 认证方式
    
    private String apiUrl = "https://api.weixin.qq.com"; // 微信请求服务器
    
    private int beforeMinutes = 5; // 提前多长时间重新申请TOKEN 单位:分钟
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    
    private ValueOperations valueOperations;
    
    /**
     * 根据配置文件中的信息获取access_token
     *
     * @return WeChatToken
     * @throws WeChatException
     */
    public WeChatToken getWeChatToken() throws WeChatException {
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(secret))
            throw new WeChatException("未成功初始化WeChat");
        
        WeChatToken weChatToken = null;
        // 无法连接Redis数据库时的情况
        if (getValueOperations() == null) {
            String errMsg = "无法获得Redis的操作对象";
            log.error(errMsg);
            throw new WeChatException(errMsg);
        }
        
        // 能正常获取到WeChatToken时
        if (getValueOperations().get(appId) != null && !isTime2Refresh(((WeChatToken) getValueOperations().get(appId)).getExpiresTime())) {
            weChatToken = (WeChatToken) getValueOperations().get(appId);
        } else {
            weChatToken = updateToken();
            getValueOperations().set(appId, weChatToken);
        }
        
        return weChatToken;
    }
    
    /**
     * 接口A: 适用于需要的码数量较少的业务场景 接口地址：
     * https://api.weixin.qq.com/wxa/getwxacode?access_token=ACCESS_TOKEN
     * 通过该接口生成的小程序码，永久有效，数量限制见文末说明，请谨慎使用。用户扫描该码进入小程序后，将直接进入 path 对应的页面
     *
     * @param path       不能为空，最大长度 128 字节
     * @param width      二维码的宽度 默认 430
     * @param auto_color 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调 默认 false
     * @param line_color auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} auto_color为false时生效
     * @return 二维码在对象存储的URL
     */
    public ResponseEntity<byte[]> getWXACode(String path, int width, boolean auto_color, Object line_color) throws WeChatException {
        String url = new StringBuffer(apiUrl).append("/wxa/getwxacode?access_token=").append(getWeChatToken().getAccessToken()).toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        //设置请求参数
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(Const.WeChat.PATH, path);
        map.put(Const.WeChat.WIDTH, width);
        map.put(Const.WeChat.AUTO_COLOR, auto_color);
        if (!auto_color) {
            map.put(Const.WeChat.LINE_COLOR, line_color);
        }
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
    }
    
    /**
     * 接口B：适用于需要的码数量极多，或仅临时使用的业务场景 接口地址：
     * https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN
     * 通过该接口生成的小程序码，永久有效，数量暂无限制。
     * 用户扫描该码进入小程序后，开发者需在对应页面获取的码中 scene 字段的值，再做处理逻辑。
     * 使用如下代码可以获取到二维码中的 scene 字段的值。
     * 调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx
     * 进行模拟，开发工具模拟时的 scene 的参数值需要进行 urlencode
     *
     * @param scene      最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     * @param page       必须是已经发布的小程序页面，例如 "pages/index/index" ,如果不填写这个字段，默认跳主页面
     * @param width      二维码的宽度 默认 430
     * @param auto_color 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
     * @param line_color auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
     * @return 二维码在对象存储的URL
     */
    public ResponseEntity<byte[]> getWXACodeUnlimit(String scene, String page, int width, boolean auto_color, Object line_color) throws WeChatException {
        // 构造 Url
        String url = new StringBuffer(apiUrl).append("/wxa/getwxacodeunlimit?access_token=").append(getWeChatToken().getAccessToken()).toString();
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        
        //设置请求参数
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(Const.WeChat.SCENE, scene);
        if (!StringUtils.isEmpty(page)) {
            map.put(Const.WeChat.PAGE, page);
        }
        map.put(Const.WeChat.WIDTH, width);
        map.put(Const.WeChat.AUTO_COLOR, auto_color);
        if (!auto_color)
            map.put(Const.WeChat.LINE_COLOR, line_color);
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
    }
    
    
    /**
     * 接口C：适用于需要的码数量较少的业务场景 接口地址：
     * https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN
     *
     * @param path  不能为空，最大长度 128 字节
     * @param width 二维码的宽度
     * @return 二维码在对象存储的URL
     */
    public ResponseEntity<byte[]> createWXAQRCode(String path, int width) throws WeChatException {
        String url = new StringBuffer(apiUrl).append("/cgi-bin/wxaapp/createwxaqrcode?access_token=").append(getWeChatToken().getAccessToken()).toString();
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        //设置请求参数
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(Const.WeChat.PATH, path);
        map.put(Const.WeChat.WIDTH, width);
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
    }
    
    private synchronized WeChatToken updateToken() throws WeChatException {
        
        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        
        // Build Uri
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(new StringBuffer(apiUrl).append("/cgi-bin/token").toString())
                                               .queryParam(Const.WeChat.APPID, appId)
                                               .queryParam(Const.WeChat.SECRET, secret)
                                               .queryParam(Const.WeChat.GRANT_TYPE, grantType);
        
        // Build RequestEntity And Exchange ResponseEntity
        HttpEntity<Map> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                httpEntity,
                Map.class
        );
        
        
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Map<String, Object> body = response.getBody();
            if (body != null && !body.isEmpty()) {
                if (body.containsKey(Const.WeChat.ERRCODE)) {
                    Integer errCode = (Integer) body.getOrDefault(Const.WeChat.ERRCODE, -1);
                    String errMsg = (String) body.getOrDefault(Const.WeChat.ERRMSG, "无法获得错误信息");
                    log.debug("ErrCode: {}, ErrMsg: {}", errCode, errMsg);
                    throw new WeChatException(errMsg);
                }
            }
            WeChatToken token = new WeChatToken();
            String accessToken = (String) body.get(Const.WeChat.ACCESS_TOKEN);
            token.setAccessToken(accessToken);
            Integer expiresIn = (Integer) body.get(Const.WeChat.EXPIRES_IN);
            if (response.getHeaders() != null && !response.getHeaders().isEmpty()) {
                Date createTime = new Date(response.getHeaders().getDate());
                Date expiresTime = new Date(createTime.getTime() + (expiresIn * 1000));
                token.setCreateTime(createTime);
                token.setExpiresTime(expiresTime);
            }
            return token;
        }
        throw new WeChatException("Http请求异常");
    }
    
    
    /**
     * 提前5分钟刷新Token
     *
     * @param expiresTime
     * @return
     */
    private boolean isTime2Refresh(Date expiresTime) {
        long diff = TimeUtil.getTimeDiff(new Date(), expiresTime);
        if (diff >= 0 || (-diff) < (beforeMinutes * 60))
            return true;
        return false;
    }
    
    
    /**
     * 获取 ValueOperations
     *
     * @return 如果正常连接Redis数据库则返回ValueOperations 否则返回Null
     */
    private ValueOperations getValueOperations() {
        if (valueOperations == null)
            if (redisTemplate != null)
                valueOperations = redisTemplate.opsForValue();
            else
                return null;
        return redisTemplate.opsForValue();
    }
    
    /**
     * https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
     *
     * @param code
     * @return
     */
    @Override
    public ResponseEntity<String> code2Session(String code) {
        checkInitialized();
        String uriString = UriComponentsBuilder.fromHttpUrl("https://api.weixin.qq.com/sns/jscode2session")
                                   .queryParam("appid", this.appId)
                                   .queryParam("secret", this.secret)
                                   .queryParam("js_code", code)
                                   .queryParam("grant_type", "authorization_code")
                                   .toUriString();
    
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Map> httpEntity = new HttpEntity<>(headers);
    
    
        return restTemplate.exchange(uriString, HttpMethod.GET, httpEntity, String.class);
    }
    
    /**
     * 确认微信已经初始化
     */
    private void checkInitialized() {
        if (StringUtils.isEmpty(this.appId) || StringUtils.isEmpty(this.secret))
            throw new WeChatException("微信初始化错误");
    }
}