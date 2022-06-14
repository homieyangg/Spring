package tw.leonchen.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//相當於web.xml的java程式組態
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override //用來註冊相當於beans.config.xml java程式組態的類別
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override //用來註冊相當於mvc-servlet.xml java程式組態的類別
	protected Class<?>[] getServletConfigClasses() {
		//return new Class[] {WebAppConfig.class};
		return null;
	}

	@Override //用來設定servlet mapping url
	protected String[] getServletMappings() {
		//return new String[] {"/"};
		return null;
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
//		CharacterEncodingFilter cef = new CharacterEncodingFilter("UTF-8",true);
		return new Filter[] {cef};
	}
	
}
