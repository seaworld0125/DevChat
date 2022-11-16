package com.ntt.app.translate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * packageName    : com.ntt.app.translate
 * fileName       : NotionSearchDto
 * author         : Kim
 * date           : 2022-11-11
 */
@Getter
public class NotionSearchDto {

    @JsonProperty("results")
    private List<Results> results;

    @JsonProperty("object")
    private String object;

    @Getter
    public static class Results {
        @JsonProperty("url")
        private String url;

        @JsonProperty("properties")
        private Properties properties;

        @JsonProperty("archived")
        private boolean archived;

        @JsonProperty("parent")
        private Parent parent;

        @JsonProperty("last_edited_time")
        private String lastEditedTime;

        @JsonProperty("id")
        private String id;

        @JsonProperty("object")
        private String object;
    }

    @Getter
    public static class Properties {
        @JsonProperty("title")
        private Title title;
    }

    @Getter
    public static class Title {
        @JsonProperty("title")
        private List<InnerTitle> title;
    }

    @Getter
    public static class InnerTitle {
        @JsonProperty("plain_text")
        private String plainText;
    }

    @Getter
    public static class Parent {
        @JsonProperty("workspace")
        private boolean workspace;

        @JsonProperty("type")
        private String type;
    }
}
