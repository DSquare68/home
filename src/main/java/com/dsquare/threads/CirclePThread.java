package com.dsquare.threads;

import java.util.Calendar;
import java.util.Date;

import com.dsquare.view.CircleProgress;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.VaadinSession;

public class CirclePThread extends Thread {
		private Calendar cal = Calendar.getInstance();
        private final UI ui;
        private final CircleProgress timeBar;

        public CirclePThread(UI ui, CircleProgress timeBar) {
            this.ui = ui;
            this.timeBar = timeBar;
        }
        @Override
        public void run() {
        	while(true) {
			    cal.setTime(new Date());
				int h = cal.get(Calendar.HOUR_OF_DAY);
				int m = cal.get(Calendar.MINUTE);
				int s = cal.get(Calendar.SECOND);
				if(!ui.isAttached()) {
					this.interrupt();
					return;
				}
				ui.access(()->{
				//vs.lock();
					timeBar.setPercent((h * 3600.0 + m * 60.0 + s) / (24 * 60 * 60.0));
					timeBar.setCaption(h + ":" + m + ":" + s);
				//vs.unlock();
					//ui.push();
				});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
}
