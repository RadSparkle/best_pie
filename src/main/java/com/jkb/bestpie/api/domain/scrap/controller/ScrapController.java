package com.jkb.bestpie.api.domain.scrap.controller;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;
import com.jkb.bestpie.api.domain.scrap.service.ScrapServiceImpl;
import com.jkb.bestpie.common.config.ScrapeConfig;
import com.jkb.bestpie.common.utils.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
@AllArgsConstructor
@Log4j2
public class ScrapController {

    private ScrapServiceImpl scrapService;

    private TimeUtil timeUtil;

    private ScrapeConfig scrapeConfig;

    private static String DCINSIDE = "DCINSIDE";

    private static String CLIEN = "CLIEN";

    private static String NATE = "NATE";

    private static String BOBAE = "BOBAE";

    @Scheduled(fixedRate = 10000)
    public void dcinsideScrape() {
        Community community = new Community();
        try {
            Elements elements = getWebPage(scrapeConfig.getDcinsideBestUrl()).select(scrapeConfig.getDcinsidePostListCssQuery());

            for(Element element : elements) {
                community.setUrl(URLDecoder.decode(element.select(scrapeConfig.getDcinsidePostListCssQuery()).attr("href"), "UTF-8"));
                community.setTitle(element.selectFirst("a").text());
                community.setRegDate(timeUtil.getLocalDateTime(element.selectFirst(scrapeConfig.getDcinsideRegDateCssQuery()).attr("title")));
                community.setSiteName(DCINSIDE);

                scrapService.saveScrap(community);
            }
            log.info("DCINSIDE SCRAPE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 10000)
    public void clienScrape() throws UnsupportedEncodingException {
        Community community = new Community();

        Elements elements = getWebPage(scrapeConfig.getClienBestUrl()).select(scrapeConfig.getClienPostListCssQuery());

        for(Element element : elements) {
            community.setUrl(URLDecoder.decode(element.select(scrapeConfig.getClienUrlCssQuery()).attr("href"), "UTF-8"));
            community.setTitle(element.select(scrapeConfig.getClienUrlCssQuery()).attr("title"));
            community.setRegDate(timeUtil.getLocalDateTime(element.select(scrapeConfig.getClienRegDateCssQuery()).text()));
            community.setSiteName(CLIEN);

            scrapService.saveScrap(community);
        }
        log.info("CLIEN SCRAPE");
    }

    @Scheduled(fixedRate = 10000)
    public void natePanScrape() throws UnsupportedEncodingException {
        Community community = new Community();
        Elements elements = getWebPage(scrapeConfig.getNateBestUrl()).select(scrapeConfig.getNatePostListCssQuery()).select("li");
        for(Element element : elements) {
            String url = scrapeConfig.getNateHomeUrl() + URLDecoder.decode(element.select("a").attr("href"), "UTF-8");

            community.setUrl(url);
            community.setTitle(element.select("h2").text());
            community.setRegDate(timeUtil.getLocalDateTime(getWebPage(url).selectFirst(scrapeConfig.getNateRegDateCssQuery()).text()));
            community.setSiteName(NATE);

            scrapService.saveScrap(community);
        }
        log.info("NATE PAN SCRAPE");
    }

    @Scheduled(fixedRate = 10000)
    public void bobaeScrape() throws UnsupportedEncodingException {
        Community community = new Community();
        Elements elements = getWebPage(scrapeConfig.getBobaeBestUrl()).select(scrapeConfig.getBobaePostListCssQuery()).select("tbody").select("tr");
        for(Element element : elements) {
            community.setUrl(scrapeConfig.getBobaeHomeUrl() + element.select(scrapeConfig.getBobaeUrlCssQuery()).attr("href"));
            community.setTitle(element.select(scrapeConfig.getBobaeTitleCssQuery()).text());

            //시간 포맷이 일반적이지 않아서 일단 보류
//            community.setRegDate(timeUtil.getLocalDateTime(getWebPage(url).selectFirst("span.date").text()));
            community.setSiteName(BOBAE);

            scrapService.saveScrap(community);
        }
        log.info("BOBAE DREAM SCRAPE");
    }

    public Document getWebPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Can't get web page : {}", url);
        }
        return null;
    }
}
