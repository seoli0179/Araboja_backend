package com.example.araboja.service.parser;

import com.example.araboja.data.dto.dailyPriceByCategoryList.DailyPriceByCategoryListDto;
import com.example.araboja.data.dto.dailyPriceByCategoryList.MyItem;
import com.example.araboja.data.dto.dailySalesList.DailySalesListDto;
import com.example.araboja.data.dto.dailySalesList.EconomicalProductResponse;
import com.example.araboja.data.dto.dailySalesList.PriceInfo;
import com.example.araboja.data.entity.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class KamisAPI {

    private final RestTemplate restTemplate;

    @Value("${spring.kamis.secret}")
    private String p_cert_key;

    @Value("${spring.kamis.id}")
    private String p_cert_id;

    private final String p_returntype = "json"; // (json:Json 데이터 형식, xml:XML데이터형식)

    //일별 부류별 도.소매가격정보
    public String dailyPriceByCategoryList(String category, String date) {

        String str = "";
        //String p_product_cls_code = "02"; // 구분 ( 01:소매, 02:도매, default:02 )
        //String p_item_category_code = "100"; //부류코드(100:식량작물, 200:채소류, 300:특용작물, 400:과일류, 500:축산물, 600:수산물, default:100)
        //String p_country_code = "1101"; //* 소매가격 선택가능 지역 (1101:서울, 2100:부산, 2200:대구, 2300:인천, 2401:광주, 2501:대전, 2601:울산, 3111:수원, 3211:춘천, 3311:청주, 3511:전주, 3711:포항, 3911:제주, 3113:의정부, 3613:순천, 3714:안동, 3814:창원, 3145:용인) 도매가격 선택가능 지역 (1101:서울, 2100:부산, 2200:대구, 2401:광주, 2501:대전)  default : 전체지역
        //String p_regday = "2024-03-23"; // 날짜 : yyyy-mm-dd (default : 최근 조사일자) 공휴일 또는 일요일에는 조사 안함 data=001
        //String p_convert_kg_yn = "N"; // kg단위 환산여부(Y : 1kg 단위표시, N : 정보조사 단위표시, ex: 쌀 20kg) default : N


        String url = "https://www.kamis.or.kr/service/price/xml.do?action=dailyPriceByCategoryList" + "&"
                + "p_cert_key=" + p_cert_key + "&"
                + "p_cert_id=" + p_cert_id + "&"
                + "p_item_category_code=" + category + "&"
                + "p_regday=" + date + "&"
                + "p_returntype=" + p_returntype;

        System.out.println(url);

        restTemplate.getInterceptors().add(((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        }));

        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());

        try {

            ResponseEntity<DailyPriceByCategoryListDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, DailyPriceByCategoryListDto.class);
            System.out.println(response.getBody());

            List<MyItem> itemList = Objects.requireNonNull(response.getBody()).getData().getItem();
            for (MyItem item : itemList) {
                str += item.toString();
                System.out.println(item);
            }

            return str;

        } catch (Exception e) {
            return e.getMessage();
        }


    }

    public List<EconomicalProductResponse> dailySalesList() throws JsonProcessingException {

        List<EconomicalProductResponse> economicalProductResponses = new ArrayList<>();


        URI uri = UriComponentsBuilder
                .fromUriString("https://www.kamis.or.kr")
                .path("service/price/xml.do")
                .queryParam("action", "dailySalesList")
                .queryParam("p_cert_key", p_cert_key)
                .queryParam("p_cert_id", p_cert_id)
                .queryParam("p_returntype", p_returntype)
                .encode().build().toUri();

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONObject json = new JSONObject(response.getBody());

        // condition 필드 처리
        JSONArray conditionArray = json.getJSONArray("condition");
        List<List<String>> condition = new ArrayList<>();
        for (int i = 0; i < conditionArray.length(); i++) {
            JSONArray innerArray = conditionArray.getJSONArray(i);
            List<String> innerList = new ArrayList<>();
            for (int j = 0; j < innerArray.length(); j++) {
                innerList.add(innerArray.getString(j));
            }
            condition.add(innerList);
        }

        // price 필드 처리
        JSONArray priceArray = json.getJSONArray("price");
        List<PriceInfo> priceList = new ArrayList<>();
        for (int i = 0; i < priceArray.length(); i++) {
            JSONObject priceObject = priceArray.getJSONObject(i);
            PriceInfo priceInfo = new PriceInfo();
            // priceInfo 객체의 필드 설정
            // 여기에서는 수동으로 필드 설정을 수행해야 합니다.
            // 예를 들어, priceInfo.setProductName(priceObject.getString("productName")) 등

            priceInfo.setProduct_cls_code(priceObject.getString("product_cls_code"));
            priceInfo.setProduct_cls_name(priceObject.getString("product_cls_name"));
            priceInfo.setCategory_code(priceObject.getString("category_code"));
            priceInfo.setCategory_name(priceObject.getString("category_name"));
            priceInfo.setProductno(priceObject.getString("productno"));
            priceInfo.setLastest_day(priceObject.getString("lastest_day"));
            priceInfo.setProductName(priceObject.getString("productName"));
            priceInfo.setItem_name(priceObject.getString("item_name"));
            priceInfo.setUnit(priceObject.getString("unit"));
            priceInfo.setDay1(priceObject.getString("day1"));
            priceInfo.setDay2(priceObject.getString("day2"));
            priceInfo.setDay3(priceObject.getString("day3"));
            priceInfo.setDay4(priceObject.getString("day4"));
            //priceInfo.setDirection(priceObject.getString("direction"));
            //priceInfo.setValue(priceObject.getString("value"));

            Object test = priceObject.get("dpr1");
            if (test instanceof String) {
                priceInfo.setDpr1(Integer.parseInt(priceObject.getString("dpr1").replace(",","")));
            } else {
                priceInfo.setDpr1(0);
            }
            test = priceObject.get("dpr2");
            if (test instanceof String) {
                priceInfo.setDpr2(Integer.parseInt(priceObject.getString("dpr2").replace(",","")));
            } else {
                priceInfo.setDpr2(0);
            }
            test = priceObject.get("dpr3");
            if (test instanceof String) {
                priceInfo.setDpr3(Integer.parseInt(priceObject.getString("dpr3").replace(",","")));
            } else {
                priceInfo.setDpr3(0);
            }
            test = priceObject.get("dpr4");
            if (test instanceof String) {
                priceInfo.setDpr4(Integer.parseInt(priceObject.getString("dpr4").replace(",","")));
            } else {
                priceInfo.setDpr4(0);
            }

            if(priceInfo.getDpr1() == 0 || priceInfo.getDpr4() == 0){
                priceInfo.setPriceDiff(Double.MAX_VALUE);
            }else {
                priceInfo.setPriceDiff(calculatePercentageDifference(priceInfo.getDpr4(), priceInfo.getDpr1()));
            }



            priceList.add(priceInfo);
        }

        priceList.sort(Comparator.comparing(PriceInfo::getPriceDiff));
        priceList.subList(3, priceList.size()).clear();

        for(PriceInfo priceInfo : priceList) {

            EconomicalProductResponse dto = new EconomicalProductResponse();
            dto.setProductNo(priceInfo.getProductno());
            dto.setProductName(priceInfo.getProductName());
            dto.setPrice(priceInfo.getDpr1());
            dto.setPriceLastYear(priceInfo.getDpr4());
            dto.setUnit(priceInfo.getUnit());
            dto.setPriceChangeRate(priceInfo.getPriceDiff());

            economicalProductResponses.add(dto);

            System.out.println(dto.getPriceChangeRate());

        }

        return economicalProductResponses;


    }

    public static double calculatePercentageDifference(int price1, int price2) {
        // 두 가격의 차이를 계산
        double difference = price2 - price1;
        return (difference / price1) * 100;
    }

}

