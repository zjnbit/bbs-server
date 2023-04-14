package com.zjnbit.bbs.api.framework.config;

import cn.hutool.extra.mail.MailAccount;
import com.zjnbit.bbs.api.framework.prop.MailProperties;
import com.zjnbit.bbs.api.framework.template.EmailTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({MailProperties.class})
public class NoticeConfiguration {

    private MailProperties emailConf;

    public NoticeConfiguration(MailProperties emailConf) {
        this.emailConf = emailConf;
    }

    @Bean
    @ConditionalOnMissingBean(EmailTemplate.class)
    public EmailTemplate emailTemplate() {
        MailAccount account = new MailAccount();
        account.setHost(emailConf.getHost());
        account.setPort(emailConf.getPort());
        account.setAuth(true);
        account.setFrom(emailConf.getFrom());
        account.setUser(emailConf.getUser());
        account.setPass(emailConf.getPass());
        account.setSslEnable(emailConf.getSslEnable());
        return new EmailTemplate(account);
    }
}
