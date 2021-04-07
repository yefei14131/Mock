package org.apache.skywalking.apm.mock.agent.core.mock.rest.bean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author yefei
 * @date: 2020/4/23
 */
@Data
public class MockResponse {
    private byte[] body;
    private Headers headers;

    public String getContentType() {
        return headers.get("content-type");
    }

    public static class Headers {

        private HashMap<String, List<String>> headers = new HashMap<>();

        public Headers(okhttp3.Headers headers) {
            headers.toMultimap().forEach((name, value) -> {
                checkName(name);
                checkValue(value);
                this.headers.put(name, value);
            });
        }

        private void checkName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("header name must not be null");
            }
        }

        private void checkValue(List<String> value) {
            if (value == null) {
                throw new IllegalArgumentException("header value must not be null");
            }
            if (value.size() == 0) {
                throw new IllegalArgumentException("header value must not be empty");
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("{");
            this.forEach((name, values) -> {
                builder.append("\"");
                builder.append(name);
                builder.append("\"");
                builder.append(":");
                builder.append("[");
                values.forEach(value -> {
                    builder.append("\"");
                    builder.append(value);
                    builder.append("\"");
                    builder.append(",");
                });
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.append("]");

                builder.append(",");
            });
            builder.deleteCharAt(builder.lastIndexOf(","));

            builder.append("}");
            return builder.toString();
        }

        public String get(String name) {
            List<String> values = getValues(name);
            return values == null ? null : values.get(0);
        }

        private List<String> getValues(String name) {
            return headers.get(name.toLowerCase());
        }

        public Collection<List<String>> names() {
            return headers.values();
        }

        public void forEach(BiConsumer<? super String, ? super List<String>> action) {
            headers.forEach(action);
        }

        public HashMap<String, String> toSingleValueMap() {
            HashMap<String, String> map = new HashMap<>();
            headers.forEach((name, value) -> map.put(name, value.get(0)));
            return map;
        }
    }

}
