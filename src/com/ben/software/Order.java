package com.ben.software;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Order {

    /**
     * Command to the service to update the data, receiving callbacks
     * from the service.  The Message's replyTo field must be a Messenger of
     * the client where callbacks should be sent.
     */
    public static final int MSG_UPDATE_DATA = 1;

    /**
     * Command to the service to send an order, receiving callbacks
     * from the service.  The Message's replyTo field must be a Messenger of
     * the client where callbacks should be sent.
     */
    public static final int MSG_SEND_ORDER = 2;

    /**
     * Command to the service to query the orders, receiving callbacks
     * from the service.  The Message's replyTo field must be a Messenger of
     * the client where callbacks should be sent.
     */
    public static final int MSG_QUERY_ORDERS = 3;

    public static final String AUTHORITY = "com.ben.software.db.OrderProvider";

    // This class cannot be instantiated
    private Order() {}

    /**
     * Cuisine table
     */
    public static final class CuisineColumns implements BaseColumns {
        // This class cannot be instantiated
        private CuisineColumns() {}

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/cuisines");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "_id DESC";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of cuisines.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.roadmap.cuisine";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single cuisine.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.roadmap.cuisine";

        /**
         * The name of the cuisine
         * <P>Type: TEXT</P>
         */
        public static final String NAME = "name";

        /**
         * The code of the cuisine
         * <P>Type: TEXT</P>
         */
        public static final String CODE = "code";

        /**
         * The price of the cuisine
         * <P>Type: TEXT</P>
         */
        public static final String PRICE = "price";

        /**
         * The discount of the cuisine
         * <P>Type: TEXT</P>
         */
        public static final String DISCOUNT = "discount";

        /**
         * The remark of the cuisine
         * <P>Type: TEXT</P>
         */
        public static final String REMARK = "remark";
    }

    /**
     * Target table
     */
    public static final class TargetColumns implements BaseColumns {
        // This class cannot be instantiated
        private TargetColumns() {}

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/targets");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "_id DESC";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of target.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.roadmap.target";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single target.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.roadmap.target";

        /**
         * The name of the target
         * <P>Type: TEXT</P>
         */
        public static final String NAME = "name";

        /**
         * The use state of the target
         * <P>Type: TEXT</P>
         */
        public static final String ISUSED = "isused";
    }

}
