package org.nthdimenzion.ddd.infrastructure;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 29/4/13
 * Time: 11:22 AM
 */
public final class LoggedInUserHolder {

    private LoggedInUserHolder() {
    }

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setUserName(String username) {
        contextHolder.set(username);
    }

    public static String getUserName() {
        return contextHolder.get();
    }

    public static void clearLoggedInUser() {
        contextHolder.remove();
    }
}
