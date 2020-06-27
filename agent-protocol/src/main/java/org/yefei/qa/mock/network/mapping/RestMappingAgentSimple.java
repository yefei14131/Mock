package org.yefei.qa.mock.network.mapping;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author yefei
 * @date: 2020/4/27
 */
@Data
public class RestMappingAgentSimple implements Serializable {

    private String host;
    private String uri;
    private String uriPrefix;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestMappingAgentSimple)) return false;
        if (!super.equals(o)) return false;
        RestMappingAgentSimple that = (RestMappingAgentSimple) o;
        return Objects.equals(host, that.host) &&
                Objects.equals(uri, that.uri) &&
                Objects.equals(uriPrefix, that.uriPrefix);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), host, uri, uriPrefix);
    }
}
