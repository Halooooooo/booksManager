package Listener;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Bean.DB.DB;
import Bean.entity.Users;

public class mysessionListener implements HttpSessionListener,HttpSessionAttributeListener {
	long createTime;
	long destroyTime;
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if("loginInfo".equals(se.getName())){
			HttpSession session = se.getSession();
			List<String> list  = (List<String>) session.getAttribute("loginInfo");
			Users user = DB.getUserByName(list.get(0));
			if(user!=null){
				createTime=Calendar.getInstance().getTimeInMillis();
				DB.timeStamp(user.getU_id(),createTime);
			}
		}
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if("loginInfo".equals(se.getName())){
			destroyTime=Calendar.getInstance().getTimeInMillis();
			List<String> list  = (List<String>) se.getValue();
			Users user = DB.getUserByName(list.get(0));
			if(user!=null){
				createTime=DB.delStamp(user.getU_id());
				DB.setUserTime(user.getU_id(),(destroyTime-createTime)/60000);
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}
