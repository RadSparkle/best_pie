package com.jkb.bestpie.api.domain.community.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.jkb.bestpie.api.domain.community.controller.CommunityRestController.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    @GetMapping("/")
    public String getCommunitiesView(Model model) {
        List<CommunityDto> dcinsideList = fetchData("/api/v1/community/dcincideList");
        List<CommunityDto> nateList = fetchData("/api/v1/community/nateList");
        List<CommunityDto> bobaeList = fetchData("/api/v1/community/bobaeList");

        model.addAttribute("dcinside", dcinsideList);
        model.addAttribute("nate", nateList);
        model.addAttribute("bobae", bobaeList);
        return "community/communityList";
    }

    private List<CommunityDto> fetchData(String endpoint) {
        ResponseEntity<List<CommunityDto>> response = restTemplate.exchange(
                apiUrl + endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CommunityDto>>() {}
        );
        return response.getBody();
    }
}