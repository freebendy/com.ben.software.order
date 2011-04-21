package com.ben.software;

public final class OrderMenu {

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

    // This class cannot be instantiated
    private OrderMenu() {}
}
