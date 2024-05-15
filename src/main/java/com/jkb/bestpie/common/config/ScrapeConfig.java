package com.jkb.bestpie.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ScrapeConfig {

    @Value("${scrape.best.url.dcinside}")
    private String dcinsideBestUrl;

    @Value("${scrape.best.url.clien}")
    private String clienBestUrl;

    @Value("${scrape.best.url.nate}")
    private String nateBestUrl;

    @Value("${scrape.best.url.bobae}")
    private String bobaeBestUrl;

    @Value("${scrape.home.url.dcinside}")
    private String dcinsideHomeUrl;

    @Value("${scrape.best.url.clien}")
    private String clienHomeUrl;

    @Value("${scrape.best.url.nate}")
    private String nateHomeUrl;

    @Value("${scrape.best.url.bobae}")
    private String bobaeHomeUrl;

    @Value("${scrape.css_query.dcinside.post_list}")
    private String dcinsidePostListCssQuery;

    @Value("${scrape.css_query.dcinside.url}")
    private String dcinsideUrlCssQuery;

    @Value("${scrape.css_query.dcinside.reg_date}")
    private String dcinsideRegDateCssQuery;

    @Value("${scrape.css_query.clien.post_list}")
    private String clienPostListCssQuery;

    @Value("${scrape.css_query.clien.url}")
    private String clienUrlCssQuery;

    @Value("${scrape.css_query.clien.title}")
    private String clienTitleCssQuery;

    @Value("${scrape.css_query.clien.reg_date}")
    private String clienRegDateCssQuery;

    @Value("${scrape.css_query.nate.post_list}")
    private String natePostListCssQuery;

    @Value("${scrape.css_query.nate.reg_date}")
    private String nateRegDateCssQuery;

    @Value("${scrape.css_query.bobae.post_list}")
    private String bobaePostListCssQuery;

    @Value("${scrape.css_query.bobae.url}")
    private String bobaeUrlCssQuery;

    @Value("${scrape.css_query.bobae.title}")
    private String bobaeTitleCssQuery;
}
