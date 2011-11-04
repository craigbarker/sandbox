package org.sgodden.test;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

public class JsonAnnotationTest {

    @Test
    public void basicTest() throws Exception {

        MyJsonObject foo = new MyJsonObject();
        foo.setFoo("FOO");
        foo.setBar(23);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        String json = mapper.writeValueAsString(foo);

        System.out.println(json);

        MyJsonObject object = new ObjectMapper().readValue(json, MyJsonObject.class);

        assertEquals(object.getFoo(), foo.getFoo());
        assertEquals(object.getBar(), foo.getBar());

    }


    public static class MyJsonObject {

        private String foo;

        private int bar;

        private Date date = new Date();

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public int getBar() {
            return bar;
        }

        public void setBar(int bar) {
            this.bar = bar;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
