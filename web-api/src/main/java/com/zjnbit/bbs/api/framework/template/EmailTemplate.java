package com.zjnbit.bbs.api.framework.template;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/30 08:57
 * @Description
 **/
@Slf4j
public  class EmailTemplate {
    private MailAccount account;

    public EmailTemplate(MailAccount account) {
        this.account = account;
    }

    public String sendSimpleMail(MailAccount account, String to, String subject, String content) {
        if (null == account) {
            return this.sendSimpleMail(to, subject, content);
        }
        String result = MailUtil.send(account, to, subject, content, false);
        log.info("send simple email to 【{}】 with subject 【{}】 and content 【{}】 result is 【{}】", to, subject, content, result);
        return result;
    }

    public String sendSimpleMail(String to, String subject, String content) {
        return sendSimpleMail(account, to, subject, content);
    }

}
