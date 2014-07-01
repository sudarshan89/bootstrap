package org.nthdimenzion.presentation;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 6/8/13
 * Time: 1:04 PM
 */
public enum HomePageViews {

    ADMIN_HOME_PAGE("/admin.html#homepage");

    public final String homepage;

    HomePageViews(String homepage) {
        this.homepage = homepage;
    }
}
