package com.ice.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Description:服务伪造
 * Cteated by wangpeng
 * 2018/3/10 1:10
 */
public class MockService {

    public static void main(String[] args) throws IOException {
        configureFor(8062);
        removeAllMappings();

        mock("/order/1","01");
        mock("/user/1","02");

    }

    private static void mock(String url, String file) throws IOException {

        ClassPathResource resource = new ClassPathResource("moceresponse/"+ file +".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8").toArray(),"\n");
        stubFor(get(urlPathEqualTo(url))
                .willReturn(aResponse().withBody(content)
                        .withStatus(200)));
    }
}
