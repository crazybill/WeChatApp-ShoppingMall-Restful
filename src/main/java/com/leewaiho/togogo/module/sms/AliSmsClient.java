package com.leewaiho.togogo.module.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.leewaiho.togogo.common.Const.ServiceCode;
import com.leewaiho.togogo.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
@Component
public class AliSmsClient {
    
    private static final Logger log = LoggerFactory.getLogger(AliSmsClient.class);
    private static final String PRODUCT = "Dysmsapi"; //短信API产品名称（短信产品名固定，无需修改）
    private static final String DOMAIN = "dysmsapi.aliyuncs.com"; //短信API产品域名（接口地址固定，无需修改）
    private static final String REGION_ID = "cn-hangzhou";
    private static final String ENDPOINT_NAME = "cn-hangzhou";
    
    static {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    }
    
    @Value("${sms.accesskey.id}")
    private String accessKeyId;
    
    @Value("${sms.accesskey.secret}")
    private String accessKeySecret;
    
    @Value("${sms.signName}")
    private String signName;
    
    @Value("${sms.templateCode}")
    private String templateCode;
    
    public String sendCode(String phoneNumber, String code) throws ClientException {
        //设置超时时间-可自行调整
        
        IClientProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint(ENDPOINT_NAME, REGION_ID, PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        String templateParam = "{\"code\":\"" + code + "\"}";
        request.setTemplateParam(templateParam);
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            return sendSmsResponse.getMessage();
        }
        log.error("Code: {}, Message:{}, BizId:{}, RequestId:{} ", sendSmsResponse.getCode(), sendSmsResponse.getMessage(), sendSmsResponse.getBizId(), sendSmsResponse.getRequestId());
        throw new ServiceException(ServiceCode.UNKNOWED, "短信服务异常,请通知管理员查看");
    }
}
