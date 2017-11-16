package com.b2s.wiki.model;

import java.util.Random;

public class AppConstants {
    public static final int PAGE_SIZE = 20;
    public static final int CONTENT_LENGTH = 300;

    public static final String PRE_HIGH_TAG = "<font color='red'>";
    public static final String POST_HIGH_TAG = "</font>";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final class FIELD {
        public static final String ID = "id";
        public static final String ID_USED_TO_DELETE = "idUsedToDelete";
        public static final String TITLE = "title";
        public static final String AUTHOR = "author";
        public static final String CREATE_DATE = "createDate";
        public static final String CONTENT = "content";
        public static final String CREATE_DATE_TIME = "createDateTime";
        public static final String DEFAULT_INDEX = "html";
    }
}
