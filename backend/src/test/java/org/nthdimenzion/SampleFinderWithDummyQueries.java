package org.nthdimenzion;

import org.nthdimenzion.ddd.domain.annotations.Finder;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 30/7/13
 * Time: 12:42 PM
 */
@Finder
class SampleFinderWithDummyQueries {

    public static String happy = "Super happyKid";

    public static String niceQuery = "select * from student where id = :id";
}
