package org.nthdimenzion.presentation.infrastructure;

import org.nthdimenzion.presentation.HomePageViews;

import java.io.IOException;
import java.util.Properties;

/**
 * @TODO Use this
 */
public class HomePageUrlLocator {

    private Properties properties = new Properties();

    public void setUrlHolder(String fileName) {
	ClassLoader bundleClassLoader = Thread.currentThread().getContextClassLoader();
	try {
		properties.load(bundleClassLoader.getResourceAsStream(fileName));
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
	}

	public void setUrlHolders(String[] fileNames) {
	for(String basename : fileNames)
		setUrlHolder(basename);
    }

    public String getUrlForHomePageViewId(HomePageViews homePageView){
        return properties.getProperty(homePageView.toString());
    }



}
