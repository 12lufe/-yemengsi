package com.sky.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class MockPayUtil {

    public JSONObject pay(String outTradeNo, BigDecimal total, String description, String openid) {
        JSONObject jsonObject = new JSONObject();
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = generateNonceStr();
        String prepayId = "mock_" + System.currentTimeMillis();

        jsonObject.put("timeStamp", timeStamp);
        jsonObject.put("nonceStr", nonceStr);
        jsonObject.put("signType", "RSA");
        jsonObject.put("package", "prepay_id=" + prepayId);
        jsonObject.put("paySign", "MOCK_SIGN_" + nonceStr.substring(0, 10));

        return jsonObject;
    }

    private String generateNonceStr() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}