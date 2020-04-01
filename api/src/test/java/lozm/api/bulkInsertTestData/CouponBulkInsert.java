package lozm.api.bulkInsertTestData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lozm.core.code.CouponType;
import lozm.core.dto.coupon.PostCouponDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CouponBulkInsert {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void setCoupons() {
        setCoupon(CouponType.PRICE.name());
        setCoupon(CouponType.RATIO.name());
    }

    public void setCoupon(String itemType) {
        try {
            Faker faker = new Faker();

            for(int i=0; i<100; i++) {
                PostCouponDto.Request reqDto = PostCouponDto.Request.setRequestTestData(
                    faker.book().title(),
                    faker.book().publisher(),
                    itemType,
            ThreadLocalRandom.current().nextLong(100, 999) * 10,
                    ThreadLocalRandom.current().nextLong(1, 200),

                );
                postCoupon(reqDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postCoupon(PostCouponDto.Request reqDto) throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqDto))
        );

        result.andExpect(status().is(200));
        System.out.println("result = " + result);
    }

    private void getStartDt() {
        long minDay = LocalDateTime.of(2014, 1, 1, 0, 0, 0).toEpochDay();
        long maxDay = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
//        LocalDateTime randomDate = LocalDateTime.ofEpochSecond(randomDay);
    }

    private void getEndDt() {

    }

}
