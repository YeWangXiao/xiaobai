package com.tianlan.tlproject.demos.Utils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Component
public class CaptchaGenerator {

    private final DefaultKaptcha defaultKaptcha;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CaptchaGenerator(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }

    public BufferedImage generateCaptchaImage(String captchaText) {
        return defaultKaptcha.createImage(captchaText);
    }

    // 获取验证码图片的字节
    public byte[] getCaptchaImageBytes(String captchaText) {
        // 根据验证码文本生成验证码图片
        BufferedImage image = generateCaptchaImage(captchaText);
        // 创建一个字节数组输出流，用于将图片转换为字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            // 使用ImageIO将图片写入字节数组输出流
            ImageIO.write(image, "jpg", outputStream);
        } catch (Exception e) {
            // 打印异常堆栈信息
            e.printStackTrace();
        }
        // 返回图片的字节数组
        return outputStream.toByteArray();
    }

    //验证验证码
    public  boolean verifyCaptcha(HttpServletRequest request, String captchaText) {
        /*HttpSession session = request.getSession();
        String verificationcode = (String) session.getAttribute("Verificationcode");*/

        //判断验证码是否过期
        if (!stringRedisTemplate.hasKey("Verificationcode")) {
            return false;
        }

        //从redis中获取验证码
        String verificationcode = stringRedisTemplate.opsForValue().get("Verificationcode");

        return captchaText.equalsIgnoreCase(verificationcode);
    }


}
