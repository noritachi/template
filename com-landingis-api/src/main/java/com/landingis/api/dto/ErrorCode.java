package com.landingis.api.dto;

public class ErrorCode {

    /**
     * General error code
     */
    public static final String GENERAL_ERROR_UNAUTHORIZED = "ERROR-GENERAL-000";
    public static final String GENERAL_ERROR_NOT_FOUND = "ERROR-GENERAL-001";
    public static final String GENERAL_ERROR_BAD_REQUEST = "ERROR-GENERAL-002";
    public static final String GENERAL_ERROR_LOGIN_FAILED = "ERROR-GENERAL-003";
    public static final String GENERAL_ERROR_NOT_MATCH = "ERROR-GENERAL-004";
    public static final String GENERAL_ERROR_WRONG_HASH = "ERROR-GENERAL-005";
    public static final String GENERAL_ERROR_LOCKED = "ERROR-GENERAL-006";
    public static final String GENERAL_ERROR_INVALID = "ERROR-GENERAL-007";

    /**
     * Category error code
     */
    public static final String CATEGORY_ERROR_UNAUTHORIZED = "ERROR-CATEGORY-000";
    public static final String CATEGORY_ERROR_NOT_FOUND = "ERROR-CATEGORY-001";

    /**
     * Course error code
     */
    public static final String COURSE_ERROR_UNAUTHORIZED = "ERROR-COURSE-000";
    public static final String COURSE_ERROR_NOT_FOUND = "ERROR-COURSE-001";

    /**
     * Article error code
     */
    public static final String ARTICLE_ERROR_UNAUTHORIZED = "ERROR-ARTICLE-000";
    public static final String ARTICLE_ERROR_NOT_FOUND = "ERROR-ARTICLE-001";

    /**
     * Video error code
     */
    public static final String VIDEO_ERROR_UNAUTHORIZED = "ERROR-VIDEO-000";
    public static final String VIDEO_ERROR_NOT_FOUND = "ERROR-VIDEO-001";

    /**
     * Course error code
     */
    public static final String QUIZ_ERROR_UNAUTHORIZED = "ERROR-QUIZ-000";
    public static final String QUIZ_ERROR_NOT_FOUND = "ERROR-QUIZ-001";

    /**
     * Group error code
     */
    public static final String GROUP_ERROR_UNAUTHORIZED = "ERROR-GROUP-000";
    public static final String GROUP_ERROR_NOT_FOUND = "ERROR-GROUP-001";
    public static final String GROUP_ERROR_EXIST = "ERROR-GROUP-002";
    public static final String GROUP_ERROR_CAN_NOT_DELETED = "ERROR-GROUP-003";

    /**
     * Permission error code
     */
    public static final String PERMISSION_ERROR_UNAUTHORIZED = "ERROR-PERMISSION-000";
    public static final String PERMISSION_ERROR_NOT_FOUND = "ERROR-PERMISSION-001";

    /**
     * News error code
     */
    public static final String NEWS_ERROR_UNAUTHORIZED = "ERROR-NEWS-000";
    public static final String NEWS_ERROR_NOT_FOUND = "ERROR-NEWS-001";
    

    private ErrorCode() { throw new IllegalStateException("Utility class"); }
}
