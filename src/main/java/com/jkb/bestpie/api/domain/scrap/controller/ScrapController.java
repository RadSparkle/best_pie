package com.jkb.bestpie.api.domain.scrap.controller;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;
import com.jkb.bestpie.api.domain.scrap.service.ScrapServiceImpl;
import com.jkb.bestpie.common.config.ScrapeConfig;
import com.jkb.bestpie.common.utils.SSL;
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
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Component
@AllArgsConstructor
@Log4j2
public class ScrapController {

    private ScrapServiceImpl scrapService;

    private SSL ssl;

    private ScrapeConfig scrapeConfig;

    private final static String DCINSIDE = "DCINSIDE";

    private final static String CLIEN = "CLIEN";

    private final static String NATE = "NATE";

    private final static String BOBAE = "BOBAE";

    @Scheduled(fixedRate = 600000)
    public void dcinsideScrape() {
        Community community = new Community();
        Elements elements = getWebPage(scrapeConfig.getDcinsideBestUrl()).select(scrapeConfig.getDcinsidePostListCssQuery());

        for(Element element : elements) {
            community.setUrl(URLDecoder.decode(element.select(scrapeConfig.getDcinsideUrlCssQuery()).attr("href"), StandardCharsets.UTF_8));
            community.setTitle(element.selectFirst("a").text());
            community.setSiteName(DCINSIDE);

            log.info("DCINSIDE SCRAPE START : {}", community.getTitle());
            scrapService.saveScrap(community);
            log.info("DCINSIDE SCRAPE DONE : {}", community.getTitle());
        }
    }

    @Scheduled(fixedRate = 600000)
    public void clienScrape() {
        Community community = new Community();

        Elements elements = getWebPage(scrapeConfig.getClienBestUrl()).select(scrapeConfig.getClienPostListCssQuery());

        for(Element element : elements) {
            community.setUrl(URLDecoder.decode(element.select(scrapeConfig.getClienUrlCssQuery()).attr("href"), StandardCharsets.UTF_8));
            community.setTitle(element.select(scrapeConfig.getClienUrlCssQuery()).attr("title"));
            community.setSiteName(CLIEN);

            log.info("CLIEN SCRAPE START : {}", community.getTitle());
            scrapService.saveScrap(community);
            log.info("CLIEN SCRAPE DONE : {}", community.getTitle());
        }
    }

    @Scheduled(fixedRate = 600000)
    public void natePanScrape() {
        Community community = new Community();
        Elements elements = getWebPage(scrapeConfig.getNateBestUrl()).select(scrapeConfig.getNatePostListCssQuery()).select("li");
        for(Element element : elements) {
            String url = scrapeConfig.getNateHomeUrl() + URLDecoder.decode(element.select("a").attr("href"), StandardCharsets.UTF_8);

            community.setUrl(url);
            community.setTitle(element.select("h2").text());
            community.setSiteName(NATE);

            log.info("NATE SCRAPE START : {}", community.getTitle());
            scrapService.saveScrap(community);
            log.info("NATE SCRAPE DONE : {}", community.getTitle());
        }
    }

    @Scheduled(fixedRate = 600000)
    public void bobaeScrape() {
        Community community = new Community();
        Elements elements = getWebPage(scrapeConfig.getBobaeBestUrl()).select(scrapeConfig.getBobaePostListCssQuery()).select("tbody").select("tr");
        for(Element element : elements) {
            community.setUrl(scrapeConfig.getBobaeHomeUrl() + element.select(scrapeConfig.getBobaeUrlCssQuery()).attr("href"));
            community.setTitle(element.select(scrapeConfig.getBobaeTitleCssQuery()).text());
            community.setSiteName(BOBAE);

            log.info("BOBAE SCRAPE START : {}", community.getTitle());
            scrapService.saveScrap(community);
            log.info("BOBAE SCRAPE DONE : {}", community.getTitle());
        }
    }

    public Document getWebPage(String url) {
        try {
            ssl.setSSL();
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Can't get web page : {}, {}", url, e);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
