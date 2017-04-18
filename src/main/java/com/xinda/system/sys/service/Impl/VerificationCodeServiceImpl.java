package com.xinda.system.sys.service.Impl;

import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.system.sys.exception.SysException;
import com.xinda.system.sys.service.IVerificationCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码实现类.
 *
 * @Author Coundy.
 * @Date 2017/3/28 14:32.
 */
@Service
@Transactional
public class VerificationCodeServiceImpl implements IVerificationCodeService {

    @Override
    public String generateVerificationCode() {
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuilder randomCode = new StringBuilder();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < BaseConstants.VERIFICATION_CODE_COUNT; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(BaseConstants.VERIFICATION_CODE_ARRAY[random.nextInt(BaseConstants.VERIFICATION_CODE_ARRAY.length)]);
            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        return randomCode.toString();
    }

    @Override
    public void generateVerificationCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String verificationCode = generateVerificationCode();
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("verificationCode", verificationCode);

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(BaseConstants.VERIFICATION_CODE_WIDTH,
                BaseConstants.VERIFICATION_CODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 设定图像背景色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, BaseConstants.VERIFICATION_CODE_WIDTH, BaseConstants.VERIFICATION_CODE_HEIGHT);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, BaseConstants.VERIFICATION_CODE_FONT_HEIGHT);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, BaseConstants.VERIFICATION_CODE_WIDTH - 1, BaseConstants.VERIFICATION_CODE_HEIGHT - 1);

        // 随机产生多条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < BaseConstants.INTERFERE_LINE_COUNT; i++) {
            int x = random.nextInt(BaseConstants.VERIFICATION_CODE_WIDTH);
            int y = random.nextInt(BaseConstants.VERIFICATION_CODE_HEIGHT);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        int sw = Math.floorDiv(BaseConstants.VERIFICATION_CODE_WIDTH, verificationCode.length());
        for (int i = 0; i < verificationCode.length(); i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(verificationCode.charAt(i));
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, i * sw, 26);
        }

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到输出流中。
        try (OutputStream ignored = response.getOutputStream()) {
            ImageIO.write(buffImg, "jpeg", response.getOutputStream());
        }

    }

    @Override
    public void valiLoginVerificationCode(HttpServletRequest request) throws SysException {
        //获取Session中存储的验证码Code
        HttpSession session = request.getSession();
        String sessionCode = String.valueOf(session.getAttribute("verificationCode"));
        //获取前台参数
        String verificationCode = request.getParameter("verificationCode");
        //移除Session属性
        session.removeAttribute("verificationCode");
        if (session == null || StringUtils.isEmpty(verificationCode)
                || !verificationCode.equalsIgnoreCase(sessionCode)) {
            throw new SysException("SYS", SysException.MSG_ERROR_SYS_VERIFICATION_CODE_ERROR);
        }
    }
}
