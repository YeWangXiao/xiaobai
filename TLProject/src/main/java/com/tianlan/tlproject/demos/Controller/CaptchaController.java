package com.tianlan.tlproject.demos.Controller;
import com.tianlan.tlproject.demos.Utils.CaptchaGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.util.RandomUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private final CaptchaGenerator captchaGenerator;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CaptchaController(CaptchaGenerator captchaGenerator) {
        this.captchaGenerator = captchaGenerator;
    }

    @GetMapping("/generate")
    public void generateCaptcha(HttpServletResponse response, HttpSession session) throws IOException {

        //生成4位随机字符串
        String captchaText = RandomUtil.randomString(4);
        //把验证码存入session中
/*
        session.setAttribute("Verificationcod" +
                "" +
                "" +
                "e", captchaText);
*/

        //把验证码存入redis中,设置过期时间为60秒
        stringRedisTemplate.opsForValue().set("Verificationcode", captchaText);
        //设置过期时间,单位为秒,这里设置为60秒
        stringRedisTemplate.expire("Verificationcode", Duration.ofSeconds(60));

        System.out.println(stringRedisTemplate.opsForValue().get("Verificationcode"));

        byte[] captchaImageBytes = captchaGenerator.getCaptchaImageBytes(captchaText);

        //设置响应头，以便浏览器不缓存
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        //设置响应类型和长度
        response.setContentType("image/jpeg");
        response.setContentLength(captchaImageBytes.length);
        //写入验证码图片字节
        response.getOutputStream().write(captchaImageBytes);
        //刷新缓冲区
        response.getOutputStream().flush();
        //关闭缓冲区
        response.getOutputStream().close();
    }
}
