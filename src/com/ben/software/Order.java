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
         * <P>Type: REAL</P>
         */
        public static final String PRICE = "price";

        /**
         * The discount of the cuisine
         * <P>Type: REAL</P>
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
    public static final class TargetTypeColumns implements BaseColumns {
        // This class cannot be instantiated
        private TargetTypeColumns() {}

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/targettype");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "_id DESC";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of target type.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.roadmap.targettype";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single target type.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.roadmap.targettype";

        /**
         * The name of the target type
         * <P>Type: TEXT</P>
         */
        public static final String NAME = "name";
    }

    /**
     * Target table
     */
    public static final class TargetStateColumns implements BaseColumns {
        // This class cannot be instantiated
        private TargetStateColumns() {}

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/targetstate");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "_id DESC";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of target state.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.roadmap.targetstate";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single target state.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.roadmap.targetstate";

        /**
         * The name of the target state
         * <P>Type: TEXT</P>
         */
        public static final String NAME = "name";
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
         * The full name of the target(including target type)
         * <P>Type: TEXT</P>
         */
        public static final String FULL_NAME = "full_name";

        /**
         * The use state's id of the target
         * <P>Type: INTEGER</P>
         */
        public static final String STATE_ID = "state_id";

        /**
         * The name of use state
         * <P>Type: TEXT</P>
         */
        public static final String STATE_NAME = "state_name";

        /**
         * The type's id of the target
         * <P>Type: INTEGER</P>
         */
        public static final String TYPE_ID = "type_id";

        /**
         * The name of the type
         * <P>Type: TEXT</P>
         */
        public static final String TYPE_NAME = "type_name";

        /**
         * The customer count of the target
         * <P>Type: INTEGER</P>
         */
        public static final String CUSTOMER_COUNT = "customer_count";
    }

    /**
     * Order table
     */
    public static final class OrderColumns implements BaseColumns {
        // This class cannot be instantiated
        private OrderColumns() {}

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/orders");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "_id DESC";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of order.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.roadmap.order";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single order.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.roadmap.order";

        /**
         * The target id of the order
         * <P>Type: INTEGER</P>
         */
        public static final String TARGET_ID = "target_id";

        /**
         * The target name of the order
         * <P>Type: TEXT</P>
         */
        public static final String TARGET_NAME = "target_name";

        /**
         * The name of target's use state
         * <P>Type: TEXT</P>
         */
        public static final String TARGET_STATE_NAME = "target_state_name";

        /**
         * The name of the target's type
         * <P>Type: TEXT</P>
         */
        public static final String TARGET_TYPE_NAME = "target_type_name";

        /**
         * The cuisine id of the order
         * <P>Type: INTEGER</P>
         */
        public static final String CUISINE_ID = "cuisine_id";

        /**
         * The name of the cuisine
         * <P>Type: INTEGER</P>
         */
        public static final String CUISINE_NAME = "cuisine_name";

        /**
         * The count of the cuisine in the order
         * <P>Type: INTEGER</P>
         */
        public static final String COUNT = "count";

        /**
         * The remark of the order
         * <P>Type: TEXT</P>
         */
        public static final String REMARK = "remark";
    }

}
